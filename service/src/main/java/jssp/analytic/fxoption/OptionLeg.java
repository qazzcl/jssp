package jssp.analytic.fxoption;

public class OptionLeg {

    private RawFxOption instrument;
    private double amount;

    public OptionLeg(RawFxOption instrument, double amount) {
        this.instrument = instrument;
        this.amount = amount;
    }

    public RawFxOption getInstrument() {
        return instrument;
    }

    public void setInstrument(RawFxOption instrument) {
        this.instrument = instrument;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
