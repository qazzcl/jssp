package jssp.analytic.irs.instrument;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jssp.analytic.bond.analytic.AnalyticCouponBond;
import jssp.analytic.bond.domain.db.BondRedemption;
import jssp.analytic.common.domain.Tenor;
import jssp.analytic.exception.AnalyticException;
import jssp.analytic.index.IndexRate;
import jssp.analytic.irs.domain.IndexSource;
import jssp.analytic.irs.domain.SimplePeriod;
import jssp.analytic.irs.domain.SimpleSwapCurve;
import jssp.analytic.irs.domain.SwapFixing;
import jssp.analytic.irs.domain.SwapSchedule;
import jssp.analytic.utils.DateUtils;
import org.quantlib.BusinessDayConvention;
import org.quantlib.CashFlow;
import org.quantlib.CashFlows;
import org.quantlib.Compounding;
import org.quantlib.Date;
import org.quantlib.DayCounter;
import org.quantlib.Leg;
import org.quantlib.Period;
import org.quantlib.SimpleCashFlow;
import org.quantlib.TimeUnit;
import org.quantlib._VanillaSwap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static jssp.analytic.irs.instrument.SingleIrs.FixingMethod.Adjusted;
import static jssp.analytic.irs.instrument.SingleIrs.FixingMethod.Raw;
import static java.lang.Math.abs;
import static org.quantlib.Compounding.Compounded;
import static org.quantlib.Compounding.Simple;
import static org.quantlib.Frequency.NoFrequency;
import static org.quantlib.Frequency.Once;
import static org.quantlib._VanillaSwap.Type.Receiver;

public class SingleIrs implements IrsInstrument {

    public enum FixingMethod { Raw, Adjusted}

    public final _VanillaSwap.Type type;
    public final double notional;
    public final double fixedRate;

    public final SwapSchedule swapSchedule;
    public final IndexSource indexSource;
    public final SimpleSwapCurve swapCurve;

    public final DayCounter fixedDayCounter;
    public final DayCounter floatingDayCounter;

    private final Leg fixedLeg;
    private SwapResult singleResult;

    public SingleIrs(_VanillaSwap.Type type,
                     double notional,
                     double fixedRate,
                     SwapSchedule swapSchedule,
                     IndexSource indexSource,
                     SimpleSwapCurve swapCurve) {
        this.type = type;
        this.notional = notional;
        this.fixedRate = fixedRate;
        this.swapSchedule = swapSchedule;
        this.indexSource = indexSource;
        this.swapCurve = swapCurve;
        this.fixedDayCounter = swapCurve.fixedDayCounter;
        this.floatingDayCounter = swapCurve.floatingDayCounter;
        this.fixedLeg = toQuantLibLeg(fixedLeg(fixedRate));
    }

    @SuppressWarnings("unused")
    public SingleIrs withSchedule(SwapSchedule newSwapSchedule) {
        return new SingleIrs(type, notional, fixedRate, newSwapSchedule, indexSource, swapCurve);
    }

    public SwapResult calc() {
        if(singleResult == null) {
            singleResult = doCalc();
        }
        return singleResult;
    }

    private SwapResult doCalc() {
        if (DateUtils.isAfter(swapSchedule.maturityDate, swapCurve.underlyingCurve.maxDate())) {
            throw new AnalyticException("Curve too short");
        }
        SimpleSwapCurve bumpedYieldCurve = swapCurve.withAllRatesBumped(0.0001);

        double floatingNpv = npv(toQuantLibLeg(floatingLeg(swapCurve)), swapCurve, floatingDayCounter);
        double floatingNpvBumped = npv(toQuantLibLeg(floatingLeg(bumpedYieldCurve)), bumpedYieldCurve, floatingDayCounter);

        double fixedNpv = npv(toQuantLibLeg(fixedLeg(fixedRate)), swapCurve, fixedDayCounter);
        double fixedNpvPlus = npv(toQuantLibLeg(fixedLeg(fixedRate + 0.0001)), swapCurve, fixedDayCounter);
        double fixedNpvBumped = npv(toQuantLibLeg(fixedLeg(fixedRate)), bumpedYieldCurve, fixedDayCounter);

        double fixedCouponNpv = npv(fixedLeg, swapCurve, fixedDayCounter);
        double floatingCouponNpv = npv(toQuantLibLeg(floatingCoupons(swapCurve)), swapCurve, fixedDayCounter);

        double floatingDv01 = floatingNpv - floatingNpvBumped;
        double fixedDv01 = (fixedNpv - fixedNpvBumped);
        double netNpv = floatingNpv + fixedNpv;
        double netDv01 = floatingDv01 + fixedDv01;

        double fixedBondDuration = fixedBondDuration();

        double pv01 = abs(fixedNpvPlus - fixedNpv);
        return new SwapResult(
                floatingNpv,
                floatingDv01,
                fixedNpv,
                fixedDv01,
                fixedCouponNpv,
                floatingCouponNpv,
                netNpv,
                netDv01,
                pv01,
                fixedBondDuration,
                fixedAccruedAmount(),
                floatingAccruedAmount(),
                new ArrayList<>());
    }

    public Map<Tenor, Double> keyRates() {
        Map<Tenor, Double> result = Maps.newTreeMap();
        Date maxDate = swapCurve.underlyingCurve.maxDate();
        for (final Tenor keyTenor : dv01Tenors()) {
            Date forwardDate = swapSchedule.effectiveDate.add(keyTenor.qlPeriod());
            if (DateUtils.lessOrEqual(forwardDate, maxDate)) {
                result.put(keyTenor, keyRateDv01(keyTenor));
            }
        }
        return result;
    }

    public double fairRate() {
        double floatingCouponNpv = npv(toQuantLibLeg(floatingCoupons(swapCurve)), swapCurve, floatingDayCounter);
        return fairFixedRate(floatingCouponNpv);
    }

    public double keyRateDv01(Tenor keyTenor) {
        SimpleSwapCurve pointBumped = swapCurve.withOnePointBumped(keyTenor, 1E-4);
        double bumpedFloatingNpv = npv(toQuantLibLeg(floatingLeg(pointBumped)), pointBumped, floatingDayCounter);
        double bumpedFixedNpv = npv(fixedLeg, pointBumped, fixedDayCounter);

        return calc().netNpv - (bumpedFixedNpv + bumpedFloatingNpv);
    }

    private double fixedBondDuration() {
        return fixedLegSign() * fixedLeg2Bond().fromYield(fixedRate).modifiedDuration;
    }

    private AnalyticCouponBond fixedLeg2Bond() {
        Compounding compounding =  (Once == swapSchedule.settlementFrequency) ? Simple : Compounded;
        return new AnalyticCouponBond(
                swapSchedule.effectiveDate,
                swapSchedule.maturityDate,
                new Date(),
                100.0,
                fixedRate,
                fixedRate,
                swapSchedule.settlementFrequency,
                swapSchedule.effectiveDate,
                swapSchedule.calendar,
                BusinessDayConvention.ModifiedFollowing,
                BusinessDayConvention.ModifiedFollowing,
                swapCurve.fixedDayCounter,
                swapCurve.fixedDayCounter,
                compounding,
                new TreeMap<>(),
                BondRedemption.NoRedemption,
                0,
                false);
    }

    private List<Tenor> dv01Tenors() {
        int index = 0;

        List<Tenor> allTenors = swapCurve.allTenors();
        for (int i = 0; i < allTenors.size(); i++) {
            Tenor tenor = allTenors.get(i);
            final Period keyPeriod = tenor.qlPeriod();
            if (DateUtils.equalOrAfter(swapSchedule.effectiveDate.add(keyPeriod), swapSchedule.maturityDate)) {
                index = i;
                break;
            }
        }
        return allTenors.subList(Math.max(0, index - 3), Math.min(index + 4, allTenors.size()));
    }

    private double fairFixedRate(final double floatingCouponNpv) {
        double onePercent = 0.01;
        return abs(floatingCouponNpv) / abs(npv(toQuantLibLeg(fixedLegCoupons(onePercent)), swapCurve, fixedDayCounter)) * onePercent;
    }

    private double npv(Leg leg, SimpleSwapCurve curve, DayCounter dayCounter) {
        return CashFlows.npv(leg, curve.underlyingCurve, 0, dayCounter, Simple, NoFrequency, false, curve.qlSpotDate);
    }

    public List<CashFlow> fixedLeg(double rate) {
        List<CashFlow> result = fixedLegCoupons(rate);
        result.add(new SimpleCashFlow(fixedLegSign() * notional, swapSchedule.maturityDate));
        return result;
    }

    private double fixedAccruedAmount() {
        for (SimplePeriod settlementPeriod : swapSchedule.settlementPeriods()) {
            if (settlementPeriod.contains(swapCurve.qlSpotDate)) {
                return fixedLegSign() * notional * fixedDayCounter.yearFraction(settlementPeriod.begin, swapCurve.qlSpotDate) * fixedRate;
            }
        }
        return 0;
    }

    private double floatingAccruedAmount() {
        for (SimplePeriod settlementPeriod : swapSchedule.settlementPeriods()) {
            if (settlementPeriod.contains(swapCurve.qlSpotDate)) {
                SimplePeriod virtualAccrualPeriod = new SimplePeriod(settlementPeriod.begin, swapCurve.qlSpotDate);
                return floatingCashFlowOfPeriod(virtualAccrualPeriod, swapCurve).amount();
            }
        }
        return 0;
    }

    public List<CashFlow> fixedLegCoupons(double rate) {
        List<CashFlow> result = Lists.newArrayList();
        List<SimplePeriod> settlementPeriods = swapSchedule.settlementPeriods();
        for (SimplePeriod settlementPeriod : settlementPeriods) {
            double time = fixedDayCounter.yearFraction(settlementPeriod.begin, settlementPeriod.end);
            result.add(new SimpleCashFlow(fixedLegSign() * notional * time * rate, settlementPeriod.end));
        }
        return result;
    }

    public List<CashFlow> floatingLeg(SimpleSwapCurve curve) {
        List<CashFlow> result = floatingCoupons(curve);
        result.add(new SimpleCashFlow(floatingLegSign() * notional, swapSchedule.maturityDate));
        return result;
    }

    public List<CashFlow> floatingCoupons(SimpleSwapCurve curve) {
        List<CashFlow> result = Lists.newArrayList();
        List<SimplePeriod> settlementPeriods = swapSchedule.settlementPeriods();
        for (SimplePeriod settlementPeriod : settlementPeriods) {
            CashFlow cf = floatingCashFlowOfPeriod(settlementPeriod, curve);
            result.add(cf);
        }
        return result;
    }

    public List<SwapFixing> fixings(SimplePeriod settlementPeriod, SimpleSwapCurve curve, FixingMethod fixingMethod) {
        List<SwapFixing> result = Lists.newArrayList();
        for (SimplePeriod fixingPeriod : swapSchedule.fixingPeriods(settlementPeriod)) {
            CashFlow cashFlow = fixingRateOf(fixingPeriod, curve, fixingMethod);
            SwapFixing swapFixing = new SwapFixing(fixingPeriod.begin, fixingPeriod.end, cashFlow.date(), cashFlow.amount());
            result.add(swapFixing);
        }
        return result;
    }

    private CashFlow floatingCashFlowOfPeriod(SimplePeriod settlementPeriod, SimpleSwapCurve curve) {
        double compoundingFactor = floatingCompoundingFactor(settlementPeriod, curve);
        return new SimpleCashFlow(notional * floatingLegSign() * (compoundingFactor - 1), settlementPeriod.end);
    }

    private double floatingCompoundingFactor(SimplePeriod settlementPeriod, SimpleSwapCurve curve) {
        double compoundingFactor = 1.0;
        List<SwapFixing> resets = fixings(settlementPeriod, curve, Adjusted);
        for (SwapFixing reset : resets) {
            double yearFraction = floatingDayCounter.yearFraction(reset.begin, reset.end);
            compoundingFactor *= (1 + yearFraction * reset.rate);
        }
        return compoundingFactor;
    }

    private CashFlow fixingRateOf(SimplePeriod period, SimpleSwapCurve curve, FixingMethod fixingMethod) {
        Date qlFixingDate = swapSchedule.fixingDateOf(period.begin);

        if (DateUtils.isBefore(qlFixingDate, curve.qlSpotDate)) {
            Optional<IndexRate> indexOptional = indexSource.rateOf(qlFixingDate);
            if (indexOptional.isPresent()) {
                return new SimpleCashFlow(indexOptional.get().value, qlFixingDate);
            } else {
                throw new RuntimeException("could not get index rate of " + qlFixingDate);
            }
        }
        double rate;
        if (Adjusted.equals(fixingMethod)) {
            rate = curve.forwardRate(period);
        } else if (Raw.equals(fixingMethod)) {
            Date forwardBegin = swapSchedule.calendar.advance(qlFixingDate, swapSchedule.fixingDays, TimeUnit.Days, BusinessDayConvention.Following, false);
            Date forwardEndCandidate = swapSchedule.calendar.advance(forwardBegin, new Period(swapSchedule.resetFrequency), BusinessDayConvention.ModifiedFollowing, true);
            Date forwardEnd = forwardBegin.equals(period.begin) ? DateUtils.min(forwardEndCandidate, period.end) : forwardEndCandidate;
            rate = curve.forwardRate(new SimplePeriod(forwardBegin, forwardEnd));
        } else {
            throw new IllegalStateException("should not hit here");
        }
        return new SimpleCashFlow(rate, qlFixingDate);
    }

    private int floatingLegSign() {
        return (type == Receiver ? -1 : 1);
    }

    private int fixedLegSign() {
        return -1 * floatingLegSign();
    }

    private Leg toQuantLibLeg(List<CashFlow> cashFlows) {
        Leg result = new Leg();
        for (CashFlow cashFlow : cashFlows) {
            result.add(cashFlow);
        }
        return result;
    }

}
