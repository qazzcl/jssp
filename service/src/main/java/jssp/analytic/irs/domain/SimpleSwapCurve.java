package jssp.analytic.irs.domain;


import com.google.common.collect.Lists;
import jssp.analytic.common.domain.Tenor;
import jssp.analytic.utils.DateUtils;
import org.joda.time.LocalDate;
import org.quantlib.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.quantlib.BusinessDayConvention.Following;
import static org.quantlib.BusinessDayConvention.ModifiedFollowing;
import static org.quantlib.BusinessDayConvention.Unadjusted;
import static org.quantlib.Compounding.Simple;
import static org.quantlib.Frequency.NoFrequency;

public class SimpleSwapCurve {

    public enum InterpolationType {
        Linear, LinearSmooth, FlatForward, SmoothForward, ConvexMonotone
    }

    public final InterpolationType interpolationType;
    public final Date curveDate;
    public final Period indexTenor;

    public final int swapFixingDays;
    public final Calendar calendar;
    public final Frequency cashExchangeFrequency;
    public final DayCounter fixedDayCounter;
    public final DayCounter floatingDayCounter;
    public final LocalDate spotDate;

    public final Date qlSpotDate;
    public final List<MarketRate> indexRates;

    public final List<MarketRate> swapRates;
    public final YieldTermStructure underlyingCurve;

    public SimpleSwapCurve(InterpolationType interpolationType, Date curveDate, Period indexTenor, int swapFixingDays, Frequency cashExchangeFrequency, DayCounter fixedDayCounter, DayCounter floatingDayCounter, Calendar calendar, List<MarketRate> indexRates, List<MarketRate> swapRates) {
        this.interpolationType = interpolationType;
        this.curveDate = curveDate;
        this.indexTenor = indexTenor;
        this.swapFixingDays = swapFixingDays;
        this.cashExchangeFrequency = cashExchangeFrequency;
        this.fixedDayCounter = fixedDayCounter;
        this.floatingDayCounter = floatingDayCounter;
        this.calendar = calendar;
        this.indexRates = indexRates;
        this.swapRates = swapRates;

        qlSpotDate = calendar.advance(curveDate, swapFixingDays, TimeUnit.Days, Following, true);
        spotDate = DateUtils.toJodaDate(qlSpotDate);

        Settings.instance().setEvaluationDate(curveDate);
        underlyingCurve = createCurve();
    }

    public SimpleSwapCurve withAllRatesBumped(double bumped) {
        List<MarketRate> bumpedIndexRates = bumpMarketRates(indexRates, bumped);
        List<MarketRate> bumpedSwapRates = bumpMarketRates(swapRates, bumped);
        return new SimpleSwapCurve(interpolationType, curveDate, indexTenor, swapFixingDays, cashExchangeFrequency, fixedDayCounter, floatingDayCounter, calendar, bumpedIndexRates, bumpedSwapRates);
    }

    public SimpleSwapCurve withOnePointBumped(Tenor tenor, double bumped) {
        List<MarketRate> bumpedIndexRates = newArrayList();
        for (MarketRate indexRate : indexRates) {
            if (tenor.equals(indexRate.tenor)) {
                bumpedIndexRates.add(indexRate.withBump(bumped));
            } else {
                bumpedIndexRates.add(indexRate);
            }
        }
        List<MarketRate> bumpedSwapRates = newArrayList();
        for (MarketRate swapRate : swapRates) {
            if (tenor.equals(swapRate.tenor)) {
                bumpedSwapRates.add(swapRate.withBump(bumped));
            } else {
                bumpedSwapRates.add(swapRate);
            }
        }
        return new SimpleSwapCurve(interpolationType, curveDate, indexTenor, swapFixingDays, cashExchangeFrequency, fixedDayCounter, floatingDayCounter, calendar, bumpedIndexRates, bumpedSwapRates);
    }

    public List<Tenor> allTenors() {
        List<Tenor> result = Lists.newArrayList();
        for (MarketRate rate : indexRates) {
            result.add(rate.tenor);
        }
        for (MarketRate rate : swapRates) {
            result.add(rate.tenor);
        }
        return result;
    }

    private YieldTermStructure createCurve() {
        RateHelperVector rates = new RateHelperVector();
        for (MarketRate indexRate : indexRates) {
            RateHelper rateHelper = new DepositRateHelper(indexRate.rate, indexRate.period, indexRate.fixingDays, calendar, Unadjusted, false, indexRate.dayCounter);
            rates.add(rateHelper);
        }
        for (MarketRate rate : swapRates) {
            rates.add(swapRate(rate.rate, rate.period));
        }

        switch (interpolationType) {
            case Linear:
                return new PiecewiseLinearZero(qlSpotDate, rates, fixedDayCounter);
            case LinearSmooth:
                return new PiecewiseCubicZero(qlSpotDate, rates, fixedDayCounter);
            case FlatForward:
                return new PiecewiseFlatForward(qlSpotDate, rates, fixedDayCounter);
            case SmoothForward:
                return new PiecewiseLogCubicDiscount(qlSpotDate, rates, fixedDayCounter);
            case ConvexMonotone:
                return new PiecewiseLinearForward(qlSpotDate, rates, fixedDayCounter);
            default:
                throw new IllegalStateException("should not hit here");
        }
    }

    public double forwardRate(SimplePeriod period) {
        return underlyingCurve.forwardRate(period.begin, period.end, floatingDayCounter, Simple, NoFrequency).rate();
    }

    private List<MarketRate> bumpMarketRates(List<MarketRate> source, double bumped) {
        List<MarketRate> bumpedSwapRates = newArrayList();
        for (MarketRate swapRate : source) {
            bumpedSwapRates.add(swapRate.withBump(bumped));
        }
        return bumpedSwapRates;
    }

    private RateHelper swapRate(double rate, Period period) {
        return bondHelper(rate, period);
    }

    private RateHelper bondHelper(double rate, Period period) {
        QuoteHandle cleanPriceHandler = new QuoteHandle(new SimpleQuote(100.0));
        Date endDate = calendar.advance(qlSpotDate, period, ModifiedFollowing);
        Schedule schedule = new Schedule(qlSpotDate, endDate, new Period(cashExchangeFrequency), calendar, ModifiedFollowing, ModifiedFollowing, DateGeneration.Rule.Forward, false);
        DoubleVector coupons = new DoubleVector();
        coupons.add(rate);
        return new FixedRateBondHelper(cleanPriceHandler, swapFixingDays, 100.0, schedule, coupons, fixedDayCounter, ModifiedFollowing, 100);
    }

}