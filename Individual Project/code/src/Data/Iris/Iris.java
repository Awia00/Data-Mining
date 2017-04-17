package Data.Iris;


import Common.DataTypes.Nominal;
import Common.DataTypes.Numeric;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.NDimensionalPoint;
import Common.DataTypes.SpaceComparable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Iris implements ClassifiablePoint<Nominal> {

    Map<Integer, SpaceComparable> attributes;
    Nominal irisClass;

    public Iris() {
        this(0f, 0f, 0f, 0f, "");
    }

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, String iris_class) {
        this(sepal_length, sepal_width, petal_length, petal_width, ResolveIrisClass(iris_class));
    }

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, IrisClass iris_class) {
        attributes = new LinkedHashMap<>();
        attributes.put(0, new Numeric(sepal_length));
        attributes.put(1, new Numeric(sepal_width));
        attributes.put(2, new Numeric(petal_length));
        attributes.put(3, new Numeric(petal_width));
        //attributes.put(new AttributeKey<>(IrisClass.class), new Nominal(iris_class));
        irisClass = new Nominal(iris_class);
    }

    public Iris(double sepal_length, double sepal_width, double petal_length, double petal_width, IrisClass iris_class) {
        attributes = new LinkedHashMap<>();
        attributes.put(0, new Numeric(sepal_length));
        attributes.put(1, new Numeric(sepal_width));
        attributes.put(2, new Numeric(petal_length));
        attributes.put(3, new Numeric(petal_width));
        //attributes.put(new AttributeKey<>(IrisClass.class), new Nominal(iris_class));
        irisClass = new Nominal(iris_class);
    }

    private static IrisClass ResolveIrisClass(String iris_class) {
        if (iris_class.equals("Iris-setosa")) {
            return IrisClass.Iris_setosa;
        } else if (iris_class.equals("Iris-versicolor")) {
            return IrisClass.Iris_versicolor;
        } else if (iris_class.equals("Iris-virginica")) {
            return IrisClass.Iris_virginica;
        }

        return null;
    }

    @Override
    public String toString() {
        return irisClass.getValue() + "";
    }

    @Override
    public SpaceComparable get(Integer key) {
        return attributes.get(key);
    }

    @Override
    public Set<Integer> keySet() {
        return attributes.keySet();
    }

    @Override
    public Collection<SpaceComparable> values() {
        return attributes.values();
    }

    @Override
    public Set<Map.Entry<Integer, SpaceComparable>> entrySet() {
        return attributes.entrySet();
    }

    @Override
    public NDimensionalPoint getDefaultPoint() {
        return new Iris();
    }

    @Override
    public Nominal getClassification() {
        return irisClass;
    }

    @Override
    public boolean checkClassification(Nominal other) {
        return other.equals(irisClass);
    }
}
