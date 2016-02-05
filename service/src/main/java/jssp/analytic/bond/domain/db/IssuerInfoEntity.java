package jssp.analytic.bond.domain.db;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@javax.persistence.Table(name = "p_issuer_info", schema = "")
public class IssuerInfoEntity {
    private String id;

    @Id
    @javax.persistence.Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String delflag;

    @Basic
    @javax.persistence.Column(name = "delflag")
    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    private Timestamp createDate;

    @Basic
    @javax.persistence.Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    private Timestamp modifyDate;

    @Basic
    @javax.persistence.Column(name = "modify_date")
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    private String username;

    @Basic
    @javax.persistence.Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String inputer;

    @Basic
    @javax.persistence.Column(name = "inputer")
    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    private String checker;

    @Basic
    @javax.persistence.Column(name = "checker")
    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    private String sts;

    @Basic
    @javax.persistence.Column(name = "sts")
    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    private String isMunicipal;

    @Basic
    @javax.persistence.Column(name = "Is_Municipal")
    public String getIsMunicipal() {
        return isMunicipal;
    }

    public void setIsMunicipal(String isMunicipal) {
        this.isMunicipal = isMunicipal;
    }

    private String institutionCode;

    @Basic
    @javax.persistence.Column(name = "Institution_Code")
    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    private String issuerName;

    @Basic
    @javax.persistence.Column(name = "Issuer_Name")
    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    private String issuerType;

    @Basic
    @javax.persistence.Column(name = "Issuer_Type")
    public String getIssuerType() {
        return issuerType;
    }

    public void setIssuerType(String issuerType) {
        this.issuerType = issuerType;
    }

    private String orgType;

    @Basic
    @javax.persistence.Column(name = "Org_Type")
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    private String swSector;

    @Basic
    @javax.persistence.Column(name = "SW_Sector")
    public String getSwSector() {
        return swSector;
    }

    public void setSwSector(String swSector) {
        this.swSector = swSector;
    }

    private String swSubsector;

    @Basic
    @javax.persistence.Column(name = "SW_Subsector")
    public String getSwSubsector() {
        return swSubsector;
    }

    public void setSwSubsector(String swSubsector) {
        this.swSubsector = swSubsector;
    }

    private String province;

    @Basic
    @javax.persistence.Column(name = "Province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private String city;

    @Basic
    @javax.persistence.Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private BigDecimal regdCapital;

    @Basic
    @javax.persistence.Column(name = "Regd_capital")
    public BigDecimal getRegdCapital() {
        return regdCapital;
    }

    public void setRegdCapital(BigDecimal regdCapital) {
        this.regdCapital = regdCapital;
    }

    private String regdCapitalCurrency;

    @Basic
    @javax.persistence.Column(name = "Regd_capital_Currency")
    public String getRegdCapitalCurrency() {
        return regdCapitalCurrency;
    }

    public void setRegdCapitalCurrency(String regdCapitalCurrency) {
        this.regdCapitalCurrency = regdCapitalCurrency;
    }

    private String stockholderName;

    @Basic
    @javax.persistence.Column(name = "Stockholder_Name")
    public String getStockholderName() {
        return stockholderName;
    }

    public void setStockholderName(String stockholderName) {
        this.stockholderName = stockholderName;
    }

    private String stockholderType;

    @Basic
    @javax.persistence.Column(name = "Stockholder_Type")
    public String getStockholderType() {
        return stockholderType;
    }

    public void setStockholderType(String stockholderType) {
        this.stockholderType = stockholderType;
    }

    private String stockholdingPercentage;

    @Basic
    @javax.persistence.Column(name = "Stockholding_Percentage")
    public String getStockholdingPercentage() {
        return stockholdingPercentage;
    }

    public void setStockholdingPercentage(String stockholdingPercentage) {
        this.stockholdingPercentage = stockholdingPercentage;
    }

    private String actualControllerName;

    @Basic
    @javax.persistence.Column(name = "Actual_Controller_Name")
    public String getActualControllerName() {
        return actualControllerName;
    }

    public void setActualControllerName(String actualControllerName) {
        this.actualControllerName = actualControllerName;
    }

    private String actualControllerType;

    @Basic
    @javax.persistence.Column(name = "Actual_Controller_Type")
    public String getActualControllerType() {
        return actualControllerType;
    }

    public void setActualControllerType(String actualControllerType) {
        this.actualControllerType = actualControllerType;
    }

    private String ultimateActualControllerName;

    @Basic
    @javax.persistence.Column(name = "Ultimate_Actual_Controller_Name")
    public String getUltimateActualControllerName() {
        return ultimateActualControllerName;
    }

    public void setUltimateActualControllerName(String ultimateActualControllerName) {
        this.ultimateActualControllerName = ultimateActualControllerName;
    }

    private String ultimateActualControllerType;

    @Basic
    @javax.persistence.Column(name = "Ultimate_Actual_Controller_Type")
    public String getUltimateActualControllerType() {
        return ultimateActualControllerType;
    }

    public void setUltimateActualControllerType(String ultimateActualControllerType) {
        this.ultimateActualControllerType = ultimateActualControllerType;
    }

    private String cbrcFinancingPlatform;

    @Basic
    @javax.persistence.Column(name = "CBRC_Financing_Platform")
    public String getCbrcFinancingPlatform() {
        return cbrcFinancingPlatform;
    }

    public void setCbrcFinancingPlatform(String cbrcFinancingPlatform) {
        this.cbrcFinancingPlatform = cbrcFinancingPlatform;
    }

    private String endowment;

    @Basic
    @javax.persistence.Column(name = "Endowment")
    public String getEndowment() {
        return endowment;
    }

    public void setEndowment(String endowment) {
        this.endowment = endowment;
    }

    private String municipalBusiness;

    @Basic
    @javax.persistence.Column(name = "Municipal_Business")
    public String getMunicipalBusiness() {
        return municipalBusiness;
    }

    public void setMunicipalBusiness(String municipalBusiness) {
        this.municipalBusiness = municipalBusiness;
    }

    private String businessScope;

    @Basic
    @javax.persistence.Column(name = "Business_Scope")
    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    private String swSectorCode;

    @Basic
    @javax.persistence.Column(name = "SW_Sector_Code")
    public String getSwSectorCode() {
        return swSectorCode;
    }

    public void setSwSectorCode(String swSectorCode) {
        this.swSectorCode = swSectorCode;
    }

    private String swSubsectorCode;

    @Basic
    @javax.persistence.Column(name = "SW_Subsector_Code")
    public String getSwSubsectorCode() {
        return swSubsectorCode;
    }

    public void setSwSubsectorCode(String swSubsectorCode) {
        this.swSubsectorCode = swSubsectorCode;
    }

    private String provinceCode;

    @Basic
    @javax.persistence.Column(name = "Province_Code")
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssuerInfoEntity that = (IssuerInfoEntity) o;

        if (actualControllerName != null ? !actualControllerName.equals(that.actualControllerName) : that.actualControllerName != null) return false;
        if (actualControllerType != null ? !actualControllerType.equals(that.actualControllerType) : that.actualControllerType != null) return false;
        if (businessScope != null ? !businessScope.equals(that.businessScope) : that.businessScope != null) return false;
        if (cbrcFinancingPlatform != null ? !cbrcFinancingPlatform.equals(that.cbrcFinancingPlatform) : that.cbrcFinancingPlatform != null) return false;
        if (checker != null ? !checker.equals(that.checker) : that.checker != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (delflag != null ? !delflag.equals(that.delflag) : that.delflag != null) return false;
        if (endowment != null ? !endowment.equals(that.endowment) : that.endowment != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (inputer != null ? !inputer.equals(that.inputer) : that.inputer != null) return false;
        if (institutionCode != null ? !institutionCode.equals(that.institutionCode) : that.institutionCode != null) return false;
        if (isMunicipal != null ? !isMunicipal.equals(that.isMunicipal) : that.isMunicipal != null) return false;
        if (issuerName != null ? !issuerName.equals(that.issuerName) : that.issuerName != null) return false;
        if (issuerType != null ? !issuerType.equals(that.issuerType) : that.issuerType != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;
        if (municipalBusiness != null ? !municipalBusiness.equals(that.municipalBusiness) : that.municipalBusiness != null) return false;
        if (orgType != null ? !orgType.equals(that.orgType) : that.orgType != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (provinceCode != null ? !provinceCode.equals(that.provinceCode) : that.provinceCode != null) return false;
        if (regdCapital != null ? !regdCapital.equals(that.regdCapital) : that.regdCapital != null) return false;
        if (regdCapitalCurrency != null ? !regdCapitalCurrency.equals(that.regdCapitalCurrency) : that.regdCapitalCurrency != null) return false;
        if (stockholderName != null ? !stockholderName.equals(that.stockholderName) : that.stockholderName != null) return false;
        if (stockholderType != null ? !stockholderType.equals(that.stockholderType) : that.stockholderType != null) return false;
        if (stockholdingPercentage != null ? !stockholdingPercentage.equals(that.stockholdingPercentage) : that.stockholdingPercentage != null) return false;
        if (sts != null ? !sts.equals(that.sts) : that.sts != null) return false;
        if (swSector != null ? !swSector.equals(that.swSector) : that.swSector != null) return false;
        if (swSectorCode != null ? !swSectorCode.equals(that.swSectorCode) : that.swSectorCode != null) return false;
        if (swSubsector != null ? !swSubsector.equals(that.swSubsector) : that.swSubsector != null) return false;
        if (swSubsectorCode != null ? !swSubsectorCode.equals(that.swSubsectorCode) : that.swSubsectorCode != null) return false;
        if (ultimateActualControllerName != null ? !ultimateActualControllerName.equals(that.ultimateActualControllerName) : that.ultimateActualControllerName != null) return false;
        if (ultimateActualControllerType != null ? !ultimateActualControllerType.equals(that.ultimateActualControllerType) : that.ultimateActualControllerType != null) return false;
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
        result = 31 * result + (isMunicipal != null ? isMunicipal.hashCode() : 0);
        result = 31 * result + (institutionCode != null ? institutionCode.hashCode() : 0);
        result = 31 * result + (issuerName != null ? issuerName.hashCode() : 0);
        result = 31 * result + (issuerType != null ? issuerType.hashCode() : 0);
        result = 31 * result + (orgType != null ? orgType.hashCode() : 0);
        result = 31 * result + (swSector != null ? swSector.hashCode() : 0);
        result = 31 * result + (swSubsector != null ? swSubsector.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (regdCapital != null ? regdCapital.hashCode() : 0);
        result = 31 * result + (regdCapitalCurrency != null ? regdCapitalCurrency.hashCode() : 0);
        result = 31 * result + (stockholderName != null ? stockholderName.hashCode() : 0);
        result = 31 * result + (stockholderType != null ? stockholderType.hashCode() : 0);
        result = 31 * result + (stockholdingPercentage != null ? stockholdingPercentage.hashCode() : 0);
        result = 31 * result + (actualControllerName != null ? actualControllerName.hashCode() : 0);
        result = 31 * result + (actualControllerType != null ? actualControllerType.hashCode() : 0);
        result = 31 * result + (ultimateActualControllerName != null ? ultimateActualControllerName.hashCode() : 0);
        result = 31 * result + (ultimateActualControllerType != null ? ultimateActualControllerType.hashCode() : 0);
        result = 31 * result + (cbrcFinancingPlatform != null ? cbrcFinancingPlatform.hashCode() : 0);
        result = 31 * result + (endowment != null ? endowment.hashCode() : 0);
        result = 31 * result + (municipalBusiness != null ? municipalBusiness.hashCode() : 0);
        result = 31 * result + (businessScope != null ? businessScope.hashCode() : 0);
        result = 31 * result + (swSectorCode != null ? swSectorCode.hashCode() : 0);
        result = 31 * result + (swSubsectorCode != null ? swSubsectorCode.hashCode() : 0);
        result = 31 * result + (provinceCode != null ? provinceCode.hashCode() : 0);
        return result;
    }
}
