package jssp.analytic.bond.domain.option;

import com.google.common.base.Optional;
import org.joda.time.LocalDate;

public class AmericanSchedule implements OptionSchedule {

    private final LocalDate startDate;
    private final double strike;

    public AmericanSchedule(LocalDate startDate, double strike) {
        this.startDate = startDate;
        this.strike = strike;
    }

    @Override
    public Optional<Execution> getClosestExecution(LocalDate settlementDate) {
        if (settlementDate.isBefore(startDate)) {
            return Optional.of(new Execution(startDate, strike));
        }
        return Optional.absent();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public double getStrike() {
        return strike;
    }

    @Override
    public String toString() {
        return "AmericanSchedule{" +
                "startDate=" + startDate +
                ", strike=" + strike +
                '}';
    }
}
