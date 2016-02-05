package jssp.analytic.irs.domain;

import com.google.common.collect.Lists;
import org.quantlib.Actual360;
import org.quantlib.Actual365Fixed;
import org.quantlib.DayCounter;
import org.quantlib.Frequency;

import java.util.List;

import static org.quantlib.Frequency.*;

public enum SwapIndex {

    FR007(1, "FR007", Quarterly, Weekly, new Actual365Fixed()),
    SHIBOR_3M(1, "SHIBOR_3M", Quarterly, Quarterly, new Actual360()),
    SHIBOR_ON(0, "SHIBOR_O/N", Once, Daily, new Actual360()),
    DEPO_1Y(1, "DEPO_1Y", Annual, Annual, new Actual360());


    public final int fixingDays;
    public final String indexId;
    public final Frequency settlementFrequency;
    public final Frequency fixingFrequency;
    public final DayCounter floatingDayCounter;

    SwapIndex(int fixingDays, String indexId, Frequency settlementFrequency, Frequency fixingFrequency, DayCounter floatingDayCounter) {
        this.fixingDays = fixingDays;
        this.indexId = indexId;
        this.settlementFrequency = settlementFrequency;
        this.fixingFrequency = fixingFrequency;
        this.floatingDayCounter = floatingDayCounter;
    }

    @SuppressWarnings("unused")
    public static SwapIndex fromIndexId(String indexId) {
        for (SwapIndex swapIndex : SwapIndex.values()) {
            if (swapIndex.indexId.equals(indexId)) {
                return swapIndex;
            }
        }
        throw new IllegalArgumentException("unknown swap indexId " + indexId);
    }

    public static List<String> indexIds() {
        return Lists.newArrayList(
                FR007.indexId,
                SHIBOR_3M.indexId,
                SHIBOR_ON.indexId,
                DEPO_1Y.indexId
        );
    }
}
