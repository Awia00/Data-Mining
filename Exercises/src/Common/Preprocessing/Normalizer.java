package Common.Preprocessing;

import Common.AttributeKey;
import Common.EuclideanSpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;
import Common.Interfaces.SpaceComparable;
import Common.Interfaces.WithAttributes;
import Common.NominalSpaceComparable;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Normalizer {

    public Collection<NDimensionalPoint> NormilizeData(Collection<NDimensionalPoint> elements, WithAttributes<SpaceComparable> attributes, NDimensionalPointBuilder builder)
    {
        Collection<NDimensionalPoint> normalizedElements = new ArrayList<>();
        Map<AttributeKey, Double> maxValues = new HashMap<>();
        Map<AttributeKey, Double> minValues = new HashMap<>();

        for (NDimensionalPoint point : elements) {
            for (AttributeKey attributeKey : attributes.getAttributes()) {
                Class<? extends SpaceComparable> attributeType = attributes.getValueOfAttribute(attributeKey).getClass();
                if(attributeType == EuclideanSpaceComparable.class)
                {
                    double value = ((EuclideanSpaceComparable)point.getValueOfAttribute(attributeKey)).getDoubleValue();
                    if(maxValues.containsKey(attributeKey))
                        maxValues.put(attributeKey, Math.max(value, maxValues.get(attributeKey)));
                    else
                        maxValues.put(attributeKey, value);

                    if(minValues.containsKey(attributeKey))
                        minValues.put(attributeKey, Math.min(value, minValues.get(attributeKey)));
                    else
                        minValues.put(attributeKey, value);
                }
            }
        }
        for (NDimensionalPoint point : elements) {
            builder.baseOnOriginal(point);
            for (AttributeKey attributeKey : attributes.getAttributes()) {
                Class<? extends SpaceComparable> attributeType = attributes.getValueOfAttribute(attributeKey).getClass();
                if(attributeType == EuclideanSpaceComparable.class) {
                    double value = ((EuclideanSpaceComparable)point.getValueOfAttribute(attributeKey)).getDoubleValue();
                    double min = minValues.get(attributeKey);
                    double max = maxValues.get(attributeKey);
                    value = (value-min)/(max-min);
                    builder.addAttributeValue(attributeKey, new EuclideanSpaceComparable(value));
                }
                else {
                    builder.addAttributeValue(attributeKey, point.getValueOfAttribute(attributeKey));
                }
            }
            normalizedElements.add(builder.buildPoint());
        }
        return normalizedElements;
    }
}
