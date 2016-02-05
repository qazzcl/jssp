package jssp.analytic.bond.analytic;

import jssp.analytic.bond.domain.BondCashflow;
import org.quantlib.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.quantlib.BusinessDayConvention.Unadjusted;
import static org.quantlib.Frequency.NoFrequency;

public class AnalyticPamBond extends AnalyticBond {

    public final double redemption;
    public final double pamCouponRate;

    public final Calendar calendar;
    public final DayCounter yieldDayCounter;
    public final DayCounter accrualDayCounter;

    public AnalyticPamBond(Date accrualStartDate, Date maturityDate, double redemption, double pamCouponRate, Calendar calendar, DayCounter yieldDayCounter, Date settlementDate, int settlementDays, DayCounter accrualDayCounter) {
        super(accrualStartDate, maturityDate, settlementDate, calendar, settlementDays, false);
        this.redemption = redemption;
        this.pamCouponRate = pamCouponRate;
        this.calendar = calendar;
        this.yieldDayCounter = yieldDayCounter;
        this.accrualDayCounter = accrualDayCounter;
        underlyingBond = createBond();
    }

    private ZeroCouponBond createBond() {
        return new ZeroCouponBond(
                0,
                calendar,
                100.0,
                maturityDate,
                Unadjusted,
                redemption,
                accrualStartDate);
    }

    public BondResult fromYield(double yield) {
        if (overdue()) {
            return overdueResult();
        }
        double remainingTime = remainingTime();
        double fullPrice = redemption / (1 + yield * remainingTime);
        double accruedAmount = accruedAmount(settlementDate);
        double cleanPrice = fullPrice - accruedAmount;

        double modifiedDuration = remainingTime / (1 + yield * remainingTime);
        double convexity = CashFlows.convexity(underlyingBond.cashflows(), yield, yieldDayCounter, Compounding.Simple, NoFrequency, false, settlementDate, settlementDate);
        double pvbp = Math.abs(BondFunctions.basisPointValue(underlyingBond, yield, yieldDayCounter, Compounding.Simple, NoFrequency, settlementDate));
        int accruedDays = accruedDays();

        return new BondResult(settlementDate,
                cleanPrice,
                fullPrice,
                yield,
                accruedAmount,
                accruedDays,
                modifiedDuration,
                remainingTime, //macaulayDuration
                convexity,
                pvbp);
    }

    public BondResult fromFullPrice(double fullPrice) {
        double yield = (redemption - fullPrice) / fullPrice / remainingTime();
        return fromYield(yield);
    }

    private double remainingTime() {
        return yieldDayCounter.yearFraction(settlementDate, maturityDate, accrualStartDate, maturityDate);
    }

    @Override
    public double accruedAmount(Date date) {
        if (overdue() || date.equals(maturityDate)) {
            return 0;
        }
        Date effectiveDate = date.add(settlementDays);
        return 100.0 * pamCouponRate * accrualDayCounter.yearFraction(accrualStartDate, effectiveDate, accrualStartDate, maturityDate);

    }

    @Override
    public int accruedDays() {
        return Math.max(0, settlementDate.add(settlementDays).serialNumber() - accrualStartDate.serialNumber());
    }

    @Override
    public List<BondCashflow> cashflow() {
        return newArrayList(
                new BondCashflow(
                        maturityDate,
                        calendar.adjust(maturityDate),
                        redemption - 100.0,
                        100.0,
                        pamCouponRate,
                        100.0));
    }

    private BondResult overdueResult() {
        return new BondResult(
                settlementDate,
                100,
                100,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        );
    }
}
