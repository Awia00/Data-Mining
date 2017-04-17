package Common.DataTypes;

enum PosNeg {
    positive,
    negative
}
public class Binary extends Nominal {

    public Binary(PosNeg value) {
        super(value);
    }

    private static Binary negative =  new Binary(PosNeg.negative);
    private static Binary positive =  new Binary(PosNeg.positive);
    public static Binary negative(){return negative;}
    public static Binary positive(){return positive;}

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
