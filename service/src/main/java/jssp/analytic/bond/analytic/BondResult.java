package jssp.analytic.bond.analytic;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.quantlib.Date;

public class BondResult {

    public final Date valueDate;

    public final double cleanPrice;
    public final double fullPrice;
    public final double yield;

    public final double accruedAmount;
    public final int accruedDays;

    public final double modifiedDuration;
    public final double macaulayDuration;
    public final double pvbp;
    public final double convexity;

    public BondResult(Date valueDate, double cleanPrice, double fullPrice, double yield, double accruedAmount, int accruedDays, double modifiedDuration, double macaulayDuration, double convexity, double pvbp) {
        this.valueDate = valueDate;
        this.cleanPrice = cleanPrice;
        this.fullPrice = fullPrice;
        this.accruedAmount = accruedAmount;
        this.yield = yield;
        this.modifiedDuration = modifiedDuration;
        this.macaulayDuration = macaulayDuration;
        this.convexity = convexity;
        this.pvbp = pvbp;
        this.accruedDays = accruedDays;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("valueDate", valueDate)
                .append("cleanPrice", cleanPrice)
                .append("fullPrice", fullPrice)
                .append("yield", yield)
                .append("accruedAmount", accruedAmount)
                .append("accruedDays", accruedDays)
                .append("modifiedDuration", modifiedDuration)
                .append("macaulayDuration", macaulayDuration)
                .append("pvbp", pvbp)
                .append("convexity", convexity)
                .toString();
    }
}
