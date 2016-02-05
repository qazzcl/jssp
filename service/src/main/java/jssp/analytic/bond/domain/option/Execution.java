package jssp.analytic.bond.domain.option;

import org.joda.time.LocalDate;

public class Execution {
    public final LocalDate date;
    public final double strike;

    public Execution(LocalDate date, double strike) {
        this.date = date;
        this.strike = strike;
    }
}
