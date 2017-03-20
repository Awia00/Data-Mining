package Common.Interfaces;

import Common.DataTypes.Nominal;

/**
 * Created by ander on 13-03-2017.
 */
public interface Classifiable<T extends Nominal> {
    T getClassification();
    boolean checkClassification(T other);
}
