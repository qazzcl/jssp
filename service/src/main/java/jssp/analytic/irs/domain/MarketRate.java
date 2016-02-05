package jssp.analytic.irs.domain;

import jssp.analytic.common.domain.Tenor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.quantlib.BusinessDayConvention;
import org.quantlib.DayCounter;
import org.quantlib.Period;

public class MarketRate {
    public double rate;
    public final Tenor tenor;
    public Period period;
    public int fixingDays;
    public DayCounter dayCounter;
    public BusinessDayConvention dayConvention;

    public MarketRate(double rate, Tenor tenor) {
        this(rate, tenor, 0, null, null);
    }

    public MarketRate(double rate, Tenor tenor, int fixingDays, DayCounter dayCounter, BusinessDayConvention dayConvention) {
        this.rate = rate;
        this.tenor = tenor;
        this.period = tenor.qlPeriod();
        this.dayCounter = dayCounter;
        this.dayConvention = dayConvention;
        this.fixingDays = fixingDays;
    }

    public MarketRate withBump(double bump) {
        return new MarketRate(rate + bump, tenor, fixingDays, dayCounter, dayConvention);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("rate", rate)
                .append("fixingDays", fixingDays)
                .append("tenor", tenor)
                .append("period", period)
                .append("dayCounter", dayCounter)
                .append("dayConvention", dayConvention)
                .toString();
    }
}
