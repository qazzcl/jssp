package jssp.analytic.bond.domain;

import jssp.analytic.bond.domain.db.BondRedemption;
import jssp.analytic.bond.domain.option.OptionSchedule;
import jssp.analytic.utils.DateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.quantlib.Date;

import java.util.Map;
import java.util.TreeMap;

public class BondInfo {

    public final String bondKey;
    public final String bondId;

    public final BondBasic bondBasic;
    public final CouponInfo couponInfo;

    public final BondConvention bondConvention;

    public final BondRedemption bondRedemption;
    public final Map<Integer, Double> compensationSchedule;
    public final OptionSchedule callSchedule;
    public final OptionSchedule putSchedule;

    public BondInfo(String bondKey, String bondId, BondBasic bondBasic, CouponInfo couponInfo, BondConvention bondConvention){
        this(bondKey, bondId, bondBasic, couponInfo, bondConvention, BondRedemption.NoRedemption, new TreeMap<Integer, Double>(), OptionSchedule.EmptyOptionSchedule, OptionSchedule.EmptyOptionSchedule);
    }

    public BondInfo(String bondKey, String bondId, BondBasic bondBasic, CouponInfo couponInfo, BondConvention bondConvention, BondRedemption bondRedemption, Map<Integer, Double> compensationSchedule, OptionSchedule callSchedule, OptionSchedule putSchedule) {
        this.bondKey = bondKey;
        this.bondId = bondId;
        this.bondBasic = bondBasic;
        this.couponInfo = couponInfo;
        this.bondConvention = bondConvention;
        this.bondRedemption = bondRedemption;
        this.compensationSchedule = compensationSchedule;
        this.callSchedule = callSchedule;
        this.putSchedule = putSchedule;
    }

    @SuppressWarnings("unused")
    public double remainingYear(Date settlementDate) {
        return (DateUtils.toQlDate(bondBasic.maturityDate).serialNumber() - settlementDate.serialNumber()) / 365.0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bondKey", bondKey)
                .append("bondId", bondId)
                .append("bondBasic", bondBasic)
                .append("couponInfo", couponInfo)
                .append("bondConvention", bondConvention)
                .append("redemptionSchedule", bondRedemption)
                .append("compensationSchedule", compensationSchedule)
                .append("callSchedule", callSchedule)
                .append("putSchedule", putSchedule)
                .toString();
    }
}
