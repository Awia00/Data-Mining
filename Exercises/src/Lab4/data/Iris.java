package Lab4.data;


import Common.AttributeKey;
import Common.Classification;
import Common.EuclideanSpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.SpaceComparable;
import Common.NominalSpaceComparable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Iris extends NDimensionalPoint {

    Map<AttributeKey, SpaceComparable> attributes;
    IrisClass irisClass;

    public Iris() {
        this(0f,0f,0f,0f, "");
    }

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, String iris_class) {
        this(sepal_length, sepal_width, petal_length, petal_width, ResolveIrisClass(iris_class));
    }

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, IrisClass iris_class) {
        attributes = new LinkedHashMap<>();
        attributes.put(new AttributeKey<>("sepalLength"), new EuclideanSpaceComparable<>(sepal_length));
        attributes.put(new AttributeKey<>("sepalWidth"), new EuclideanSpaceComparable<>(sepal_width));
        attributes.put(new AttributeKey<>("petalLength"), new EuclideanSpaceComparable<>(petal_length));
        attributes.put(new AttributeKey<>("petalWidth"), new EuclideanSpaceComparable<>(petal_width));
        //attributes.put(new AttributeKey<>(IrisClass.class), new NominalSpaceComparable(iris_class));
        irisClass = iris_class;
    }

    public Iris(double sepal_length, double sepal_width, double petal_length, double petal_width, IrisClass iris_class) {
        attributes = new LinkedHashMap<>();
        attributes.put(new AttributeKey<>("sepalLength"), new EuclideanSpaceComparable<>(sepal_length));
        attributes.put(new AttributeKey<>("sepalWidth"), new EuclideanSpaceComparable<>(sepal_width));
        attributes.put(new AttributeKey<>("petalLength"), new EuclideanSpaceComparable<>(petal_length));
        attributes.put(new AttributeKey<>("petalWidth"), new EuclideanSpaceComparable<>(petal_width));
        //attributes.put(new AttributeKey<>(IrisClass.class), new NominalSpaceComparable(iris_class));
        irisClass = iris_class;
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
        String result = "Iris Object --> | ";
        for (AttributeKey attributeKey : getAttributes()) {
            result+= attributeKey.toString() + " = " + getValueOfAttribute(attributeKey) + " | ";
        }
        result += "class: " + irisClass + " | ";
        return result;
    }

    @Override
    public Collection<AttributeKey> getAttributes() {
        return attributes.keySet();
    }

    @Override
    public SpaceComparable getValueOfAttribute(AttributeKey attributeKey) {
        return attributes.get(attributeKey);
    }

}
