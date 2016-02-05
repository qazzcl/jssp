package jssp.analytic.bond.domain.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "p_bond_list_info", schema = "")
public class BondListInfoEntity {
    private String id;
    private String delflag;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private String sts;
    private String bondKey;
    private String listedMarket;
    private String bondId;
    private String isCrossMkt;
    private Integer listedDate;
    private Integer delistedDate;
    private String mktType;

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
    @Column(name = "modify_date")
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "sts")
    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
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
    @Column(name = "Listed_Market")
    public String getListedMarket() {
        return listedMarket;
    }

    public void setListedMarket(String listedMarket) {
        this.listedMarket = listedMarket;
    }

    @Basic
    @Column(name = "Bond_ID")
    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId;
    }

    @Basic
    @Column(name = "Is_Cross_Mkt")
    public String getIsCrossMkt() {
        return isCrossMkt;
    }

    public void setIsCrossMkt(String isCrossMkt) {
        this.isCrossMkt = isCrossMkt;
    }

    @Basic
    @Column(name = "Listed_Date")
    public Integer getListedDate() {
        return listedDate;
    }

    public void setListedDate(Integer listedDate) {
        this.listedDate = listedDate;
    }

    @Basic
    @Column(name = "Delisted_Date")
    public Integer getDelistedDate() {
        return delistedDate;
    }

    public void setDelistedDate(Integer delistedDate) {
        this.delistedDate = delistedDate;
    }

    @Basic
    @Column(name = "Mkt_Type")
    public String getMktType() {
        return mktType;
    }

    public void setMktType(String mktType) {
        this.mktType = mktType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BondListInfoEntity that = (BondListInfoEntity) o;

        if (bondId != null ? !bondId.equals(that.bondId) : that.bondId != null) return false;
        if (bondKey != null ? !bondKey.equals(that.bondKey) : that.bondKey != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (delflag != null ? !delflag.equals(that.delflag) : that.delflag != null) return false;
        if (delistedDate != null ? !delistedDate.equals(that.delistedDate) : that.delistedDate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isCrossMkt != null ? !isCrossMkt.equals(that.isCrossMkt) : that.isCrossMkt != null) return false;
        if (listedDate != null ? !listedDate.equals(that.listedDate) : that.listedDate != null) return false;
        if (listedMarket != null ? !listedMarket.equals(that.listedMarket) : that.listedMarket != null) return false;
        if (mktType != null ? !mktType.equals(that.mktType) : that.mktType != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;
        if (sts != null ? !sts.equals(that.sts) : that.sts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (delflag != null ? delflag.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (sts != null ? sts.hashCode() : 0);
        result = 31 * result + (bondKey != null ? bondKey.hashCode() : 0);
        result = 31 * result + (listedMarket != null ? listedMarket.hashCode() : 0);
        result = 31 * result + (bondId != null ? bondId.hashCode() : 0);
        result = 31 * result + (isCrossMkt != null ? isCrossMkt.hashCode() : 0);
        result = 31 * result + (listedDate != null ? listedDate.hashCode() : 0);
        result = 31 * result + (delistedDate != null ? delistedDate.hashCode() : 0);
        result = 31 * result + (mktType != null ? mktType.hashCode() : 0);
        return result;
    }
}
