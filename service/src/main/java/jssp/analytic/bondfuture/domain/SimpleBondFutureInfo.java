package jssp.analytic.bondfuture.domain;

import org.joda.time.LocalDate;

public class SimpleBondFutureInfo {
    public final String tfId;
    public final String tfKey;
    public final LocalDate deliveryDate;

    public SimpleBondFutureInfo(String tfId, String tfKey, LocalDate deliveryDate) {
        this.tfId = tfId;
        this.tfKey = tfKey;
        this.deliveryDate = deliveryDate;
    }
}
