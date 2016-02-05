package jssp.analytic.common.domain;

import jssp.analytic.exception.AnalyticException;

public enum TermUnit {
    ON(-2), TN(-1), Day(1), Week(7), Month(30), Year(360);

    public final int days;

    TermUnit(int days) {
        this.days = days;
    }

    public static TermUnit termOf(String termName) {
        switch (termName.toLowerCase()) {
            case "on":
                return ON;
            case "tn":
                return TN;
            case "d":
                return Day;
            case "w":
                return Week;
            case "m":
                return Month;
            case "y":
                return Year;
            default:
                throw new AnalyticException("Wrong term unit: " + termName);
        }
    }
}
