package Lab4.data;


import Common.Attribute;
import Common.Classification;
import Common.Interfaces.SpaceComparable;
import Common.Interfaces.WithAttributes;

import java.util.Collection;

public class Iris implements WithAttributes<SpaceComparable>{

    public final float Sepal_Length;
    public final float Sepal_Width;
    public final float Petal_Length;
    public final float Petal_Width;
    public final IrisClass Class;

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, String iris_class) {
        this(sepal_length, sepal_width, petal_length, petal_width, ResolveIrisClass(iris_class));
    }

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, IrisClass iris_class) {
        this.Sepal_Length = sepal_length;
        this.Sepal_Width = sepal_width;
        this.Petal_Length = petal_length;
        this.Petal_Width = petal_width;
        this.Class = iris_class;
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
        String result = "Iris Object --> | Sepal_Length = " + this.Sepal_Length + " | Sepal_Width = " + this.Sepal_Width + " | Petal_Length = " + this.Petal_Length + " | Petal_Width = " + this.Petal_Width + " | Class = " + this.Class;

        return result;
    }

    @Override
    public Collection<Attribute> getAttributes() {
        return null;
    }

    @Override
    public SpaceComparable getValueOfAttribute(Attribute attribute) {
        return null;
    }
}
