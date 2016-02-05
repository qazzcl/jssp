package jssp.analytic.refdata;

import com.google.common.collect.Maps;
import jssp.analytic.bond.domain.BondInfo;
import jssp.analytic.bond.domain.db.BondListInfoEntity;
import jssp.analytic.exception.AnalyticException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class BondIdMarketSource implements ReferenceDataSource<String, BondInfo> {

    private static final Logger logger = LoggerFactory.getLogger(BondIdMarketSource.class);

    private final Map<String, BondInfo> all = Maps.newConcurrentMap();
    private final SessionFactory factory;
    private final BondInfoSource bondInfoSource;

    public static BondIdMarketSource dummySource() {
        return new BondIdMarketSource(null, null);
    }

    public BondIdMarketSource(SessionFactory factory, BondInfoSource bondInfoSource) {
        this.factory = factory;
        this.bondInfoSource = bondInfoSource;
    }

    @Override
    public BondInfo get(String bondIdAndMarket) {
        return all.get(bondIdAndMarket);
    }

    @Override
    public Map<String, BondInfo> allData() {
        return all;
    }

    @Override
    public void refresh() {
        update(bondIdMarketToBondKeyMap());
    }

    public void update(Map<String, BondInfo> data) {
        all.putAll(data);
    }

    private Map<String, BondInfo> bondIdMarketToBondKeyMap() {
        Map<String, BondInfo> result = Maps.newHashMap();
        for (BondListInfoEntity bondListInfoEntity : allBondListInfo()) {
            try {
                String bondIdAndMarket = bondIdAndMarket(bondListInfoEntity);
                String bondKey = bondListInfoEntity.getBondKey();

                if (bondKey != null) {
                    result.put(bondIdAndMarket, bondInfoSource.get(bondKey));
                }
            } catch (AnalyticException e) {
                //ignore
            }
        }
        return result;
    }

    private String bondIdAndMarket(BondListInfoEntity bondListInfoEntity) {
        String bondId = bondListInfoEntity.getBondId();
        if (bondId.contains(".")) { // already has market info
            return bondId;
        }
        String market = marketOf(bondListInfoEntity.getListedMarket());
        return bondId + "." + market;
    }

    private String marketOf(String listedMarket) {
        if(listedMarket == null) {
            throw new AnalyticException("null listedMarket");
        }

        switch (listedMarket) {
            case "CIB":
                return "IB";
            case "SSE":
                return "SH";
            case "SZE":
                return "SZ";
            default:
                throw new IllegalArgumentException("unexpected listedMarket: " + listedMarket);
        }
    }

    private List<BondListInfoEntity> allBondListInfo() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from BondListInfoEntity where delflag=:delflag and bondId is not null");
            query.setInteger("delflag", 0);
            return query.list();
        } finally {
            session.close();
        }
    }
}
