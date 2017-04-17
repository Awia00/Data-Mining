package Lab3;

import java.util.Set;

/**
 * Created by aws on 16-04-2017.
 */
public class AssociationRule {
    public final Set from;
    public final Set to;
    public final double confidence;

    public AssociationRule(Set from, Set to, double confidence) {
        this.from = from;
        this.to = to;
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "AssociationRule: [" + from + "  => " + to + "] with confidence: " + ((int)(confidence*100)) + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssociationRule that = (AssociationRule) o;

        if (Double.compare(that.confidence, confidence) != 0) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return to != null ? to.equals(that.to) : that.to == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        temp = Double.doubleToLongBits(confidence);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
