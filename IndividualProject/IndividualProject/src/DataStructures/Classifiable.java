package DataStructures;

import DataTypes.Nominal;

import java.util.Collection;

/**
 * Created by ander on 20-03-2017.
 */
public interface Classifiable<T extends Nominal> {
    Collection<T> getRange();
    T getClassification();
}
