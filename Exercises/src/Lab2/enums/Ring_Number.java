package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Ring_Number implements SpaceComparable<Ring_Number> {
    none,
    one,
    two;

    public double distance(Ring_Number comparable) {
        return this == comparable ? 0 : 1;
    }
}
