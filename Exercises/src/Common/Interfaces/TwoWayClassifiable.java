package Common.Interfaces;

import Common.TwoWayClassification;

/**
 * Created by aws on 27-02-2017.
 */
public interface TwoWayClassifiable<T> extends Classifiable<T, TwoWayClassification> {
    TwoWayClassification getClassification();

    boolean checkClassification(TwoWayClassification twoWayClassification);
}
