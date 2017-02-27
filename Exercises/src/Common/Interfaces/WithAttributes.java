package Common.Interfaces;

import Common.Attribute;
import Common.Classification;

import java.util.Collection;

/**
 * Created by aws on 22-02-2017.
 */
public interface WithAttributes<T> {

    Collection<Attribute> getAttributes();

    T getValueOfAttribute(Attribute attribute);
}
