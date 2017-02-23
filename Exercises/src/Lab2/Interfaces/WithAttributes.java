package Lab2.Interfaces;

import java.util.Collection;

/**
 * Created by aws on 22-02-2017.
 */
public interface WithAttributes<T> {

    Collection<Attribute> getAttributes();

    T getValueOfAttribute(Attribute attribute);

    Classification getClassification();

    boolean checkClassification(Classification classification);
}
