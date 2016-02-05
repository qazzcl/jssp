package jssp.analytic.bondfuture;

@SuppressWarnings("unused")
public enum BondFutureCalcTarget {
    IRR("IRR"), Ytm("债券价格"), FuturePrice("期货价格");

    public final String displayName;

    BondFutureCalcTarget(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
