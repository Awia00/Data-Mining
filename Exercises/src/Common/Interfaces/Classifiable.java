package Common.Interfaces;

import Common.NominalSpaceComparable;

/**
 * Created by ander on 13-03-2017.
 */
public interface Classifiable<T, V extends NominalSpaceComparable> extends WithAttributes<T> {
    V getClassification();

    boolean checkClassification(V twoWayClassification);
}
