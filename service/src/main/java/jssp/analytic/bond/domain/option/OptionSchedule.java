package jssp.analytic.bond.domain.option;

import com.google.common.base.Optional;
import org.joda.time.LocalDate;

@FunctionalInterface
public interface OptionSchedule {

    OptionSchedule EmptyOptionSchedule = settlementDate -> Optional.absent();

    Optional<Execution> getClosestExecution(LocalDate settlementDate);

}
