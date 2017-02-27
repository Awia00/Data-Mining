package Common.Interfaces;

import Common.AttributeKey;

import java.util.Map;

/**
 * Created by ander on 27-02-2017.
 */
public interface NDimensionalPointBuilder {

    NDimensionalPoint buildPoint(Map<AttributeKey, SpaceComparable> attributes);
}
