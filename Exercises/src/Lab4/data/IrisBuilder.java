package Lab4.data;

import Common.AttributeKey;
import Common.EuclideanSpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;
import Common.Interfaces.SpaceComparable;
import Common.NominalSpaceComparable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ander on 27-02-2017.
 */
public class IrisBuilder implements NDimensionalPointBuilder {
    Map<AttributeKey, SpaceComparable> map = new LinkedHashMap<>();
    private IrisClass irisClass;

    @Override
    public void addAttributeValue(AttributeKey key, SpaceComparable value) {
        map.put(key, value);
    }

    @Override
    public void baseOnOriginal(NDimensionalPoint point) {
        irisClass = ((Iris)point).irisClass;
    }

    @Override
    public NDimensionalPoint buildPoint() {
        Iris result = (Iris) buildPointOnlyFrom(map);
        result.irisClass = irisClass;
        irisClass = null;

        map.clear();
        return result;
    }

    @Override
    public NDimensionalPoint buildPointOnlyFrom(Map<AttributeKey, SpaceComparable> attributes) {
        double sepal_length = ((EuclideanSpaceComparable)attributes.get(new AttributeKey<>("sepalLength"))).getDoubleValue();
        double sepal_width = ((EuclideanSpaceComparable)attributes.get(new AttributeKey<>("sepalWidth"))).getDoubleValue();
        double petal_length = ((EuclideanSpaceComparable)attributes.get(new AttributeKey<>("petalLength"))).getDoubleValue();
        double petal_width = ((EuclideanSpaceComparable)attributes.get(new AttributeKey<>("petalWidth"))).getDoubleValue();
        //IrisClass iris_class = (IrisClass) ((NominalSpaceComparable)attributes.get(new AttributeKey<>(IrisClass.class))).getValue();
        return new Iris(sepal_length, sepal_width, petal_length, petal_width, null);
    }
}
