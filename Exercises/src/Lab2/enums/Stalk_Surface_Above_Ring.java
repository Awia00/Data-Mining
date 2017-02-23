package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Stalk_Surface_Above_Ring implements SpaceComparable<Stalk_Surface_Above_Ring> {
    ibrous,
    scaly,
    silky,
    smooth;

    public double distance(Stalk_Surface_Above_Ring comparable) {
        return this == comparable ? 0 : 1;
    }
}
