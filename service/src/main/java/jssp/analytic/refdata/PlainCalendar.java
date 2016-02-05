package jssp.analytic.refdata;

import org.joda.time.LocalDate;

import java.util.Collection;

public interface PlainCalendar {

    void update(Collection<LocalDate> holidays);

    LocalDate nextWorkDay(LocalDate date);

    boolean isHoliday(LocalDate date);

    boolean isWorkday(LocalDate date);

}
