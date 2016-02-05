package jssp.analytic.irs.domain;

import jssp.analytic.utils.DateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.quantlib.Date;

public class SimplePeriod {
    public final Date begin;
    public final Date end;


    public SimplePeriod(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public boolean contains(Date date) {
//        return begin.compareTo(date) <= 0 && date.compareTo(end) < 0;
        return DateUtils.betweenIncludingStart(date, begin, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        SimplePeriod that = (SimplePeriod) o;
        return begin.equals(that.begin) && end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return begin.hashCode() + 31 * end.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("begin", begin)
                .append("end", end)
                .toString();
    }
}
