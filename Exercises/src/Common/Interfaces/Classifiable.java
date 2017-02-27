package Common.Interfaces;

import Common.Classification;

/**
 * Created by aws on 27-02-2017.
 */
public interface Classifiable<T> extends WithAttributes<T> {
    Classification getClassification();

    boolean checkClassification(Classification classification);
}
