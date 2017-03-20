package KNN;

import Classifiers.Classifier;
import DataStructures.ClassifiablePoint;
import DataTypes.Nominal;

import java.util.Collection;

/**
 * Created by ander on 20-03-2017.
 */
public class KNearestNeighboursClassifier<T extends Nominal> implements Classifier<ClassifiablePoint<T>,T> {

    private int k;

    public KNearestNeighboursClassifier(int k) {
        this.k = k;
    }

    @Override
    public void trainWith(Collection<ClassifiablePoint<T>> elements) {

    }

    @Override
    public T classify(ClassifiablePoint<T> classifiable) {
        return null;
    }
}
