package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Veil_Type implements SpaceComparable<Veil_Type> {
    partial,
    universal;

    public double distance(Veil_Type comparable) {
        return this == comparable ? 0 : 1;
    }
}
