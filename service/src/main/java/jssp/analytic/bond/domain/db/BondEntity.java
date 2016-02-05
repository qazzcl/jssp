package jssp.analytic.bond.domain.db;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@javax.persistence.Table(name = "bond", schema = "")
public class BondEntity implements Cloneable{
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

    private String bondKey;

    @Basic
    @javax.persistence.Column(name = "Bond_Key")
    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
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

    private String currency;

    @Basic
    @javax.persistence.Column(name = "Currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private String bondId;

    @Basic
    @javax.persistence.Column(name = "Bond_ID")
    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId;
    }

    private String shortName;

    @Basic
    @javax.persistence.Column(name = "Short_Name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private String isin;

    @Basic
    @javax.persistence.Column(name = "ISIN")
    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    private String fullName;

    @Basic
    @javax.persistence.Column(name = "Full_Name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String issuerCode;

    @Basic
    @javax.persistence.Column(name = "Issuer_Code")
    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    private String issuerKeyLei;

    @Basic
    @javax.persistence.Column(name = "Issuer_Key_LEI")
    public String getIssuerKeyLei() {
        return issuerKeyLei;
    }

    public void setIssuerKeyLei(String issuerKeyLei) {
        this.issuerKeyLei = issuerKeyLei;
    }

    private String underwriterCode;

    @Basic
    @javax.persistence.Column(name = "Underwriter_Code")
    public String getUnderwriterCode() {
        return underwriterCode;
    }

    public void setUnderwriterCode(String underwriterCode) {
        this.underwriterCode = underwriterCode;
    }

    private String yieldCurveId;

    @Basic
    @javax.persistence.Column(name = "Yield_Curve_ID")
    public String getYieldCurveId() {
        return yieldCurveId;
    }

    public void setYieldCurveId(String yieldCurveId) {
        this.yieldCurveId = yieldCurveId;
    }

    private String yieldCurveType;

    @Basic
    @javax.persistence.Column(name = "Yield_Curve_Type")
    public String getYieldCurveType() {
        return yieldCurveType;
    }

    public void setYieldCurveType(String yieldCurveType) {
        this.yieldCurveType = yieldCurveType;
    }

    private String bondType;

    @Basic
    @javax.persistence.Column(name = "Bond_Type")
    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    private String bondSubtype;

    @Basic
    @javax.persistence.Column(name = "Bond_Subtype")
    public String getBondSubtype() {
        return bondSubtype;
    }

    public void setBondSubtype(String bondSubtype) {
        this.bondSubtype = bondSubtype;
    }

    private String externalType;

    @Basic
    @javax.persistence.Column(name = "External_Type")
    public String getExternalType() {
        return externalType;
    }

    public void setExternalType(String externalType) {
        this.externalType = externalType;
    }

    private BigDecimal maturityTermY;

    @Basic
    @javax.persistence.Column(name = "Maturity_Term_Y")
    public BigDecimal getMaturityTermY() {
        return maturityTermY;
    }

    public void setMaturityTermY(BigDecimal maturityTermY) {
        this.maturityTermY = maturityTermY;
    }

    private BigDecimal maturityTerm;

    @Basic
    @javax.persistence.Column(name = "Maturity_Term")
    public BigDecimal getMaturityTerm() {
        return maturityTerm;
    }

    public void setMaturityTerm(BigDecimal maturityTerm) {
        this.maturityTerm = maturityTerm;
    }

    private String termUnit;

    @Basic
    @javax.persistence.Column(name = "Term_Unit")
    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    private Integer interestStartDate;

    @Basic
    @javax.persistence.Column(name = "Interest_Start_Date")
    public Integer getInterestStartDate() {
        return interestStartDate;
    }

    public void setInterestStartDate(Integer interestStartDate) {
        this.interestStartDate = interestStartDate;
    }

    private Integer maturityDate;

    @Basic
    @javax.persistence.Column(name = "Maturity_Date")
    public Integer getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Integer maturityDate) {
        this.maturityDate = maturityDate;
    }

    private Integer firstCouponDate;

    @Basic
    @javax.persistence.Column(name = "First_Coupon_Date")
    public Integer getFirstCouponDate() {
        return firstCouponDate;
    }

    public void setFirstCouponDate(Integer firstCouponDate) {
        this.firstCouponDate = firstCouponDate;
    }

    private Integer nextCouponDate;

    @Basic
    @javax.persistence.Column(name = "Next_Coupon_Date")
    public Integer getNextCouponDate() {
        return nextCouponDate;
    }

    public void setNextCouponDate(Integer nextCouponDate) {
        this.nextCouponDate = nextCouponDate;
    }

    private Integer announceDate;

    @Basic
    @javax.persistence.Column(name = "Announce_Date")
    public Integer getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(Integer announceDate) {
        this.announceDate = announceDate;
    }

    private Integer issueStartDate;

    @Basic
    @javax.persistence.Column(name = "Issue_Start_Date")
    public Integer getIssueStartDate() {
        return issueStartDate;
    }

    public void setIssueStartDate(Integer issueStartDate) {
        this.issueStartDate = issueStartDate;
    }

    private Integer issueEndDate;

    @Basic
    @javax.persistence.Column(name = "Issue_End_Date")
    public Integer getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(Integer issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    private Integer wiStartDate;

    @Basic
    @javax.persistence.Column(name = "WI_Start_Date")
    public Integer getWiStartDate() {
        return wiStartDate;
    }

    public void setWiStartDate(Integer wiStartDate) {
        this.wiStartDate = wiStartDate;
    }

    private Integer wiEndDate;

    @Basic
    @javax.persistence.Column(name = "WI_End_Date")
    public Integer getWiEndDate() {
        return wiEndDate;
    }

    public void setWiEndDate(Integer wiEndDate) {
        this.wiEndDate = wiEndDate;
    }

    private Integer distDateStart;

    @Basic
    @javax.persistence.Column(name = "Dist_Date_Start")
    public Integer getDistDateStart() {
        return distDateStart;
    }

    public void setDistDateStart(Integer distDateStart) {
        this.distDateStart = distDateStart;
    }

    private Integer distDateEnd;

    @Basic
    @javax.persistence.Column(name = "Dist_Date_End")
    public Integer getDistDateEnd() {
        return distDateEnd;
    }

    public void setDistDateEnd(Integer distDateEnd) {
        this.distDateEnd = distDateEnd;
    }

    private Integer auctionDateStart;

    @Basic
    @javax.persistence.Column(name = "Auction_Date_Start")
    public Integer getAuctionDateStart() {
        return auctionDateStart;
    }

    public void setAuctionDateStart(Integer auctionDateStart) {
        this.auctionDateStart = auctionDateStart;
    }

    private Integer auctionDateEnd;

    @Basic
    @javax.persistence.Column(name = "Auction_Date_End")
    public Integer getAuctionDateEnd() {
        return auctionDateEnd;
    }

    public void setAuctionDateEnd(Integer auctionDateEnd) {
        this.auctionDateEnd = auctionDateEnd;
    }

    private Integer paymentDate;

    @Basic
    @javax.persistence.Column(name = "Payment_Date")
    public Integer getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Integer paymentDate) {
        this.paymentDate = paymentDate;
    }

    private Integer listedDate;

    @Basic
    @javax.persistence.Column(name = "Listed_Date")
    public Integer getListedDate() {
        return listedDate;
    }

    public void setListedDate(Integer listedDate) {
        this.listedDate = listedDate;
    }

    private Integer delistedDate;

    @Basic
    @javax.persistence.Column(name = "Delisted_Date")
    public Integer getDelistedDate() {
        return delistedDate;
    }

    public void setDelistedDate(Integer delistedDate) {
        this.delistedDate = delistedDate;
    }

    private String register;

    @Basic
    @javax.persistence.Column(name = "Register")
    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    private String optionType;

    @Basic
    @javax.persistence.Column(name = "Option_Type")
    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    private String optionStyle;

    @Basic
    @javax.persistence.Column(name = "Option_Style")
    public String getOptionStyle() {
        return optionStyle;
    }

    public void setOptionStyle(String optionStyle) {
        this.optionStyle = optionStyle;
    }

    private Integer optionDate;

    @Basic
    @javax.persistence.Column(name = "Option_Date")
    public Integer getOptionDate() {
        return optionDate;
    }

    public void setOptionDate(Integer optionDate) {
        this.optionDate = optionDate;
    }

    private String optionPriceRatio;

    @Basic
    @javax.persistence.Column(name = "Option_Price_Ratio")
    public String getOptionPriceRatio() {
        return optionPriceRatio;
    }

    public void setOptionPriceRatio(String optionPriceRatio) {
        this.optionPriceRatio = optionPriceRatio;
    }

    private String optionRecordDate;

    @Basic
    @javax.persistence.Column(name = "Option_Record_Date")
    public String getOptionRecordDate() {
        return optionRecordDate;
    }

    public void setOptionRecordDate(String optionRecordDate) {
        this.optionRecordDate = optionRecordDate;
    }

    private BigInteger callNo;

    @Basic
    @javax.persistence.Column(name = "Call_No")
    public BigInteger getCallNo() {
        return callNo;
    }

    public void setCallNo(BigInteger callNo) {
        this.callNo = callNo;
    }

    private String callStr;

    @Basic
    @javax.persistence.Column(name = "Call_Str")
    public String getCallStr() {
        return callStr;
    }

    public void setCallStr(String callStr) {
        this.callStr = callStr;
    }

    private BigInteger putNo;

    @Basic
    @javax.persistence.Column(name = "Put_No")
    public BigInteger getPutNo() {
        return putNo;
    }

    public void setPutNo(BigInteger putNo) {
        this.putNo = putNo;
    }

    private String putStr;

    @Basic
    @javax.persistence.Column(name = "Put_Str")
    public String getPutStr() {
        return putStr;
    }

    public void setPutStr(String putStr) {
        this.putStr = putStr;
    }

    private String firstExchgBondKey;

    @Basic
    @javax.persistence.Column(name = "First_Exchg_Bond_Key")
    public String getFirstExchgBondKey() {
        return firstExchgBondKey;
    }

    public void setFirstExchgBondKey(String firstExchgBondKey) {
        this.firstExchgBondKey = firstExchgBondKey;
    }

    private BigInteger firstExchgNo;

    @Basic
    @javax.persistence.Column(name = "First_Exchg_No")
    public BigInteger getFirstExchgNo() {
        return firstExchgNo;
    }

    public void setFirstExchgNo(BigInteger firstExchgNo) {
        this.firstExchgNo = firstExchgNo;
    }

    private String firstExchgStr;

    @Basic
    @javax.persistence.Column(name = "First_Exchg_Str")
    public String getFirstExchgStr() {
        return firstExchgStr;
    }

    public void setFirstExchgStr(String firstExchgStr) {
        this.firstExchgStr = firstExchgStr;
    }

    private BigInteger secExchgNo;

    @Basic
    @javax.persistence.Column(name = "Sec_Exchg_No")
    public BigInteger getSecExchgNo() {
        return secExchgNo;
    }

    public void setSecExchgNo(BigInteger secExchgNo) {
        this.secExchgNo = secExchgNo;
    }

    private String secExchgYear;

    @Basic
    @javax.persistence.Column(name = "Sec_Exchg_Year")
    public String getSecExchgYear() {
        return secExchgYear;
    }

    public void setSecExchgYear(String secExchgYear) {
        this.secExchgYear = secExchgYear;
    }

    private String assignTransKey;

    @Basic
    @javax.persistence.Column(name = "Assign_Trans_Key")
    public String getAssignTransKey() {
        return assignTransKey;
    }

    public void setAssignTransKey(String assignTransKey) {
        this.assignTransKey = assignTransKey;
    }

    private BigInteger assignTransNo;

    @Basic
    @javax.persistence.Column(name = "Assign_Trans_No")
    public BigInteger getAssignTransNo() {
        return assignTransNo;
    }

    public void setAssignTransNo(BigInteger assignTransNo) {
        this.assignTransNo = assignTransNo;
    }

    private String assignTransStr;

    @Basic
    @javax.persistence.Column(name = "Assign_Trans_Str")
    public String getAssignTransStr() {
        return assignTransStr;
    }

    public void setAssignTransStr(String assignTransStr) {
        this.assignTransStr = assignTransStr;
    }

    private String compensateRate;

    @Basic
    @javax.persistence.Column(name = "Compensate_Rate")
    public String getCompensateRate() {
        return compensateRate;
    }

    public void setCompensateRate(String compensateRate) {
        this.compensateRate = compensateRate;
    }

    private String compensateFrom;

    @Basic
    @javax.persistence.Column(name = "Compensate_From")
    public String getCompensateFrom() {
        return compensateFrom;
    }

    public void setCompensateFrom(String compensateFrom) {
        this.compensateFrom = compensateFrom;
    }

    private String optionExercise;

    @Basic
    @javax.persistence.Column(name = "Option_Exercise")
    public String getOptionExercise() {
        return optionExercise;
    }

    public void setOptionExercise(String optionExercise) {
        this.optionExercise = optionExercise;
    }

    private String optionExerciseDate;

    @Basic
    @javax.persistence.Column(name = "Option_Exercise_Date")
    public String getOptionExerciseDate() {
        return optionExerciseDate;
    }

    public void setOptionExerciseDate(String optionExerciseDate) {
        this.optionExerciseDate = optionExerciseDate;
    }

    private String couponType;

    @Basic
    @javax.persistence.Column(name = "Coupon_Type")
    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    private BigDecimal couponRateSpread;

    @Basic
    @javax.persistence.Column(name = "Coupon_Rate_Spread")
    public BigDecimal getCouponRateSpread() {
        return couponRateSpread;
    }

    public void setCouponRateSpread(BigDecimal couponRateSpread) {
        this.couponRateSpread = couponRateSpread;
    }

    private BigDecimal couponRateCurrent;

    @Basic
    @javax.persistence.Column(name = "Coupon_Rate_Current")
    public BigDecimal getCouponRateCurrent() {
        return couponRateCurrent;
    }

    public void setCouponRateCurrent(BigDecimal couponRateCurrent) {
        this.couponRateCurrent = couponRateCurrent;
    }

    private String couponFrequency;

    @Basic
    @javax.persistence.Column(name = "Coupon_Frequency")
    public String getCouponFrequency() {
        return couponFrequency;
    }

    public void setCouponFrequency(String couponFrequency) {
        this.couponFrequency = couponFrequency;
    }

    private String compoundFrequency;

    @Basic
    @javax.persistence.Column(name = "Compound_Frequency")
    public String getCompoundFrequency() {
        return compoundFrequency;
    }

    public void setCompoundFrequency(String compoundFrequency) {
        this.compoundFrequency = compoundFrequency;
    }

    private String interestBasis;

    @Basic
    @javax.persistence.Column(name = "Interest_Basis")
    public String getInterestBasis() {
        return interestBasis;
    }

    public void setInterestBasis(String interestBasis) {
        this.interestBasis = interestBasis;
    }

    private String couponDist;

    @Basic
    @javax.persistence.Column(name = "Coupon_Dist")
    public String getCouponDist() {
        return couponDist;
    }

    public void setCouponDist(String couponDist) {
        this.couponDist = couponDist;
    }

    private BigDecimal frnMultiplier;

    @Basic
    @javax.persistence.Column(name = "FRN_Multiplier")
    public BigDecimal getFrnMultiplier() {
        return frnMultiplier;
    }

    public void setFrnMultiplier(BigDecimal frnMultiplier) {
        this.frnMultiplier = frnMultiplier;
    }

    private String frnIndexId;

    @Basic
    @javax.persistence.Column(name = "FRN_Index_ID")
    public String getFrnIndexId() {
        return frnIndexId;
    }

    public void setFrnIndexId(String frnIndexId) {
        this.frnIndexId = frnIndexId;
    }

    private String firstIndexRate;

    @Basic
    @javax.persistence.Column(name = "First_Index_Rate")
    public String getFirstIndexRate() {
        return firstIndexRate;
    }

    public void setFirstIndexRate(String firstIndexRate) {
        this.firstIndexRate = firstIndexRate;
    }

    private String fixingFrequency;

    @Basic
    @javax.persistence.Column(name = "Fixing_Frequency")
    public String getFixingFrequency() {
        return fixingFrequency;
    }

    public void setFixingFrequency(String fixingFrequency) {
        this.fixingFrequency = fixingFrequency;
    }

    private Integer fixingMaDays;

    @Basic
    @javax.persistence.Column(name = "Fixing_MA_Days")
    public Integer getFixingMaDays() {
        return fixingMaDays;
    }

    public void setFixingMaDays(Integer fixingMaDays) {
        this.fixingMaDays = fixingMaDays;
    }

    private String fixingPreceds;

    @Basic
    @javax.persistence.Column(name = "Fixing_Preceds")
    public String getFixingPreceds() {
        return fixingPreceds;
    }

    public void setFixingPreceds(String fixingPreceds) {
        this.fixingPreceds = fixingPreceds;
    }

    private String fixingCalendarKey;

    @Basic
    @javax.persistence.Column(name = "Fixing_Calendar_Key")
    public String getFixingCalendarKey() {
        return fixingCalendarKey;
    }

    public void setFixingCalendarKey(String fixingCalendarKey) {
        this.fixingCalendarKey = fixingCalendarKey;
    }

    private String fixingTailing;

    @Basic
    @javax.persistence.Column(name = "Fixing_Tailing")
    public String getFixingTailing() {
        return fixingTailing;
    }

    public void setFixingTailing(String fixingTailing) {
        this.fixingTailing = fixingTailing;
    }

    private BigInteger fixingDigit;

    @Basic
    @javax.persistence.Column(name = "Fixing_Digit")
    public BigInteger getFixingDigit() {
        return fixingDigit;
    }

    public void setFixingDigit(BigInteger fixingDigit) {
        this.fixingDigit = fixingDigit;
    }

    private String resetEffective;

    @Basic
    @javax.persistence.Column(name = "Reset_Effective")
    public String getResetEffective() {
        return resetEffective;
    }

    public void setResetEffective(String resetEffective) {
        this.resetEffective = resetEffective;
    }

    private String cap;

    @Basic
    @javax.persistence.Column(name = "Cap")
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    private String flr;

    @Basic
    @javax.persistence.Column(name = "Flr")
    public String getFlr() {
        return flr;
    }

    public void setFlr(String flr) {
        this.flr = flr;
    }

    private String simpleCompound;

    @Basic
    @javax.persistence.Column(name = "Simple_Compound")
    public String getSimpleCompound() {
        return simpleCompound;
    }

    public void setSimpleCompound(String simpleCompound) {
        this.simpleCompound = simpleCompound;
    }

    private String issueReference;

    @Basic
    @javax.persistence.Column(name = "Issue_Reference")
    public String getIssueReference() {
        return issueReference;
    }

    public void setIssueReference(String issueReference) {
        this.issueReference = issueReference;
    }

    private String issueReferenceDesc;

    @Basic
    @javax.persistence.Column(name = "Issue_Reference_Desc")
    public String getIssueReferenceDesc() {
        return issueReferenceDesc;
    }

    public void setIssueReferenceDesc(String issueReferenceDesc) {
        this.issueReferenceDesc = issueReferenceDesc;
    }

    private String variableSchedule;

    @Basic
    @javax.persistence.Column(name = "Variable_Schedule")
    public String getVariableSchedule() {
        return variableSchedule;
    }

    public void setVariableSchedule(String variableSchedule) {
        this.variableSchedule = variableSchedule;
    }

    private String couponDayAdjust;

    @Basic
    @javax.persistence.Column(name = "Coupon_Day_Adjust")
    public String getCouponDayAdjust() {
        return couponDayAdjust;
    }

    public void setCouponDayAdjust(String couponDayAdjust) {
        this.couponDayAdjust = couponDayAdjust;
    }

    private String couponDayDmc;

    @Basic
    @javax.persistence.Column(name = "Coupon_Day_DMC")
    public String getCouponDayDmc() {
        return couponDayDmc;
    }

    public void setCouponDayDmc(String couponDayDmc) {
        this.couponDayDmc = couponDayDmc;
    }

    private String couponCalendarKey;

    @Basic
    @javax.persistence.Column(name = "Coupon_Calendar_Key")
    public String getCouponCalendarKey() {
        return couponCalendarKey;
    }

    public void setCouponCalendarKey(String couponCalendarKey) {
        this.couponCalendarKey = couponCalendarKey;
    }

    private String pricingConv;

    @Basic
    @javax.persistence.Column(name = "Pricing_Conv")
    public String getPricingConv() {
        return pricingConv;
    }

    public void setPricingConv(String pricingConv) {
        this.pricingConv = pricingConv;
    }

    private BigDecimal issuePrice;

    @Basic
    @javax.persistence.Column(name = "Issue_Price")
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    private BigDecimal issueRate;

    @Basic
    @javax.persistence.Column(name = "Issue_Rate")
    public BigDecimal getIssueRate() {
        return issueRate;
    }

    public void setIssueRate(BigDecimal issueRate) {
        this.issueRate = issueRate;
    }

    private BigDecimal plannedIssueAmount;

    @Basic
    @javax.persistence.Column(name = "Planned_Issue_Amount")
    public BigDecimal getPlannedIssueAmount() {
        return plannedIssueAmount;
    }

    public void setPlannedIssueAmount(BigDecimal plannedIssueAmount) {
        this.plannedIssueAmount = plannedIssueAmount;
    }

    private BigDecimal firstIssueAmount;

    @Basic
    @javax.persistence.Column(name = "First_Issue_Amount")
    public BigDecimal getFirstIssueAmount() {
        return firstIssueAmount;
    }

    public void setFirstIssueAmount(BigDecimal firstIssueAmount) {
        this.firstIssueAmount = firstIssueAmount;
    }

    private BigDecimal issueAmount;

    @Basic
    @javax.persistence.Column(name = "Issue_Amount")
    public BigDecimal getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }

    private BigDecimal outstandingAmount;

    @Basic
    @javax.persistence.Column(name = "Outstanding_Amount")
    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    private Integer redemptionNo;

    @Basic
    @javax.persistence.Column(name = "Redemption_No")
    public Integer getRedemptionNo() {
        return redemptionNo;
    }

    public void setRedemptionNo(Integer redemptionNo) {
        this.redemptionNo = redemptionNo;
    }

    private String redemptionStr;

    @Basic
    @javax.persistence.Column(name = "Redemption_Str")
    public String getRedemptionStr() {
        return redemptionStr;
    }

    public void setRedemptionStr(String redemptionStr) {
        this.redemptionStr = redemptionStr;
    }

    private String maturityAdjust;

    @Basic
    @javax.persistence.Column(name = "Maturity_Adjust")
    public String getMaturityAdjust() {
        return maturityAdjust;
    }

    public void setMaturityAdjust(String maturityAdjust) {
        this.maturityAdjust = maturityAdjust;
    }

    private String maturityDmc;

    @Basic
    @javax.persistence.Column(name = "Maturity_DMC")
    public String getMaturityDmc() {
        return maturityDmc;
    }

    public void setMaturityDmc(String maturityDmc) {
        this.maturityDmc = maturityDmc;
    }

    private String maturityCalendarKey;

    @Basic
    @javax.persistence.Column(name = "Maturity_Calendar_Key")
    public String getMaturityCalendarKey() {
        return maturityCalendarKey;
    }

    public void setMaturityCalendarKey(String maturityCalendarKey) {
        this.maturityCalendarKey = maturityCalendarKey;
    }

    private String sceniority;

    @Basic
    @javax.persistence.Column(name = "Sceniority")
    public String getSceniority() {
        return sceniority;
    }

    public void setSceniority(String sceniority) {
        this.sceniority = sceniority;
    }

    private String ratingCurrent;

    @Basic
    @javax.persistence.Column(name = "Rating_Current")
    public String getRatingCurrent() {
        return ratingCurrent;
    }

    public void setRatingCurrent(String ratingCurrent) {
        this.ratingCurrent = ratingCurrent;
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

    private Integer ratingDate;

    @Basic
    @javax.persistence.Column(name = "Rating_Date")
    public Integer getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Integer ratingDate) {
        this.ratingDate = ratingDate;
    }

    private String ratingAugment;

    @Basic
    @javax.persistence.Column(name = "Rating_Augment")
    public String getRatingAugment() {
        return ratingAugment;
    }

    public void setRatingAugment(String ratingAugment) {
        this.ratingAugment = ratingAugment;
    }

    private String warranter;

    @Basic
    @javax.persistence.Column(name = "Warranter")
    public String getWarranter() {
        return warranter;
    }

    public void setWarranter(String warranter) {
        this.warranter = warranter;
    }

    private String warrantNote;

    @Basic
    @javax.persistence.Column(name = "Warrant_Note")
    public String getWarrantNote() {
        return warrantNote;
    }

    public void setWarrantNote(String warrantNote) {
        this.warrantNote = warrantNote;
    }

    private String bondCollateral;

    @Basic
    @javax.persistence.Column(name = "Bond_Collateral")
    public String getBondCollateral() {
        return bondCollateral;
    }

    public void setBondCollateral(String bondCollateral) {
        this.bondCollateral = bondCollateral;
    }

    private BigDecimal collateralValue;

    @Basic
    @javax.persistence.Column(name = "Collateral_Value")
    public BigDecimal getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(BigDecimal collateralValue) {
        this.collateralValue = collateralValue;
    }

    private Integer evaluationDate;

    @Basic
    @javax.persistence.Column(name = "Evaluation_Date")
    public Integer getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Integer evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    private String collateralType;

    @Basic
    @javax.persistence.Column(name = "Collateral_Type")
    public String getCollateralType() {
        return collateralType;
    }

    public void setCollateralType(String collateralType) {
        this.collateralType = collateralType;
    }

    private String issuerRatingCurrent;

    @Basic
    @javax.persistence.Column(name = "Issuer_Rating_Current")
    public String getIssuerRatingCurrent() {
        return issuerRatingCurrent;
    }

    public void setIssuerRatingCurrent(String issuerRatingCurrent) {
        this.issuerRatingCurrent = issuerRatingCurrent;
    }

    private String issuerOutlookCurrent;

    @Basic
    @javax.persistence.Column(name = "Issuer_Outlook_Current")
    public String getIssuerOutlookCurrent() {
        return issuerOutlookCurrent;
    }

    public void setIssuerOutlookCurrent(String issuerOutlookCurrent) {
        this.issuerOutlookCurrent = issuerOutlookCurrent;
    }

    private String issuerRatingInstitutionCode;

    @Basic
    @javax.persistence.Column(name = "Issuer_Rating_Institution_Code")
    public String getIssuerRatingInstitutionCode() {
        return issuerRatingInstitutionCode;
    }

    public void setIssuerRatingInstitutionCode(String issuerRatingInstitutionCode) {
        this.issuerRatingInstitutionCode = issuerRatingInstitutionCode;
    }

    private Integer issuerRatingDate;

    @Basic
    @javax.persistence.Column(name = "Issuer_Rating_Date")
    public Integer getIssuerRatingDate() {
        return issuerRatingDate;
    }

    public void setIssuerRatingDate(Integer issuerRatingDate) {
        this.issuerRatingDate = issuerRatingDate;
    }

    private BigDecimal issueCommissionRate;

    @Basic
    @javax.persistence.Column(name = "Issue_Commission_Rate")
    public BigDecimal getIssueCommissionRate() {
        return issueCommissionRate;
    }

    public void setIssueCommissionRate(BigDecimal issueCommissionRate) {
        this.issueCommissionRate = issueCommissionRate;
    }

    private BigDecimal issueBonusRate;

    @Basic
    @javax.persistence.Column(name = "Issue_Bonus_Rate")
    public BigDecimal getIssueBonusRate() {
        return issueBonusRate;
    }

    public void setIssueBonusRate(BigDecimal issueBonusRate) {
        this.issueBonusRate = issueBonusRate;
    }

    private BigDecimal redmCommissionRate;

    @Basic
    @javax.persistence.Column(name = "Redm_Commission_Rate")
    public BigDecimal getRedmCommissionRate() {
        return redmCommissionRate;
    }

    public void setRedmCommissionRate(BigDecimal redmCommissionRate) {
        this.redmCommissionRate = redmCommissionRate;
    }

    private BigDecimal riskWeight;

    @Basic
    @javax.persistence.Column(name = "Risk_Weight")
    public BigDecimal getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(BigDecimal riskWeight) {
        this.riskWeight = riskWeight;
    }

    private Integer issueYear;

    @Basic
    @javax.persistence.Column(name = "Issue_Year")
    public Integer getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(Integer issueYear) {
        this.issueYear = issueYear;
    }

    private Integer issueNo;

    @Basic
    @javax.persistence.Column(name = "Issue_No")
    public Integer getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(Integer issueNo) {
        this.issueNo = issueNo;
    }

    private String quotation;

    @Basic
    @javax.persistence.Column(name = "Quotation")
    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    private Integer ycDctKey;

    @Basic
    @javax.persistence.Column(name = "YC_DCT_Key")
    public Integer getYcDctKey() {
        return ycDctKey;
    }

    public void setYcDctKey(Integer ycDctKey) {
        this.ycDctKey = ycDctKey;
    }

    private Integer ycCpnKey;

    @Basic
    @javax.persistence.Column(name = "YC_CPN_Key")
    public Integer getYcCpnKey() {
        return ycCpnKey;
    }

    public void setYcCpnKey(Integer ycCpnKey) {
        this.ycCpnKey = ycCpnKey;
    }

    private String assetStatus;

    @Basic
    @javax.persistence.Column(name = "Asset_Status")
    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    private String purposeOfIssue;

    @Basic
    @javax.persistence.Column(name = "Purpose_of_Issue")
    public String getPurposeOfIssue() {
        return purposeOfIssue;
    }

    public void setPurposeOfIssue(String purposeOfIssue) {
        this.purposeOfIssue = purposeOfIssue;
    }

    private String firstIssueKey;

    @Basic
    @javax.persistence.Column(name = "First_Issue_Key")
    public String getFirstIssueKey() {
        return firstIssueKey;
    }

    public void setFirstIssueKey(String firstIssueKey) {
        this.firstIssueKey = firstIssueKey;
    }

    private String note;

    @Basic
    @javax.persistence.Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String termStructure;

    @Basic
    @javax.persistence.Column(name = "Term_structure")
    public String getTermStructure() {
        return termStructure;
    }

    public void setTermStructure(String termStructure) {
        this.termStructure = termStructure;
    }

    private BigDecimal termBeforeExercise;

    @Basic
    @javax.persistence.Column(name = "Term_Before_Exercise")
    public BigDecimal getTermBeforeExercise() {
        return termBeforeExercise;
    }

    public void setTermBeforeExercise(BigDecimal termBeforeExercise) {
        this.termBeforeExercise = termBeforeExercise;
    }

    private String pRatingCurrent;

    @Basic
    @javax.persistence.Column(name = "P_Rating_Current")
    public String getpRatingCurrent() {
        return pRatingCurrent;
    }

    public void setpRatingCurrent(String pRatingCurrent) {
        this.pRatingCurrent = pRatingCurrent;
    }

    private String pIssuerRatingCurrent;

    @Basic
    @javax.persistence.Column(name = "P_Issuer_Rating_Current")
    public String getpIssuerRatingCurrent() {
        return pIssuerRatingCurrent;
    }

    public void setpIssuerRatingCurrent(String pIssuerRatingCurrent) {
        this.pIssuerRatingCurrent = pIssuerRatingCurrent;
    }

    private String pIssuerOutlookCurrent;

    @Basic
    @javax.persistence.Column(name = "P_Issuer_Outlook_Current")
    public String getpIssuerOutlookCurrent() {
        return pIssuerOutlookCurrent;
    }

    public void setpIssuerOutlookCurrent(String pIssuerOutlookCurrent) {
        this.pIssuerOutlookCurrent = pIssuerOutlookCurrent;
    }

    private Integer noAddIssue;

    @Basic
    @javax.persistence.Column(name = "No_Add_Issue")
    public Integer getNoAddIssue() {
        return noAddIssue;
    }

    public void setNoAddIssue(Integer noAddIssue) {
        this.noAddIssue = noAddIssue;
    }

    private String isCrossMkt;

    @Basic
    @javax.persistence.Column(name = "Is_Cross_Mkt")
    public String getIsCrossMkt() {
        return isCrossMkt;
    }

    public void setIsCrossMkt(String isCrossMkt) {
        this.isCrossMkt = isCrossMkt;
    }

    private String isMortgage;

    @Basic
    @javax.persistence.Column(name = "Is_Mortgage")
    public String getIsMortgage() {
        return isMortgage;
    }

    public void setIsMortgage(String isMortgage) {
        this.isMortgage = isMortgage;
    }

    private String mktType;

    @Basic
    @javax.persistence.Column(name = "Mkt_Type")
    public String getMktType() {
        return mktType;
    }

    public void setMktType(String mktType) {
        this.mktType = mktType;
    }

    private String annStatus;

    @Basic
    @javax.persistence.Column(name = "Ann_Status")
    public String getAnnStatus() {
        return annStatus;
    }

    public void setAnnStatus(String annStatus) {
        this.annStatus = annStatus;
    }

    private String liquiditySupporter;

    @Basic
    @javax.persistence.Column(name = "Liquidity_Supporter")
    public String getLiquiditySupporter() {
        return liquiditySupporter;
    }

    public void setLiquiditySupporter(String liquiditySupporter) {
        this.liquiditySupporter = liquiditySupporter;
    }

    private String isCib;

    @Basic
    @javax.persistence.Column(name = "Is_CIB")
    public String getIsCib() {
        return isCib;
    }

    public void setIsCib(String isCib) {
        this.isCib = isCib;
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

    private String ratingCurrentNpy;

    @Basic
    @javax.persistence.Column(name = "Rating_Current_NPY")
    public String getRatingCurrentNpy() {
        return ratingCurrentNpy;
    }

    public void setRatingCurrentNpy(String ratingCurrentNpy) {
        this.ratingCurrentNpy = ratingCurrentNpy;
    }

    private String issuerRatingCurrentNpy;

    @Basic
    @javax.persistence.Column(name = "Issuer_Rating_Current_NPY")
    public String getIssuerRatingCurrentNpy() {
        return issuerRatingCurrentNpy;
    }

    public void setIssuerRatingCurrentNpy(String issuerRatingCurrentNpy) {
        this.issuerRatingCurrentNpy = issuerRatingCurrentNpy;
    }

    private String etsStr;

    @Basic
    @javax.persistence.Column(name = "Ets_Str")
    public String getEtsStr() {
        return etsStr;
    }

    public void setEtsStr(String etsStr) {
        this.etsStr = etsStr;
    }

    @Override
    public BondEntity clone() {
        try {
            return (BondEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BondEntity that = (BondEntity) o;

        if (annStatus != null ? !annStatus.equals(that.annStatus) : that.annStatus != null) return false;
        if (announceDate != null ? !announceDate.equals(that.announceDate) : that.announceDate != null) return false;
        if (assetStatus != null ? !assetStatus.equals(that.assetStatus) : that.assetStatus != null) return false;
        if (assignTransKey != null ? !assignTransKey.equals(that.assignTransKey) : that.assignTransKey != null) return false;
        if (assignTransNo != null ? !assignTransNo.equals(that.assignTransNo) : that.assignTransNo != null) return false;
        if (assignTransStr != null ? !assignTransStr.equals(that.assignTransStr) : that.assignTransStr != null) return false;
        if (auctionDateEnd != null ? !auctionDateEnd.equals(that.auctionDateEnd) : that.auctionDateEnd != null) return false;
        if (auctionDateStart != null ? !auctionDateStart.equals(that.auctionDateStart) : that.auctionDateStart != null) return false;
        if (bondCollateral != null ? !bondCollateral.equals(that.bondCollateral) : that.bondCollateral != null) return false;
        if (bondId != null ? !bondId.equals(that.bondId) : that.bondId != null) return false;
        if (bondKey != null ? !bondKey.equals(that.bondKey) : that.bondKey != null) return false;
        if (bondSubtype != null ? !bondSubtype.equals(that.bondSubtype) : that.bondSubtype != null) return false;
        if (bondType != null ? !bondType.equals(that.bondType) : that.bondType != null) return false;
        if (callNo != null ? !callNo.equals(that.callNo) : that.callNo != null) return false;
        if (callStr != null ? !callStr.equals(that.callStr) : that.callStr != null) return false;
        if (cap != null ? !cap.equals(that.cap) : that.cap != null) return false;
        if (checker != null ? !checker.equals(that.checker) : that.checker != null) return false;
        if (collateralType != null ? !collateralType.equals(that.collateralType) : that.collateralType != null) return false;
        if (collateralValue != null ? !collateralValue.equals(that.collateralValue) : that.collateralValue != null) return false;
        if (compensateFrom != null ? !compensateFrom.equals(that.compensateFrom) : that.compensateFrom != null) return false;
        if (compensateRate != null ? !compensateRate.equals(that.compensateRate) : that.compensateRate != null) return false;
        if (compoundFrequency != null ? !compoundFrequency.equals(that.compoundFrequency) : that.compoundFrequency != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (couponCalendarKey != null ? !couponCalendarKey.equals(that.couponCalendarKey) : that.couponCalendarKey != null) return false;
        if (couponDayAdjust != null ? !couponDayAdjust.equals(that.couponDayAdjust) : that.couponDayAdjust != null) return false;
        if (couponDayDmc != null ? !couponDayDmc.equals(that.couponDayDmc) : that.couponDayDmc != null) return false;
        if (couponDist != null ? !couponDist.equals(that.couponDist) : that.couponDist != null) return false;
        if (couponFrequency != null ? !couponFrequency.equals(that.couponFrequency) : that.couponFrequency != null) return false;
        if (couponRateCurrent != null ? !couponRateCurrent.equals(that.couponRateCurrent) : that.couponRateCurrent != null) return false;
        if (couponRateSpread != null ? !couponRateSpread.equals(that.couponRateSpread) : that.couponRateSpread != null) return false;
        if (couponType != null ? !couponType.equals(that.couponType) : that.couponType != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (delflag != null ? !delflag.equals(that.delflag) : that.delflag != null) return false;
        if (delistedDate != null ? !delistedDate.equals(that.delistedDate) : that.delistedDate != null) return false;
        if (distDateEnd != null ? !distDateEnd.equals(that.distDateEnd) : that.distDateEnd != null) return false;
        if (distDateStart != null ? !distDateStart.equals(that.distDateStart) : that.distDateStart != null) return false;
        if (etsStr != null ? !etsStr.equals(that.etsStr) : that.etsStr != null) return false;
        if (evaluationDate != null ? !evaluationDate.equals(that.evaluationDate) : that.evaluationDate != null) return false;
        if (externalType != null ? !externalType.equals(that.externalType) : that.externalType != null) return false;
        if (firstCouponDate != null ? !firstCouponDate.equals(that.firstCouponDate) : that.firstCouponDate != null) return false;
        if (firstExchgBondKey != null ? !firstExchgBondKey.equals(that.firstExchgBondKey) : that.firstExchgBondKey != null) return false;
        if (firstExchgNo != null ? !firstExchgNo.equals(that.firstExchgNo) : that.firstExchgNo != null) return false;
        if (firstExchgStr != null ? !firstExchgStr.equals(that.firstExchgStr) : that.firstExchgStr != null) return false;
        if (firstIndexRate != null ? !firstIndexRate.equals(that.firstIndexRate) : that.firstIndexRate != null) return false;
        if (firstIssueAmount != null ? !firstIssueAmount.equals(that.firstIssueAmount) : that.firstIssueAmount != null) return false;
        if (firstIssueKey != null ? !firstIssueKey.equals(that.firstIssueKey) : that.firstIssueKey != null) return false;
        if (fixingCalendarKey != null ? !fixingCalendarKey.equals(that.fixingCalendarKey) : that.fixingCalendarKey != null) return false;
        if (fixingDigit != null ? !fixingDigit.equals(that.fixingDigit) : that.fixingDigit != null) return false;
        if (fixingFrequency != null ? !fixingFrequency.equals(that.fixingFrequency) : that.fixingFrequency != null) return false;
        if (fixingMaDays != null ? !fixingMaDays.equals(that.fixingMaDays) : that.fixingMaDays != null) return false;
        if (fixingPreceds != null ? !fixingPreceds.equals(that.fixingPreceds) : that.fixingPreceds != null) return false;
        if (fixingTailing != null ? !fixingTailing.equals(that.fixingTailing) : that.fixingTailing != null) return false;
        if (flr != null ? !flr.equals(that.flr) : that.flr != null) return false;
        if (frnIndexId != null ? !frnIndexId.equals(that.frnIndexId) : that.frnIndexId != null) return false;
        if (frnMultiplier != null ? !frnMultiplier.equals(that.frnMultiplier) : that.frnMultiplier != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (inputer != null ? !inputer.equals(that.inputer) : that.inputer != null) return false;
        if (interestBasis != null ? !interestBasis.equals(that.interestBasis) : that.interestBasis != null) return false;
        if (interestStartDate != null ? !interestStartDate.equals(that.interestStartDate) : that.interestStartDate != null) return false;
        if (isCib != null ? !isCib.equals(that.isCib) : that.isCib != null) return false;
        if (isCrossMkt != null ? !isCrossMkt.equals(that.isCrossMkt) : that.isCrossMkt != null) return false;
        if (isMortgage != null ? !isMortgage.equals(that.isMortgage) : that.isMortgage != null) return false;
        if (isMunicipal != null ? !isMunicipal.equals(that.isMunicipal) : that.isMunicipal != null) return false;
        if (isin != null ? !isin.equals(that.isin) : that.isin != null) return false;
        if (issueAmount != null ? !issueAmount.equals(that.issueAmount) : that.issueAmount != null) return false;
        if (issueBonusRate != null ? !issueBonusRate.equals(that.issueBonusRate) : that.issueBonusRate != null) return false;
        if (issueCommissionRate != null ? !issueCommissionRate.equals(that.issueCommissionRate) : that.issueCommissionRate != null) return false;
        if (issueEndDate != null ? !issueEndDate.equals(that.issueEndDate) : that.issueEndDate != null) return false;
        if (issueNo != null ? !issueNo.equals(that.issueNo) : that.issueNo != null) return false;
        if (issuePrice != null ? !issuePrice.equals(that.issuePrice) : that.issuePrice != null) return false;
        if (issueRate != null ? !issueRate.equals(that.issueRate) : that.issueRate != null) return false;
        if (issueReference != null ? !issueReference.equals(that.issueReference) : that.issueReference != null) return false;
        if (issueReferenceDesc != null ? !issueReferenceDesc.equals(that.issueReferenceDesc) : that.issueReferenceDesc != null) return false;
        if (issueStartDate != null ? !issueStartDate.equals(that.issueStartDate) : that.issueStartDate != null) return false;
        if (issueYear != null ? !issueYear.equals(that.issueYear) : that.issueYear != null) return false;
        if (issuerCode != null ? !issuerCode.equals(that.issuerCode) : that.issuerCode != null) return false;
        if (issuerKeyLei != null ? !issuerKeyLei.equals(that.issuerKeyLei) : that.issuerKeyLei != null) return false;
        if (issuerOutlookCurrent != null ? !issuerOutlookCurrent.equals(that.issuerOutlookCurrent) : that.issuerOutlookCurrent != null) return false;
        if (issuerRatingCurrent != null ? !issuerRatingCurrent.equals(that.issuerRatingCurrent) : that.issuerRatingCurrent != null) return false;
        if (issuerRatingCurrentNpy != null ? !issuerRatingCurrentNpy.equals(that.issuerRatingCurrentNpy) : that.issuerRatingCurrentNpy != null) return false;
        if (issuerRatingDate != null ? !issuerRatingDate.equals(that.issuerRatingDate) : that.issuerRatingDate != null) return false;
        if (issuerRatingInstitutionCode != null ? !issuerRatingInstitutionCode.equals(that.issuerRatingInstitutionCode) : that.issuerRatingInstitutionCode != null) return false;
        if (liquiditySupporter != null ? !liquiditySupporter.equals(that.liquiditySupporter) : that.liquiditySupporter != null) return false;
        if (listedDate != null ? !listedDate.equals(that.listedDate) : that.listedDate != null) return false;
        if (maturityAdjust != null ? !maturityAdjust.equals(that.maturityAdjust) : that.maturityAdjust != null) return false;
        if (maturityCalendarKey != null ? !maturityCalendarKey.equals(that.maturityCalendarKey) : that.maturityCalendarKey != null) return false;
        if (maturityDate != null ? !maturityDate.equals(that.maturityDate) : that.maturityDate != null) return false;
        if (maturityDmc != null ? !maturityDmc.equals(that.maturityDmc) : that.maturityDmc != null) return false;
        if (maturityTerm != null ? !maturityTerm.equals(that.maturityTerm) : that.maturityTerm != null) return false;
        if (maturityTermY != null ? !maturityTermY.equals(that.maturityTermY) : that.maturityTermY != null) return false;
        if (mktType != null ? !mktType.equals(that.mktType) : that.mktType != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;
        if (nextCouponDate != null ? !nextCouponDate.equals(that.nextCouponDate) : that.nextCouponDate != null) return false;
        if (noAddIssue != null ? !noAddIssue.equals(that.noAddIssue) : that.noAddIssue != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (optionDate != null ? !optionDate.equals(that.optionDate) : that.optionDate != null) return false;
        if (optionExercise != null ? !optionExercise.equals(that.optionExercise) : that.optionExercise != null) return false;
        if (optionExerciseDate != null ? !optionExerciseDate.equals(that.optionExerciseDate) : that.optionExerciseDate != null) return false;
        if (optionPriceRatio != null ? !optionPriceRatio.equals(that.optionPriceRatio) : that.optionPriceRatio != null) return false;
        if (optionRecordDate != null ? !optionRecordDate.equals(that.optionRecordDate) : that.optionRecordDate != null) return false;
        if (optionStyle != null ? !optionStyle.equals(that.optionStyle) : that.optionStyle != null) return false;
        if (optionType != null ? !optionType.equals(that.optionType) : that.optionType != null) return false;
        if (outstandingAmount != null ? !outstandingAmount.equals(that.outstandingAmount) : that.outstandingAmount != null) return false;
        if (pIssuerOutlookCurrent != null ? !pIssuerOutlookCurrent.equals(that.pIssuerOutlookCurrent) : that.pIssuerOutlookCurrent != null) return false;
        if (pIssuerRatingCurrent != null ? !pIssuerRatingCurrent.equals(that.pIssuerRatingCurrent) : that.pIssuerRatingCurrent != null) return false;
        if (pRatingCurrent != null ? !pRatingCurrent.equals(that.pRatingCurrent) : that.pRatingCurrent != null) return false;
        if (paymentDate != null ? !paymentDate.equals(that.paymentDate) : that.paymentDate != null) return false;
        if (plannedIssueAmount != null ? !plannedIssueAmount.equals(that.plannedIssueAmount) : that.plannedIssueAmount != null) return false;
        if (pricingConv != null ? !pricingConv.equals(that.pricingConv) : that.pricingConv != null) return false;
        if (purposeOfIssue != null ? !purposeOfIssue.equals(that.purposeOfIssue) : that.purposeOfIssue != null) return false;
        if (putNo != null ? !putNo.equals(that.putNo) : that.putNo != null) return false;
        if (putStr != null ? !putStr.equals(that.putStr) : that.putStr != null) return false;
        if (quotation != null ? !quotation.equals(that.quotation) : that.quotation != null) return false;
        if (ratingAugment != null ? !ratingAugment.equals(that.ratingAugment) : that.ratingAugment != null) return false;
        if (ratingCurrent != null ? !ratingCurrent.equals(that.ratingCurrent) : that.ratingCurrent != null) return false;
        if (ratingCurrentNpy != null ? !ratingCurrentNpy.equals(that.ratingCurrentNpy) : that.ratingCurrentNpy != null) return false;
        if (ratingDate != null ? !ratingDate.equals(that.ratingDate) : that.ratingDate != null) return false;
        if (ratingInstitutionCode != null ? !ratingInstitutionCode.equals(that.ratingInstitutionCode) : that.ratingInstitutionCode != null) return false;
        if (redemptionNo != null ? !redemptionNo.equals(that.redemptionNo) : that.redemptionNo != null) return false;
        if (redemptionStr != null ? !redemptionStr.equals(that.redemptionStr) : that.redemptionStr != null) return false;
        if (redmCommissionRate != null ? !redmCommissionRate.equals(that.redmCommissionRate) : that.redmCommissionRate != null) return false;
        if (register != null ? !register.equals(that.register) : that.register != null) return false;
        if (resetEffective != null ? !resetEffective.equals(that.resetEffective) : that.resetEffective != null) return false;
        if (riskWeight != null ? !riskWeight.equals(that.riskWeight) : that.riskWeight != null) return false;
        if (sceniority != null ? !sceniority.equals(that.sceniority) : that.sceniority != null) return false;
        if (secExchgNo != null ? !secExchgNo.equals(that.secExchgNo) : that.secExchgNo != null) return false;
        if (secExchgYear != null ? !secExchgYear.equals(that.secExchgYear) : that.secExchgYear != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (simpleCompound != null ? !simpleCompound.equals(that.simpleCompound) : that.simpleCompound != null) return false;
        if (sts != null ? !sts.equals(that.sts) : that.sts != null) return false;
        if (termBeforeExercise != null ? !termBeforeExercise.equals(that.termBeforeExercise) : that.termBeforeExercise != null) return false;
        if (termStructure != null ? !termStructure.equals(that.termStructure) : that.termStructure != null) return false;
        if (termUnit != null ? !termUnit.equals(that.termUnit) : that.termUnit != null) return false;
        if (underwriterCode != null ? !underwriterCode.equals(that.underwriterCode) : that.underwriterCode != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (variableSchedule != null ? !variableSchedule.equals(that.variableSchedule) : that.variableSchedule != null) return false;
        if (warrantNote != null ? !warrantNote.equals(that.warrantNote) : that.warrantNote != null) return false;
        if (warranter != null ? !warranter.equals(that.warranter) : that.warranter != null) return false;
        if (wiEndDate != null ? !wiEndDate.equals(that.wiEndDate) : that.wiEndDate != null) return false;
        if (wiStartDate != null ? !wiStartDate.equals(that.wiStartDate) : that.wiStartDate != null) return false;
        if (ycCpnKey != null ? !ycCpnKey.equals(that.ycCpnKey) : that.ycCpnKey != null) return false;
        if (ycDctKey != null ? !ycDctKey.equals(that.ycDctKey) : that.ycDctKey != null) return false;
        if (yieldCurveId != null ? !yieldCurveId.equals(that.yieldCurveId) : that.yieldCurveId != null) return false;
        if (yieldCurveType != null ? !yieldCurveType.equals(that.yieldCurveType) : that.yieldCurveType != null) return false;

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
        result = 31 * result + (bondKey != null ? bondKey.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (bondId != null ? bondId.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (isin != null ? isin.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (issuerCode != null ? issuerCode.hashCode() : 0);
        result = 31 * result + (issuerKeyLei != null ? issuerKeyLei.hashCode() : 0);
        result = 31 * result + (underwriterCode != null ? underwriterCode.hashCode() : 0);
        result = 31 * result + (yieldCurveId != null ? yieldCurveId.hashCode() : 0);
        result = 31 * result + (yieldCurveType != null ? yieldCurveType.hashCode() : 0);
        result = 31 * result + (bondType != null ? bondType.hashCode() : 0);
        result = 31 * result + (bondSubtype != null ? bondSubtype.hashCode() : 0);
        result = 31 * result + (externalType != null ? externalType.hashCode() : 0);
        result = 31 * result + (maturityTermY != null ? maturityTermY.hashCode() : 0);
        result = 31 * result + (maturityTerm != null ? maturityTerm.hashCode() : 0);
        result = 31 * result + (termUnit != null ? termUnit.hashCode() : 0);
        result = 31 * result + (interestStartDate != null ? interestStartDate.hashCode() : 0);
        result = 31 * result + (maturityDate != null ? maturityDate.hashCode() : 0);
        result = 31 * result + (firstCouponDate != null ? firstCouponDate.hashCode() : 0);
        result = 31 * result + (nextCouponDate != null ? nextCouponDate.hashCode() : 0);
        result = 31 * result + (announceDate != null ? announceDate.hashCode() : 0);
        result = 31 * result + (issueStartDate != null ? issueStartDate.hashCode() : 0);
        result = 31 * result + (issueEndDate != null ? issueEndDate.hashCode() : 0);
        result = 31 * result + (wiStartDate != null ? wiStartDate.hashCode() : 0);
        result = 31 * result + (wiEndDate != null ? wiEndDate.hashCode() : 0);
        result = 31 * result + (distDateStart != null ? distDateStart.hashCode() : 0);
        result = 31 * result + (distDateEnd != null ? distDateEnd.hashCode() : 0);
        result = 31 * result + (auctionDateStart != null ? auctionDateStart.hashCode() : 0);
        result = 31 * result + (auctionDateEnd != null ? auctionDateEnd.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (listedDate != null ? listedDate.hashCode() : 0);
        result = 31 * result + (delistedDate != null ? delistedDate.hashCode() : 0);
        result = 31 * result + (register != null ? register.hashCode() : 0);
        result = 31 * result + (optionType != null ? optionType.hashCode() : 0);
        result = 31 * result + (optionStyle != null ? optionStyle.hashCode() : 0);
        result = 31 * result + (optionDate != null ? optionDate.hashCode() : 0);
        result = 31 * result + (optionPriceRatio != null ? optionPriceRatio.hashCode() : 0);
        result = 31 * result + (optionRecordDate != null ? optionRecordDate.hashCode() : 0);
        result = 31 * result + (callNo != null ? callNo.hashCode() : 0);
        result = 31 * result + (callStr != null ? callStr.hashCode() : 0);
        result = 31 * result + (putNo != null ? putNo.hashCode() : 0);
        result = 31 * result + (putStr != null ? putStr.hashCode() : 0);
        result = 31 * result + (firstExchgBondKey != null ? firstExchgBondKey.hashCode() : 0);
        result = 31 * result + (firstExchgNo != null ? firstExchgNo.hashCode() : 0);
        result = 31 * result + (firstExchgStr != null ? firstExchgStr.hashCode() : 0);
        result = 31 * result + (secExchgNo != null ? secExchgNo.hashCode() : 0);
        result = 31 * result + (secExchgYear != null ? secExchgYear.hashCode() : 0);
        result = 31 * result + (assignTransKey != null ? assignTransKey.hashCode() : 0);
        result = 31 * result + (assignTransNo != null ? assignTransNo.hashCode() : 0);
        result = 31 * result + (assignTransStr != null ? assignTransStr.hashCode() : 0);
        result = 31 * result + (compensateRate != null ? compensateRate.hashCode() : 0);
        result = 31 * result + (compensateFrom != null ? compensateFrom.hashCode() : 0);
        result = 31 * result + (optionExercise != null ? optionExercise.hashCode() : 0);
        result = 31 * result + (optionExerciseDate != null ? optionExerciseDate.hashCode() : 0);
        result = 31 * result + (couponType != null ? couponType.hashCode() : 0);
        result = 31 * result + (couponRateSpread != null ? couponRateSpread.hashCode() : 0);
        result = 31 * result + (couponRateCurrent != null ? couponRateCurrent.hashCode() : 0);
        result = 31 * result + (couponFrequency != null ? couponFrequency.hashCode() : 0);
        result = 31 * result + (compoundFrequency != null ? compoundFrequency.hashCode() : 0);
        result = 31 * result + (interestBasis != null ? interestBasis.hashCode() : 0);
        result = 31 * result + (couponDist != null ? couponDist.hashCode() : 0);
        result = 31 * result + (frnMultiplier != null ? frnMultiplier.hashCode() : 0);
        result = 31 * result + (frnIndexId != null ? frnIndexId.hashCode() : 0);
        result = 31 * result + (firstIndexRate != null ? firstIndexRate.hashCode() : 0);
        result = 31 * result + (fixingFrequency != null ? fixingFrequency.hashCode() : 0);
        result = 31 * result + (fixingMaDays != null ? fixingMaDays.hashCode() : 0);
        result = 31 * result + (fixingPreceds != null ? fixingPreceds.hashCode() : 0);
        result = 31 * result + (fixingCalendarKey != null ? fixingCalendarKey.hashCode() : 0);
        result = 31 * result + (fixingTailing != null ? fixingTailing.hashCode() : 0);
        result = 31 * result + (fixingDigit != null ? fixingDigit.hashCode() : 0);
        result = 31 * result + (resetEffective != null ? resetEffective.hashCode() : 0);
        result = 31 * result + (cap != null ? cap.hashCode() : 0);
        result = 31 * result + (flr != null ? flr.hashCode() : 0);
        result = 31 * result + (simpleCompound != null ? simpleCompound.hashCode() : 0);
        result = 31 * result + (issueReference != null ? issueReference.hashCode() : 0);
        result = 31 * result + (issueReferenceDesc != null ? issueReferenceDesc.hashCode() : 0);
        result = 31 * result + (variableSchedule != null ? variableSchedule.hashCode() : 0);
        result = 31 * result + (couponDayAdjust != null ? couponDayAdjust.hashCode() : 0);
        result = 31 * result + (couponDayDmc != null ? couponDayDmc.hashCode() : 0);
        result = 31 * result + (couponCalendarKey != null ? couponCalendarKey.hashCode() : 0);
        result = 31 * result + (pricingConv != null ? pricingConv.hashCode() : 0);
        result = 31 * result + (issuePrice != null ? issuePrice.hashCode() : 0);
        result = 31 * result + (issueRate != null ? issueRate.hashCode() : 0);
        result = 31 * result + (plannedIssueAmount != null ? plannedIssueAmount.hashCode() : 0);
        result = 31 * result + (firstIssueAmount != null ? firstIssueAmount.hashCode() : 0);
        result = 31 * result + (issueAmount != null ? issueAmount.hashCode() : 0);
        result = 31 * result + (outstandingAmount != null ? outstandingAmount.hashCode() : 0);
        result = 31 * result + (redemptionNo != null ? redemptionNo.hashCode() : 0);
        result = 31 * result + (redemptionStr != null ? redemptionStr.hashCode() : 0);
        result = 31 * result + (maturityAdjust != null ? maturityAdjust.hashCode() : 0);
        result = 31 * result + (maturityDmc != null ? maturityDmc.hashCode() : 0);
        result = 31 * result + (maturityCalendarKey != null ? maturityCalendarKey.hashCode() : 0);
        result = 31 * result + (sceniority != null ? sceniority.hashCode() : 0);
        result = 31 * result + (ratingCurrent != null ? ratingCurrent.hashCode() : 0);
        result = 31 * result + (ratingInstitutionCode != null ? ratingInstitutionCode.hashCode() : 0);
        result = 31 * result + (ratingDate != null ? ratingDate.hashCode() : 0);
        result = 31 * result + (ratingAugment != null ? ratingAugment.hashCode() : 0);
        result = 31 * result + (warranter != null ? warranter.hashCode() : 0);
        result = 31 * result + (warrantNote != null ? warrantNote.hashCode() : 0);
        result = 31 * result + (bondCollateral != null ? bondCollateral.hashCode() : 0);
        result = 31 * result + (collateralValue != null ? collateralValue.hashCode() : 0);
        result = 31 * result + (evaluationDate != null ? evaluationDate.hashCode() : 0);
        result = 31 * result + (collateralType != null ? collateralType.hashCode() : 0);
        result = 31 * result + (issuerRatingCurrent != null ? issuerRatingCurrent.hashCode() : 0);
        result = 31 * result + (issuerOutlookCurrent != null ? issuerOutlookCurrent.hashCode() : 0);
        result = 31 * result + (issuerRatingInstitutionCode != null ? issuerRatingInstitutionCode.hashCode() : 0);
        result = 31 * result + (issuerRatingDate != null ? issuerRatingDate.hashCode() : 0);
        result = 31 * result + (issueCommissionRate != null ? issueCommissionRate.hashCode() : 0);
        result = 31 * result + (issueBonusRate != null ? issueBonusRate.hashCode() : 0);
        result = 31 * result + (redmCommissionRate != null ? redmCommissionRate.hashCode() : 0);
        result = 31 * result + (riskWeight != null ? riskWeight.hashCode() : 0);
        result = 31 * result + (issueYear != null ? issueYear.hashCode() : 0);
        result = 31 * result + (issueNo != null ? issueNo.hashCode() : 0);
        result = 31 * result + (quotation != null ? quotation.hashCode() : 0);
        result = 31 * result + (ycDctKey != null ? ycDctKey.hashCode() : 0);
        result = 31 * result + (ycCpnKey != null ? ycCpnKey.hashCode() : 0);
        result = 31 * result + (assetStatus != null ? assetStatus.hashCode() : 0);
        result = 31 * result + (purposeOfIssue != null ? purposeOfIssue.hashCode() : 0);
        result = 31 * result + (firstIssueKey != null ? firstIssueKey.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (termStructure != null ? termStructure.hashCode() : 0);
        result = 31 * result + (termBeforeExercise != null ? termBeforeExercise.hashCode() : 0);
        result = 31 * result + (pRatingCurrent != null ? pRatingCurrent.hashCode() : 0);
        result = 31 * result + (pIssuerRatingCurrent != null ? pIssuerRatingCurrent.hashCode() : 0);
        result = 31 * result + (pIssuerOutlookCurrent != null ? pIssuerOutlookCurrent.hashCode() : 0);
        result = 31 * result + (noAddIssue != null ? noAddIssue.hashCode() : 0);
        result = 31 * result + (isCrossMkt != null ? isCrossMkt.hashCode() : 0);
        result = 31 * result + (isMortgage != null ? isMortgage.hashCode() : 0);
        result = 31 * result + (mktType != null ? mktType.hashCode() : 0);
        result = 31 * result + (annStatus != null ? annStatus.hashCode() : 0);
        result = 31 * result + (liquiditySupporter != null ? liquiditySupporter.hashCode() : 0);
        result = 31 * result + (isCib != null ? isCib.hashCode() : 0);
        result = 31 * result + (isMunicipal != null ? isMunicipal.hashCode() : 0);
        result = 31 * result + (ratingCurrentNpy != null ? ratingCurrentNpy.hashCode() : 0);
        result = 31 * result + (issuerRatingCurrentNpy != null ? issuerRatingCurrentNpy.hashCode() : 0);
        result = 31 * result + (etsStr != null ? etsStr.hashCode() : 0);
        return result;
    }
}
