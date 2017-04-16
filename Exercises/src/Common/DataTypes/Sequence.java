package Common.DataTypes;

import java.util.List;

/**
 * Created by ander on 15-04-2017.
 */
public class Sequence<T extends SpaceComparable> implements SpaceComparable<Sequence<T>> {

    public List<T> getElements() {
        return elements;
    }

    private List<T> elements;
    public Sequence(List<T> elements){
        this.elements = elements;
    }

    @Override
    public double distance(Sequence<T> comparable) {
        double result = 0;
        for (T other : comparable.elements) {
            double minDistance = 0;
            for (T element : elements) {
                minDistance = Math.min(minDistance, element.distance(other));
            }
            result += minDistance;
        }
        result += Math.abs(elements.size()-comparable.elements.size());
        return result;
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
