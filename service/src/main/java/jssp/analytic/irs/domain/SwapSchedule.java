package jssp.analytic.irs.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jssp.analytic.common.domain.Tenor;
import jssp.analytic.utils.DateUtils;
import org.quantlib.BusinessDayConvention;
import org.quantlib.Calendar;
import org.quantlib.Date;
import org.quantlib.Frequency;
import org.quantlib.Period;
import org.quantlib.Schedule;
import org.quantlib.TimeUnit;

import java.util.List;
import java.util.Map;

import static org.quantlib.BusinessDayConvention.ModifiedFollowing;
import static org.quantlib.DateGeneration.Rule.Forward;

public class SwapSchedule {

    public final Date effectiveDate;
    public final Date maturityDate;
    public final Frequency settlementFrequency;
    public final Frequency resetFrequency;
    public final Date firstSettlementDate;
    public final int fixingDays;
    public final Calendar calendar;

    public final Schedule underlyingSchedule;

    private List<SimplePeriod> settlementPeriods;
    private Map<SimplePeriod, List<SimplePeriod>> accrualPeriods = Maps.newHashMap();

    public SwapSchedule(Date effectiveDate, Date maturityDate, Frequency settlementFrequency, Frequency resetFrequency, Date firstSettlementDate, int fixingDays, Calendar calendar) {
        this.effectiveDate = effectiveDate;
        this.maturityDate = maturityDate;
        this.settlementFrequency = settlementFrequency;
        this.resetFrequency = resetFrequency;
        this.firstSettlementDate = firstSettlementDate;
        this.fixingDays = fixingDays;
        this.calendar = calendar;

        underlyingSchedule = new Schedule(
                effectiveDate,
                maturityDate,
                new Period(settlementFrequency),
                calendar,
                ModifiedFollowing,
                ModifiedFollowing,
                Forward,
                false,
                firstSettlementDate);
    }

    public SwapSchedule withTenor(Tenor newTenor) {
        Date newMaturityDate = calendar.advance(effectiveDate, newTenor.qlPeriod(), ModifiedFollowing, false);
        return withDate(effectiveDate, newMaturityDate);
    }

    public SwapSchedule withDate(Date newEffectiveDate, Date newMaturityDate) {
        return new SwapSchedule(newEffectiveDate, newMaturityDate, settlementFrequency, resetFrequency, new Date(), fixingDays, calendar);
    }

    public List<SimplePeriod> settlementPeriods() {
        if (settlementPeriods == null) {
            settlementPeriods = Lists.newArrayList();
            List<Date> settlementDates = settlementDates();
            for (int i = 0; i < settlementDates.size() - 1; i++) {
                settlementPeriods.add(new SimplePeriod(settlementDates.get(i), settlementDates.get(i + 1)));
            }
        }
        return settlementPeriods;
    }

    public List<SimplePeriod> fixingPeriods(SimplePeriod settlementPeriod) {
        List<SimplePeriod> periods = accrualPeriods.get(settlementPeriod);
        if (periods == null) {
            periods = accrualPeriodsOf(settlementPeriod);
            accrualPeriods.put(settlementPeriod, periods);
        }
        return periods;
    }

    public List<SimplePeriod> accrualPeriodsOf(SimplePeriod period) {
        List<SimplePeriod> result = Lists.newArrayList();
        if (settlementFrequency == resetFrequency) {
            result.add(new SimplePeriod(period.begin, period.end));
        } else {
            Date cursor = period.begin;
            while (DateUtils.isBefore(cursor, period.end)) {
                Date cursorNext = cursor.add(new Period(resetFrequency));
                result.add(new SimplePeriod(cursor, DateUtils.min(cursorNext, period.end)));
                cursor = cursorNext;
            }
        }
        return result;
    }

    private List<Date> settlementDates() {
        if (Frequency.Once.toString().equals(settlementFrequency.toString())) {
            return Lists.newArrayList(effectiveDate, maturityDate);
        }
        List<Date> result = Lists.newArrayList();
        for (int i = 0; i < underlyingSchedule.size(); i++) {
            result.add(underlyingSchedule.date(i));
        }
        return result;
    }

    public Date fixingDateOf(Date valueDate) {
        return calendar.advance(valueDate, -1 * fixingDays, TimeUnit.Days, BusinessDayConvention.Preceding, false);
    }
}
