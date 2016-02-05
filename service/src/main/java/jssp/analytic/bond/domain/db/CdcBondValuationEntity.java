package jssp.analytic.bond.domain.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cdc_bond_valuation", schema = "")
public class CdcBondValuationEntity {
    private String id;
    private String bondKey;
    private String bondId;
    private Integer valuateDate;
    private String listedMarket;
    private BigDecimal valIntradayDirtyPrice;
    private BigDecimal valIntradayAccruedInterest;
    private BigDecimal valCleanPrice;
    private BigDecimal valYield;
    private BigDecimal valModifiedDuration;
    private BigDecimal valConvexity;
    private BigDecimal remainingParValue;
    private BigDecimal remainingYear;
    private BigDecimal relativeLiquidityCoefficient;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Bond_Key", nullable = true, insertable = true, updatable = true, length = 25)
    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    @Basic
    @Column(name = "Bond_ID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId;
    }

    @Basic
    @Column(name = "Valuate_Date", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getValuateDate() {
        return valuateDate;
    }

    public void setValuateDate(Integer valuateDate) {
        this.valuateDate = valuateDate;
    }

    @Basic
    @Column(name = "Listed_Market", nullable = true, insertable = true, updatable = true, length = 3)
    public String getListedMarket() {
        return listedMarket;
    }

    public void setListedMarket(String listedMarket) {
        this.listedMarket = listedMarket;
    }

    @Basic
    @Column(name = "Val_Intraday_Dirty_Price", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getValIntradayDirtyPrice() {
        return valIntradayDirtyPrice;
    }

    public void setValIntradayDirtyPrice(BigDecimal valIntradayDirtyPrice) {
        this.valIntradayDirtyPrice = valIntradayDirtyPrice;
    }

    @Basic
    @Column(name = "Val_Intraday_Accrued_Interest", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getValIntradayAccruedInterest() {
        return valIntradayAccruedInterest;
    }

    public void setValIntradayAccruedInterest(BigDecimal valIntradayAccruedInterest) {
        this.valIntradayAccruedInterest = valIntradayAccruedInterest;
    }

    @Basic
    @Column(name = "Val_Clean_Price", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getValCleanPrice() {
        return valCleanPrice;
    }

    public void setValCleanPrice(BigDecimal valCleanPrice) {
        this.valCleanPrice = valCleanPrice;
    }

    @Basic
    @Column(name = "Val_Yield", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getValYield() {
        return valYield;
    }

    public void setValYield(BigDecimal valYield) {
        this.valYield = valYield;
    }

    @Basic
    @Column(name = "Val_Modified_Duration", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getValModifiedDuration() {
        return valModifiedDuration;
    }

    public void setValModifiedDuration(BigDecimal valModifiedDuration) {
        this.valModifiedDuration = valModifiedDuration;
    }

    @Basic
    @Column(name = "Val_Convexity", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getValConvexity() {
        return valConvexity;
    }

    public void setValConvexity(BigDecimal valConvexity) {
        this.valConvexity = valConvexity;
    }

    @Basic
    @Column(name = "Remaining_Par_Value", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getRemainingParValue() {
        return remainingParValue;
    }

    public void setRemainingParValue(BigDecimal remainingParValue) {
        this.remainingParValue = remainingParValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdcBondValuationEntity that = (CdcBondValuationEntity) o;

        if (bondId != null ? !bondId.equals(that.bondId) : that.bondId != null) return false;
        if (bondKey != null ? !bondKey.equals(that.bondKey) : that.bondKey != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (listedMarket != null ? !listedMarket.equals(that.listedMarket) : that.listedMarket != null) return false;
        if (remainingParValue != null ? !remainingParValue.equals(that.remainingParValue) : that.remainingParValue != null) return false;
        if (valCleanPrice != null ? !valCleanPrice.equals(that.valCleanPrice) : that.valCleanPrice != null) return false;
        if (valConvexity != null ? !valConvexity.equals(that.valConvexity) : that.valConvexity != null) return false;
        if (valIntradayAccruedInterest != null ? !valIntradayAccruedInterest.equals(that.valIntradayAccruedInterest) : that.valIntradayAccruedInterest != null) return false;
        if (valIntradayDirtyPrice != null ? !valIntradayDirtyPrice.equals(that.valIntradayDirtyPrice) : that.valIntradayDirtyPrice != null) return false;
        if (valModifiedDuration != null ? !valModifiedDuration.equals(that.valModifiedDuration) : that.valModifiedDuration != null) return false;
        if (valYield != null ? !valYield.equals(that.valYield) : that.valYield != null) return false;
        if (valuateDate != null ? !valuateDate.equals(that.valuateDate) : that.valuateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bondKey != null ? bondKey.hashCode() : 0);
        result = 31 * result + (bondId != null ? bondId.hashCode() : 0);
        result = 31 * result + (valuateDate != null ? valuateDate.hashCode() : 0);
        result = 31 * result + (listedMarket != null ? listedMarket.hashCode() : 0);
        result = 31 * result + (valIntradayDirtyPrice != null ? valIntradayDirtyPrice.hashCode() : 0);
        result = 31 * result + (valIntradayAccruedInterest != null ? valIntradayAccruedInterest.hashCode() : 0);
        result = 31 * result + (valCleanPrice != null ? valCleanPrice.hashCode() : 0);
        result = 31 * result + (valYield != null ? valYield.hashCode() : 0);
        result = 31 * result + (valModifiedDuration != null ? valModifiedDuration.hashCode() : 0);
        result = 31 * result + (valConvexity != null ? valConvexity.hashCode() : 0);
        result = 31 * result + (remainingParValue != null ? remainingParValue.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "Remaining_Year", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getRemainingYear() {
        return remainingYear;
    }

    public void setRemainingYear(BigDecimal remainingYear) {
        this.remainingYear = remainingYear;
    }

    @Basic
    @Column(name = "Relative_Liquidity_Coefficient", nullable = true, insertable = true, updatable = true, precision = 4)
    public BigDecimal getRelativeLiquidityCoefficient() {
        return relativeLiquidityCoefficient;
    }

    public void setRelativeLiquidityCoefficient(BigDecimal relativeLiquidityCoefficient) {
        this.relativeLiquidityCoefficient = relativeLiquidityCoefficient;
    }
}
