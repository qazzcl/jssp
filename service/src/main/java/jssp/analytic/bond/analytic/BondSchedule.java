package jssp.analytic.bond.analytic;

import jssp.analytic.bond.domain.BondInfo;
import jssp.analytic.utils.DateUtils;
import org.joda.time.LocalDate;
import org.quantlib.*;

import static org.quantlib.DateGeneration.Rule.Backward;

public class BondSchedule {

    private final BondInfo bondInfo;
    private final Calendar qlCalendar;

    public BondSchedule(BondInfo bondInfo, Calendar calendar) {
        this.bondInfo = bondInfo;
        this.qlCalendar = calendar;
    }

    public Schedule schedule() {
        return new Schedule(
                bondInfo.bondBasic.qlAccrualStartDate(),
                bondInfo.bondBasic.qlMaturityDate(),
                new Period(bondInfo.couponInfo.couponFrequency.frequency),
                qlCalendar,
                BusinessDayConvention.Unadjusted,
                BusinessDayConvention.Unadjusted,
                Backward,
                false);
    }

    public boolean onlyOneScheduleLeft(Date settlementDate, Date maturityDate) {
//        return DateUtils.equals(schedule().nextDate(settlementDate), maturityDate);//TODO
        return true;
    }

    public LocalDate lastCouponDate(LocalDate settlementDate) {
        LocalDate accrualStartDate = bondInfo.bondBasic.accrualStartDate;
        if(settlementDate.isBefore(accrualStartDate)){
            return accrualStartDate;
        }
        Schedule schedule = schedule();
        Date qlSettlementDate = DateUtils.toQlDate(settlementDate);

        for (int i = (int) (schedule.size() - 1); i >= 0; i--) {
            if (DateUtils.beforeOrEqual(schedule.date(i), qlSettlementDate)) {
                return DateUtils.toJodaDate(schedule.date(i));
            }
        }
        throw new IllegalStateException("should not hit here");
    }
}
