package Common.DataTypes;

/**
 * Created by ander on 27-02-2017.
 */
public class Numeric implements SpaceComparable<Numeric> {
    private double value;

    public Numeric(double value) {
        this.value = value;
    }

    public Double getValue(){
        return value;
    }
    public double distance(Numeric other){
        double delta = other.getValue()-getValue();
        return delta*delta;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
