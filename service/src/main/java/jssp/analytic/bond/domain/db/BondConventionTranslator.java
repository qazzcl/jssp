package jssp.analytic.bond.domain.db;

import com.google.common.collect.Maps;
import jssp.analytic.bond.domain.BondConvention;
import jssp.analytic.common.Translator;
import org.quantlib.*;

import java.util.Map;

public class BondConventionTranslator implements Translator<BondEntity, BondConvention> {

    private static Map<String, DayCounter> dayCounterMap = Maps.newHashMap();

//    private static final DayCounter modifiedAFB = new ModifiedActualAFB(); //TODO

    static {
//        dayCounterMap.put("BAA", modifiedAFB);//TODO
        dayCounterMap.put("BAA", null);
        dayCounterMap.put("BA5", new ActualActual(ActualActual.Convention.ISDA));
        dayCounterMap.put("BAF", new Actual365Fixed());
        dayCounterMap.put("BA0", new Actual360());
        dayCounterMap.put("B30", new Thirty360());
    }

    @Override
    public BondConvention translate(BondEntity entry) {
        return new BondConvention(dayCounter(entry.getInterestBasis()));
    }

    private DayCounter dayCounter(String interestBasis) {
        DayCounter dayCounter = dayCounterMap.get(interestBasis);
        if(dayCounter != null) {
            return dayCounter;
        }
//        return modifiedAFB;// TODO
        return null;
    }

}
