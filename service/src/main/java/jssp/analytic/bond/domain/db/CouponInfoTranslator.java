package jssp.analytic.bond.domain.db;


import com.google.common.base.Preconditions;
import jssp.analytic.bond.domain.CouponFrequency;
import jssp.analytic.bond.domain.CouponInfo;
import jssp.analytic.bond.domain.CouponType;
import jssp.analytic.common.Translator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class CouponInfoTranslator implements Translator<BondEntity, CouponInfo> {

    private static final Pattern precedsPattern = Pattern.compile("P(\\d+)D");

    @Override
    public CouponInfo translate(BondEntity bondEntity) {

        return new CouponInfo(
                parseCouponType(bondEntity),
                parseFrequency(bondEntity),
                bondEntity.getFrnIndexId(),
                rateOrSpread(bondEntity),
                currentRateOf(bondEntity),
                precedingDays(bondEntity),
                fixingDays(bondEntity),
                fixingDigits(bondEntity),
                floor(bondEntity),
                cap(bondEntity)
        );
    }

    private double currentRateOf(BondEntity bondEntity) {
        BigDecimal couponRateCurrent = bondEntity.getCouponRateCurrent();
        if(couponRateCurrent != null) {
            return couponRateCurrent.doubleValue() / 100.0;
        }
        return 0;
    }

    private double rateOrSpread(BondEntity bondEntity) {
        Preconditions.checkNotNull(bondEntity.getCouponRateSpread(), "empty couponRateSpread ");
        return bondEntity.getCouponRateSpread().doubleValue() / 100.0;
    }

    private int precedingDays(BondEntity bondEntity){
        if(isEmpty(bondEntity.getFixingPreceds())){
            return 0;
        }
        Matcher matcher = precedsPattern.matcher(bondEntity.getFixingPreceds());
        if(matcher.find()){
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }


    private int fixingDays(BondEntity bondEntity) {
        return bondEntity.getFixingMaDays() == null ? 1 : bondEntity.getFixingMaDays();
    }

    private int fixingDigits(BondEntity bondEntity) {
        return bondEntity.getFixingDigit() == null ? 2 : bondEntity.getFixingDigit().intValue();
    }

    private double cap(BondEntity entity) {
        if(isNotEmpty(entity.getCap())){
            return Double.parseDouble(entity.getCap()) / 100.0;
        }
        return 100;
    }

    private double floor(BondEntity entity) {
        if(isNotEmpty(entity.getFlr())){
            return Double.parseDouble(entity.getFlr()) / 100.0;
        }
        return -100;
    }

    private CouponType parseCouponType(BondEntity bondEntity) {
        String type = bondEntity.getCouponType();
        if ("FRB".equalsIgnoreCase(type)) {
            return CouponType.Fixed;
        } else if ("PAM".equalsIgnoreCase(type)) {
            return CouponType.PayAtMaturity;
        } else if ("DSC".equalsIgnoreCase(type)) {
            return CouponType.Discount;
        } else if ("FRN".equalsIgnoreCase(type)) {
            return CouponType.Floating;
        } else {
            throw new IllegalArgumentException("invalid coupon type: " + type);
        }
    }

    private CouponFrequency parseFrequency(BondEntity bondEntity) {
        String frequencyStr = bondEntity.getCouponFrequency();
        if ("N".equalsIgnoreCase(frequencyStr)) {
            return CouponFrequency.NoFrequency;
        } else if ("A".equalsIgnoreCase(frequencyStr)) {
            return CouponFrequency.Annual;
        } else if ("S".equalsIgnoreCase(frequencyStr)) {
            return CouponFrequency.SemiAnnual;
        } else if ("Q".equalsIgnoreCase(frequencyStr)) {
            return CouponFrequency.Quarterly;
        } else if ("M".equalsIgnoreCase(frequencyStr)) {
            return CouponFrequency.Monthly;
        } else {
            //default
            return CouponFrequency.Annual;
        }
    }
}

