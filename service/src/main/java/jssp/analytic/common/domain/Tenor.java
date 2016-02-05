package jssp.analytic.common.domain;

import jssp.analytic.exception.AnalyticException;
import jssp.analytic.utils.DateUtils;
import org.quantlib.BusinessDayConvention;
import org.quantlib.Calendar;
import org.quantlib.Date;
import org.quantlib.Period;
import org.quantlib.TimeUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tenor implements Comparable<Tenor> {

    private static final Pattern tenorPattern = Pattern.compile("(\\d+)(d|w|m|y)");

    public final int periodNum;
    public final TermUnit term;
    public final String name;

    public Tenor(String name) {
        this.name = name;
        String nameInLowCase = name.toLowerCase();
        Matcher matcher = tenorPattern.matcher(nameInLowCase);

        if (!matcher.matches()) {
            throw new AnalyticException("Wrong tenor: " + name);
        }
        periodNum = Integer.parseInt(matcher.group(1));
        term = TermUnit.termOf(matcher.group(2));
    }

    public Period qlPeriod() {
        switch (term) {
            case Day:
                return new Period(periodNum, TimeUnit.Days);
            case Week:
                return new Period(periodNum, TimeUnit.Weeks);
            case Month:
                return new Period(periodNum, TimeUnit.Months);
            case Year:
                return new Period(periodNum, TimeUnit.Years);
            default:
                throw new IllegalStateException("should not reach here");
        }
    }

    public Date advance(Date start, Calendar calendar, BusinessDayConvention businessDayConvention) {
        Period period = qlPeriod();
        if (term.equals(TermUnit.Day)) {
            return calendar.adjust(start.add(period), businessDayConvention);
        }
        return calendar.advance(start, period, businessDayConvention);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tenor)) return false;

        Tenor tenor = (Tenor) o;
        return name.equals(tenor.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @SuppressWarnings("unused")
    public double monthCount() {
        switch (term) {
            case Day:
                return periodNum / 30.0;
            case Week:
                return periodNum / 4.0;
            case Month:
                return periodNum;
            case Year:
                return periodNum * 12;
            default:
                throw new IllegalStateException("should not reach here");
        }
    }

    @Override
    public int compareTo(Tenor other) {
        Date today = Date.todaysDate();
        return DateUtils.compare(today.add(qlPeriod()), today.add(other.qlPeriod()));

    }
}
