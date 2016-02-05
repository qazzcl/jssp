package jssp.analytic.bond;

import com.google.common.base.Optional;
import jssp.analytic.bond.analytic.*;
import jssp.analytic.bond.domain.BondBasic;
import jssp.analytic.bond.domain.BondConvention;
import jssp.analytic.bond.domain.BondInfo;
import jssp.analytic.bond.domain.CouponInfo;
import jssp.analytic.exception.AnalyticException;
import jssp.analytic.index.IndexRate;
import jssp.analytic.refdata.MarketCalendar;
import jssp.analytic.utils.DateUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.quantlib.*;

import static org.quantlib.Compounding.Compounded;
import static org.quantlib.Compounding.Simple;

public class AnalyticBondFactory {

    public static final DayCounter actualBond = new ActualActual(ActualActual.Convention.Bond);
    public static final DayCounter actualAFB = new ActualActual(ActualActual.Convention.AFB);
//    public static final DayCounter modifiedAFB = new ModifiedActualAFB();//TODO
    public static final DayCounter actual365NoLeap = new Actual365NoLeap();
    public static final DayCounter actual365Fixed = new Actual365Fixed();

    private final jssp.analytic.refdata.MarketCalendar MarketCalendar;
    private final IndexRateFinder indexRateFinder;

    public AnalyticBondFactory(MarketCalendar MarketCalendar, IndexRateFinder indexRateFinder) {
        this.MarketCalendar = MarketCalendar;
        this.indexRateFinder = indexRateFinder;
    }

    @SuppressWarnings("unused")
    public AnalyticBond createBond(BondInfo bondInfo, BondMarketType marketType, Date settlementDate) {
        LocalDate jodaDate = DateUtils.toJodaDate(settlementDate);
        return createBond(bondInfo, marketType, settlementDate, indexRateFinder.predictRefRate(jodaDate, bondInfo.couponInfo), currentFixedRate(bondInfo, jodaDate));
    }

    public AnalyticBond createBond(BondInfo bondInfo, BondMarketType bondMarketType, Date settlementDate, Optional<IndexRate> predictRefRate, Optional<IndexRate> currentRefRate) {
        int settlementDays = settlementDaysOf(bondMarketType);
        boolean includeSettlementDateCashflow = includeSettlementDateCashflow(bondMarketType);

        CouponInfo couponInfo = bondInfo.couponInfo;
        final AnalyticBond bond;
        switch (couponInfo.type) {
            case Fixed:
                bond = createCouponBond(bondInfo, bondMarketType, settlementDate, couponInfo.rateOrSpread, couponInfo.rateOrSpread, settlementDays, includeSettlementDateCashflow);
                break;
            case Floating:
                checkReferenceRate(predictRefRate, currentRefRate);
                double predictCouponRate = applySpreadAndFloorCap(predictRefRate, couponInfo);
                double currentCouponRate = applySpreadAndFloorCap(currentRefRate, couponInfo);
                bond = createCouponBond(bondInfo, bondMarketType, settlementDate, currentCouponRate, predictCouponRate, settlementDays, includeSettlementDateCashflow);
                break;
            case PayAtMaturity:
                bond = createPam(bondInfo, bondMarketType, settlementDate, settlementDays);
                break;
            case Discount:
                bond = createDiscount(bondInfo, bondMarketType, settlementDate, settlementDays);
                break;
            default:
                throw new IllegalStateException("should not reach here");
        }
        return bond;
    }

    private Optional<IndexRate> currentFixedRate(BondInfo bondInfo, LocalDate jodaDate) {
        LocalDate lastCouponDate = new BondSchedule(bondInfo, qlCalendar()).lastCouponDate(jodaDate);
        return indexRateFinder.currentReferenceRate(lastCouponDate, bondInfo.couponInfo);
    }

    private int settlementDaysOf(BondMarketType bondMarketType) {
        return bondMarketType == BondMarketType.Exchange ? 1 : 0;
    }

    private boolean includeSettlementDateCashflow(BondMarketType bondMarketType) {
        return bondMarketType == BondMarketType.Exchange;
    }

    private void checkReferenceRate(Optional<IndexRate> predictRate, Optional<IndexRate> currentRefRate) {
        if (!predictRate.isPresent()) {
            throw new AnalyticException("No Predict Rate");
        }
        if (!currentRefRate.isPresent()) {
            throw new AnalyticException("No Current Reference Rate");
        }
    }

    private double applySpreadAndFloorCap(Optional<IndexRate> currentRefRate, CouponInfo couponInfo) {
        double candidate = currentRefRate.get().value + couponInfo.rateOrSpread;
        if (candidate < couponInfo.floor) {
            candidate = couponInfo.floor;
        }
        if (candidate > couponInfo.cap) {
            candidate = couponInfo.cap;
        }
        return candidate;
    }


    private AnalyticBond createDiscount(BondInfo bondInfo, BondMarketType bondMarketType, Date settlementDate, int settleDays) {
        DayCounter yieldDayCounter = yieldDayCounterOfZeroBond(bondMarketType);

        return new AnalyticDiscountBond(
                bondInfo.bondBasic.qlAccrualStartDate(),
                bondInfo.bondBasic.qlMaturityDate(),
                bondInfo.bondBasic.issuePrice,
                qlCalendar(),
                yieldDayCounter,
                settlementDate,
                settleDays);
    }

    private AnalyticBond createPam(BondInfo bondInfo, BondMarketType bondMarketType, Date settlementDate, int settlementDays) {
        DayCounter yieldDayCounter = yieldDayCounterOfZeroBond(bondMarketType);
        DayCounter accrualDayCounter = accrualDayCounterOfZeroBond(bondMarketType, bondInfo.bondConvention);

        BondBasic bondBasic = bondInfo.bondBasic;
        double redemption = redemptionOfPam(bondMarketType, bondBasic, bondInfo.bondConvention, bondInfo.couponInfo.rateOrSpread);
        return new AnalyticPamBond(
                bondBasic.qlAccrualStartDate(),
                bondBasic.qlMaturityDate(),
                redemption,
                bondInfo.couponInfo.rateOrSpread,
                qlCalendar(),
                yieldDayCounter,
                settlementDate,
                settlementDays,
                accrualDayCounter);
    }

    private double redemptionOfPam(BondMarketType bondMarketType, BondBasic bondBasic, BondConvention bondConvention, double rateOrSpread) {
        if(bondMarketType == BondMarketType.InterBank_CFETS || bondMarketType == BondMarketType.Exchange) {
            DayCounter redemptionDayCounter = accrualDayCounterOfZeroBond(bondMarketType, bondConvention);
            Date qlAccrualStartDate = bondBasic.qlAccrualStartDate();
            Date qlMaturityDate = bondBasic.qlMaturityDate();
            return 100.0 * (1 + rateOrSpread * redemptionDayCounter.yearFraction(qlAccrualStartDate, qlMaturityDate, qlAccrualStartDate, qlMaturityDate));
        }
        if(bondMarketType == BondMarketType.InterBank_CDC) {
            LocalDate startDate = DateUtils.toJodaDate(bondBasic.qlAccrualStartDate());
            LocalDate maturityDate = DateUtils.toJodaDate(bondBasic.qlMaturityDate());

            int years = Years.yearsBetween(startDate, maturityDate).getYears();

            startDate = years > 0 ? startDate.plusYears(years) : startDate;
            maturityDate = DateUtils.isLeapDay(maturityDate) ? maturityDate.minusDays(1) : maturityDate;

            double yearFraction = years + Days.daysBetween(startDate, maturityDate).getDays() / 365.0;
            return 100.0 * (1 + rateOrSpread * yearFraction);
        }
        throw new IllegalStateException("should not hit here");
    }

    private DayCounter accrualDayCounterOfZeroBond(BondMarketType bondMarketType, BondConvention bondConvention) {
        switch (bondMarketType) {
            case InterBank_CFETS:
                return bondConvention.accrInterestDayCounter;
            case InterBank_CDC:
//                return modifiedAFB; //TODO
                return null;
            case Exchange:
                return actual365NoLeap;
            default:
                throw new IllegalStateException("should not hit here");
        }
    }

    private DayCounter accrualDayCounterOfCouponBond(BondMarketType bondMarketType) {
        if (bondMarketType == BondMarketType.Exchange) {
            return actual365NoLeap;
        }
        return actualBond;
    }

    private DayCounter yieldDayCounterOfCouponBond(BondMarketType bondMarketType, boolean lastCouponPeriod) {
        if (bondMarketType == BondMarketType.Exchange) {
            return actual365NoLeap;
        } else {
            return lastCouponPeriod ? actualAFB : actualBond;
        }
    }

    private DayCounter yieldDayCounterOfZeroBond(BondMarketType bondMarketType) {
        switch (bondMarketType) {
            case InterBank_CFETS:
            case InterBank_CDC:
//                return modifiedAFB;//TODO
            case Exchange:
                return actual365NoLeap;
            default:
                throw new IllegalStateException("should not hit here");
        }
    }

    private AnalyticBond createCouponBond(BondInfo bondInfo, BondMarketType bondMarketType, Date settlementDate, double currentCouponRate, double forwardCouponRate, int settlementDays, boolean includeSettlementCashflow) {

        boolean lastCouponPeriod = new BondSchedule(bondInfo, qlCalendar()).onlyOneScheduleLeft(settlementDate, bondInfo.bondBasic.qlMaturityDate());
        final DayCounter yieldDayCounter = yieldDayCounterOfCouponBond(bondMarketType, lastCouponPeriod);
        final DayCounter accrualDayCounter = accrualDayCounterOfCouponBond(bondMarketType);

        return new AnalyticCouponBond(
                bondInfo.bondBasic.qlAccrualStartDate(),
                bondInfo.bondBasic.qlMaturityDate(),
                firstCouponDate(bondInfo.bondBasic),
                100.0,
                currentCouponRate,
                forwardCouponRate,
                bondInfo.couponInfo.couponFrequency.frequency,
                settlementDate,
                qlCalendar(),
                BusinessDayConvention.Unadjusted,
                BusinessDayConvention.Unadjusted,
                accrualDayCounter,
                yieldDayCounter,
                compoundingOf(lastCouponPeriod),
                bondInfo.compensationSchedule,
                bondInfo.bondRedemption,
                settlementDays,
                includeSettlementCashflow);
    }

    private Compounding compoundingOf(boolean lastCouponPeriod) {
        return lastCouponPeriod ? Simple : Compounded;
    }

    private Date firstCouponDate(BondBasic bondBasic) {
        if (bondBasic.firstCouponDate.isPresent()) {
            return DateUtils.toQlDate(bondBasic.firstCouponDate.get());
        }
        return new Date();
    }

    private Calendar qlCalendar() {
        return MarketCalendar.qlCalendar();
    }

}
