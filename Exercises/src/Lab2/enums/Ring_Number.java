package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Ring_Number implements SpaceComparable<Ring_Number> {
    none,
    one,
    two;

    public double distance(Ring_Number comparable) {
        return this == comparable ? 0 : 1;
    }
}
