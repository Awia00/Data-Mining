package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Gill_Spacing implements SpaceComparable<Gill_Spacing> {
    close,
    crowded,
    distant;

    public double distance(Gill_Spacing comparable) {
        return this == comparable ? 0 : 1;
    }
}
