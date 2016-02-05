package jssp.analytic.refdata;

import java.util.Map;

public interface ReferenceDataSource<KeyType, ValueType> extends Refreshable {

    ValueType get(KeyType key);

    Map<KeyType, ValueType> allData();
}
