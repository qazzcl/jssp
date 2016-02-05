package jssp.analytic.bond.domain.db;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import jssp.analytic.refdata.MarketCalendar;
import jssp.analytic.bond.domain.BondBasic;
import jssp.analytic.bond.domain.CouponInfo;
import jssp.analytic.common.Translator;
import org.apache.commons.lang3.StringUtils;
import org.quantlib.Period;
import org.quantlib.Schedule;

import java.util.Map;
import java.util.TreeMap;

import static org.quantlib.BusinessDayConvention.Unadjusted;
import static org.quantlib.DateGeneration.Rule.Backward;

public class CompensationTranslator implements Translator<BondEntity, Map<Integer, Double>> {

    private final Translator<BondEntity, CouponInfo> couponInfoTranslator;
    private final Translator<BondEntity, BondBasic> bondBasicTranslator;
    private final MarketCalendar MarketCalendar;

    public CompensationTranslator(Translator<BondEntity, CouponInfo> couponInfoTranslator, Translator<BondEntity, BondBasic> bondBasicTranslator, MarketCalendar MarketCalendar) {
        this.couponInfoTranslator = couponInfoTranslator;
        this.bondBasicTranslator = bondBasicTranslator;
        this.MarketCalendar = MarketCalendar;
    }

    @Override
    public Map<Integer, Double> translate(BondEntity bondEntity) {
        String compensateRate = bondEntity.getCompensateRate();
        String compensateFrom = bondEntity.getCompensateFrom();

        if (StringUtils.isEmpty(compensateRate) || StringUtils.isEmpty(compensateFrom)) {
            return Maps.newTreeMap();
        }

        TreeMap<Integer, Double> rawSchedule = rawSchedule(compensateRate, compensateFrom);

        if (rawSchedule.isEmpty()) {
            return Maps.newTreeMap();
        }

        return steppingUp(rawSchedule, terms(bondEntity));

    }

    private TreeMap<Integer, Double> steppingUp(TreeMap<Integer, Double> rawSchedule, int terms) {
        TreeMap<Integer, Double> result = new TreeMap<Integer, Double>();
        double previous = 0;

        for (int i = rawSchedule.firstKey(); i <= terms; i++) {
            if (rawSchedule.containsKey(i)) {
                double rate = rawSchedule.get(i) + previous;
                result.put(i, rate);
                previous = rate;
            } else {
                result.put(i, previous);
            }
        }
        return result;
    }

    private TreeMap<Integer, Double> rawSchedule(String compensateRate, String compensateFrom) {
        TreeMap<Integer, Double> result = Maps.newTreeMap();
        String[] fromArray = compensateFrom.split("\\|");
        String[] rateArray = compensateRate.split(";");

        int index = 0;
        for (String fromStr : fromArray) {
            Optional<Double> possibleRate = singleRate(rateArray[index]);
            if (possibleRate.isPresent()) {
                result.put(Integer.valueOf(fromStr), possibleRate.get());
            }
            index++;
        }
        return result;

    }

    private Optional<Double> singleRate(String compensateRate) {
        if (compensateRate.contains("|")) {
            String rateArray[] = compensateRate.split("\\|");
            if (isPositiveNumber(rateArray[0]) && isPositiveNumber(rateArray[1])) {
                return Optional.of(Double.valueOf(rateArray[0]) / 100.0);
            }
        } else {
            if (isPositiveNumber(compensateRate)) {
                return Optional.of(Double.valueOf(compensateRate) / 100.0);
            }
        }
        return Optional.absent();
    }

    private boolean isPositiveNumber(String compensateRate) {
        try {
            return Double.valueOf(compensateRate) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int terms(BondEntity bondEntity) {
        CouponInfo couponInfo = couponInfoTranslator.translate(bondEntity);
        BondBasic bondBasic = bondBasicTranslator.translate(bondEntity);
        Schedule schedule = new Schedule(
                bondBasic.qlAccrualStartDate(),
                bondBasic.qlMaturityDate(),
                new Period(couponInfo.couponFrequency.frequency),
                MarketCalendar.qlCalendar(),
                Unadjusted,
                Unadjusted,
                Backward,
                false);
        return (int) schedule.size() - 1;
    }

}
