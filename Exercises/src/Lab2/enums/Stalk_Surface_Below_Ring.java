package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Stalk_Surface_Below_Ring implements SpaceComparable<Stalk_Surface_Below_Ring> {
    ibrous,
    scaly,
    silky,
    smooth;

    public double distance(Stalk_Surface_Below_Ring comparable) {
        return this == comparable ? 0 : 1;
    }
}
