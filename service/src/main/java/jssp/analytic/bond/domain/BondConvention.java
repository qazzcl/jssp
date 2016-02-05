package jssp.analytic.bond.domain;

import org.quantlib.DayCounter;

public class BondConvention {

    public final DayCounter accrInterestDayCounter;

    public BondConvention(DayCounter accrInterestDayCounter) {
        this.accrInterestDayCounter = accrInterestDayCounter;
    }
}
