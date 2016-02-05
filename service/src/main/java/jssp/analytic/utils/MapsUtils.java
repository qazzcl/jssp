package jssp.analytic.utils;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.TreeMap;

public class MapsUtils {

    public static <K, V> Map<V, K> inverse(Map<K, V> map){
        Map<V, K> result = Maps.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }

    public static <K extends Comparable<K>, V> TreeMap<K, V> treeMapOf (K key, V value) {
        TreeMap<K, V> result = Maps.newTreeMap();
        result.put(key, value);
        return result;
    }

    public static <K extends Comparable<K>, V> TreeMap<K, V> treeMapOf (K key1, V value1, K key2, V value2) {
        TreeMap<K, V> result = Maps.newTreeMap();
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }
}
