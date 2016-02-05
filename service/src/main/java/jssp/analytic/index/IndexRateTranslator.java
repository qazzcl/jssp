package jssp.analytic.index;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import jssp.analytic.common.Translator;
import jssp.analytic.index.db.MacroBaseIndexValuesEntity;
import jssp.analytic.index.db.RateInfoEntity;
import org.joda.time.LocalDate;

import java.util.Collection;
import java.util.Map;

import static jssp.analytic.utils.DateUtils.toJodaDate;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class IndexRateTranslator implements Translator<MacroBaseIndexValuesEntity, Optional<IndexRate>> {

    private final Map<String, String> gjkIndexIdMap = Maps.newHashMap();

    public IndexRateTranslator(Collection<RateInfoEntity> rateInfoEntities) {
        for (RateInfoEntity rateInfoEntity : rateInfoEntities) {
            if (isNotBlank(rateInfoEntity.getGjkCode()) && isNotBlank(rateInfoEntity.getSourceCode())) {
                gjkIndexIdMap.put(rateInfoEntity.getGjkCode(), rateInfoEntity.getSourceCode());
            }
        }
    }

    @Override
    public Optional<IndexRate> translate(MacroBaseIndexValuesEntity indexEntry) {
        if (!gjkIndexIdMap.containsKey(indexEntry.getGjkCode()) || indexEntry.getIndexValue() == null) {
            return Optional.absent();
        }
        LocalDate indexDate = toJodaDate(indexEntry.getIndexDate());
        String indexId = gjkIndexIdMap.get(indexEntry.getGjkCode());

        return Optional.of(new IndexRate(indexId, indexDate, indexEntry.getIndexValue() / 100.0));
    }
}
