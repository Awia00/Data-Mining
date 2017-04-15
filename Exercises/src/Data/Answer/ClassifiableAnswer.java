package Data.Answer;

import Common.DataTypes.Nominal;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.NDimensionalPoint;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by ander on 15-04-2017.
 */
public class ClassifiableAnswer<T extends Nominal> implements ClassifiablePoint<T> {

    private Answer answer;
    private int classifiableDimension;

    public ClassifiableAnswer(Answer answer, int classifiableDimension) {
        this.answer = answer;
        this.classifiableDimension = classifiableDimension;
    }

    @Override
    public T getClassification() {
        return (T) answer.get(classifiableDimension);
    }

    @Override
    public boolean checkClassification(T other) {
        return answer.get(classifiableDimension) == other;
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
        return answer.toString() + " class:" + answer.get(classifiableDimension);
    }
}
