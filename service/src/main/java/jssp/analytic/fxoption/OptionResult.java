package jssp.analytic.fxoption;


import java.util.List;

public class OptionResult {
    public String buySell;
    public String callPut;
    public double amount;
    public double pips;
    public double premiumAmount;
    public double pipsInCcy1;
    public double premiumAmountInCcy1;
    public double spotDelta;
    public double forwardDelta;
    public double vega;
    public double gamma;
    public double decay;
    public double rho;
    public double phi;

    public List<OptionResult> legResults;

    public OptionResult() {

    }

    public OptionResult(String buySell, String callPut, double amount, double pips, double premiumAmount, double pipsInCcy1, double premiumAmountInCcy1, double spotDelta, double forwardDelta, double vega, double gamma, double decay, double rho, double phi, List<OptionResult> legResults) {
        this.buySell = buySell;
        this.callPut = callPut;
        this.amount = amount;
        this.pips = pips;
        this.premiumAmount = premiumAmount;
        this.pipsInCcy1 = pipsInCcy1;
        this.premiumAmountInCcy1 = premiumAmountInCcy1;
        this.spotDelta = spotDelta;
        this.forwardDelta = forwardDelta;
        this.vega = vega;
        this.gamma = gamma;
        this.decay = decay;
        this.rho = rho;
        this.phi = phi;
        this.legResults = legResults;
    }

    public String getBuySell() {
        return buySell;
    }

    public void setBuySell(String buySell) {
        this.buySell = buySell;
    }

    public String getCallPut() {
        return callPut;
    }

    public void setCallPut(String callPut) {
        this.callPut = callPut;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPips() {
        return pips;
    }

    public void setPips(double pips) {
        this.pips = pips;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public double getPipsInCcy1() {
        return pipsInCcy1;
    }

    public void setPipsInCcy1(double pipsInCcy1) {
        this.pipsInCcy1 = pipsInCcy1;
    }

    public double getPremiumAmountInCcy1() {
        return premiumAmountInCcy1;
    }

    public void setPremiumAmountInCcy1(double premiumAmountInCcy1) {
        this.premiumAmountInCcy1 = premiumAmountInCcy1;
    }

    public double getSpotDelta() {
        return spotDelta;
    }

    public void setSpotDelta(double spotDelta) {
        this.spotDelta = spotDelta;
    }

    public double getForwardDelta() {
        return forwardDelta;
    }

    public void setForwardDelta(double forwardDelta) {
        this.forwardDelta = forwardDelta;
    }

    public double getVega() {
        return vega;
    }

    public void setVega(double vega) {
        this.vega = vega;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getDecay() {
        return decay;
    }

    public void setDecay(double decay) {
        this.decay = decay;
    }

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public List<OptionResult> getLegResults() {
        return legResults;
    }

    public void setLegResults(List<OptionResult> legResults) {
        this.legResults = legResults;
    }
}
