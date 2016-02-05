package jssp.analytic.fxoption;

public class PriceResult {
    public final double premium;
    public final double delta;
    public final double fwdDelta;
    public final double domesticDelta;
    public final double gamma;
    public final double vega;
    public final double theta;
    public final double rho;

    public PriceResult(double premium, double delta, double domesticDelta, double fwdDelta, double gamma, double vega, double theta, double rho) {
        this.premium = premium;
        this.delta = delta;
        this.fwdDelta = fwdDelta;
        this.domesticDelta = domesticDelta;
        this.gamma = gamma;
        this.vega = vega;
        this.theta = theta;
        this.rho = rho;
    }

    @Override
    public String toString() {
        return "PriceResult{" +
                "premium=" + premium +
                ", delta=" + delta +
                ", fwdDelta=" + fwdDelta +
                ", domesticDelta=" + domesticDelta +
                ", gamma=" + gamma +
                ", vega=" + vega +
                ", theta=" + theta +
                ", rho=" + rho +
                '}';
    }
}
