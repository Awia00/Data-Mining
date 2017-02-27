package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Gill_Color implements SpaceComparable<Gill_Color> {
    black,
    brown,
    buff,
    chocolate,
    gray,
    green,
    orange,
    pink,
    purple,
    red,
    white,
    yellow;

    public double distance(Gill_Color comparable) {
        return this == comparable ? 0 : 1;
    }
}
