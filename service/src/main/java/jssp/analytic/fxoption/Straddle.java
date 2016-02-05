package jssp.analytic.fxoption;

import com.google.common.base.Preconditions;

public class Straddle extends CompositeTrade {

    private final double amount;

    public Straddle(SingleOptionLeg callLeg, SingleOptionLeg putLeg) {
        super(new OptionTrade[]{callLeg, putLeg});
        Preconditions.checkArgument(callLeg.amount() == putLeg.amount());
        amount = callLeg.amount();
    }


    @Override
    public double amount() {
        return amount;
    }
}