package jssp.analytic.bond.domain.db;

import com.google.common.base.Preconditions;
import jssp.analytic.bond.domain.BondBasic;
import jssp.analytic.bond.domain.BondConvention;
import jssp.analytic.bond.domain.BondInfo;
import jssp.analytic.bond.domain.CouponInfo;
import jssp.analytic.bond.domain.option.OptionSchedule;
import jssp.analytic.common.Translator;
import jssp.analytic.refdata.MarketCalendar;

import java.util.Map;

public class BondTranslator implements Translator<BondEntity, BondInfo> {

    private final BondBasicTranslator basicTranslator;
    private final CouponInfoTranslator couponInfoTranslator;
    private final RedemptionTranslator redemptionTranslator;
    private final CompensationTranslator compensationTranslator;
    private final OptionScheduleTranslator callTranslator;
    private final OptionScheduleTranslator putTranslator;
    private final BondConventionTranslator conventionTranslator;

    private BondTranslator(BondBasicTranslator basicTranslator, CouponInfoTranslator couponInfoTranslator, RedemptionTranslator redemptionTranslator, CompensationTranslator compensationTranslator, OptionScheduleTranslator callTranslator, OptionScheduleTranslator putTranslator, BondConventionTranslator conventionTranslator) {
        this.basicTranslator = basicTranslator;
        this.couponInfoTranslator = couponInfoTranslator;
        this.redemptionTranslator = redemptionTranslator;
        this.compensationTranslator = compensationTranslator;
        this.callTranslator = callTranslator;
        this.putTranslator = putTranslator;
        this.conventionTranslator = conventionTranslator;
    }

    public static BondTranslator bondTranslatorOf(BondBasicTranslator basicTranslator, MarketCalendar marketCalendar) {
        CouponInfoTranslator couponInfoTranslator = new CouponInfoTranslator();
        return new BondTranslator(
                basicTranslator,
                couponInfoTranslator,
                new RedemptionTranslator(),
                new CompensationTranslator(couponInfoTranslator, basicTranslator, marketCalendar),
                new OptionScheduleTranslator(basicTranslator, couponInfoTranslator, marketCalendar, OptionScheduleTranslator.CallPut.Call),
                new OptionScheduleTranslator(basicTranslator, couponInfoTranslator, marketCalendar, OptionScheduleTranslator.CallPut.Put),
                new BondConventionTranslator());
    }

    public BondInfo translate(BondEntity bondEntity) {

        String bondKey = bondEntity.getBondKey();
        Preconditions.checkState(bondKey != null, "bondKey should not be null, table id: " + bondEntity.getId());

        String bondId = bondEntity.getBondId();

        BondBasic basic = basicTranslator.translate(bondEntity);
        CouponInfo couponInfo = couponInfoTranslator.translate(bondEntity);
        BondRedemption bondRedemption = redemptionTranslator.translate(bondEntity);
        Map<Integer, Double> compensationSchedule = compensationTranslator.translate(bondEntity);
        OptionSchedule callSchedule = callTranslator.translate(bondEntity);
        OptionSchedule putSchedule = putTranslator.translate(bondEntity);
        BondConvention bondConvention = conventionTranslator.translate(bondEntity);

        return new BondInfo(bondKey, bondId, basic, couponInfo, bondConvention, bondRedemption, compensationSchedule, callSchedule, putSchedule);

    }
}
