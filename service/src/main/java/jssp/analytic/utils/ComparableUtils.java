package jssp.analytic.utils;

public class ComparableUtils {

    public static <T extends Comparable<? super T>> T min(T first, T second) {
        return first.compareTo(second) < 0 ? first : second;
    }

    public static <T extends Comparable<? super T>> T max(T first, T second) {
        return first.compareTo(second) > 0 ? first : second;
    }
}
