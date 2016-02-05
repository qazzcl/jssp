package jssp.analytic.index.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "p_rate_info", schema = "")
public class RateInfoEntity {
    private String id;
    private String delflag;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private String username;
    private String inputer;
    private String checker;
    private String sts;
    private String gjkCode;
    private String sourceCode;
    private String seriesNameLocal;
    private String reportedUnitLocal;
    private String unitTypeLocal;
    private String seriesFreqLocal;

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "inputer")
    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Basic
    @Column(name = "checker")
    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
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
    @Column(name = "gjk_code")
    public String getGjkCode() {
        return gjkCode;
    }

    public void setGjkCode(String gjkCode) {
        this.gjkCode = gjkCode;
    }

    @Basic
    @Column(name = "source_code")
    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Basic
    @Column(name = "series_name_local")
    public String getSeriesNameLocal() {
        return seriesNameLocal;
    }

    public void setSeriesNameLocal(String seriesNameLocal) {
        this.seriesNameLocal = seriesNameLocal;
    }

    @Basic
    @Column(name = "reported_unit_local")
    public String getReportedUnitLocal() {
        return reportedUnitLocal;
    }

    public void setReportedUnitLocal(String reportedUnitLocal) {
        this.reportedUnitLocal = reportedUnitLocal;
    }

    @Basic
    @Column(name = "unit_type_local")
    public String getUnitTypeLocal() {
        return unitTypeLocal;
    }

    public void setUnitTypeLocal(String unitTypeLocal) {
        this.unitTypeLocal = unitTypeLocal;
    }

    @Basic
    @Column(name = "series_freq_local")
    public String getSeriesFreqLocal() {
        return seriesFreqLocal;
    }

    public void setSeriesFreqLocal(String seriesFreqLocal) {
        this.seriesFreqLocal = seriesFreqLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateInfoEntity that = (RateInfoEntity) o;

        if (checker != null ? !checker.equals(that.checker) : that.checker != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (delflag != null ? !delflag.equals(that.delflag) : that.delflag != null) return false;
        if (gjkCode != null ? !gjkCode.equals(that.gjkCode) : that.gjkCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (inputer != null ? !inputer.equals(that.inputer) : that.inputer != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;
        if (reportedUnitLocal != null ? !reportedUnitLocal.equals(that.reportedUnitLocal) : that.reportedUnitLocal != null) return false;
        if (seriesFreqLocal != null ? !seriesFreqLocal.equals(that.seriesFreqLocal) : that.seriesFreqLocal != null) return false;
        if (seriesNameLocal != null ? !seriesNameLocal.equals(that.seriesNameLocal) : that.seriesNameLocal != null) return false;
        if (sourceCode != null ? !sourceCode.equals(that.sourceCode) : that.sourceCode != null) return false;
        if (sts != null ? !sts.equals(that.sts) : that.sts != null) return false;
        if (unitTypeLocal != null ? !unitTypeLocal.equals(that.unitTypeLocal) : that.unitTypeLocal != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (delflag != null ? delflag.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (checker != null ? checker.hashCode() : 0);
        result = 31 * result + (sts != null ? sts.hashCode() : 0);
        result = 31 * result + (gjkCode != null ? gjkCode.hashCode() : 0);
        result = 31 * result + (sourceCode != null ? sourceCode.hashCode() : 0);
        result = 31 * result + (seriesNameLocal != null ? seriesNameLocal.hashCode() : 0);
        result = 31 * result + (reportedUnitLocal != null ? reportedUnitLocal.hashCode() : 0);
        result = 31 * result + (unitTypeLocal != null ? unitTypeLocal.hashCode() : 0);
        result = 31 * result + (seriesFreqLocal != null ? seriesFreqLocal.hashCode() : 0);
        return result;
    }
}
