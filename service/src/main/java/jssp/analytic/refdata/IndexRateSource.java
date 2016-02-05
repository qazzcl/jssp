package jssp.analytic.refdata;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import jssp.analytic.index.IndexRate;
import jssp.analytic.index.IndexRateTranslator;
import jssp.analytic.index.db.MacroBaseIndexValuesEntity;
import jssp.analytic.index.db.RateInfoEntity;
import jssp.analytic.irs.domain.SwapIndex;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class IndexRateSource implements ReferenceDataSource<String, NavigableMap<LocalDate, IndexRate>> {

    private static final Logger logger = LoggerFactory.getLogger(IndexRateSource.class);
    private final Map<String, NavigableMap<LocalDate, IndexRate>> data = Maps.newConcurrentMap();
    private final SessionFactory factory;
    private final BondInfoSource bondInfoSource;

    public static IndexRateSource dummyIndexRateSource() {
        return new IndexRateSource();
    }

    private IndexRateSource() {
        this(null, null);
    }

    public IndexRateSource(SessionFactory factory, BondInfoSource bondInfoSource) {
        this.factory = factory;
        this.bondInfoSource = bondInfoSource;
    }

    public NavigableMap<LocalDate, IndexRate> get(SwapIndex swapIndex) {
        return get(swapIndex.indexId);
    }

    @Override
    public NavigableMap<LocalDate, IndexRate> get(String index) {
        return data.get(index);
    }

    @Override
    public Map<String, NavigableMap<LocalDate, IndexRate>> allData() {
        return Collections.unmodifiableMap(data);
    }

    @Override
    public void refresh() {
        List<IndexRate> indexRates = Lists.newArrayList();

        Collection<RateInfoEntity> rateInfoEntities = rateInfoEntities();

        IndexRateTranslator indexRateTranslator = new IndexRateTranslator(rateInfoEntities);
        Set<String> gjkCodes = gjkCodes(rateInfoEntities);

        for (MacroBaseIndexValuesEntity entity : macroIndexes(gjkCodes)) {
            try {
                Optional<IndexRate> indexRateOptional = indexRateTranslator.translate(entity);
                if (indexRateOptional.isPresent()) {
                    indexRates.add(indexRateOptional.get());
                }
            } catch (Exception e) {
                logger.info("could not load Index of id : " + entity.getId(), e);
            }
        }
        update(indexRates);
        logger.info("loaded {} index rate ", indexRates.size());
    }

    private Set<String> gjkCodes(Collection<RateInfoEntity> rateInfoEntities) {
        Set<String> gjkCodes = Sets.newHashSet();
        for (RateInfoEntity rateInfoEntity: rateInfoEntities) {
            gjkCodes.add(rateInfoEntity.getSourceCode());
        }
        return gjkCodes;
    }

    public IndexRateSource update(Collection<IndexRate> indexRates) {
        for (IndexRate indexRate : indexRates) {
            if (!data.containsKey(indexRate.indexId)) {
                data.put(indexRate.indexId, new ConcurrentSkipListMap<>());
            }
            NavigableMap<LocalDate, IndexRate> dateMap = data.get(indexRate.indexId);
            dateMap.put(indexRate.date, indexRate);
        }
        return this;
    }

    private Collection<RateInfoEntity> rateInfoEntities() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from RateInfoEntity where delflag=:delflag");
            query.setInteger("delflag", 0);
            return Collections2.filter(query.list(), new Predicate<RateInfoEntity>() {
                public boolean apply(RateInfoEntity rateInfoEntity) {
                    String sourceCode = rateInfoEntity.getSourceCode();
                    return (sourceCode != null) &&
                            (sourceCode.startsWith("FR0") ||
                                    sourceCode.startsWith("SHIBOR") ||
                                    bondInfoSource.frnIndexes().contains(sourceCode));
                }
            });
        } finally {
            session.close();
        }
    }



    private List<MacroBaseIndexValuesEntity> macroIndexes(Set<String> gjkCodes) {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from MacroBaseIndexValuesEntity where indexValue!= null");
            Criteria criteria = session.createCriteria(MacroBaseIndexValuesEntity.class);
            criteria.add(Restrictions.in("gjkCode", gjkCodes));
            return query.list();
        } finally {
            session.close();
        }
    }

}
