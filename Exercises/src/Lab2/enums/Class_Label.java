package Lab2.enums;

import Lab2.Interfaces.SpaceComparable;

public enum Class_Label implements SpaceComparable<Class_Label> {
    edible,
    poisonous;

    public double distance(Class_Label comparable) {
        return this == comparable ? 0 : 1;
    }
}
