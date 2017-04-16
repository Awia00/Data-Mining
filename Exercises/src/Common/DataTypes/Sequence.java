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
        if(comparable.elements.size()>elements.size()) {
            return comparable.distance(this);
        }
        else{
            double result = 0;
            for (int i = 0; i < comparable.elements.size(); i++) {
                result += comparable.elements.get(i).distance(elements.get(i));
            }
            result += elements.size() - comparable.elements.size();
            return result/elements.size();
        }
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
