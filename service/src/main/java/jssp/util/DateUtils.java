package jssp.util;


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
/*

    public static boolean lessOrEqual(Date d1, Date d2) {
        return d1.compareTo(d2) <= 0;
    }

*/

    public static boolean isLeapDay(LocalDate date) {
        return date.getDayOfMonth() == 29 && date.getMonthOfYear() == 2;
    }
}
