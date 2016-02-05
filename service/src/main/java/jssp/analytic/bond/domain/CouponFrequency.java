package jssp.analytic.bond.domain;

import org.quantlib.Frequency;

public enum CouponFrequency {

    Annual(Frequency.Annual, "年度"),
    SemiAnnual(Frequency.Semiannual, "半年度"),
    Quarterly(Frequency.Quarterly, "季度"),
    Monthly(Frequency.Monthly, "月度"),
    NoFrequency(Frequency.NoFrequency, "无");

    public final Frequency frequency;
    public final String displayName;

    CouponFrequency(Frequency frequency, String displayName) {
        this.frequency = frequency;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static CouponFrequency fromFrequency(Frequency frequency) {
        for (CouponFrequency couponFrequency : values()) {
            if (couponFrequency.frequency == frequency) {
                return couponFrequency;
            }
        }
        throw new IllegalArgumentException("unknown frequency :" + frequency);
    }
}
