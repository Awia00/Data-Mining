package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Odor implements SpaceComparable<Odor> {
    almond,
    anise,
    creosote,
    fishy,
    foul,
    musty,
    none,
    pungent,
    spicy;

    public double distance(Odor comparable) {
        return this == comparable ? 0 : 1;
    }
}
