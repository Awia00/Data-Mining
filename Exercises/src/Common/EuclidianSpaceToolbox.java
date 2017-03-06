package Common;

import java.util.Collection;

/**
 * Created by ander on 06-03-2017.
 */
public class EuclidianSpaceToolbox {

    public static EuclideanSpaceComparable mean(Collection<EuclideanSpaceComparable> euclideanElements){
        return new EuclideanSpaceComparable<Double>(euclideanElements.stream().mapToDouble(x -> x.getDoubleValue()).sum() / euclideanElements.size());
    }
}
