package jssp.analytic.refdata;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import jssp.analytic.bond.domain.BondInfo;
import jssp.analytic.bond.domain.db.BondBasicTranslator;
import jssp.analytic.bond.domain.db.BondEntity;
import jssp.analytic.bond.domain.db.BondTranslator;
import jssp.analytic.exception.AnalyticException;
import jssp.analytic.irs.domain.SwapIndex;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BondInfoSource implements ReferenceDataSource<String, BondInfo> {

    private static final Logger logger = LoggerFactory.getLogger(BondInfoSource.class);

    private final Map<String, BondInfo> data = Maps.newConcurrentMap();
    private final Map<String, BondEntity> rawMap = Maps.newConcurrentMap();
    private final Map<String, String> bondIdKeyMap = Maps.newConcurrentMap();
    private final Set<String> frnIndexes = Sets.newConcurrentHashSet(SwapIndex.indexIds());
    private final SessionFactory factory;
    private final BondTranslator bondTranslator;

    public BondInfoSource(SessionFactory factory, MarketCalendar bondCalendar) {
        this.factory = factory;
        bondTranslator = BondTranslator.bondTranslatorOf(new BondBasicTranslator(), bondCalendar);
    }

    @Override
    public BondInfo get(String bondKey) {
        BondInfo bondInfo = data.get(bondKey);
        if (bondInfo == null) {
            throw new AnalyticException("Problematic bond, missing basic info");
        }
        return bondInfo;
    }

    @SuppressWarnings("unused")
    public BondInfo getWithCouponOverridden(String bondKey, double newCouponRate) {
        try {
            return bondTranslator.translate(withCouponRate(bondKey, newCouponRate));
        } catch (Exception e) {
            throw new AnalyticException("Problematic bond, missing basic info", e);
        }
    }

    @SuppressWarnings("unused")
    public BondInfo getWithIssuePriceOverridden(String bondKey, double newIssuePrice) {
        try {
            return bondTranslator.translate(withIssuePrice(bondKey, newIssuePrice));
        } catch (Exception e) {
            throw new AnalyticException("Problematic bond, missing basic info", e);
        }
    }

    private BondEntity withIssuePrice(String bondKey, double newIssuePrice) {
        BondEntity copy = bondEntityCopyOf(bondKey);
        copy.setIssuePrice(new BigDecimal(newIssuePrice));
        return copy;
    }

    private BondEntity withCouponRate(String bondKey, double newCouponRate) {
        BondEntity copy = bondEntityCopyOf(bondKey);
        copy.setCouponRateSpread(new BigDecimal(newCouponRate * 100));
        return copy;
    }

    private BondEntity bondEntityCopyOf(String bondKey) {
        BondEntity entity = rawMap.get(bondKey);
        return entity.clone();
    }

    @Override
    public Map<String, BondInfo> allData() {
        return Collections.unmodifiableMap(data);
    }

    @SuppressWarnings("unused")
    public Map<String, String> idKeyMap() {
        return Collections.unmodifiableMap(bondIdKeyMap);
    }

    @SuppressWarnings("unused")
    public Map<String, BondEntity> rawMap() {
        return Collections.unmodifiableMap(rawMap);
    }

    public Set<String> frnIndexes(){
        return Collections.unmodifiableSet(frnIndexes);
    }

    @Override
    public void refresh() {
        logger.info("loading all bonds ... ");
        update(allBonds());
        logger.info("loading all bonds done.");
    }

    public void update(List<BondInfo> bondInfos) {
        for (BondInfo bondInfo : bondInfos) {
            data.put(bondInfo.bondKey, bondInfo);
        }
    }

    private List<BondInfo> allBonds() {
        List<BondInfo> bondInfos = Lists.newArrayList();
        for (BondEntity bondEntity : bonds()) {
            try {
                if (bondEntity.getBondKey() != null) {
                    rawMap.put(bondEntity.getBondKey(), bondEntity);
                }
                BondInfo bondInfo = bondTranslator.translate(bondEntity);
                bondInfos.add(bondInfo);
                if(bondInfo.bondId != null) {
                    bondIdKeyMap.put(bondInfo.bondId, bondInfo.bondKey);
                }
                String frnIndex = bondInfo.couponInfo.frnIndexId;
                if(frnIndex != null) {
                    frnIndexes.add(frnIndex);
                }
            } catch (Exception e) {
                logger.info("could not load bond of id : {}, reason: {}", bondEntity.getBondId(), e.getMessage());
            }
        }
        return bondInfos;
    }

    private List<BondEntity> bonds() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from BondEntity where delflag=:delflag and maturityDate is not null and bondKey is not null");
            query.setInteger("delflag", 0);
            return query.list();
        } finally {
            session.close();
        }
    }
}
