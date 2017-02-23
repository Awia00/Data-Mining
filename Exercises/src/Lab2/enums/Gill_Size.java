package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Gill_Size implements SpaceComparable<Gill_Size> {
    broad,
    narrow;

    public double distance(Gill_Size comparable) {
        return this == comparable ? 0 : 1;
    }
}
