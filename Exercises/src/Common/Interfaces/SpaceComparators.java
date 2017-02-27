package Common.Interfaces;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by aws on 27-02-2017.
 */
public abstract class SpaceComparators {
    public static <T> double nominalDistance(T first, T second) {
        return first.equals(second) ? 0 : 1;
    }

    public static <T> double manhatttenDistance(T first, T second) {
        throw new NotImplementedException();
    }

    public static <T> double euclidianDistance(T first, T second) {
        throw new NotImplementedException();
    }
}
