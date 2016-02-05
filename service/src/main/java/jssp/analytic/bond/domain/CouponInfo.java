package jssp.analytic.bond.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CouponInfo {
    public final CouponType type;
    public final CouponFrequency couponFrequency;
    public final String frnIndexId;
    public final double rateOrSpread;
    public final double currentRate;

    //floating
    public final int precedingDays;
    public final int movingAverageDays;
    public final int fixingDigits;
    public final double floor;
    public final double cap;

    public CouponInfo (CouponType couponType, CouponFrequency couponFrequency, double couponRate){
         this(couponType, couponFrequency, null, couponRate, 0, 0, 0);
    }

    public CouponInfo(CouponType type, CouponFrequency couponFrequency, String frnIndexId, double rateOrSpread, int precedingDays, int movingAverageDays, int fixingDigits){
        this(type, couponFrequency, frnIndexId, rateOrSpread, 0, precedingDays, movingAverageDays, fixingDigits, -100, 100);
    }

    public CouponInfo(CouponType type, CouponFrequency couponFrequency, String frnIndexId, double rateOrSpread, double currentRate, int precedingDays, int movingAverageDays, int fixingDigits, double floor, double cap) {
        this.type = type;
        this.couponFrequency = couponFrequency;
        this.frnIndexId = frnIndexId;
        this.rateOrSpread = rateOrSpread;
        this.currentRate = currentRate;
        this.precedingDays = precedingDays;
        this.movingAverageDays = movingAverageDays;
        this.fixingDigits = fixingDigits;
        this.floor = floor;
        this.cap = cap;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("type", type)
                .append("couponFrequency", couponFrequency)
                .append("frnIndexId", frnIndexId)
                .append("rateOrSpread", rateOrSpread)
                .append("currentRate", currentRate)
                .append("precedingDays", precedingDays)
                .append("fixingDays", movingAverageDays)
                .append("fixingDigits", fixingDigits)
                .append("floor", floor)
                .append("cap", cap)
                .toString();
    }

}
