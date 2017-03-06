package Common;

import Common.Interfaces.SpaceComparable;

/**
 * Created by ander on 27-02-2017.
 */
public class EuclideanSpaceComparable<T extends Number> implements SpaceComparable<EuclideanSpaceComparable<T>> {
    private final T value;

    public EuclideanSpaceComparable(T value) {
        this.value = value;
    }

    @Override
    public double distance(EuclideanSpaceComparable<T> comparable) {
        double delta = (value.doubleValue() - comparable.value.doubleValue());
        return delta * delta;
    }

    public double getDoubleValue() {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
