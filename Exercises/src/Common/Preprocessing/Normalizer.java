package Common.Preprocessing;

import Common.DataTypes.Numeric;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;
import Common.DataTypes.SpaceComparable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Normalizer {

    public Collection<NDimensionalPoint> NormilizeData(Collection<? extends NDimensionalPoint> elements, NDimensionalPointBuilder builder) {
        Collection<NDimensionalPoint> normalizedElements = new ArrayList<>();
        Map<Integer, Double> maxValues = new HashMap<>();
        Map<Integer, Double> minValues = new HashMap<>();

        for (NDimensionalPoint point : elements) {
            for (Integer attributeKey : builder.getAttributesOfType()) {
                Class<? extends SpaceComparable> attributeType = point.get(attributeKey).getClass();
                if (attributeType == Numeric.class) {
                    double value = ((Numeric) point.get(attributeKey)).getValue();
                    if (maxValues.containsKey(attributeKey))
                        maxValues.put(attributeKey, Math.max(value, maxValues.get(attributeKey)));
                    else
                        maxValues.put(attributeKey, value);

                    if (minValues.containsKey(attributeKey))
                        minValues.put(attributeKey, Math.min(value, minValues.get(attributeKey)));
                    else
                        minValues.put(attributeKey, value);
                }
            }
        }
        for (NDimensionalPoint point : elements) {
            builder.baseOnOriginal(point);
            for (Integer attributeKey : builder.getAttributesOfType()) {
                Class<? extends SpaceComparable> attributeType = point.get(attributeKey).getClass();
                if (attributeType == Numeric.class) {
                    double value = ((Numeric) point.get(attributeKey)).getValue();
                    double min = minValues.get(attributeKey);
                    double max = maxValues.get(attributeKey);
                    value = (value - min) / (max - min);
                    builder.addAttributeValue(attributeKey, new Numeric(value));
                } else {
                    builder.addAttributeValue(attributeKey, point.get(attributeKey));
                }
            }
            normalizedElements.add(builder.buildPoint());
        }
        return normalizedElements;
    }
}
