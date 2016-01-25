package util;

import org.quantlib.DoubleVector;

import java.util.List;

public class QuantLibUtils {

    public static DoubleVector doubleVectorWithScale(List<Double> values, double scale) {
        DoubleVector result = new DoubleVector();
        for (Double v : values) {
            result.add(v * scale);
        }
        return result;
    }

    public static DoubleVector doubleVector(List<Double> values) {
        return doubleVectorWithScale(values, 1);
    }
}
