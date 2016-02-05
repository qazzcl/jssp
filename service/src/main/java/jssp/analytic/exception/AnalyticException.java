package jssp.analytic.exception;

public class AnalyticException extends  RuntimeException{

    public final String message;

    public AnalyticException(String message) {
        super(message);
        this.message = message;
    }

    public AnalyticException(String message, Throwable clause) {
        super(message, clause);
        this.message = message;
    }
}
