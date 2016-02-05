package jssp.analytic.bondfuture.analytic;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BondFutureResult {
    public final double dirtyPrice;
    public final double futurePrice;
    public final double impliedRepoRate;
    public final double convertFactor;
    public final double basis;
    public final double invoicePrice;
    public final double couponBetween;
    public final double interestReturn;
    public final double timeWeightedCouponBetween;
    public final double futureSpotSpread;

    public BondFutureResult(double dirtyPrice, double futurePrice, double impliedRepoRate, double convertFactor, double basis, double invoicePrice, double couponBetween, double interestReturn, double timeWeightedCouponBetween, double futureSpotSpread) {
        this.dirtyPrice = dirtyPrice;
        this.futurePrice = futurePrice;
        this.impliedRepoRate = impliedRepoRate;
        this.convertFactor = convertFactor;
        this.basis = basis;
        this.invoicePrice = invoicePrice;
        this.couponBetween = couponBetween;
        this.interestReturn = interestReturn;
        this.timeWeightedCouponBetween = timeWeightedCouponBetween;
        this.futureSpotSpread = futureSpotSpread;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("dirtyPrice", dirtyPrice)
                .append("futurePrice", futurePrice)
                .append("impliedRepoRate", impliedRepoRate)
                .append("convertFactor", convertFactor)
                .append("basis", basis)
                .append("invoicePrice", invoicePrice)
                .append("couponBetween", couponBetween)
                .append("interestReturn", interestReturn)
                .append("timeWeightedCouponBetween", timeWeightedCouponBetween)
                .append("futureSpotSpread", futureSpotSpread)
                .toString();
    }
}
