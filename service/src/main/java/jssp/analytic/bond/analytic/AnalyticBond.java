package jssp.analytic.bond.analytic;

import jssp.analytic.bond.domain.BondCashflow;
import jssp.analytic.utils.DateUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.quantlib.Bond;
import org.quantlib.Calendar;
import org.quantlib.Compounding;
import org.quantlib.Date;
import org.quantlib.DayCounter;

import java.util.List;

public abstract class AnalyticBond {

    public Bond underlyingBond;
    public final Calendar calendar;
    public final Date accrualStartDate;
    public final Date maturityDate;
    public final Date settlementDate;
    public final int settlementDays;
    public final boolean includeSettlementCashflow;


    protected AnalyticBond(Date accrualStartDate, Date maturityDate, Date settlementDate, Calendar calendar, int settlementDays, boolean includeSettlementCashflow) {
        this.accrualStartDate = accrualStartDate;
        this.maturityDate = maturityDate;
        this.settlementDate = settlementDate;
        this.calendar = calendar;
        this.settlementDays = settlementDays;
        this.includeSettlementCashflow = includeSettlementCashflow;
    }

    public BondResult fromCleanPrice(double cleanPrice) {
        return fromFullPrice(cleanPrice + accruedAmount(settlementDate));
    }

    public abstract BondResult fromYield(double yield);

    public abstract double accruedAmount(Date date);

    public abstract BondResult fromFullPrice(double fullPrice);

    public double remainingPrincipal() {
        double result  = 0;
        List<BondCashflow> cashflow = cashflow();
        for (BondCashflow bondCashflow : cashflow) {
//            if(bondCashflow.date.isAfter(settlementDate)) {
            if(DateUtils.isAfter(bondCashflow.date, settlementDate)) {
                result += bondCashflow.redemption;
            }
        }
        return result;
    }

    public AnalyticBond withOptionExecution(Date executionDate, double strike, DayCounter newYieldDayCounter, Compounding newCompounding) {
        throw new NotImplementedException("withOptionExecution");
    }

    public abstract int accruedDays();

    public double interestBetween(Date startDate, Date endDate) {
        double result = 0;
        for (BondCashflow bondCashflow : cashflow()) {
            if (between(bondCashflow.date, startDate, endDate)) {
                result += bondCashflow.coupon;
            }
        }
        return result;
    }

    public double redemptionBetween(final Date startDate, Date endDate) {
        double result = 0;
        for (BondCashflow bondCashflow : cashflow()) {
            if (between(bondCashflow.date, startDate, endDate)) {
                result += bondCashflow.redemption;
            }
        }
        return result;
    }

    public double redemptionBetweenExcludeLastPrincipal(final Date startDate, Date endDate) {
        double result = 0;
        List<BondCashflow> allCashflow = cashflow();
        List<BondCashflow> excludeLast = allCashflow.subList(0, allCashflow.size()-1);
        for (BondCashflow bondCashflow : excludeLast) {
            if (between(bondCashflow.date, startDate, endDate)) {
                result += bondCashflow.redemption;
            }
        }
        return result;
    }

    public abstract List<BondCashflow> cashflow();

    private boolean between(Date date, Date startDate, Date endDate) {
//        return startDate.compareTo(date) < 0 && date.compareTo(endDate) <= 0;
        return DateUtils.betweenIncludingEnd(date, startDate, endDate);
    }

    protected boolean overdue() {
//        return maturityDate.compareTo(settlementDate) <=0;
        return DateUtils.beforeOrEqual(maturityDate, settlementDate);
    }

}
