package Lab2.enums;

import Common.Interfaces.SpaceComparable;

public enum Gill_Attachment implements SpaceComparable<Gill_Attachment> {
    attached,
    descending,
    free,
    notched;

    public double distance(Gill_Attachment comparable) {
        return this == comparable ? 0 : 1;
    }
}
