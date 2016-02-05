package jssp.analytic.bond.domain.db;

import com.google.common.collect.Maps;
import jssp.analytic.common.Translator;

import java.util.NavigableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class RedemptionTranslator implements Translator<BondEntity, BondRedemption> {

    private static final Pattern pattern = Pattern.compile("(\\d+\\.?\\d*?)\\|(\\d+\\.?\\d*?)\\%");

    @Override
    public BondRedemption translate(BondEntity bondEntity) {
        String redemptionStr = bondEntity.getRedemptionStr();
        if (isEmpty(redemptionStr)) {
            return BondRedemption.NoRedemption;
        }
        Matcher matcher = pattern.matcher(redemptionStr);
        NavigableMap<Integer, Double> schedule = Maps.newTreeMap();
        while (matcher.find()) {
            Integer term = Integer.valueOf(matcher.group(1));
            Double amount = Double.valueOf(matcher.group(2));
            schedule.put(term, amount);
        }
        if(redemptionStr.endsWith("F")){
            return new BondRedemption(schedule, BondRedemptionType.Share);
        }
        return new BondRedemption(schedule, BondRedemptionType.FaceAmount);
    }
}
