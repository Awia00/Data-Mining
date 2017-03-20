package Classifiers;

import DataStructures.Classifiable;
import DataTypes.Nominal;

import java.util.Collection;

/**
 * Created by ander on 20-03-2017.
 */
public interface Classifier<T extends Classifiable<V>, V extends Nominal> {
    void trainWith(Collection<T> elements);
    V classify(T classifiable);
}
