package jssp.analytic.fxoption;

public class Strangle extends CompositeTrade {

    private final double amount;

    public Strangle(SingleOptionLeg callLeg, SingleOptionLeg putLeg) {
        super(new OptionTrade[]{callLeg, putLeg});
        this.amount = callLeg.amount();
    }

    @Override
    public double amount() {
        return amount;
    }
}
