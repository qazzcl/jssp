package jssp.analytic.refdata;

import com.google.common.collect.Maps;
import jssp.analytic.bond.domain.Issuer;
import jssp.analytic.bond.domain.db.BondEntity;
import jssp.analytic.bond.domain.db.InstitutionEntity;
import jssp.analytic.bond.domain.db.IssuerInfoEntity;
import jssp.analytic.utils.ListUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public class IssuerSource implements ReferenceDataSource<String, Issuer> {

    private final Map<String, Issuer> all = Maps.newConcurrentMap();
    private final SessionFactory factory;

    public IssuerSource(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Issuer get(String key) {
        return all.get(key);
    }

    @Override
    public Map<String, Issuer> allData() {
        return all;
    }

    @Override
    public void refresh() {
        all.putAll(bondKeyIssuerMap(issuerMap()));
    }

    private Map<String, Issuer> bondKeyIssuerMap(Map<String, Issuer> issuerMap) {
        Map<String, Issuer> result = Maps.newHashMap();
        for (BondEntity bondEntity : allBonds()) {
            String issuerCode = bondEntity.getIssuerCode();
            if (issuerCode != null) {
                Issuer value = issuerMap.get(issuerCode);
                if (value != null && bondEntity.getBondKey() != null) {
                    result.put(bondEntity.getBondKey(), value);
                }
            }
        }
        return result;
    }

    private Map<String, Issuer> issuerMap() {
        Map<String, IssuerInfoEntity> issuerMap = ListUtils.groupByToSimpleMap(allIssuerInfo(), new Function<IssuerInfoEntity, String>() {
            public String apply(IssuerInfoEntity input) {
                return input.getInstitutionCode();
            }
        });

        Map<String, InstitutionEntity> institutionMap = ListUtils.groupByToSimpleMap(allInstitutionInfo(), new Function<InstitutionEntity, String>() {
            public String apply(InstitutionEntity input) {
                return input.getInstitutionCode();
            }
        });

        Map<String, Issuer> result = Maps.newHashMap();
        for (Map.Entry<String, IssuerInfoEntity> entity : issuerMap.entrySet()) {
            String institutionCode = entity.getKey();
            InstitutionEntity institutionEntity = institutionMap.get(institutionCode);
            if (institutionEntity != null) {
                String institutionType = institutionEntity.getInstitutionSubtype();
                String issuerName = entity.getValue().getIssuerName();
                result.put(institutionCode, new Issuer(institutionCode, issuerName, institutionType));
            }
        }
        return result;
    }

    private List<InstitutionEntity> allInstitutionInfo() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from InstitutionEntity where delflag=0");
            return query.list();
        } finally {
            session.close();
        }
    }

    private List<IssuerInfoEntity> allIssuerInfo() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from IssuerInfoEntity where delflag=0");
            return query.list();
        } finally {
            session.close();
        }
    }

    private List<BondEntity> allBonds() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("from BondEntity where delflag=0");
            return query.list();
        } finally {
            session.close();
        }
    }
}
