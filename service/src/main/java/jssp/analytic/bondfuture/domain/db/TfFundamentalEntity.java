package jssp.analytic.bondfuture.domain.db;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TF_Fundamental", schema = "")
public class TfFundamentalEntity {
    private String tfKey;

    @Id
    @javax.persistence.Column(name = "TF_Key")
    public String getTfKey() {
        return tfKey;
    }

    public void setTfKey(String tfKey) {
        this.tfKey = tfKey;
    }

    private String tfId;

    @Basic
    @javax.persistence.Column(name = "TF_ID")
    public String getTfId() {
        return tfId;
    }

    public void setTfId(String tfId) {
        this.tfId = tfId;
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

    private Integer deliveryMonth;

    @Basic
    @javax.persistence.Column(name = "Delivery_Month")
    public Integer getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(Integer deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    private Integer lastTradingDay;

    @Basic
    @javax.persistence.Column(name = "Last_Trading_Day")
    public Integer getLastTradingDay() {
        return lastTradingDay;
    }

    public void setLastTradingDay(Integer lastTradingDay) {
        this.lastTradingDay = lastTradingDay;
    }

    private Integer deliveryDate;

    @Basic
    @javax.persistence.Column(name = "Delivery_Date")
    public Integer getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Integer deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    private Integer bondTerm;

    @Basic
    @javax.persistence.Column(name = "Bond_Term")
    public Integer getBondTerm() {
        return bondTerm;
    }

    public void setBondTerm(Integer bondTerm) {
        this.bondTerm = bondTerm;
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

    private String underlyingAsset;

    @Basic
    @javax.persistence.Column(name = "Underlying_Asset")
    public String getUnderlyingAsset() {
        return underlyingAsset;
    }

    public void setUnderlyingAsset(String underlyingAsset) {
        this.underlyingAsset = underlyingAsset;
    }

    private String deliverableBonds;

    @Basic
    @javax.persistence.Column(name = "Deliverable_Bonds")
    public String getDeliverableBonds() {
        return deliverableBonds;
    }

    public void setDeliverableBonds(String deliverableBonds) {
        this.deliverableBonds = deliverableBonds;
    }

    private Integer deliverableMaturityStart;

    @Basic
    @javax.persistence.Column(name = "Deliverable_maturity_Start")
    public Integer getDeliverableMaturityStart() {
        return deliverableMaturityStart;
    }

    public void setDeliverableMaturityStart(Integer deliverableMaturityStart) {
        this.deliverableMaturityStart = deliverableMaturityStart;
    }

    private Integer deliverableMaturityEnd;

    @Basic
    @javax.persistence.Column(name = "Deliverable_maturity_End")
    public Integer getDeliverableMaturityEnd() {
        return deliverableMaturityEnd;
    }

    public void setDeliverableMaturityEnd(Integer deliverableMaturityEnd) {
        this.deliverableMaturityEnd = deliverableMaturityEnd;
    }

    private String quotedWay;

    @Basic
    @javax.persistence.Column(name = "Quoted_Way")
    public String getQuotedWay() {
        return quotedWay;
    }

    public void setQuotedWay(String quotedWay) {
        this.quotedWay = quotedWay;
    }

    private BigDecimal minChange;

    @Basic
    @javax.persistence.Column(name = "Min_Change")
    public BigDecimal getMinChange() {
        return minChange;
    }

    public void setMinChange(BigDecimal minChange) {
        this.minChange = minChange;
    }

    private String tradingTime;

    @Basic
    @javax.persistence.Column(name = "Trading_Time")
    public String getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(String tradingTime) {
        this.tradingTime = tradingTime;
    }

    private String lastDayTradingTime;

    @Basic
    @javax.persistence.Column(name = "Last_Day_Trading_Time")
    public String getLastDayTradingTime() {
        return lastDayTradingTime;
    }

    public void setLastDayTradingTime(String lastDayTradingTime) {
        this.lastDayTradingTime = lastDayTradingTime;
    }

    private BigDecimal priceLimit;

    @Basic
    @javax.persistence.Column(name = "Price_Limit")
    public BigDecimal getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(BigDecimal priceLimit) {
        this.priceLimit = priceLimit;
    }

    private BigDecimal lowestMargins;

    @Basic
    @javax.persistence.Column(name = "Lowest_Margins")
    public BigDecimal getLowestMargins() {
        return lowestMargins;
    }

    public void setLowestMargins(BigDecimal lowestMargins) {
        this.lowestMargins = lowestMargins;
    }

    private String deliveryWay;

    @Basic
    @javax.persistence.Column(name = "Delivery_Way")
    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    private String tradingId;

    @Basic
    @javax.persistence.Column(name = "Trading_ID")
    public String getTradingId() {
        return tradingId;
    }

    public void setTradingId(String tradingId) {
        this.tradingId = tradingId;
    }

    private String listedMarket;

    @Basic
    @javax.persistence.Column(name = "Listed_Market")
    public String getListedMarket() {
        return listedMarket;
    }

    public void setListedMarket(String listedMarket) {
        this.listedMarket = listedMarket;
    }

    private String isTradable;

    @Basic
    @javax.persistence.Column(name = "Is_Tradable")
    public String getIsTradable() {
        return isTradable;
    }

    public void setIsTradable(String isTradable) {
        this.isTradable = isTradable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TfFundamentalEntity that = (TfFundamentalEntity) o;

        if (bondTerm != null ? !bondTerm.equals(that.bondTerm) : that.bondTerm != null) return false;
        if (couponFrequency != null ? !couponFrequency.equals(that.couponFrequency) : that.couponFrequency != null) return false;
        if (deliverableBonds != null ? !deliverableBonds.equals(that.deliverableBonds) : that.deliverableBonds != null) return false;
        if (deliverableMaturityEnd != null ? !deliverableMaturityEnd.equals(that.deliverableMaturityEnd) : that.deliverableMaturityEnd != null) return false;
        if (deliverableMaturityStart != null ? !deliverableMaturityStart.equals(that.deliverableMaturityStart) : that.deliverableMaturityStart != null) return false;
        if (deliveryDate != null ? !deliveryDate.equals(that.deliveryDate) : that.deliveryDate != null) return false;
        if (deliveryMonth != null ? !deliveryMonth.equals(that.deliveryMonth) : that.deliveryMonth != null) return false;
        if (deliveryWay != null ? !deliveryWay.equals(that.deliveryWay) : that.deliveryWay != null) return false;
        if (isTradable != null ? !isTradable.equals(that.isTradable) : that.isTradable != null) return false;
        if (lastDayTradingTime != null ? !lastDayTradingTime.equals(that.lastDayTradingTime) : that.lastDayTradingTime != null) return false;
        if (lastTradingDay != null ? !lastTradingDay.equals(that.lastTradingDay) : that.lastTradingDay != null) return false;
        if (listedMarket != null ? !listedMarket.equals(that.listedMarket) : that.listedMarket != null) return false;
        if (lowestMargins != null ? !lowestMargins.equals(that.lowestMargins) : that.lowestMargins != null) return false;
        if (minChange != null ? !minChange.equals(that.minChange) : that.minChange != null) return false;
        if (priceLimit != null ? !priceLimit.equals(that.priceLimit) : that.priceLimit != null) return false;
        if (quotedWay != null ? !quotedWay.equals(that.quotedWay) : that.quotedWay != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (tfId != null ? !tfId.equals(that.tfId) : that.tfId != null) return false;
        if (tfKey != null ? !tfKey.equals(that.tfKey) : that.tfKey != null) return false;
        if (tradingId != null ? !tradingId.equals(that.tradingId) : that.tradingId != null) return false;
        if (tradingTime != null ? !tradingTime.equals(that.tradingTime) : that.tradingTime != null) return false;
        if (underlyingAsset != null ? !underlyingAsset.equals(that.underlyingAsset) : that.underlyingAsset != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tfKey != null ? tfKey.hashCode() : 0;
        result = 31 * result + (tfId != null ? tfId.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (deliveryMonth != null ? deliveryMonth.hashCode() : 0);
        result = 31 * result + (lastTradingDay != null ? lastTradingDay.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + (bondTerm != null ? bondTerm.hashCode() : 0);
        result = 31 * result + (couponFrequency != null ? couponFrequency.hashCode() : 0);
        result = 31 * result + (underlyingAsset != null ? underlyingAsset.hashCode() : 0);
        result = 31 * result + (deliverableBonds != null ? deliverableBonds.hashCode() : 0);
        result = 31 * result + (deliverableMaturityStart != null ? deliverableMaturityStart.hashCode() : 0);
        result = 31 * result + (deliverableMaturityEnd != null ? deliverableMaturityEnd.hashCode() : 0);
        result = 31 * result + (quotedWay != null ? quotedWay.hashCode() : 0);
        result = 31 * result + (minChange != null ? minChange.hashCode() : 0);
        result = 31 * result + (tradingTime != null ? tradingTime.hashCode() : 0);
        result = 31 * result + (lastDayTradingTime != null ? lastDayTradingTime.hashCode() : 0);
        result = 31 * result + (priceLimit != null ? priceLimit.hashCode() : 0);
        result = 31 * result + (lowestMargins != null ? lowestMargins.hashCode() : 0);
        result = 31 * result + (deliveryWay != null ? deliveryWay.hashCode() : 0);
        result = 31 * result + (tradingId != null ? tradingId.hashCode() : 0);
        result = 31 * result + (listedMarket != null ? listedMarket.hashCode() : 0);
        result = 31 * result + (isTradable != null ? isTradable.hashCode() : 0);
        return result;
    }
}
