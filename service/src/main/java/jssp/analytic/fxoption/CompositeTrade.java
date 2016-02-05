package jssp.analytic.fxoption;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class CompositeTrade implements OptionTrade {

    protected final OptionTrade[] legs;

    public CompositeTrade(OptionTrade... legs) {
        this.legs = legs;
    }

    @Override
    public OptionResult price() {
        List<OptionResult> resultList = Lists.newArrayList();
        for (OptionTrade leg : legs) {
            resultList.add(leg.price());
        }
        return combine(resultList);
    }

    private OptionResult combine(List<OptionResult> childResults) {
        double premiumAmount = 0;
        double premiumAmountInCcy1 = 0;
        double spotDelta = 0;
        double forwardDelta = 0;
        double vega = 0;
        double gamma = 0;
        double decay = 0;
        double rho = 0;
        double phi = 0;

        for (OptionResult singleResult : childResults) {
            double absoluteAmount = Math.abs(singleResult.amount);
            premiumAmount += singleResult.premiumAmount;
            premiumAmountInCcy1 += singleResult.premiumAmountInCcy1;
            spotDelta += singleResult.spotDelta * absoluteAmount;
            forwardDelta += singleResult.forwardDelta * absoluteAmount;
            vega += singleResult.vega * absoluteAmount;
            gamma += singleResult.gamma * absoluteAmount;
            decay += singleResult.decay * absoluteAmount;
            rho += singleResult.rho * absoluteAmount;
            phi += singleResult.phi * absoluteAmount;
        }
        double overallAmount = amount();
        return new OptionResult(
                "buy",
                "unknown",
                overallAmount,
                premiumAmount / overallAmount,
                premiumAmount,
                premiumAmountInCcy1 / overallAmount,
                premiumAmountInCcy1,
                spotDelta / overallAmount,
                forwardDelta / overallAmount,
                vega / overallAmount,
                gamma / overallAmount,
                decay / overallAmount,
                rho / overallAmount,
                phi / overallAmount,
                childResults);
    }
}
