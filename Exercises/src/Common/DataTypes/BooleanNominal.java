package Common.DataTypes;

enum PosNeg {
    positive,
    negative;
}
public class BooleanNominal extends Nominal {

    public BooleanNominal(PosNeg value) {
        super(value);
    }

    private static BooleanNominal negative =  new BooleanNominal(PosNeg.negative);
    private static BooleanNominal positive =  new BooleanNominal(PosNeg.positive);
    public static BooleanNominal negative(){return negative;}
    public static BooleanNominal positive(){return positive;}
}
