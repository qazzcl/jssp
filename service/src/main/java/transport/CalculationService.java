package transport;

public interface CalculationService<Request, Response> {
    Response calc(Request request);
}
