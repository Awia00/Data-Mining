package Common.Interfaces;

import Common.DataTypes.SpaceComparable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by ander on 27-02-2017.
 */
public interface NDimensionalPoint extends SpaceComparable<NDimensionalPoint> {

    SpaceComparable get(Integer key);
    Set<Integer> keySet();
    Collection<SpaceComparable> values();
    Set<Map.Entry<Integer, SpaceComparable>> entrySet();

    NDimensionalPoint getDefaultPoint();
    default double distance(NDimensionalPoint other){
        double distance = 0.0;
        for (Integer integer : keySet()) {
            if(other.get(integer) == null || get(integer) == null)
                System.out.println("test");
            distance += other.get(integer).distance(get(integer));
        }
        return distance;
    }

    double epsilon = 1E-10;

    default boolean almostEquals(NDimensionalPoint comparable) {
        return Math.abs(distance(comparable)) <= epsilon;
    }

    default boolean almostEquals(NDimensionalPoint comparable, double epsilonFactor) {
        return Math.abs(distance(comparable)) <= epsilonFactor * epsilon;
    }
}
