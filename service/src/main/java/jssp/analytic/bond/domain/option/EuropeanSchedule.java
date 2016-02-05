package jssp.analytic.bond.domain.option;

import com.google.common.base.Optional;
import org.joda.time.LocalDate;

import java.util.Map;
import java.util.NavigableMap;

public class EuropeanSchedule implements OptionSchedule {

    private final NavigableMap<LocalDate, Double> schedules;

    public EuropeanSchedule(NavigableMap<LocalDate, Double> schedules) {
        this.schedules = schedules;
    }

    @Override
    public Optional<Execution> getClosestExecution(LocalDate settlementDate) {
        Map.Entry<LocalDate, Double> higherEntry = schedules.higherEntry(settlementDate);
        if (higherEntry != null) {
            return Optional.of(new Execution(higherEntry.getKey(), higherEntry.getValue()));
        }
        return Optional.absent();
    }
}
