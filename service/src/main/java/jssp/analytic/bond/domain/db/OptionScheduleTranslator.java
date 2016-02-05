package jssp.analytic.bond.domain.db;

import com.google.common.collect.Lists;
import jssp.analytic.bond.domain.BondBasic;
import jssp.analytic.bond.domain.CouponInfo;
import jssp.analytic.bond.domain.option.AmericanSchedule;
import jssp.analytic.bond.domain.option.EuropeanSchedule;
import jssp.analytic.bond.domain.option.OptionSchedule;
import jssp.analytic.common.Translator;
import jssp.analytic.refdata.MarketCalendar;
import jssp.analytic.utils.DateUtils;
import jssp.analytic.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.quantlib.*;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jssp.analytic.utils.DateUtils.toQlDate;
import static jssp.analytic.utils.DateUtils.toJodaDate;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class OptionScheduleTranslator implements Translator<BondEntity, OptionSchedule> {

    private static final String PATTERN_DATE = "\\d{8}";
    private static final String PATTERN_DOUBLE = "\\d+\\.?\\d*?";
    private static final Pattern patternAME = Pattern.compile(String.format("^(%s)\\|(%s)\\|(%s)$", PATTERN_DATE, PATTERN_DATE, PATTERN_DOUBLE));
    private static final Pattern patternEUR = Pattern.compile(String.format("(%s)\\|(%s)", PATTERN_DATE, PATTERN_DOUBLE));
    private static final Pattern patternASSTerm = Pattern.compile("\\|(\\d*\\.?\\d+)");
    private static final Pattern patternASSStrike = Pattern.compile(String.format("^(\\d+)\\|.*$"));
    private final Translator<BondEntity, BondBasic> bondBasicTranslator;
    private final Translator<BondEntity, CouponInfo> couponInfoTranslator;
    private final MarketCalendar marketCalendar;
    private final CallPut callPut;

    public OptionScheduleTranslator(Translator<BondEntity, BondBasic> bondBasicTranslator, Translator<BondEntity, CouponInfo> couponInfoTranslator, MarketCalendar MarketCalendar, CallPut callPut) {
        this.bondBasicTranslator = bondBasicTranslator;
        this.couponInfoTranslator = couponInfoTranslator;
        this.marketCalendar = MarketCalendar;
        this.callPut = callPut;
    }

    public OptionScheduleTranslator withCallPut(CallPut newCallPut) {
        return new OptionScheduleTranslator(bondBasicTranslator, couponInfoTranslator, marketCalendar, newCallPut);
    }

    @Override
    public OptionSchedule translate(BondEntity bondEntity) {
        String callStr = bondEntity.getCallStr();
        String putStr = bondEntity.getPutStr();
        String assignTransStr = bondEntity.getAssignTransStr();

        BondBasic bondBasic = bondBasicTranslator.translate(bondEntity);
        CouponInfo couponInfo = couponInfoTranslator.translate(bondEntity);

        String type = bondEntity.getOptionType();
        OptionType optionType = StringUtils.isEmpty(type) ? OptionType.NON : OptionType.valueOf(type);

        String style = bondEntity.getOptionStyle();
        if ("EUR".equalsIgnoreCase(style)) {
            NavigableMap<LocalDate, Double> schedules = new TreeMap<>();
            if(callPut == CallPut.Call) {
                if (OptionType.CAL == optionType || OptionType.CNP == optionType) {
                    parseEUROption(callStr, schedules);
                }
            } else {
                parseEurPut(putStr, assignTransStr, bondBasic, couponInfo, optionType, schedules);
            }
            if(!schedules.isEmpty()) {
                return new EuropeanSchedule(schedules);
            }
        }
        if ("AME".equalsIgnoreCase(style)) {
            if (callPut == CallPut.Call) {
                if (OptionType.CAL == optionType || OptionType.CNP == optionType) {
                    return parseAMEOption(callStr);
                }
            } else {
                if (OptionType.PUT == optionType || OptionType.ASS == optionType) {
                    return parseAMEOption(putStr);
                }
            }
        }
        return OptionSchedule.EmptyOptionSchedule;
    }

    private void parseEurPut(String putStr, String assignTransStr, BondBasic bondBasic, CouponInfo couponInfo, OptionType optionType, NavigableMap<LocalDate, Double> schedules) {
        if (OptionType.PUT == optionType || OptionType.CNP == optionType) {
            parseEUROption(putStr, schedules);
        }
        if (OptionType.ASS == optionType) {
            if (!isBlank(assignTransStr)) {
                parseASSOption(assignTransStr, schedules, bondBasic, couponInfo, marketCalendar);
            }
        }
    }

    protected void parseASSOption(String assignTransStr, NavigableMap<LocalDate, Double> schedules, BondBasic bondBasic, CouponInfo couponInfo, MarketCalendar marketCalendar) {
        List<Double> terms = parseTerms(assignTransStr);
        Double strike = parseStrike(assignTransStr);
        boolean half = isHalf(terms);

        Double[] termArray = terms.toArray(new Double[terms.size()]);
        Frequency freq = couponInfo.couponFrequency.frequency;
        if (half) {
            for (int i = 0; i < terms.size(); i++) {
                termArray[i] = terms.get(i) * 2;
            }
            if (freq == Frequency.Annual) {
                freq = Frequency.Semiannual;
            } else if (freq == Frequency.Semiannual) {
                freq = Frequency.Quarterly;
            }
        }

        Schedule schedule = new Schedule(toQlDate(bondBasic.accrualStartDate),
                toQlDate(bondBasic.maturityDate),
                new Period(freq),
                marketCalendar.qlCalendar(),
                BusinessDayConvention.Unadjusted,
                BusinessDayConvention.Unadjusted,
                DateGeneration.Rule.Backward,
                false);


        for (Double term : termArray) {
            int idx = term.intValue();
            Date date = schedule.date(idx);
            schedules.put(toJodaDate(date), strike);
        }
    }

    private boolean isHalf(List<Double> terms) {
        for (Double term : terms) {
            if (NumberUtils.doubleEquals(term - Math.floor(term), 0.5)) {
                return true;
            }
        }
        return false;
    }

    private List<Double> parseTerms(String assignTransStr) {
        List<Double> result = Lists.newArrayList();
        Matcher matcher = patternASSTerm.matcher(assignTransStr);
        while (matcher.find()) {
            result.add(Double.valueOf(matcher.group(1)));
        }
        return result;
    }

    private Double parseStrike(String assignTransStr) {
        Matcher strikeMatcher = patternASSStrike.matcher(assignTransStr);
        if (strikeMatcher.matches()) {
            return Double.valueOf(strikeMatcher.group(1));
        } else {
            throw new IllegalArgumentException("Invalid AssignTransStr");
        }
    }

    protected OptionSchedule parseAMEOption(String optionStr) {
        OptionSchedule optionSchedule = null;
        if (!isBlank(optionStr)) {
            Matcher matcher = patternAME.matcher(optionStr);
            if (matcher.matches()) {
                optionSchedule = new AmericanSchedule(DateUtils.toJodaDate(matcher.group(1)), Double.valueOf(matcher.group(3)));
            }
        }
        return optionSchedule;
    }

    protected void parseEUROption(String optionStr, NavigableMap<LocalDate, Double> schedules) {
        if (!isBlank(optionStr)) {
            Matcher matcher = patternEUR.matcher(optionStr);   //"20150331|100|20160331|100"
            while (matcher.find()) {
                String date = matcher.group(1);
                Double strike = Double.valueOf(matcher.group(2));
                LocalDate localDate = DateUtils.toJodaDate(date);
                schedules.put(localDate, strike);
            }
        }
    }

    public enum CallPut {Call, Put}


    private enum OptionType {CAL, PUT, CNP, ASS, NON, DCN, CNV, ETS}

}
