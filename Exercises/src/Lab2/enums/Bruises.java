package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Bruises implements SpaceComparable<Bruises> {
    bruises,
    no_bruises;

    public double distance(Bruises comparable) {
        return this == comparable ? 0 : 1;
    }
}
