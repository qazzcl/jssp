package jssp.analytic.bond.domain.db;

import com.google.common.collect.Maps;
import jssp.analytic.utils.DateUtils;
import org.quantlib.Date;
import org.quantlib.Schedule;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class BondRedemption {

    public static final BondRedemption NoRedemption = new BondRedemption(new TreeMap<Integer, Double>(), BondRedemptionType.NONE);

    public final NavigableMap<Integer, Double> redemptionSchedule;
    public final BondRedemptionType redemptionType;

    public BondRedemption(NavigableMap<Integer, Double> redemptionSchedule, BondRedemptionType redemptionType) {
        this.redemptionSchedule = redemptionSchedule;
        this.redemptionType = redemptionType;
    }

    public BondRedemption normalizeNotional(Schedule schedule, Date currentDate) {
        int nextCouponIndex = nextCouponIndex(schedule, currentDate);
        NavigableMap<Integer, Double> remainingRedemption = redemptionSchedule.subMap(nextCouponIndex, true, redemptionSchedule.lastKey(), true);
        double total = sum(remainingRedemption);
        NavigableMap<Integer, Double> scaledMap = scaleValues(remainingRedemption, 100 / total);
        return new BondRedemption(scaledMap, BondRedemptionType.FaceAmount);
    }

    private double sum(NavigableMap<Integer, Double> remainingRedemption) {
        double total = 0;
        for (Double value : remainingRedemption.values()) {
            total += value;
        }
        return total;
    }

    private NavigableMap<Integer, Double> scaleValues(NavigableMap<Integer, Double> map, double scale) {
        NavigableMap<Integer, Double> result = Maps.newTreeMap();
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue() * scale);
        }
        return result;
    }

    private int nextCouponIndex(Schedule schedule, Date settlementDate) {
        int size = (int) schedule.size();
        for (int i = 0; i < size - 1; i++) {
            Date date = schedule.date(i);
            if (DateUtils.isAfter(date, settlementDate)) {
                return i;
            }
        }
        return 0;
    }
}
