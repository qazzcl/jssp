package jssp.analytic.index.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class MacroBaseIndexValuesEntityPK implements Serializable {
    private String gjkCode;
    private Date indexDate;

    @Column(name = "GJK_Code")
    @Id
    public String getGjkCode() {
        return gjkCode;
    }

    public void setGjkCode(String gjkCode) {
        this.gjkCode = gjkCode;
    }

    @Column(name = "Index_Date")
    @Id
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

        MacroBaseIndexValuesEntityPK that = (MacroBaseIndexValuesEntityPK) o;

        if (gjkCode != null ? !gjkCode.equals(that.gjkCode) : that.gjkCode != null) return false;
        if (indexDate != null ? !indexDate.equals(that.indexDate) : that.indexDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gjkCode != null ? gjkCode.hashCode() : 0;
        result = 31 * result + (indexDate != null ? indexDate.hashCode() : 0);
        return result;
    }
}
