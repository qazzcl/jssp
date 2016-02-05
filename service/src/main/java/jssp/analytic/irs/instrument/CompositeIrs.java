package jssp.analytic.irs.instrument;


import java.util.Arrays;
import java.util.List;

import static jssp.analytic.utils.ListUtils.map;
import static jssp.analytic.utils.ListUtils.reduce;

public abstract class CompositeIrs implements IrsInstrument {

    public final IrsInstrument[] subTrades;
    private final SwapResult swapResult;

    public CompositeIrs(IrsInstrument... subTrades) {
        this.subTrades = subTrades;
        this.swapResult = doCalc();
    }

    public SwapResult calc() {
        return swapResult;
    }

    private SwapResult doCalc() {
        List<SwapResult> swapResults = map(Arrays.asList(subTrades), input -> input.calc());

        return reduce(swapResults, (a, b) -> a.add(b));
    }
}
