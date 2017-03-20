package DataStructures;

import DataTypes.SpaceComparable;

import java.util.Map;

/**
 * Created by ander on 20-03-2017.
 */
public interface NDimensionalPoint extends SpaceComparable<NDimensionalPoint>, Map<Integer, SpaceComparable> {

    NDimensionalPoint getDefaultPoint();
    default double distance(NDimensionalPoint other){
        double distance = 0.0;
        for (Entry<Integer, SpaceComparable> attributeKeySpaceComparableEntry : entrySet()) {
            distance += other.get(attributeKeySpaceComparableEntry.getKey()).distance(this);
        }
        return distance;
    }
}
