package jssp.analytic.funding;

import jssp.analytic.index.IndexRate;
import jssp.analytic.refdata.IndexRateSource;
import jssp.analytic.utils.DateUtils;
import org.joda.time.LocalDate;
import org.quantlib.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

import static jssp.analytic.utils.DateUtils.toJodaDate;
import static jssp.analytic.utils.DateUtils.toQlDate;
import static jssp.analytic.utils.ListUtils.end;
import static org.joda.time.Days.daysBetween;

public class FundingCostCalculator {

    private final IndexRateSource indexRateSource;
    private final double notional;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String index;
    private final double rateDelta;
    private final Period frequency;
    private final Calendar calendar;
    private final DayCounter dayCounter;
    private final int fixingDays;
    private final Compounding rollCompounding;

    public FundingCostCalculator(IndexRateSource indexRateSource, double notional, LocalDate startDate, LocalDate endDate, String index, double rateDelta, Period frequency, Calendar calendar, DayCounter dayCounter, int fixingDays, Compounding rollCompounding) {
        this.indexRateSource = indexRateSource;
        this.notional = notional;
        this.startDate = startDate;
        this.endDate = endDate;
        this.index = index;
        this.rateDelta = rateDelta;
        this.frequency = frequency;
        this.calendar = calendar;
        this.dayCounter = dayCounter;
        this.fixingDays = fixingDays;
        this.rollCompounding = rollCompounding;
    }

    public List<FundingResult> calc() {
        List<FundingResult> result = new ArrayList<>();


        Date qlStartDate = toQlDate(startDate);
        Date qlEndDate = toQlDate(endDate);

        Date periodStart = qlStartDate;
        while (DateUtils.isBefore(periodStart, qlEndDate)) {
            Date targetPeriodEnd = periodStart.add(frequency);
            Date periodEnd = DateUtils.min(targetPeriodEnd, qlEndDate);

            double baseNotional = baseNotionalOf(result);

            FundingResult singleResult = resultOfPeriod(periodStart, periodEnd, baseNotional);
            result.add(singleResult);
            periodStart = targetPeriodEnd;
        }
        return result;
    }

    private double baseNotionalOf(List<FundingResult> result) {
        if (rollCompounding == Compounding.Simple) {
            return notional;
        } else if (rollCompounding == Compounding.Compounded) {
            return lastNotional(result);
        }
        throw new IllegalStateException("should not hit here");
    }

    private double lastNotional(List<FundingResult> result) {
        if (result.isEmpty()) {
            return notional;
        }
        return end(result).totalAmount;
    }

    private FundingResult resultOfPeriod(Date start, Date end, double baseNotional) {
        LocalDate jodaStartDate = toJodaDate(start);
        LocalDate jodaEndDate = toJodaDate(end);

        LocalDate resetDate = toJodaDate(calendar.advance(start, fixingDays * -1, TimeUnit.Days, BusinessDayConvention.Preceding));
        double indexRate = indexRateOf(index, resetDate);
        double effectiveRate = indexRate + rateDelta;

        double time = dayCounter.yearFraction(start, end);
        double totalAmount = baseNotional * (1 + time * effectiveRate);
        double interest = totalAmount - baseNotional;

        return new FundingResult(
                jodaStartDate,
                jodaEndDate,
                daysBetween(jodaStartDate, jodaEndDate).getDays(),
                indexRate,
                effectiveRate,
                interest,
                totalAmount
        );
    }

    private double indexRateOf(String index, LocalDate date) {
        NavigableMap<LocalDate, IndexRate> navigableMap = indexRateSource.get(index);
        return navigableMap.floorEntry(date).getValue().value;
    }
}
