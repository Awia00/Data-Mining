package Common.Interfaces;

import Common.DataTypes.SpaceComparable;

import java.util.Collection;
import java.util.Map;

/**
 * Created by ander on 27-02-2017.
 */
public interface NDimensionalPointBuilder {

    Collection<Integer> getAttributesOfType();

    void addAttributeValue(Integer dimension, SpaceComparable value);

    void baseOnOriginal(NDimensionalPoint point);

    NDimensionalPoint buildPoint();

    NDimensionalPoint buildPointOnlyFrom(Map<Integer, SpaceComparable> attributes);
}
