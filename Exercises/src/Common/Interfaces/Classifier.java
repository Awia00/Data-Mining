package Common.Interfaces;

import Common.DataTypes.Nominal;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface Classifier<T extends Classifiable<V>, V extends Nominal> {

    void trainWithSet(Collection<T> trainSet);
    V classify(T classifiable);

}
