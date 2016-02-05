package jssp.analytic.fxoption;

public class RiskReversal extends CompositeTrade {
    private final double amount;

    public RiskReversal(SingleOptionLeg callLeg, SingleOptionLeg putLeg) {
        super(new OptionTrade[]{callLeg, putLeg});
        this.amount = callLeg.amount();
    }

    @Override
    public double amount() {
        return amount;
    }
}
