package jssp.analytic.index.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "macro_base_index_values", schema = "")
@IdClass(MacroBaseIndexValuesEntityPK.class)
public class MacroBaseIndexValuesEntity {
    private long id;
    private String gjkCode;
    private Double indexValue;
    private Date indexDate;

    @Basic
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "GJK_Code")
    public String getGjkCode() {
        return gjkCode;
    }

    public void setGjkCode(String gjkCode) {
        this.gjkCode = gjkCode;
    }

    @Basic
    @Column(name = "Index_Value")
    public Double getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(Double indexValue) {
        this.indexValue = indexValue;
    }

    @Id
    @Column(name = "Index_Date")
    public Date getIndexDate() {
        return indexDate;
    }

    public void setIndexDate(Date indexDate) {
        this.indexDate = indexDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MacroBaseIndexValuesEntity that = (MacroBaseIndexValuesEntity) o;

        if (id != that.id) return false;
        if (gjkCode != null ? !gjkCode.equals(that.gjkCode) : that.gjkCode != null) return false;
        if (indexDate != null ? !indexDate.equals(that.indexDate) : that.indexDate != null) return false;
        if (indexValue != null ? !indexValue.equals(that.indexValue) : that.indexValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gjkCode != null ? gjkCode.hashCode() : 0);
        result = 31 * result + (indexValue != null ? indexValue.hashCode() : 0);
        result = 31 * result + (indexDate != null ? indexDate.hashCode() : 0);
        return result;
    }
}
