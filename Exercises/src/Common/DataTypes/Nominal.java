package Common.DataTypes;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ander on 27-02-2017.
 */
public class Nominal implements SpaceComparable<Nominal> {

    private Enum value;
    public Nominal(Enum value){
        this.value = value;
    }

    public Collection<Enum> getPossibleValues(){
        return Arrays.asList(value.getClass().getEnumConstants());
    }

    public Enum getValue() {
        return value;
    }

    public double distance(Nominal other){
        return other.equals(this) ? 0 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nominal nominal = (Nominal) o;

        return value != null ? value.equals(nominal.value) : nominal.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}