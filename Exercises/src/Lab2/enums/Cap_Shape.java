package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Cap_Shape implements SpaceComparable<Cap_Shape> {
    bell,
    conical,
    convex,
    flat,
    knobbed,
    sunken;

    public double distance(Cap_Shape comparable) {
        return this == comparable ? 0 : 1;
    }
}
