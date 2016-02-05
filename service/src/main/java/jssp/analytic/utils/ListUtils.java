package jssp.analytic.utils;

import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import jssp.analytic.common.ReduceFunctionTwo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> T head(List<T> list) {
        return list.get(0);
    }

    public static <T> List<T> tail(List<T> list) {
        return list.subList(1, list.size());
    }

    public static <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        List<T> result = Lists.newArrayList(list);
        Collections.sort(result, comparator);
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = Lists.newArrayListWithExpectedSize(list.size());
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> Optional<T> firstOf(List<T> list, Predicate<T> predicate) {
        for (T t : list) {
            if (predicate.test(t)) {
                return Optional.of(t);
            }
        }
        return Optional.absent();
    }

    public static <T> T end(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <K, S> Multimap<K, S> groupBy(List<S> list, Function<S, K> function) {
        Multimap<K, S> result = HashMultimap.create();
        for (S item : list) {
            K key = function.apply(item);
            result.put(key, item);
        }
        return result;
    }

    public static <K, S> Map<K, S> groupByToSimpleMap(List<S> list, Function<S, K> function) {
        Map<K, S> result = Maps.newHashMap();
        for (S item : list) {
            K key = function.apply(item);
            result.put(key, item);
        }
        return result;
    }

    public static <T> boolean forAny(List<T> list, Predicate<T> predicate) {
        for (T t : list) {
            if(predicate.test(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean forAll(List<T> list, Predicate<T> predicate) {
        for (T t : list) {
            if(!predicate.test(t)) {
                return false;
            }
        }
        return true;
    }

    public static <T> void traverse(List<T> list, Consumer<T> function) {
        for (T t : list) {
            function.accept(t);
        }
    }

    public static <S, R> List<R> map(List<S> list, Function<S, R> function) {
        List<R> result = Lists.newArrayListWithExpectedSize(list.size());
        for (S s : list) {
            result.add(function.apply(s));
        }
        return result;
    }

    public static <T> List<T> join(List<T> list1, List<T> list2) {
        List<T> result = Lists.newArrayListWithExpectedSize(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    public static <R, T> R fold(R seed, List<T> list, ReduceFunctionTwo<R, T> func) {
        R result = seed;
        for (T t : list) {
            result = func.apply(result, t);
        }
        return result;
    }

    public static <T> T reduce(List<T> list, BinaryOperator<T> func) {
        T result = head(list);
        for (T t : tail(list)) {
            result = func.apply(result, t);
        }
        return result;
    }
}
