package jssp.analytic.bond.domain.db;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@javax.persistence.Table(name = "institution", schema = "", catalog = "ss_product")
public class InstitutionEntity {
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

    private String institutionCode;

    @Basic
    @javax.persistence.Column(name = "Institution_Code")
    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    private String institutionType;

    @Basic
    @javax.persistence.Column(name = "Institution_Type")
    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    private String institutionRating;

    @Basic
    @javax.persistence.Column(name = "Institution_Rating")
    public String getInstitutionRating() {
        return institutionRating;
    }

    public void setInstitutionRating(String institutionRating) {
        this.institutionRating = institutionRating;
    }

    private Integer establishmentDate;

    @Basic
    @javax.persistence.Column(name = "Establishment_Date")
    public Integer getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Integer establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    private Integer changeDate;

    @Basic
    @javax.persistence.Column(name = "Change_Date")
    public Integer getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Integer changeDate) {
        this.changeDate = changeDate;
    }

    private String fullNameC;

    @Basic
    @javax.persistence.Column(name = "Full_Name_C")
    public String getFullNameC() {
        return fullNameC;
    }

    public void setFullNameC(String fullNameC) {
        this.fullNameC = fullNameC;
    }

    private String shortNameC;

    @Basic
    @javax.persistence.Column(name = "Short_Name_C")
    public String getShortNameC() {
        return shortNameC;
    }

    public void setShortNameC(String shortNameC) {
        this.shortNameC = shortNameC;
    }

    private String fullNameE;

    @Basic
    @javax.persistence.Column(name = "Full_Name_E")
    public String getFullNameE() {
        return fullNameE;
    }

    public void setFullNameE(String fullNameE) {
        this.fullNameE = fullNameE;
    }

    private String shortNameE;

    @Basic
    @javax.persistence.Column(name = "Short_Name_E")
    public String getShortNameE() {
        return shortNameE;
    }

    public void setShortNameE(String shortNameE) {
        this.shortNameE = shortNameE;
    }

    private String country;

    @Basic
    @javax.persistence.Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    private String largestShareholder;

    @Basic
    @javax.persistence.Column(name = "Largest_Shareholder")
    public String getLargestShareholder() {
        return largestShareholder;
    }

    public void setLargestShareholder(String largestShareholder) {
        this.largestShareholder = largestShareholder;
    }

    private String legalRepresentative;

    @Basic
    @javax.persistence.Column(name = "Legal_Representative")
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    private String registeredCapital;

    @Basic
    @javax.persistence.Column(name = "Registered_Capital")
    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    private String registeredAddress;

    @Basic
    @javax.persistence.Column(name = "Registered_Address")
    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
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

    private String product;

    @Basic
    @javax.persistence.Column(name = "Product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    private Integer ratingDate;

    @Basic
    @javax.persistence.Column(name = "Rating_Date")
    public Integer getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Integer ratingDate) {
        this.ratingDate = ratingDate;
    }

    private String rating;

    @Basic
    @javax.persistence.Column(name = "Rating")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String ratingInstitutionCode;

    @Basic
    @javax.persistence.Column(name = "Rating_Institution_Code")
    public String getRatingInstitutionCode() {
        return ratingInstitutionCode;
    }

    public void setRatingInstitutionCode(String ratingInstitutionCode) {
        this.ratingInstitutionCode = ratingInstitutionCode;
    }

    private String formerName;

    @Basic
    @javax.persistence.Column(name = "Former_Name")
    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
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

    private String institutionSubtype;

    @Basic
    @javax.persistence.Column(name = "Institution_Subtype")
    public String getInstitutionSubtype() {
        return institutionSubtype;
    }

    public void setInstitutionSubtype(String institutionSubtype) {
        this.institutionSubtype = institutionSubtype;
    }

    private String pinYin;

    @Basic
    @javax.persistence.Column(name = "Pin_Yin")
    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    private String pinYinFull;

    @Basic
    @javax.persistence.Column(name = "Pin_Yin_Full")
    public String getPinYinFull() {
        return pinYinFull;
    }

    public void setPinYinFull(String pinYinFull) {
        this.pinYinFull = pinYinFull;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        InstitutionEntity that = (InstitutionEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(delflag, that.delflag)
                .append(createDate, that.createDate)
                .append(modifyDate, that.modifyDate)
                .append(username, that.username)
                .append(inputer, that.inputer)
                .append(checker, that.checker)
                .append(sts, that.sts)
                .append(institutionCode, that.institutionCode)
                .append(institutionType, that.institutionType)
                .append(institutionRating, that.institutionRating)
                .append(establishmentDate, that.establishmentDate)
                .append(changeDate, that.changeDate)
                .append(fullNameC, that.fullNameC)
                .append(shortNameC, that.shortNameC)
                .append(fullNameE, that.fullNameE)
                .append(shortNameE, that.shortNameE)
                .append(country, that.country)
                .append(province, that.province)
                .append(city, that.city)
                .append(largestShareholder, that.largestShareholder)
                .append(legalRepresentative, that.legalRepresentative)
                .append(registeredCapital, that.registeredCapital)
                .append(registeredAddress, that.registeredAddress)
                .append(businessScope, that.businessScope)
                .append(product, that.product)
                .append(ratingDate, that.ratingDate)
                .append(rating, that.rating)
                .append(ratingInstitutionCode, that.ratingInstitutionCode)
                .append(formerName, that.formerName)
                .append(isMunicipal, that.isMunicipal)
                .append(issuerType, that.issuerType)
                .append(orgType, that.orgType)
                .append(swSector, that.swSector)
                .append(swSubsector, that.swSubsector)
                .append(regdCapital, that.regdCapital)
                .append(regdCapitalCurrency, that.regdCapitalCurrency)
                .append(stockholderName, that.stockholderName)
                .append(stockholderType, that.stockholderType)
                .append(stockholdingPercentage, that.stockholdingPercentage)
                .append(actualControllerName, that.actualControllerName)
                .append(actualControllerType, that.actualControllerType)
                .append(ultimateActualControllerName, that.ultimateActualControllerName)
                .append(ultimateActualControllerType, that.ultimateActualControllerType)
                .append(cbrcFinancingPlatform, that.cbrcFinancingPlatform)
                .append(endowment, that.endowment)
                .append(municipalBusiness, that.municipalBusiness)
                .append(institutionSubtype, that.institutionSubtype)
                .append(pinYin, that.pinYin)
                .append(pinYinFull, that.pinYinFull)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(delflag)
                .append(createDate)
                .append(modifyDate)
                .append(username)
                .append(inputer)
                .append(checker)
                .append(sts)
                .append(institutionCode)
                .append(institutionType)
                .append(institutionRating)
                .append(establishmentDate)
                .append(changeDate)
                .append(fullNameC)
                .append(shortNameC)
                .append(fullNameE)
                .append(shortNameE)
                .append(country)
                .append(province)
                .append(city)
                .append(largestShareholder)
                .append(legalRepresentative)
                .append(registeredCapital)
                .append(registeredAddress)
                .append(businessScope)
                .append(product)
                .append(ratingDate)
                .append(rating)
                .append(ratingInstitutionCode)
                .append(formerName)
                .append(isMunicipal)
                .append(issuerType)
                .append(orgType)
                .append(swSector)
                .append(swSubsector)
                .append(regdCapital)
                .append(regdCapitalCurrency)
                .append(stockholderName)
                .append(stockholderType)
                .append(stockholdingPercentage)
                .append(actualControllerName)
                .append(actualControllerType)
                .append(ultimateActualControllerName)
                .append(ultimateActualControllerType)
                .append(cbrcFinancingPlatform)
                .append(endowment)
                .append(municipalBusiness)
                .append(institutionSubtype)
                .append(pinYin)
                .append(pinYinFull)
                .toHashCode();
    }
}
