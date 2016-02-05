package jssp.analytic.fxoption;

public class Spread extends CompositeTrade {
    private final double amount;

    public Spread(SingleOptionLeg leg1, SingleOptionLeg leg2) {
        super(new OptionTrade[]{leg1, leg2});
        this.amount = leg1.amount();
    }

    @Override
    public double amount() {
        return amount;
    }
}
