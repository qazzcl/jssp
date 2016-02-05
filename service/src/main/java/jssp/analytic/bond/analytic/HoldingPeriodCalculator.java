package jssp.analytic.bond.analytic;

import com.google.common.base.Preconditions;
import jssp.analytic.utils.DateUtils;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.quantlib.Date;
import org.quantlib.DayCounter;
import org.quantlib.Leg;

import static java.lang.Math.max;

public class HoldingPeriodCalculator {

    private double faceAmount = 100.0;

    public enum Direction {
        BuyThenSell(1), SellThenBuy(-1);

        public final int side;

        Direction(int side) {
            this.side = side;
        }

    }

    private static final int maxEval = 300;

    private final AnalyticBond bond;

    private final Date startDate;
    private final Date endDate;

    private final double notional;
    private final double interestTaxRate;
    private final double businessTaxRate;
    private final double holdingCost;
    private final Direction direction;
    private final double startFrontCommission;

    private final double startBackCommission;
    private final double endFrontCommission;
    private final double endBackCommission;
    private final DayCounter dayCounter;

    private final Leg redemptions;

    public HoldingPeriodCalculator(AnalyticBond bond, Date startDate, Date endDate, double notional, double interestTaxRate, double businessTaxRate, double holdingCost, Direction direction, double startFrontCommission, double startBackCommission, double endFrontCommission, double endBackCommission, DayCounter dayCounter) {
        this.bond = bond;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notional = notional;
        this.interestTaxRate = interestTaxRate;
        this.businessTaxRate = businessTaxRate;
        this.holdingCost = holdingCost;
        this.direction = direction;
        this.startFrontCommission = startFrontCommission;
        this.startBackCommission = startBackCommission;
        this.endFrontCommission = endFrontCommission;
        this.endBackCommission = endBackCommission;
        this.dayCounter = dayCounter;

        this.redemptions = bond.underlyingBond.redemptions();

        ensureNotNegative(startFrontCommission);
        ensureNotNegative(startBackCommission);
        ensureNotNegative(endFrontCommission);
        ensureNotNegative(endBackCommission);
        ensureNotNegative(holdingCost);
    }

    public HoldingPeriodResult fromStartEndPrices(final double startCleanPrice, final double endCleanPrice) {
        final HoldingPeriodResult result = new HoldingPeriodResult();
        solver().solve(maxEval, annualReturn2Solve -> yieldEquation(startCleanPrice, endCleanPrice, annualReturn2Solve, result, redemptions), -1000, 1000, 0.05);
        return result;
    }


    public HoldingPeriodResult fromStartPriceAndReturn(final double startCleanPrice, final double annualReturn) {
        final HoldingPeriodResult result = new HoldingPeriodResult();
        solver().solve(maxEval, endCleanPrice2Solve -> yieldEquation(startCleanPrice, endCleanPrice2Solve, annualReturn, result, redemptions), 0, 10000, 100);
        return result;
    }

    public HoldingPeriodResult fromEndPriceAndReturn(final double endCleanPrice, final double annualReturn) {
        final HoldingPeriodResult result = new HoldingPeriodResult();
        solver().solve(maxEval, startPrice2Solve -> yieldEquation(startPrice2Solve, endCleanPrice, annualReturn, result, redemptions), 0, 10000, 100);
        return result;
    }

    public HoldingPeriodResult fromStartPriceAndNetPnl(final double startCleanPrice, final double netPnl) {
        final HoldingPeriodResult result = new HoldingPeriodResult();
        solver().solve(maxEval, endPrice2Solve -> netPnlEquation(startCleanPrice, endPrice2Solve, netPnl, result, redemptions), 0, 10000, 100);
        return result;
    }

    public HoldingPeriodResult fromEndPriceAndNetPnl(final double endCleanPrice, final double netPnl) {
        final HoldingPeriodResult result = new HoldingPeriodResult();
        solver().solve(maxEval, startPrice2Solve -> netPnlEquation(startPrice2Solve, endCleanPrice, netPnl, result, redemptions), 0, 10000, 100);
        return result;
    }

    private double yieldEquation(double startCleanPrice, double endCleanPrice, double annualizedReturn, HoldingPeriodResult result, Leg redemptionLeg) {
        netPnl(startCleanPrice, endCleanPrice, result, redemptionLeg, yearFraction());
        result.netAnnualizedYield = netAnnualizedYield(result);
        return result.netAnnualizedYield - annualizedReturn;
    }

    private double netPnlEquation(double startCleanPrice, double endCleanPrice, double netPnl, HoldingPeriodResult result, Leg redemptionLeg) {
        double calculatedPnl = netPnl(startCleanPrice, endCleanPrice, result, redemptionLeg, yearFraction());
        result.netAnnualizedYield = netAnnualizedYield(result);
        return calculatedPnl - netPnl;
    }


    private double netAnnualizedYield(HoldingPeriodResult result) {
        int side = direction.side;
        return -side * result.netPnL / result.startTotal / yearFraction();
    }

    private double netPnl(double startCleanPrice, double endCleanPrice, HoldingPeriodResult result, Leg redemptionLeg, double yearFraction) {
        annualEarningBeforeCost(startCleanPrice, endCleanPrice, result, redemptionLeg, yearFraction);
        double allCommission = startCommission() + endCommission();
        result.pnLPretax = result.cleanAmountPnLPreTax + result.interestPnLPreTax - allCommission;
        result.holdingPeriodYieldPreTax = -(double) direction.side * result.pnLPretax / result.startTotal;

        result.businessTax = max(0, result.cleanAmountPnLPreTax * businessTaxRate);
        result.interestTax = max(0, result.interestPnLPreTax * interestTaxRate);
        result.totalTax = result.businessTax + result.interestTax;

        result.cleanAmountPnLAT = result.cleanAmountPnLPreTax - result.businessTax;
        result.interestPnLAT = result.interestPnLPreTax - result.interestTax;
        result.pnLAT = result.cleanAmountPnLAT + result.interestPnLAT - allCommission;
        result.holdingPeriodYieldAT = -(double) direction.side * result.pnLAT / result.startTotal;
        result.annualizedHoldingPeriodYieldAT = result.holdingPeriodYieldAT / yearFraction;
        result.netPnL = result.pnLAT - holdingCost;
        return result.netPnL;
    }

    private double annualEarningBeforeCost(double startCleanPrice, double endCleanPrice, HoldingPeriodResult result, Leg redemptionLeg, double yearFraction) {
        result.startCleanPrice = startCleanPrice;
        result.endCleanPrice = endCleanPrice;

        result.startCleanAmount = -(double) direction.side * notional / faceAmount * startCleanPrice;
        result.endCleanAmount = (double) direction.side * notional / faceAmount * endCleanPrice;
        result.startInterest = -(double) direction.side * notional / faceAmount * bond.accruedAmount(startDate);
        result.endInterest = (double) direction.side * notional / faceAmount * bond.accruedAmount(endDate);
        result.startTotal = result.startCleanAmount + result.startInterest - startCommission();
        result.endTotal = result.endCleanAmount + result.endInterest - endCommission();

        result.principalBetween = redemptionBetween(redemptionLeg, (double) direction.side, faceAmount);
        result.interestBetween = max(0, notional / faceAmount * bond.interestBetween(startDate, endDate));

        result.cleanAmountPnLPreTax = result.startCleanAmount + result.endCleanAmount + result.principalBetween;
        result.interestPnLPreTax = result.startInterest + result.endInterest + result.interestBetween;
        result.annualReturnPreCost = -(double) direction.side * (result.cleanAmountPnLPreTax + result.interestPnLPreTax) / result.startTotal / yearFraction;
        return result.annualReturnPreCost;
    }

    private double endCommission() {
        return endFrontCommission + endBackCommission;
    }

    private double startCommission() {
        return startFrontCommission + startBackCommission;
    }

    private double yearFraction() {
        if(startDate.equals(endDate)) {
            return dayCounter.yearFraction(startDate, endDate.add(1));
        }
        return dayCounter.yearFraction(startDate, endDate);
    }

    private double redemptionBetween(Leg redemptionLeg, double side, double faceAmount) {
        double principalBetween = 0;
        long redemptionSize = redemptionLeg.size();
        for (int i = 0; i < redemptionSize; i++) {
            Date date = redemptionLeg.get(i).date();
            if (DateUtils.betweenIncludingEnd(date, startDate, endDate) && DateUtils.isBefore(date, bond.underlyingBond.maturityDate()) ) {
                principalBetween += (side > 0 ? side : 0) * notional / faceAmount * redemptionLeg.get(i).amount();
            }
        }
        return principalBetween;
    }

    private BrentSolver solver() {
        return new BrentSolver(1E-9);
    }

    private void ensureNotNegative(double value) {
        Preconditions.checkArgument(value >= 0);
    }

}
