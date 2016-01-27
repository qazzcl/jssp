package jssp.server.service;

public interface CalculationService<Request, Response> {
    Response calc(Request request);
}
