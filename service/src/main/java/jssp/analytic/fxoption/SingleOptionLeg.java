package jssp.analytic.fxoption;

import jssp.analytic.utils.DateUtils;
import org.joda.time.LocalDate;
import org.quantlib.Actual365Fixed;
import org.quantlib.DayCounter;
import org.quantlib.Option;

import java.util.ArrayList;

public class SingleOptionLeg implements OptionTrade {

    public enum BuySell {
        Buy(1), Sell(-1);

        public final int sign;
        public final String string;

        BuySell(int sign) {
            this.sign = sign;
            this.string = this.name().toLowerCase();
        }
    }

    private static final DayCounter volDayCounter = new Actual365Fixed();

    public final double notional;
    public final BuySell buySell;
    public final Option.Type callPut;
    public final double spot;
    public final double forward;
    public final double domesticDiscount;
    public final double volatility;
    public final double strike;
    public final LocalDate horizonDate;
    public final LocalDate spotDate;
    public final LocalDate expiryDate;
    public final LocalDate settlementDate;

    public SingleOptionLeg(double notional, BuySell buySell, Option.Type callPut, double spot, double forward, double domesticDiscount, double volatility, double strike, LocalDate horizonDate, LocalDate spotDate, LocalDate expiryDate, LocalDate settlementDate) {
        this.notional = notional;
        this.buySell = buySell;
        this.callPut = callPut;
        this.spot = spot;
        this.forward = forward;
        this.domesticDiscount = domesticDiscount;
        this.volatility = volatility;
        this.strike = strike;
        this.horizonDate = horizonDate;
        this.spotDate = spotDate;
        this.expiryDate = expiryDate;
        this.settlementDate = settlementDate;
    }

    public SingleOptionLeg with(BuySell newBuySell, Option.Type newCallPut) {
        return with(newBuySell, newCallPut, volatility, strike);
    }

    public SingleOptionLeg with(Option.Type newCallPut, double newVol, double newStrike) {
        return with(buySell, newCallPut, newVol, newStrike);
    }

    public SingleOptionLeg with(BuySell newBuySell, Option.Type newCallPut, double newVol, double newStrike) {
        return new SingleOptionLeg(notional, newBuySell, newCallPut, spot, forward, domesticDiscount, newVol, newStrike, horizonDate, spotDate, expiryDate, settlementDate);
    }

    @Override
    public OptionResult price() {
        RawFxOption instrument = instrument();

        int sign = buySell.sign;
        return new OptionResult(
                buySell.string,
                callPutString(),
                amount(),
                sign * instrument.price(),
                sign * instrument.price() * notional,
                sign * instrument.price() / spot,
                sign * instrument.price() * notional / spot,
                sign * instrument.delta(),
                sign * instrument.forwardDelta(),
                sign * instrument.vega(),
                sign * instrument.gamma(),
                sign * instrument.decay(),
                sign * instrument.rho(),
                sign * instrument.phi(),
                new ArrayList<>());
    }

    private String callPutString() {
        return callPut == Option.Type.Call ? "call" : "put";
    }

    @Override
    public double amount() {
        return notional * buySell.sign;
    }

    private RawFxOption instrument() {
        double maturity = volDayCounter.yearFraction(DateUtils.toQlDate(horizonDate), DateUtils.toQlDate(expiryDate));
        return new RawFxOption(
                callPut,
                strike,
                spot,
                forward,
                maturity,
                volatility,
                domesticDiscount);
    }
}