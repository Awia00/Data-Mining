package Common;

import Common.Interfaces.SpaceComparable;

/**
 * Created by ander on 27-02-2017.
 */
public class NominalSpaceComparable<T extends Enum> implements SpaceComparable<NominalSpaceComparable> {
    private T value;

    public NominalSpaceComparable(T value) {
        this.value = value;
    }

    @Override
    public double distance(NominalSpaceComparable comparable) {
        return comparable.value.equals(value) ? 0 : 1;
    }

    public T getValue() {
        return value;
    }

    public int range(){ return value.getClass().getEnumConstants().length; }

    @Override
    public String toString() {
        return value.toString();
    }
}
