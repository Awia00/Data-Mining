package Common.DataTypes;

import java.util.ArrayList;
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

    public BooleanNominal getBooleanNominalWithRespectTo(Enum other){
        if(other.getClass() != value.getClass()) throw new RuntimeException("non comparable types");
        if(other.equals(value))
            return new BooleanNominal(PosNeg.positive);

        return new BooleanNominal(PosNeg.negative);
    }

    public Collection<BooleanNominal> getBooleanNominalVersion(){
        Collection<BooleanNominal> result = new ArrayList<>();
        for (Enum anEnum : value.getClass().getEnumConstants()) {
            if(anEnum == value)
                result.add(new BooleanNominal(PosNeg.positive));
            else
                result.add(new BooleanNominal(PosNeg.negative));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Nominal{" +
                "value=" + value +
                '}';
    }
}