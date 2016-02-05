package jssp.analytic.bondfuture.domain;

import java.util.List;

public class BondFutureInfo {
    public final SimpleBondFutureInfo simpleBondFutureInfo;
    public final List<DeliverableBondInfo> deliverableBondList;

    public BondFutureInfo(SimpleBondFutureInfo simpleBondFutureInfo, List<DeliverableBondInfo> deliverableBondList) {
        this.simpleBondFutureInfo = simpleBondFutureInfo;
        this.deliverableBondList = deliverableBondList;
    }
}
