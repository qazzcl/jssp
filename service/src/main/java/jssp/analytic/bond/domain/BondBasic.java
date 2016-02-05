package jssp.analytic.bond.domain;

import com.google.common.base.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;
import org.quantlib.Date;

import static jssp.analytic.utils.DateUtils.toQlDate;

public class BondBasic {
    public final String shortName;
    public final String fullName;
    public final String type;
    public final String subType;
    public final String externalType;
    public final String rating;
    public final String issuerRating;

    public final LocalDate accrualStartDate;
    public final LocalDate maturityDate;
    public final Optional<LocalDate> firstCouponDate;
    public final double issuePrice;

    public BondBasic(LocalDate accrualStartDate, LocalDate maturityDate, double issuePrice) {
       this(null, null, null, null, null, null, null, accrualStartDate, maturityDate, Optional.<LocalDate>absent(), issuePrice);
    }

    public BondBasic(String shortName, String fullName, String type, String subType, String externalType, String rating, String issuerRating, LocalDate accrualStartDate, LocalDate maturityDate, Optional<LocalDate> firstCouponDate, double issuePrice) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.type = type;
        this.subType = subType;
        this.externalType = externalType;
        this.rating = rating;
        this.issuerRating = issuerRating;
        this.accrualStartDate = accrualStartDate;
        this.maturityDate = maturityDate;
        this.firstCouponDate = firstCouponDate;
        this.issuePrice = issuePrice;
    }

    public Date qlAccrualStartDate() {
        return toQlDate(accrualStartDate);
    }

    public Date qlMaturityDate() {
        return toQlDate(maturityDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("shortName", shortName)
                .append("fullName", fullName)
                .append("type", type)
                .append("subType", subType)
                .append("externalType", externalType)
                .append("rating", rating)
                .append("issuerRating", issuerRating)
                .append("accrualStartDate", accrualStartDate)
                .append("maturityDate", maturityDate)
                .append("issuePrice", issuePrice)
                .toString();
    }
}
