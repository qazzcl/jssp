package jssp.analytic.irs.instrument;

public class BasisIrs extends CompositeIrs {

    public final SingleIrs swapOne;
    public final SingleIrs swapTwo;

    public BasisIrs(SingleIrs swapOne, SingleIrs swapTwo) {
        super(swapOne, swapTwo);
        this.swapOne = swapOne;
        this.swapTwo = swapTwo;
    }

}
