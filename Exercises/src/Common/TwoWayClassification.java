package Common;

enum TwoWayClassificationEnum {
    positive,
    negative;
}
public class TwoWayClassification extends NominalSpaceComparable{

    public TwoWayClassification(TwoWayClassificationEnum value) {
        super(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TwoWayClassification)) return false;
        else
            return getValue() == ((TwoWayClassification)obj).getValue();
    }

    public static TwoWayClassification negative(){return new TwoWayClassification(TwoWayClassificationEnum.negative);}
    public static TwoWayClassification positive(){return new TwoWayClassification(TwoWayClassificationEnum.positive);}
}
