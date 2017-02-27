package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Stalk_Shape implements SpaceComparable<Stalk_Shape> {
    enlarging,
    tapering;

    public double distance(Stalk_Shape comparable) {
        return this == comparable ? 0 : 1;
    }
}
