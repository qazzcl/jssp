package jssp.analytic.index;

import org.joda.time.LocalDate;

public class IndexRate {

    public final String indexId;
    public final LocalDate date;
    public final double value;

    public IndexRate(String indexId, LocalDate date, double value) {
        this.indexId = indexId;
        this.date = date;
        this.value = value;
    }

    @Override
    public String toString() {
        return "IndexRate{" +
                "indexId='" + indexId + '\'' +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
