package jssp.analytic.bondfuture.domain;

import jssp.analytic.bondfuture.domain.db.TfFundamentalEntity;
import jssp.analytic.common.Translator;
import jssp.analytic.utils.DateUtils;
import org.joda.time.LocalDate;

public class BondFutureTranslator implements Translator<TfFundamentalEntity, SimpleBondFutureInfo> {

    public SimpleBondFutureInfo translate(TfFundamentalEntity entity) {
        String id = entity.getTfId();
        LocalDate deliveryDate = DateUtils.parseDate(entity.getDeliveryDate());
        return new SimpleBondFutureInfo(id, entity.getTfKey(), deliveryDate);
    }

}
