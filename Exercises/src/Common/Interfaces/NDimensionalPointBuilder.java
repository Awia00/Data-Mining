package Common.Interfaces;

import Common.AttributeKey;

import java.util.Map;

/**
 * Created by ander on 27-02-2017.
 */
public interface NDimensionalPointBuilder {

    void addAttributeValue(AttributeKey key, SpaceComparable value);
    NDimensionalPoint buildPoint();
    NDimensionalPoint buildPoint(Map<AttributeKey, SpaceComparable> attributes);
}
