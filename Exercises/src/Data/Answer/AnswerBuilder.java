package Data.Answer;

import Common.DataTypes.Numeric;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ander on 15-04-2017.
 */
public class AnswerBuilder implements NDimensionalPointBuilder {

    Map<Integer, SpaceComparable> map = new LinkedHashMap<>();
    @Override
    public Collection<Integer> getAttributesOfType() {
        return new Answer().keySet();
    }

    @Override
    public void addAttributeValue(Integer dimension, SpaceComparable value) {
        map.put(dimension, value);
    }

    @Override
    public void baseOnOriginal(NDimensionalPoint point) {
        map = new HashMap<>();
        for (Map.Entry<Integer, SpaceComparable> integerSpaceComparableEntry : point.entrySet()) {
            map.put(integerSpaceComparableEntry.getKey(), integerSpaceComparableEntry.getValue());
        }
    }

    @Override
    public NDimensionalPoint buildPoint() {
        NDimensionalPoint result = buildPointOnlyFrom(map);
        map.clear();
        return result;
    }

    @Override
    public NDimensionalPoint buildPointOnlyFrom(Map<Integer, SpaceComparable> attributes) {
        return new Answer(map);
    }
}
