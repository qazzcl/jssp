package jssp.analytic.funding;

import org.joda.time.LocalDate;

public class FundingResult {

    public final LocalDate startDate;
    public final LocalDate endDate;
    public final int days;
    public final double indexRate;
    public final double effectiveRate;
    public final double interest;
    public final double totalAmount;

    public FundingResult(LocalDate startDate, LocalDate endDate, int days, double indexRate, double effectiveRate, double interest, double totalAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.indexRate = indexRate;
        this.effectiveRate = effectiveRate;
        this.interest = interest;
        this.totalAmount = totalAmount;
    }
}
