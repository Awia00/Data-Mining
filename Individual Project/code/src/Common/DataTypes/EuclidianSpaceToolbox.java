package Common.DataTypes;

import java.util.Collection;

/**
 * Created by ander on 06-03-2017.
 */
public class EuclidianSpaceToolbox {

    public static Numeric mean(Collection<Numeric> euclideanElements) {
        return new Numeric(euclideanElements.stream().mapToDouble(x -> x.getValue()).sum() / euclideanElements.size());
    }
}
