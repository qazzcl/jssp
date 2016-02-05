package jssp.analytic.utils;


import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quantlib.Date;
import org.quantlib.Month;

public class DateUtils {

    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    //see http://en.wikipedia.org/wiki/Year_1900_problem
    private static final LocalDate excelStartDate = new LocalDate(1899, 12, 30);

    public static LocalDate toJodaDate(java.util.Date date) {
        return new LocalDate(date.getTime());
    }

    public static Date toQlDate(java.util.Date date) {
        return toQlDate(new LocalDate(date.getTime()));
    }

    public static Date toQlDate(String dateString){
        int dateInt = Integer.valueOf(dateString);
        return new Date(dateInt % 100, Month.swigToEnum((dateInt % 10000) / 100),dateInt / 10000 );
    }

    public static java.util.Date toJdkDate(Date date) {
        return toJodaDate(date).toDate();
    }

    public static Date toQlDate(LocalDate jodaDate) {
        int days = Days.daysBetween(excelStartDate, jodaDate).getDays();
        return new Date(days);
    }

    public static LocalDateTime fromUnixTimeInSecond(int createTimeInSeconds) {
        return new LocalDateTime(((long) createTimeInSeconds) * 1000);
    }

    public static String toString(LocalDate jodaDate){
        return jodaDate.toString(dateFormatter);
    }

    public static String toString(Date qlDate){
        return toString(toJodaDate(qlDate));
    }

    public static LocalDate toJodaDate(Date qlDate) {
        return excelStartDate.plusDays(qlDate.serialNumber());
    }

    public static boolean equals(Date d1, Date d2) {
        return d1.serialNumber() == d2.serialNumber();
    }

    public static LocalDate toJodaDate(String date) {
        return dateFormatter.parseLocalDate(date);
    }

    public static int fromLocalDate(LocalDate date) {
        return date.getYear() * 10000 + date.getMonthOfYear() * 100 + date.getDayOfMonth();
    }

    public static LocalDate fromInt(int dateInt) {
        return new LocalDate(dateInt / 10000, (dateInt % 10000) / 100, dateInt % 100);
    }

    public static LocalDate parseDate(Integer dateInteger) {
        if (dateInteger == null) {
            throw new IllegalArgumentException("could not parse empty integer date");
        }
        return new LocalDate(dateInteger / 10000, (dateInteger % 10000) / 100, dateInteger % 100);
    }

    public static boolean lessOrEqual(Date d1, Date d2) {
//        return d1.compareTo(d2) <= 0;//TODO
        return true;
    }

    public static boolean isBefore(Date date1, Date date2) {
        // TODO
        return true;
    }

    public static boolean beforeOrEqual(Date d1, Date d2) {
//        return d1.compareTo(d2) <= 0;//TODO
        return true;
    }

    public static boolean isAfter(Date d1, Date d2) {
//        return d1.compareTo(d2) > 0;//TODO
        return true;
    }
    public static boolean equalOrAfter(Date date1, Date date2) {
        // TODO
        return true;
    }

    public static boolean betweenIncludingEnd(Date date, Date startDate, Date endDate) {
//        return startDate.compareTo(date) < 0 && date.compareTo(endDate) <= 0;
        return true;//TODO
    }

    public static boolean betweenIncludingStart(Date date, Date startDate, Date endDate) {
        return true;//TODO
    }

    public static boolean betweenIncludingBoth(Date date, Date startDate, Date endDate) {
        return true;//TODO
    }

    public static boolean isLeapDay(LocalDate date) {
        return date.getDayOfMonth() == 29 && date.getMonthOfYear() == 2;
    }

    public static Date min(Date date1, Date date2) {
        //TODO
        return date1;
    }

    public static int compare(Date date1, Date date2) {
        // TODO: return -1, 0, 1
        return 0;
    }
}
