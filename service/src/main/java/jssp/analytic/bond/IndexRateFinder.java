package jssp.analytic.bond;

import com.google.common.base.Optional;
import jssp.analytic.bond.domain.CouponInfo;
import jssp.analytic.index.IndexRate;
import jssp.analytic.refdata.IndexRateSource;
import jssp.analytic.refdata.MarketCalendar;
import jssp.analytic.utils.DateUtils;
import jssp.analytic.utils.NumberUtils;
import jssp.analytic.utils.ComparableUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.quantlib.Calendar;
import org.quantlib.Date;
import org.quantlib.TimeUnit;

import java.util.NavigableMap;

import static jssp.analytic.utils.DateUtils.toQlDate;
import static jssp.analytic.utils.DateUtils.toJodaDate;
import static org.quantlib.BusinessDayConvention.Preceding;

public class IndexRateFinder {

    private final IndexRateSource indexRateSource;
    private final MarketCalendar marketCalendar;
    private final DateProvider dateProvider;

    public IndexRateFinder(IndexRateSource indexRateSource, MarketCalendar marketCalendar) {
        this(indexRateSource, marketCalendar, () -> LocalDate.now());
    }

    public IndexRateFinder(IndexRateSource indexRateSource, MarketCalendar MarketCalendar, DateProvider dateProvider) {
        this.indexRateSource = indexRateSource;
        this.marketCalendar = MarketCalendar;
        this.dateProvider = dateProvider;
    }

    public Optional<IndexRate> currentReferenceRate(LocalDate lastCouponDate, CouponInfo couponInfo) {
        if(StringUtils.isBlank(couponInfo.frnIndexId)) {
            return Optional.absent();
        }
        LocalDate referenceDate = ComparableUtils.min(lastCouponDate, dateProvider.today());

        Date fixingDate = fixingDateOf(toQlDate(referenceDate), couponInfo.precedingDays);
        return getReferenceRate(couponInfo.frnIndexId, couponInfo.movingAverageDays, couponInfo.fixingDigits, fixingDate);
    }

    public Optional<IndexRate> predictRefRate(LocalDate settlementDate, CouponInfo couponInfo) {
        if(StringUtils.isBlank(couponInfo.frnIndexId)) {
            return Optional.absent();
        }

        LocalDate referenceDate = ComparableUtils.min(settlementDate, dateProvider.today());
        Date fixingDate = fixingDateOf(toQlDate(referenceDate), couponInfo.precedingDays);
        return getReferenceRate(couponInfo.frnIndexId, couponInfo.movingAverageDays, couponInfo.fixingDigits, fixingDate);
    }

    private Optional<IndexRate> getReferenceRate(String indexId, int fixingDays, int fixingDigits, Date referenceDate) {
        if (indexId == null || indexRateSource.get(indexId) == null) {
            return Optional.absent();
        }
        Optional<Double> prevailingRate = Optional.absent();
        for (int i = 0; i < 30; i++) {
            prevailingRate = tryGetAverageIndex(indexId, referenceDate, fixingDays, fixingDigits);
            if (prevailingRate.isPresent()) {
                break;
            }
            referenceDate = referenceDate.subtract(1);
        }
        if (!prevailingRate.isPresent()) {
            return Optional.absent();
        }
        IndexRate referenceRate = new IndexRate("", toJodaDate(referenceDate), prevailingRate.get());
        return Optional.of(referenceRate);
    }

    private Optional<Double> tryGetAverageIndex(String indexId, Date referenceDate, int fixingDays, int fixingDigits) {
        double total = 0;
        int count = 0;
        Date valueDate = referenceDate;
        for (int i = 0; i < fixingDays; i++) {
            Optional<IndexRate> indexRateOptional = fromIndexIdAndDate(indexId, DateUtils.toJodaDate(valueDate));
            if (indexRateOptional.isPresent()) {
                count++;
                total += indexRateOptional.get().value;
                valueDate = qlCalendar().advance(valueDate, -1, TimeUnit.Days, Preceding);
            } else {
                return Optional.absent();
            }
        }
        double rounded = NumberUtils.roundToDigits(total / count, fixingDigits + 2);
        return Optional.of(rounded);
    }

    private Optional<IndexRate> fromIndexIdAndDate(String indexId, LocalDate localDate) {
        NavigableMap<LocalDate, IndexRate> indexes = indexRateSource.get(indexId);
        if (indexes != null) {
            IndexRate indexRate = indexes.get(localDate);
            if (indexRate != null) {
                return Optional.of(indexRate);
            }
        }
        return Optional.absent();
    }

    private Date fixingDateOf(Date qlSettlementDate, int precedingDays) {
        return qlCalendar().advance(qlSettlementDate, -1 * precedingDays, TimeUnit.Days, Preceding);
    }

    private Calendar qlCalendar() {
        return marketCalendar.qlCalendar();
    }

}
