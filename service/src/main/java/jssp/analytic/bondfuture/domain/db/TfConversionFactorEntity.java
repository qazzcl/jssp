package jssp.analytic.bondfuture.domain.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TF_Conversion_Factor", schema = "")
public class TfConversionFactorEntity {
    private String id;
    private String tfKey;
    private String bondKey;
    private BigDecimal conversionFactor;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TF_Key")
    public String getTfKey() {
        return tfKey;
    }

    public void setTfKey(String tfKey) {
        this.tfKey = tfKey;
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
    @Column(name = "Conversion_Factor")
    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TfConversionFactorEntity that = (TfConversionFactorEntity) o;

        if (bondKey != null ? !bondKey.equals(that.bondKey) : that.bondKey != null) return false;
        if (conversionFactor != null ? !conversionFactor.equals(that.conversionFactor) : that.conversionFactor != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tfKey != null ? !tfKey.equals(that.tfKey) : that.tfKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tfKey != null ? tfKey.hashCode() : 0);
        result = 31 * result + (bondKey != null ? bondKey.hashCode() : 0);
        result = 31 * result + (conversionFactor != null ? conversionFactor.hashCode() : 0);
        return result;
    }
}
