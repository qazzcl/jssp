package jssp.analytic.fxoption;

/**
 * This class provide linear combination of all option structured product,
 * Spread would be either two call or two put.
 * butterfly would be 3 or four call/put combination.
 * the same for straddle or strangle.
 */
public class LinearStructure {

    public final double amount;
    public final OptionLeg[] legs;

    public LinearStructure(double amount, OptionLeg ... legs) {
        this.amount = amount;
        this.legs = legs;
    }

    public PriceResult price() {
        double premium = 0;
        double delta = 0;
        double fwdDelta = 0;
        double domesticDelta = 0;
        double theta = 0;
        double gamma = 0;
        double vega = 0;
        double rho = 0;

        for (OptionLeg leg : legs) {
            RawFxOption instrument = leg.getInstrument();
            premium += leg.getAmount() * instrument.price();
            delta += leg.getAmount() / amount * instrument.delta();
            fwdDelta += leg.getAmount() / amount * instrument.forwardDelta();
            gamma += leg.getAmount() * instrument.gamma();
            vega += leg.getAmount() * instrument.vega();
            rho += leg.getAmount() * instrument.rho();
        }
        return new PriceResult(premium, delta, domesticDelta, fwdDelta, gamma, vega, theta, rho);
    }

}