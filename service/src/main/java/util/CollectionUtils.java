package util;

import java.util.Collection;

public class CollectionUtils {

    public static <T extends Number> double sum(Collection<T> collection) {
        double result = 0;
        for (T t : collection) {
            result += t.doubleValue();
        }
        return result;
    }
}
