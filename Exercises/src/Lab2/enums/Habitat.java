package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Habitat implements SpaceComparable<Habitat> {
    grasses,
    leaves,
    meadows,
    paths,
    urban,
    waste,
    woods;

    public double distance(Habitat comparable) {
        return this == comparable ? 0 : 1;
    }
}
