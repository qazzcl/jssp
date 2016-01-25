package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;

import static java.lang.Math.abs;

public class NumberUtils {

    public static final double InvalidNumber = -999999;
    private static final double equalTolerance = 1E-8;

    private NumberUtils() {
        //
    }

    public static double sum(Collection<? extends  Number> collection) {
        double result = 0;
        for (Number number : collection) {
            result += number.doubleValue();
        }
        return result;
    }

    @SuppressWarnings("unused")
    public static boolean isZero(double value) {
        return doubleEquals(value, 0);
    }

    @SuppressWarnings("unused")
    public static boolean notZero(double value) {
        return !isZero(value);
    }

    public static boolean doubleEquals(double v1, double v2) {
        if (v2 == 0) {
            return v1 == v2;
        }
        return abs(v1 / v2 - 1.0) < equalTolerance;
    }

    public static double roundToDigits(double value, int digits) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @SuppressWarnings("unused")
    public static String doubleString(double v, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(v);
    }

    @SuppressWarnings("unused")
    public static boolean between(double value, double floor, double ceiling){
        return floor < value && value < ceiling;
    }

    @SuppressWarnings("unused")
    public static boolean hasNumber(Double value) {
        return value != null && !Double.isNaN(value) && isValidNumber(value);
    }

    public static boolean isValidNumber(Double value) {
        return abs(value - InvalidNumber) > 1E-4;
    }
}
