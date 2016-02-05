package jssp.analytic.irs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.quantlib.Date;

public class SwapFixing {
    public final Date begin;
    public final Date end;
    public final Date fixingDate;
    public final double rate;
    public final int days;

    public SwapFixing(Date begin, Date end, Date fixingDate, double rate) {
        this.begin = begin;
        this.end = end;
        this.fixingDate = fixingDate;
        this.rate = rate;
        days = end.serialNumber() - begin.serialNumber();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("begin", begin)
                .append("end", end)
                .append("fixingDate", fixingDate)
                .append("rate", rate)
                .toString();
    }
}
