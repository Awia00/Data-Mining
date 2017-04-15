package Common.DataTypes;

import java.util.List;

/**
 * Created by ander on 15-04-2017.
 */
public class Multiple implements SpaceComparable<Multiple> {

    List<SpaceComparable> elements;
    public Multiple(List<SpaceComparable> elements){
        this.elements = elements;
    }

    @Override
    public double distance(Multiple comparable) {
        if(comparable.elements.size()<elements.size()) {
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
}
