package Common.DataTypes;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * Created by ander on 15-04-2017.
 */
public class Ordinal implements SpaceComparable<Ordinal> {

    protected int value;
    protected String printValue;
    @Override
    public double distance(Ordinal comparable) {
        return Math.abs(value - comparable.value);
    }
}

class AgeOrdinal extends Ordinal {

    public AgeOrdinal(int value) {
        super.value = value;
    }
}
