package jssp.analytic.fxoption;

import org.quantlib.BlackCalculator;
import org.quantlib.Option;
import org.quantlib.PlainVanillaPayoff;

import static org.quantlib.Option.Type.Call;
import static org.quantlib.Option.Type.Put;

public class RawFxOption {

    public final Option.Type type;
    public final double strike;
    public final double spot;
    public final double forward;
    public final double maturity;
    public final double volatility;
    public final double domesticDiscount;
    private BlackCalculator blackCalculator;


    public RawFxOption(Option.Type type, double strike, double spot, double forward, double maturity, double volatility, double domesticDiscount) {
        this.type = type;
        this.strike = strike;
        this.spot = spot;
        this.forward = forward;
        this.maturity = maturity;
        this.volatility = volatility;
        this.domesticDiscount = domesticDiscount;
    }

    public RawFxOption inverse() {
        Option.Type inverseType = (type == Call) ? Put : Call;
        return new RawFxOption(inverseType, 1.0 / strike, 1.0 / spot, 1.0 / forward, maturity, volatility, foreignDiscount());
    }

    public double price() {
        createCalculator();
        return blackCalculator.value();
    }

    public double delta() {
        createCalculator();
        return blackCalculator.delta(spot);
    }

    public double forwardDelta() {
        return delta() / foreignDiscount();
    }

    public double gamma() {
        createCalculator();
        return blackCalculator.gamma(spot) * spot / 100.0;
    }

    public double vega() {
        createCalculator();
        return blackCalculator.vega(maturity) / 100.0;
    }

    public double rho() {
        createCalculator();
        return blackCalculator.rho(maturity);
    }

    public double phi() {
        createCalculator();
        return blackCalculator.dividendRho(maturity);
    }

    public double decay() {
        createCalculator();
        return blackCalculator.theta(spot, maturity);
    }

    public double breakEven() {
        if (type == Call) {
            return price() + strike;
        }
        return strike - price();
    }

    private double foreignDiscount() {
        return forward / spot * domesticDiscount;
    }

    private void createCalculator() {
        if (blackCalculator == null) {
            blackCalculator = new BlackCalculator(new PlainVanillaPayoff(type, strike),
                    forward,
                    volatility * Math.sqrt(maturity),
                    domesticDiscount
            );
        }
    }

}
