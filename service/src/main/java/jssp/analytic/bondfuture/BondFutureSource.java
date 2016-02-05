package jssp.analytic.bondfuture;

import com.google.common.collect.Maps;
import jssp.analytic.bond.domain.BondInfo;
import jssp.analytic.bondfuture.domain.BondFutureInfo;
import jssp.analytic.bondfuture.domain.BondFutureTranslator;
import jssp.analytic.bondfuture.domain.DeliverableBondInfo;
import jssp.analytic.bondfuture.domain.SimpleBondFutureInfo;
import jssp.analytic.bondfuture.domain.db.TfConversionFactorEntity;
import jssp.analytic.bondfuture.domain.db.TfFundamentalEntity;
import jssp.analytic.refdata.BondInfoSource;
import jssp.analytic.refdata.ReferenceDataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("unused")
public class BondFutureSource implements ReferenceDataSource<String, BondFutureInfo> {

    private volatile Map<String, BondFutureInfo> bondFutureMap = Maps.newHashMap();

    private final BondInfoSource bondSource;
    private final SessionFactory sessionFactory;

    public BondFutureSource(SessionFactory sessionFactory, BondInfoSource bondSource) {
        this.bondSource = bondSource;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BondFutureInfo get(String futureId) {
        return bondFutureMap.get(futureId);
    }

    @Override
    public Map<String, BondFutureInfo> allData() {
        return Collections.unmodifiableMap(bondFutureMap);
    }

    @Override
    public void refresh() {
        Map<String, Map<String, Double>> tfConversionFactorMap = allTFConversionFactors();
        Map<String, SimpleBondFutureInfo> simpleBondFutureMap = allSimpleBondFuture();
        bondFutureMap = allBondFuture(simpleBondFutureMap, tfConversionFactorMap, bondSource.allData());
    }

    private Map<String, BondFutureInfo> allBondFuture(Map<String, SimpleBondFutureInfo> simpleBondFutureMap, Map<String, Map<String, Double>> tfConversionFactorMap, Map<String, BondInfo> bondMap) {
        TreeMap<String, BondFutureInfo> result = new TreeMap<>();
        for (SimpleBondFutureInfo simpleBondFutureInfo : simpleBondFutureMap.values()) {
            Map<String, Double> deliverableBondMap = tfConversionFactorMap.get(simpleBondFutureInfo.tfKey);
            List<DeliverableBondInfo> deliverableBondInfos = new ArrayList<>();
            for (Map.Entry<String, Double> entry : deliverableBondMap.entrySet()) {
                BondInfo bondInfo = bondMap.get(entry.getKey());
                deliverableBondInfos.add(new DeliverableBondInfo(bondInfo, entry.getValue()));
            }
            BondFutureInfo bondFutureInfo = new BondFutureInfo(simpleBondFutureInfo, deliverableBondInfos);
            result.put(simpleBondFutureInfo.tfId, bondFutureInfo);
        }
        return result;
    }

    private Map<String, Map<String, Double>> allTFConversionFactors() {
        Map<String, Map<String, Double>> result = Maps.newHashMap();
        for (TfConversionFactorEntity entity : tfConversionFactors()) {
            if (!result.containsKey(entity.getTfKey())) {
                result.put(entity.getTfKey(), new HashMap<>());
            }
            result.get(entity.getTfKey()).put(entity.getBondKey(), entity.getConversionFactor().doubleValue());
        }
        return result;
    }

    private Map<String, SimpleBondFutureInfo> allSimpleBondFuture() {
        Map<String, SimpleBondFutureInfo> result = Maps.newHashMap();
        BondFutureTranslator bondFutureTranslator = new BondFutureTranslator();
        for (TfFundamentalEntity bondFutureEntity : bondFutures()) {
            SimpleBondFutureInfo simpleBondFutureInfo = bondFutureTranslator.translate(bondFutureEntity);
            result.put(simpleBondFutureInfo.tfId, simpleBondFutureInfo);
        }
        return result;
    }

    private List<TfConversionFactorEntity> tfConversionFactors() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from TfConversionFactorEntity");
            return query.list();
        } finally {
            session.close();
        }
    }

    private List<TfFundamentalEntity> bondFutures() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from TfFundamentalEntity");
            return query.list();
        } finally {
            session.close();
        }
    }

}
