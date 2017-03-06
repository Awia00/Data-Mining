package Common.Interfaces;

import Common.AttributeKey;

/**
 * Created by ander on 27-02-2017.
 */
public abstract class NDimensionalPoint implements SpaceComparable<NDimensionalPoint>, WithAttributes<SpaceComparable> {

    @Override
    public double distance(NDimensionalPoint comparable) {
        if (getAttributes().size() != comparable.getAttributes().size())
            throw new RuntimeException("dimensionality does not uphold");
        double distance = 0.0;
        for (AttributeKey attributeKey : getAttributes()) {
            distance += getValueOfAttribute(attributeKey).distance(comparable.getValueOfAttribute(attributeKey));
        }
        return distance;
    }

    private static final double epsilon = 1E-10;

    public boolean almostEquals(NDimensionalPoint comparable) {
        return Math.abs(distance(comparable)) <= epsilon;
    }

    public boolean almostEquals(NDimensionalPoint comparable, double epsilonFactor) {
        return Math.abs(distance(comparable)) <= epsilonFactor * epsilon;
    }
}
