package DataTypes;

/**
 * Created by ander on 20-03-2017.
 */
enum PosNeg{
    positive,
    negative
}
public class BooleanNominal extends Nominal {
    public BooleanNominal(PosNeg value) {
        super(value);
    }
    static PosNeg negative(){return PosNeg.negative; }
    static PosNeg positive(){return PosNeg.positive; }
}
