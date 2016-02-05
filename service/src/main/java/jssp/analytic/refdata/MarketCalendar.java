package jssp.analytic.refdata;

import com.google.common.collect.Sets;
import jssp.analytic.common.domain.db.HolidayInfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.quantlib.Calendar;
import org.quantlib.China;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import static jssp.analytic.utils.DateUtils.toJodaDate;
import static jssp.analytic.utils.DateUtils.toQlDate;

public class MarketCalendar implements PlainCalendar, Refreshable {

    private static final Logger logger = LoggerFactory.getLogger(MarketCalendar.class);
    private Calendar quantLibCalendar = new China();
    private final SessionFactory factory;
    private final String currency;
    private final String market;

    public static MarketCalendar dummyCalendar() {
        return new MarketCalendar();
    }

    private MarketCalendar() {
        this(null, "CNY", "CIB");
    }

    public MarketCalendar(SessionFactory factory, String currency, String market) {
        this.factory = factory;
        this.currency = currency;
        this.market = market;
    }

    public void update(Collection<LocalDate> holidays) {
        quantLibCalendar = calendarFrom(holidays);
    }

    @Override
    public LocalDate nextWorkDay(LocalDate date) {
        LocalDate candidate = date.plusDays(1);
        while (true) {
            if (isWorkday(candidate)) {
                return candidate;
            }
            candidate = candidate.plusDays(1);
        }
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        return quantLibCalendar.isHoliday(toQlDate(date));
    }

    @Override
    public boolean isWorkday(LocalDate date) {
        return quantLibCalendar.isBusinessDay(toQlDate(date));
    }

    public Calendar qlCalendar() {
        return quantLibCalendar;
    }

    public void refresh() {
        TreeSet<LocalDate> holidaySet = Sets.newTreeSet();
        for (HolidayInfoEntity entity : holidays()) {
            holidaySet.add(toJodaDate(entity.getHolidayDate()));
        }
        TreeSet<LocalDate> holidaysOnWeekday = Sets.newTreeSet();
        for (LocalDate holiday : holidaySet) {
            if (!isWeekEnd(holiday)) {
                holidaysOnWeekday.add(holiday);
            }
        }
        update(holidaysOnWeekday);
        logger.info("added holiday " + holidaysOnWeekday);
    }

    private China calendarFrom(Collection<LocalDate> holidays) {
        China cal1 = new China();
        for (LocalDate holiday : holidays) {
            cal1.addHoliday(toQlDate(holiday));
        }
        return cal1;
    }

    private boolean isWeekEnd(LocalDate iteratorDate) {
        return iteratorDate.getDayOfWeek() > 5;
    }

    private List<HolidayInfoEntity> holidays() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from HolidayInfoEntity where delflag=:delflag and country=:country and marketType=:marketType");
            query.setString("delflag", "0");
            query.setString("country", currency);
            query.setString("marketType", market);
            return query.list();
        } finally {
            session.close();
        }
    }
}
