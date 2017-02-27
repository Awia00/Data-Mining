package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Spore_Print_Color implements SpaceComparable<Spore_Print_Color> {
    black,
    brown,
    buff,
    chocolate,
    green,
    orange,
    purple,
    white,
    yellow;

    public double distance(Spore_Print_Color comparable) {
        return this == comparable ? 0 : 1;
    }
}
