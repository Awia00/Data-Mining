package Data.Answer.SpecialisedTypes;

import Common.DataTypes.Binary;
import Common.DataTypes.Nominal;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.NDimensionalPoint;
import Data.Answer.Answer;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by ander on 15-04-2017.
 */
public class BinaryClassifiableAnswer implements ClassifiablePoint<Binary> {

    private Answer answer;
    private int classifiableDimension;
    private Enum truthValue;

    public BinaryClassifiableAnswer(Answer answer, int classifiableDimension, Enum truthValue) {
        this.answer = answer;
        this.classifiableDimension = classifiableDimension;
        this.truthValue = truthValue;
    }

    @Override
    public Binary getClassification() {
        return answer.get(classifiableDimension).equals(new Nominal(truthValue)) ? Binary.positive() : Binary.negative();
    }

    @Override
    public boolean checkClassification(Binary other) {
        return getClassification().equals(other);
    }

    @Override
    public SpaceComparable get(Integer key) {
        return answer.get(key);
    }

    @Override
    public Set<Integer> keySet() {
        return answer.keySet();
    }

    @Override
    public Collection<SpaceComparable> values() {
        return answer.values();
    }

    @Override
    public Set<Map.Entry<Integer, SpaceComparable>> entrySet() {
        return answer.entrySet();
    }

    @Override
    public NDimensionalPoint getDefaultPoint() {
        return answer.getDefaultPoint();
    }

    @Override
    public String toString() {
        return answer.toString() + " class:" + getClassification();
    }

}
