package jssp.analytic.bond.domain.db;

import com.google.common.base.Optional;
import jssp.analytic.bond.domain.BondBasic;
import jssp.analytic.common.Translator;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class BondBasicTranslator implements Translator<BondEntity, BondBasic> {

    @Override
    public BondBasic translate(BondEntity entity) {
        return new BondBasic(
                entity.getShortName(),
                entity.getFullName(),
                entity.getBondType(),
                entity.getBondSubtype(),
                entity.getExternalType(),
                entity.getIssuerRatingCurrent(),
                entity.getIssuerRatingCurrent(),
                parseDate(entity.getInterestStartDate()),
                parseDate(entity.getMaturityDate()),
                firstCouponDate(entity.getFirstCouponDate()),
                issuePrice(entity));
    }

    private Optional<LocalDate> firstCouponDate(Integer firstCouponDate) {
        if(firstCouponDate == null){
            return Optional.absent();
        }
        return Optional.of(parseDate(firstCouponDate));
    }


    private LocalDate parseDate(Integer dateInteger) {
        if (dateInteger == null) {
            throw new IllegalArgumentException("could not parse bond with empty InterestStartDate or MaturityDate ");
        }
        return new LocalDate(dateInteger / 10000, (dateInteger % 10000) / 100, dateInteger % 100);
    }

    private double issuePrice(BondEntity bondEntity) {
        BigDecimal issuePrice = bondEntity.getIssuePrice();

        final double price;
        if (issuePrice != null) {
            price = issuePrice.doubleValue();
        } else {
            price = 100;
        }
        return price;
    }

}
