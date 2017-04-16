package Lab3;

/**
 * Created by aws on 16-04-2017.
 */
public class AssociationRule {
    public final ItemSet from;
    public final ItemSet to;
    public final double confidence;

    public AssociationRule(ItemSet from, ItemSet to, double confidence) {
        this.from = from;
        this.to = to;
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "AssociationRule: [" + from + "  => " + to + "] with confidence: " + ((int)(confidence*100)) + "%";
    }
}
