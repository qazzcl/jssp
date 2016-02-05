package jssp.analytic.bondfuture.domain;

import jssp.analytic.bond.domain.BondInfo;

public class DeliverableBondInfo {
    public final BondInfo bondInfo;
    public final double convertFactor;

    public DeliverableBondInfo(BondInfo bondInfo, double convertFactor) {
        this.bondInfo = bondInfo;
        this.convertFactor = convertFactor;
    }

}
