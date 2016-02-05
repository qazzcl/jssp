package jssp.analytic.bond.analytic;

import com.google.common.collect.Lists;
import jssp.analytic.bond.domain.BondCashflow;
import jssp.analytic.bond.domain.db.BondRedemption;
import jssp.analytic.utils.CollectionUtils;
import jssp.analytic.utils.DateUtils;
import org.quantlib.*;
//import org.quantlib.AmortizingFixedRateBond;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.quantlib.Compounding.Compounded;
import static org.quantlib.Compounding.Simple;
import static org.quantlib.DateGeneration.Rule.Backward;
import static org.quantlib.Duration.Type.Macaulay;
import static org.quantlib.Duration.Type.Modified;

public class AnalyticCouponBond extends AnalyticBond {
    public final Date firstCouponDate;
    public final double redemption;
    //coupon
    public final double forwardCouponRate;
    public final double currentCouponRate;
    public final Frequency couponFrequency;
    // conventions
    public final Calendar calendar;
    public final BusinessDayConvention couponDateConvention;
    public final BusinessDayConvention maturityDateConvention;
    public final DayCounter accrInterestDayCounter;
    public final DayCounter yieldDayCounter;

    public final Compounding compounding;

    //compensation
    public final Map<Integer, Double> compensationSchedule;
    //redemption
    public final BondRedemption bondRedemption;
    private final Leg leg;
    public final Schedule schedule;

    public AnalyticCouponBond(
            Date accrualStartDate,
            Date maturityDate,
            Date firstCouponDate,
            double redemption,
            double currentCouponRate,
            double forwardCouponRate,
            Frequency couponFrequency,
            Date settlementDate,
            Calendar calendar,
            BusinessDayConvention couponDateConvention,
            BusinessDayConvention maturityDateConvention,
            DayCounter accrInterestDayCounter,
            DayCounter yieldDayCounter,
            Compounding compounding,
            Map<Integer, Double> compensationSchedule,
            BondRedemption bondRedemption,
            int settlementDays,
            boolean includeSettlementCashflow) {

        super(accrualStartDate, maturityDate, settlementDate, calendar, settlementDays, includeSettlementCashflow);
        this.firstCouponDate = firstCouponDate;
        this.redemption = redemption;
        this.forwardCouponRate = forwardCouponRate;
        this.currentCouponRate = currentCouponRate;
        this.couponFrequency = couponFrequency;
        this.calendar = calendar;
        this.couponDateConvention = couponDateConvention;
        this.maturityDateConvention = maturityDateConvention;
        this.accrInterestDayCounter = accrInterestDayCounter;
        this.yieldDayCounter = yieldDayCounter;
        this.compounding = compounding;

        this.compensationSchedule = compensationSchedule;
        this.bondRedemption = bondRedemption;

        schedule = schedule();
        underlyingBond = createBond(accrInterestDayCounter, schedule);
        leg = underlyingBond.cashflows();
    }

    public static AnalyticCouponBond simpleBond(Date accrualStartDate,
                                                Date maturityDate,
                                                double couponRate,
                                                Frequency couponFrequency,
                                                Date settlementDate,
                                                Calendar calendar,
                                                DayCounter dayCounter,
                                                Compounding compounding) {
        return new AnalyticCouponBond(accrualStartDate,
                maturityDate,
                new Date(),
                100.0,
                couponRate,
                couponRate,
                couponFrequency,
                settlementDate,
                calendar,
                BusinessDayConvention.Unadjusted,
                BusinessDayConvention.Unadjusted,
                new ActualActual(ActualActual.Convention.Bond),
                dayCounter,
                compounding,
                new TreeMap<>(),
                BondRedemption.NoRedemption,
                0,
                false);
    }

    public AnalyticBond withSettlementDate(Date newSettlementDate, DayCounter newYieldDayCounter, Compounding newCompounding) {
        return new AnalyticCouponBond(accrualStartDate, maturityDate, firstCouponDate, redemption, currentCouponRate, forwardCouponRate, couponFrequency,
                newSettlementDate, calendar, couponDateConvention, maturityDateConvention, accrInterestDayCounter, newYieldDayCounter, newCompounding, new TreeMap<Integer, Double>(), bondRedemption, settlementDays, includeSettlementCashflow);
    }

    @SuppressWarnings("unused")
    public AnalyticCouponBond withDayCounters(DayCounter newAccrInterestDayCounter, DayCounter newYieldDayCounter) {
        return new AnalyticCouponBond(accrualStartDate, maturityDate, firstCouponDate, redemption, currentCouponRate, forwardCouponRate, couponFrequency,
                settlementDate, calendar, couponDateConvention, maturityDateConvention, newAccrInterestDayCounter, newYieldDayCounter, compounding, compensationSchedule, bondRedemption, settlementDays, includeSettlementCashflow);
    }

    public AnalyticBond withOptionExecution(Date executionDate, double strike, DayCounter newYieldDayCounter, Compounding newCompounding) {
        return new AnalyticCouponBond(accrualStartDate, executionDate, firstCouponDate, strike, currentCouponRate, forwardCouponRate, couponFrequency,
                settlementDate, calendar, couponDateConvention, maturityDateConvention, accrInterestDayCounter, newYieldDayCounter, newCompounding, compensationSchedule, bondRedemption, settlementDays, includeSettlementCashflow);
    }


    public List<BondCashflow> cashflow() {
        List<BondCashflow> result = Lists.newArrayList();

        TreeMap<Date, Double> allMap = cashFlowsByDate(underlyingBond.cashflows());
        TreeMap<Date, Double> redemptionMap = cashFlowsByDate(underlyingBond.redemptions());

        for (Map.Entry<Date, Double> entry : allMap.entrySet()) {
            BondCashflow bondCashflow = bondCashflowOf(entry, redemptionMap);
            result.add(bondCashflow);
        }
        return result;
    }

    private TreeMap<Date, Double> cashFlowsByDate(Leg leg) {
//        TreeMap<Date, Double> result = Maps.newTreeMap();
        TreeMap<Date, Double> result = new TreeMap<>();
        long size = leg.size();
        for (int i = 0; i < size; i++) {
            CashFlow cashFlow = leg.get(i);
            Date date = cashFlow.date();
            if (!result.containsKey(date)) {
                result.put(date, cashFlow.amount());
            } else {
                result.put(date, cashFlow.amount() + result.get(date));
            }
        }
        return result;
    }

    private BondCashflow bondCashflowOf(Map.Entry<Date, Double> entry, TreeMap<Date, Double> redemptionMap) {
        Date date = entry.getKey();
        Date adjustedDate = calendar.adjust(date, BusinessDayConvention.Following);
        double redemption = redemptionMap.containsKey(date) ? redemptionMap.get(date) : 0;
        double startPrincipal = CollectionUtils.sum(redemptionMap.tailMap(date, true).values());
        return new BondCashflow(date, adjustedDate, entry.getValue() - redemption, redemption, couponRateOf(date), startPrincipal);
    }

    private double couponRateOf(Date date) {
        return underlyingBond.previousCouponRate(date);
    }

    private Bond createBond(DayCounter dayCounter, Schedule bondSchedule) {
//        if (bondRedemption.redemptionSchedule.isEmpty()) {//TODO
            return new FixedRateBond(
                    0,
                    100.0,
                    bondSchedule,
                    couponVectorOf(bondSchedule),
                    dayCounter,
                    maturityDateConvention,
                    redemption,
                    new Date());
//        }
//
//        return new AmortizingFixedRateBond(
//                0,
//                notionalVectorOf(bondSchedule),
//                bondSchedule,
//                couponVectorOf(bondSchedule),
//                accrInterestDayCounter,
//                couponDateConvention,
//                new Date());
    }

    protected Schedule schedule() {
        return new Schedule(
                accrualStartDate,
                maturityDate,
                new Period(couponFrequency),
                calendar,
                couponDateConvention,
                maturityDateConvention,
                Backward,
                false,
                firstCouponDate);
    }

    public BondResult fromYield(double yield) {
        Leg legForYieldCalc = legForYieldCalc();
        if(overdue()) {
            return overdueResult(legForYieldCalc);
        }
        double fullPrice = CashFlows.npv(legForYieldCalc, yield, yieldDayCounter, compounding, couponFrequency, false, settlementDate, settlementDate);

        double pvbp = Math.abs(CashFlows.basisPointValue(legForYieldCalc, yield, yieldDayCounter, compounding, couponFrequency, false, settlementDate));
        double macaulayDuration = macaulayDuration(legForYieldCalc, yield, yieldDayCounter, compounding, couponFrequency, settlementDate, maturityDate);
        double modifiedDuration = modifiedDuration(legForYieldCalc, yield, yieldDayCounter, compounding, couponFrequency, settlementDate, maturityDate);
        double convexity = CashFlows.convexity(legForYieldCalc, yield, yieldDayCounter, compounding, couponFrequency, false, settlementDate);

        double accruedAmount = accruedAmount(settlementDate);
        int accruedDays = accruedDays();
        return new BondResult(
                settlementDate,
                fullPrice - accruedAmount,
                fullPrice,
                yield,
                accruedAmount,
                accruedDays,
                modifiedDuration,
                macaulayDuration,
                convexity,
                pvbp);
    }

    public BondResult fromFullPrice(double fullPrice) {
        if(overdue()) {
            return overdueResult(legForYieldCalc());
        }
        double yield = CashFlows.yield(legForYieldCalc(), fullPrice, yieldDayCounter, compounding, couponFrequency, false, settlementDate, settlementDate, 1E-14);
        return fromYield(yield);
    }

    public double accruedAmount(Date date) {
//        return CashFlows.accruedAmount(legForYieldCalc(), includeSettlementCashflow, date.add(settlementDays)); //TODO
        return 0.0;
    }

    private BondResult overdueResult(Leg legForYieldCalc) {
        double finalRedemption = legForYieldCalc.get((int) (legForYieldCalc.size() -1)).amount();
        return new BondResult(
                settlementDate,
                finalRedemption,
                finalRedemption,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        );
    }

    public Leg legForYieldCalc() {
        switch (bondRedemption.redemptionType) {
            case Share:
                BondRedemption normalizeNotional = bondRedemption.normalizeNotional(schedule, settlementDate);
                return legOfNewRedemption(normalizeNotional);
            default:
                return leg;
        }
    }

    private Leg legOfNewRedemption(BondRedemption newBondRedemption) {
        return new AnalyticCouponBond(
                accrualStartDate,
                maturityDate,
                firstCouponDate,
                redemption,
                currentCouponRate,
                forwardCouponRate,
                couponFrequency,
                settlementDate,
                calendar,
                couponDateConvention,
                maturityDateConvention,
                accrInterestDayCounter,
                yieldDayCounter,
                compounding,
                compensationSchedule,
                newBondRedemption,
                settlementDays,
                includeSettlementCashflow).leg;
    }

    @Override
    public int accruedDays() {
//        Date lastCouponOrAccrualDate = max(underlyingBond.previousCashFlowDate(settlementDate), accrualStartDate); //TODO
        Date lastCouponOrAccrualDate = null;
        return settlementDate.add(settlementDays).serialNumber() - lastCouponOrAccrualDate.serialNumber();
    }

    private double macaulayDuration(Leg leg, double yield, DayCounter dayCounter, Compounding compounding, Frequency frequency, Date settlementDate, Date maturityDate) {
        if (compounding == Compounded) {
            return CashFlows.duration(leg, yield, dayCounter, compounding, frequency, Macaulay, false, settlementDate, settlementDate);
        }
        if (compounding == Simple) {
            return dayCounter.yearFraction(settlementDate, maturityDate);
        }
        throw new IllegalStateException("wrong compounding type: " + compounding);
    }

    private double modifiedDuration(Leg leg, double yield, DayCounter dayCounter, Compounding compounding, Frequency frequency, Date settlementDate, Date maturityDate) {
        if (compounding == Compounded) {
            return CashFlows.duration(leg, yield, dayCounter, compounding, frequency, Modified, false, settlementDate, settlementDate);
        }
        if (compounding == Simple) {
            double yearFraction = dayCounter.yearFraction(settlementDate, maturityDate);
            return yearFraction / (1 + yield * yearFraction);
        }
        throw new IllegalStateException("wrong compounding type: " + compounding);
    }

    private DoubleVector couponVectorOf(Schedule schedule) {
        DoubleVector result = new DoubleVector();
        long scheduleSize = schedule.size();
        for (int i = 0; i < scheduleSize - 1; i++) {
            int couponIndex = i + 1;
            final double couponRateLevel = DateUtils.lessOrEqual(schedule.date(i), settlementDate) ? currentCouponRate : forwardCouponRate;
            final double adjustedCouponRate = compensationSchedule.containsKey(couponIndex)
                    ? couponRateLevel + compensationSchedule.get(couponIndex)
                    : couponRateLevel;
            result.add(adjustedCouponRate);
        }
        return result;
    }

    private DoubleVector notionalVectorOf(Schedule schedule) {
        int scheduleSize = (int) schedule.size();
        DoubleVector result = new DoubleVector();
        for (int i = 0; i < scheduleSize; i++) {
            double notionalAmount = notionalOf(i);
            result.add(notionalAmount);
        }
        return result;
    }

    private double notionalOf(int scheduleIndex) {
        double notionalAmount = redemption;
        int couponIndex = scheduleIndex + 1;
        for (double redemptionValue : bondRedemption.redemptionSchedule.subMap(0, couponIndex).values()) {
            notionalAmount -= redemptionValue;
        }
        return notionalAmount;
    }
}

