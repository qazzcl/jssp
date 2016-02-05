package jssp.analytic.bond.analytic;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class HoldingPeriodResult {

    // start
    public double startCleanPrice;
    public double startCleanAmount;
    public double startInterest;
    public double startTotal;

    // end
    public double endCleanPrice;
    public double endCleanAmount;
    public double endInterest;
    public double endTotal;

    // between
    public double principalBetween;
    public double interestBetween;

    // pre tax
    public double cleanAmountPnLPreTax;
    public double interestPnLPreTax;
    public double pnLPretax;
    public double holdingPeriodYieldPreTax;

    // tax & cost
    public double businessTax;
    public double interestTax;
    public double totalTax;
    public double holdingCost;

    // after tax
    public double cleanAmountPnLAT;
    public double interestPnLAT;
    public double pnLAT;
    public double holdingPeriodYieldAT;
    public double annualizedHoldingPeriodYieldAT;
    public double netPnL;
    public double netAnnualizedYield;
    public double annualReturnPreCost;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("startCleanPrice", startCleanPrice)
                .append("startCleanAmount", startCleanAmount)
                .append("startInterest", startInterest)
                .append("startTotal", startTotal)
                .append("endCleanPrice", endCleanPrice)
                .append("endCleanAmount", endCleanAmount)
                .append("endInterest", endInterest)
                .append("endTotal", endTotal)
                .append("principalRepaymentBetween", principalBetween)
                .append("interestBetween", interestBetween)
                .append("cleanAmountPnLPreTax", cleanAmountPnLPreTax)
                .append("interestPnLPreTax", interestPnLPreTax)
                .append("pnLPretax", pnLPretax)
                .append("holdingPeriodYieldPreTax", holdingPeriodYieldPreTax)
                .append("businessTax", businessTax)
                .append("interestTax", interestTax)
                .append("totalTax", totalTax)
                .append("holdingCost", holdingCost)
                .append("cleanAmountPnLAT", cleanAmountPnLAT)
                .append("interestPnLAT", interestPnLAT)
                .append("pnLAT", pnLAT)
                .append("holdingPeriodYieldAT", holdingPeriodYieldAT)
                .append("annualizedHoldingPeriodYieldAT", annualizedHoldingPeriodYieldAT)
                .append("netPnL", netPnL)
                .append("netAnnualizedYield", netAnnualizedYield)
                .append("annualReturnPreCost", annualReturnPreCost)
                .toString();
    }
}
