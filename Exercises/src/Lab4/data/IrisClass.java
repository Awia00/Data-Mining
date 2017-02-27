package Lab4.data;

import Common.Interfaces.SpaceComparable;

/**
 * Enum for the different iris classes.
 */
public enum IrisClass implements SpaceComparable<IrisClass> {
    Iris_setosa,
    Iris_versicolor,
    Iris_virginica;

    @Override
    public double distance(IrisClass comparable)
    {
        return this.equals(comparable) ? 0 : 1;
    }
}
