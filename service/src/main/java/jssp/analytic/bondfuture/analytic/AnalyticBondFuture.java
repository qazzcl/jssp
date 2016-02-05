package jssp.analytic.bondfuture.analytic;

import jssp.analytic.bond.analytic.AnalyticBond;
import jssp.analytic.bond.domain.BondCashflow;
import jssp.analytic.utils.DateUtils;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.quantlib.Actual365Fixed;
import org.quantlib.Date;

import java.util.List;

public class AnalyticBondFuture {

    private static final Actual365Fixed dayCounter = new Actual365Fixed();
    private static final int maxEval = 1000;

    public final AnalyticBond bond;
    public final Date tradeDate;
    public final Date settlementDate;

    private final List<BondCashflow> cashflow;

    public AnalyticBondFuture(AnalyticBond bond, Date tradeDate, Date settlementDate) {
        this.bond = bond;
        this.tradeDate = tradeDate;
        this.settlementDate = settlementDate;
        this.cashflow = bond.cashflow();
    }

    public BondFutureResult fromDirtyPriceAndIrr(final double dirtyPrice, final double convertFactor, final double impliedRepoRate) {
        double futurePrice = solver().solve(maxEval, futurePrice2Solve -> bondFutureEquation(futurePrice2Solve, dirtyPrice, impliedRepoRate, convertFactor), 0, 50000);
        return bondFutureResultOf(dirtyPrice, futurePrice, convertFactor, impliedRepoRate);
    }

    public BondFutureResult fromFuturePriceAndIrr(final double futurePrice, final double convertFactor, final double impliedRepoRate) {
        double dirtyPrice = solver().solve(maxEval, dirtyPrice2Solve -> bondFutureEquation(futurePrice, dirtyPrice2Solve, impliedRepoRate, convertFactor), 0, 50000);
        return bondFutureResultOf(dirtyPrice, futurePrice, convertFactor, impliedRepoRate);
    }

    public BondFutureResult fromFuturePriceAndDirtyPrice(final double futurePrice, final double convertFactor, final double dirtyPrice) {
        double impliedRepoRate = solver().solve(maxEval, impliedRepoRate2Solve -> bondFutureEquation(futurePrice, dirtyPrice, impliedRepoRate2Solve, convertFactor), -500, 500);
        return bondFutureResultOf(dirtyPrice, futurePrice, convertFactor, impliedRepoRate);
    }

    private double bondFutureEquation(double futurePrice, double dirtyPrice, double impliedRepoRate, double convertFactor) {
        double dirtyPriceTerm = dirtyPrice * (1 + impliedRepoRate * dayCounter.yearFraction(tradeDate, settlementDate));
        double cashflowTerm = cashflowTerm(impliedRepoRate);
        return (dirtyPriceTerm - cashflowTerm - accruedOfSettlementDate()) / convertFactor - futurePrice;
    }

    private double cashflowTerm(double impliedRepoRate) {
        double cashflowTerm = 0;
        for (BondCashflow cf : cashflow) {
            if (includeCashflow(cf)) {
                cashflowTerm += (cf.coupon + cf.redemption) * (1 + impliedRepoRate * dayCounter.yearFraction(cf.date, settlementDate));
            }
        }
        return cashflowTerm;
    }

    private BondFutureResult bondFutureResultOf(double dirtyPrice, double futurePrice, double convertFactor, double impliedRepoRate) {
        double couponBetween = couponBetweenOf();
        double invoicePrice = invoicePriceOf(futurePrice, convertFactor);
        return new BondFutureResult(
                dirtyPrice,
                futurePrice,
                impliedRepoRate,
                convertFactor,
                basisOf(dirtyPrice, futurePrice, convertFactor),
                invoicePrice,
                couponBetween,
                interestReturnOf(couponBetween),
                timeWeightedCouponBetweenOf(),
                invoicePrice - dirtyPrice);
    }

    private double interestReturnOf(double couponBetween) {
        double startAccrued = bond.accruedAmount(tradeDate);
        double endAccrued = bond.accruedAmount(settlementDate);
        return endAccrued - startAccrued + couponBetween;
    }

    private double timeWeightedCouponBetweenOf() {
        double result = 0;
        for (BondCashflow cf : cashflow) {
            if (includeCashflow(cf)) {
                result += (cf.coupon + cf.redemption) * dayCounter.yearFraction(cf.date, settlementDate);
            }
        }
        return result;
    }

    private double couponBetweenOf() {
        double result = 0;
        for (BondCashflow cf : cashflow) {
            if (includeCashflow(cf)) {
                result += cf.coupon + cf.redemption;
            }
        }
        return result;
    }

    private boolean includeCashflow(BondCashflow cf) {
//        return tradeDate.compareTo(cf.date) < 0 && cf.date.compareTo(settlementDate) <= 0;
        return DateUtils.betweenIncludingEnd(cf.date, tradeDate, settlementDate);
    }

    private double basisOf(double dirtyPrice, double futurePrice, double convertFactor) {
        return cleanPriceOf(dirtyPrice) - futurePrice * convertFactor;
    }

    private double invoicePriceOf(double futurePrice, double convertFactor) {
        return futurePrice * convertFactor + bond.accruedAmount(settlementDate);
    }

    private double cleanPriceOf(double dirtyPrice) {
        return dirtyPrice - bond.accruedAmount(tradeDate);
    }

    private UnivariateSolver solver() {
        return new BrentSolver(1E-8);
    }

    private double accruedOfSettlementDate() {
        return bond.accruedAmount(settlementDate);
    }

}
