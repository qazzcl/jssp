package jssp.analytic.bondfuture.analytic;

import jssp.analytic.bond.analytic.AnalyticCouponBond;
import jssp.analytic.bond.domain.BondCashflow;
import jssp.analytic.utils.DateUtils;
import jssp.analytic.utils.ListUtils;
import jssp.analytic.utils.NumberUtils;
import org.quantlib.Date;
import org.quantlib.Frequency;

import java.util.List;

public class ConvertFactorCalculator {
    public final AnalyticCouponBond analyticBond;
    public final Date deliverDate;

    public ConvertFactorCalculator(AnalyticCouponBond analyticBond, Date deliverDate) {
        this.analyticBond = analyticBond;
        this.deliverDate = deliverDate;
    }

    /**
     * http://www.cffex.com.cn/tzgg/jysgg/201309/t20130903_17158.html
     * http://www.cffex.com.cn/sspz/10tf/10nqzhyzhyjlxjs/
     */
    public double convertFactor() {
        double r = 0.03;
        double x = monthsToNextCoupon();
        double n = remainingCouponCount();
        double c = analyticBond.forwardCouponRate;
        double f = frequencyOfYear();

        double result = 1 / Math.pow(1 + r / f, x * f / 12) * (c / f + c / r + (1 - c / r) * 1 / Math.pow(1 + r / f, n - 1)) - c / f * (1 - x * f / 12);
        return NumberUtils.roundToDigits(result, 4);
    }

    private int remainingCouponCount() {
        List<BondCashflow> cashflow = analyticBond.cashflow();
        return ListUtils.filter(cashflow, cf -> DateUtils.isAfter(cf.date, deliverDate)).size();
    }

    private int monthsToNextCoupon() {
        List<BondCashflow> cashflow = analyticBond.cashflow();
        BondCashflow next = ListUtils.filter(cashflow, cf -> DateUtils.isAfter(cf.date, deliverDate)).iterator().next();
        int monthDiff = DateUtils.toJodaDate(next.date).getMonthOfYear() - DateUtils.toJodaDate(deliverDate).getMonthOfYear();
        if (monthDiff < 0) {
            return monthDiff + 12;
        }
        return monthDiff;
    }

    private int frequencyOfYear() {
        Frequency couponFrequency = analyticBond.couponFrequency;
        if (couponFrequency == Frequency.Annual || couponFrequency == Frequency.Semiannual || couponFrequency == Frequency.Quarterly ||couponFrequency == Frequency.Monthly) {
            return couponFrequency.swigValue();
        }
        throw new IllegalStateException("unknown couponFrequency: " + couponFrequency);
    }
}
