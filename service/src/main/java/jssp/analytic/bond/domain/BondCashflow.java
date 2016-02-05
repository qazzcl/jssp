package jssp.analytic.bond.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.quantlib.Date;

public class BondCashflow {

    public final Date date;
    public final Date adjustedDate;
    public final double coupon;
    public final double redemption;
    public final double couponRate;
    public final double startPrincipal;

    public BondCashflow(Date date, Date adjustedDate, double coupon, double redemption, double couponRate, double startPrincipal) {
        this.date = date;
        this.adjustedDate = adjustedDate;
        this.coupon = coupon;
        this.redemption = redemption;
        this.couponRate = couponRate;
        this.startPrincipal = startPrincipal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("date", date)
                .append("adjustedDate", adjustedDate)
                .append("coupon", coupon)
                .append("redemption", redemption)
                .append("couponRate", couponRate)
                .append("startPrincipal", startPrincipal)
                .toString();
    }
}
