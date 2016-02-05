package jssp.analytic.fxoption;

public class Butterfly extends CompositeTrade {

    private final double amount;

    public Butterfly(SingleOptionLeg deltaCall, SingleOptionLeg deltaPut, SingleOptionLeg atmCall, SingleOptionLeg atmPut) {
        super(new OptionTrade[]{deltaCall, deltaPut, atmCall, atmPut});
        this.amount = deltaCall.amount();
    }

    @Override
    public double amount() {
        return amount;
    }
}
