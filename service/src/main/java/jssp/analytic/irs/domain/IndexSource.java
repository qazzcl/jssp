package jssp.analytic.irs.domain;

import com.google.common.base.Optional;
import jssp.analytic.index.IndexRate;
import org.quantlib.Date;

public interface IndexSource {

    Optional<IndexRate> rateOf(Date date);
}
