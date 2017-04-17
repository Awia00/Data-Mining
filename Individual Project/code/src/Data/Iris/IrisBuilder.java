package Data.Iris;

import Common.DataTypes.Nominal;
import Common.DataTypes.Numeric;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ander on 27-02-2017.
 */
public class IrisBuilder implements NDimensionalPointBuilder {
    Map<Integer, SpaceComparable> map = new LinkedHashMap<>();
    private Nominal irisClass;

    @Override
    public Collection<Integer> getAttributesOfType() {
        return new Iris().keySet();
    }

    @Override
    public void addAttributeValue(Integer key, SpaceComparable value) {
        map.put(key, value);
    }

    @Override
    public void baseOnOriginal(NDimensionalPoint point) {
        irisClass = ((Iris) point).irisClass;
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
    public NDimensionalPoint buildPointOnlyFrom(Map<Integer, SpaceComparable> attributes) {
        double sepal_length = ((Numeric) attributes.get(0)).getValue();
        double sepal_width = ((Numeric) attributes.get(1)).getValue();
        double petal_length = ((Numeric) attributes.get(2)).getValue();
        double petal_width = ((Numeric) attributes.get(3)).getValue();
        //IrisClass iris_class = (IrisClass) ((Nominal)attributes.get(new AttributeKey<>(IrisClass.class))).getValue();
        return new Iris(sepal_length, sepal_width, petal_length, petal_width, null);
    }
}
