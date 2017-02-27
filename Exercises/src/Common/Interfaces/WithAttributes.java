package Common.Interfaces;

import Common.AttributeKey;

import java.util.Collection;

/**
 * Created by aws on 22-02-2017.
 */
public interface WithAttributes<T> {

    Collection<AttributeKey> getAttributes();

    T getValueOfAttribute(AttributeKey attributeKey);
}