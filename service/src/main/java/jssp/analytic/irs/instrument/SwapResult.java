package jssp.analytic.irs.instrument;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static jssp.analytic.utils.ListUtils.join;

public class SwapResult {
    public final double floatingNpv;
    public final double fixedNpv;

    public final double fixedDv01;
    public final double floatingDv01;

    public final double fixedCouponNpv;
    public final double floatingCouponNpv;

    public final double netNpv;
    public final double netDv01;

    public final double pv01;

    public final double fixedBondDuration;

    public final double fixedAccrued;
    public final double floatingAccrued;

    public final List<SwapResult> childResults;

    public SwapResult(double floatingNpv, double floatingDv01, double fixedNpv, double fixedDv01, double fixedCouponNpv, double floatingCouponNpv, double netNpv, double netDv01, double pv01, double fixedBondDuration, double fixedAccrued, double floatingAccrued, List<SwapResult> childResults) {
        this.floatingNpv = floatingNpv;
        this.floatingDv01 = floatingDv01;
        this.fixedNpv = fixedNpv;
        this.fixedDv01 = fixedDv01;
        this.fixedCouponNpv = fixedCouponNpv;
        this.floatingCouponNpv = floatingCouponNpv;
        this.netNpv = netNpv;
        this.netDv01 = netDv01;
        this.pv01 = pv01;
        this.fixedBondDuration = fixedBondDuration;
        this.fixedAccrued = fixedAccrued;
        this.floatingAccrued = floatingAccrued;
        this.childResults = childResults;
    }

    public SwapResult add(SwapResult other) {
        return new SwapResult(
                floatingNpv + other.floatingNpv,
                floatingDv01 + other.floatingDv01,
                fixedNpv + other.fixedNpv,
                fixedDv01 + other.fixedDv01,
                fixedCouponNpv + other.fixedCouponNpv,
                floatingCouponNpv + other.floatingCouponNpv,
                netNpv + other.netNpv,
                netDv01 + other.netDv01,
                pv01 + other.pv01,
                fixedBondDuration + other.fixedBondDuration,
                fixedAccrued + other.fixedAccrued,
                floatingAccrued + other.floatingAccrued,
                join(join(childResults, other.childResults), newArrayList(this, other))
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("floatingNpv", floatingNpv)
                .append("fixedNpv", fixedNpv)
                .append("fixedDv01", fixedDv01)
                .append("floatingDv01", floatingDv01)
                .append("fixedCouponNpv", fixedCouponNpv)
                .append("floatingCouponNpv", floatingCouponNpv)
                .append("netNpv", netNpv)
                .append("netDv01", netDv01)
                .append("pv01", pv01)
                .append("fixedAccrued", fixedAccrued)
                .append("floatingAccrued", floatingAccrued)
                .append("childResults", childResults)
                .toString();
    }
}
