package jssp.analytic.bond.domain.db;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "csi_bond_valuation", schema = "")
public class CsiBondValuationEntity {
    private String id;
    private String delflag;
    private Timestamp createDate;
    private String bondKey;
    private Integer date;
    private String shhCode;
    private String shzCode;
    private String interBankCode;
    private BigDecimal calculationPrice;
    private BigDecimal yieldToMaturity;
    private BigDecimal modifiedDuration;
    private BigDecimal convexity;
    private BigDecimal cleanPrice;
    private BigDecimal accruedInterest;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "delflag")
    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "Bond_Key")
    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    @Basic
    @Column(name = "Date")
    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Basic
    @Column(name = "SHH_Code")
    public String getShhCode() {
        return shhCode;
    }

    public void setShhCode(String shhCode) {
        this.shhCode = shhCode;
    }

    @Basic
    @Column(name = "SHZ_Code")
    public String getShzCode() {
        return shzCode;
    }

    public void setShzCode(String shzCode) {
        this.shzCode = shzCode;
    }

    @Basic
    @Column(name = "InterBank_Code")
    public String getInterBankCode() {
        return interBankCode;
    }

    public void setInterBankCode(String interBankCode) {
        this.interBankCode = interBankCode;
    }

    @Basic
    @Column(name = "Calculation_Price")
    public BigDecimal getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(BigDecimal calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @Basic
    @Column(name = "Yield_To_Maturity")
    public BigDecimal getYieldToMaturity() {
        return yieldToMaturity;
    }

    public void setYieldToMaturity(BigDecimal yieldToMaturity) {
        this.yieldToMaturity = yieldToMaturity;
    }

    @Basic
    @Column(name = "Modified_Duration")
    public BigDecimal getModifiedDuration() {
        return modifiedDuration;
    }

    public void setModifiedDuration(BigDecimal modifiedDuration) {
        this.modifiedDuration = modifiedDuration;
    }

    @Basic
    @Column(name = "Convexity")
    public BigDecimal getConvexity() {
        return convexity;
    }

    public void setConvexity(BigDecimal convexity) {
        this.convexity = convexity;
    }

    @Basic
    @Column(name = "Clean_Price")
    public BigDecimal getCleanPrice() {
        return cleanPrice;
    }

    public void setCleanPrice(BigDecimal cleanPrice) {
        this.cleanPrice = cleanPrice;
    }

    @Basic
    @Column(name = "Accrued_Interest")
    public BigDecimal getAccruedInterest() {
        return accruedInterest;
    }

    public void setAccruedInterest(BigDecimal accruedInterest) {
        this.accruedInterest = accruedInterest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CsiBondValuationEntity that = (CsiBondValuationEntity) o;

        if (accruedInterest != null ? !accruedInterest.equals(that.accruedInterest) : that.accruedInterest != null) return false;
        if (bondKey != null ? !bondKey.equals(that.bondKey) : that.bondKey != null) return false;
        if (calculationPrice != null ? !calculationPrice.equals(that.calculationPrice) : that.calculationPrice != null) return false;
        if (cleanPrice != null ? !cleanPrice.equals(that.cleanPrice) : that.cleanPrice != null) return false;
        if (convexity != null ? !convexity.equals(that.convexity) : that.convexity != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (delflag != null ? !delflag.equals(that.delflag) : that.delflag != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (interBankCode != null ? !interBankCode.equals(that.interBankCode) : that.interBankCode != null) return false;
        if (modifiedDuration != null ? !modifiedDuration.equals(that.modifiedDuration) : that.modifiedDuration != null) return false;
        if (shhCode != null ? !shhCode.equals(that.shhCode) : that.shhCode != null) return false;
        if (shzCode != null ? !shzCode.equals(that.shzCode) : that.shzCode != null) return false;
        if (yieldToMaturity != null ? !yieldToMaturity.equals(that.yieldToMaturity) : that.yieldToMaturity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (delflag != null ? delflag.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (bondKey != null ? bondKey.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (shhCode != null ? shhCode.hashCode() : 0);
        result = 31 * result + (shzCode != null ? shzCode.hashCode() : 0);
        result = 31 * result + (interBankCode != null ? interBankCode.hashCode() : 0);
        result = 31 * result + (calculationPrice != null ? calculationPrice.hashCode() : 0);
        result = 31 * result + (yieldToMaturity != null ? yieldToMaturity.hashCode() : 0);
        result = 31 * result + (modifiedDuration != null ? modifiedDuration.hashCode() : 0);
        result = 31 * result + (convexity != null ? convexity.hashCode() : 0);
        result = 31 * result + (cleanPrice != null ? cleanPrice.hashCode() : 0);
        result = 31 * result + (accruedInterest != null ? accruedInterest.hashCode() : 0);
        return result;
    }
}
