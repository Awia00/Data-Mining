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
        if(other.value.getClass() != value.getClass()) throw new RuntimeException("");
        return other.equals(this) ? 0 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nominal nominal = (Nominal) o;

        return value != null ? value.equals(nominal.value) : nominal.value == null;
    }

    public Binary getBooleanNominalWithRespectTo(Enum other){
        if(other.getClass() != value.getClass()) throw new RuntimeException("non comparable types");
        if(other.equals(value))
            return new Binary(PosNeg.positive);

        return new Binary(PosNeg.negative);
    }

    public Collection<Binary> getBooleanNominalVersion(){
        Collection<Binary> result = new ArrayList<>();
        for (Enum anEnum : value.getClass().getEnumConstants()) {
            if(anEnum == value)
                result.add(new Binary(PosNeg.positive));
            else
                result.add(new Binary(PosNeg.negative));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}