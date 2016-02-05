package jssp.analytic.irs.instrument;

public class SpreadIrs extends CompositeIrs {

    public final SingleIrs shortEnd;

    public final SingleIrs longEnd;

    public SpreadIrs(SingleIrs shortEnd, SingleIrs longEnd) {
        super(shortEnd, longEnd);
        this.shortEnd = shortEnd;
        this.longEnd = longEnd;
    }
}

