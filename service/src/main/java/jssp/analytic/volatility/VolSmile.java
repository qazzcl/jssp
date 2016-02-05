package jssp.analytic.volatility;

//import org.quantlib.CubicSplineSmileSection;

import java.util.List;

public class VolSmile {

    public final double time;
    public final List<Double> strikes;
    public final List<Double> vols;
    public final double atmLevel;

//    private final CubicSplineSmileSection smileSection;//TODO

    public VolSmile(double time, List<Double> strikes, List<Double> vols, double atmLevel) {
        this.time = time;
        this.strikes = strikes;
        this.vols = vols;
        this.atmLevel = atmLevel;
// TODO
//        smileSection = new CubicSplineSmileSection(
//                time,
//                doubleVector(strikes),
//                doubleVectorWithScale(vols, Math.sqrt(time)),
//                atmLevel);
    }

    public double volOf(double strike) {
//        return smileSection.volatility(strike);// TODO
        return 0.0;
    }
}
