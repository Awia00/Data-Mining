package Common.DataTypes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ander on 06-03-2017.
 */
public class NominalSpaceToolbox {

    public static <T extends Nominal> T mostCommon(Collection<T> nominalElements) {
        Map<T, Integer> map = new HashMap<>();
        for (T element : nominalElements) {
            Integer current = map.get(element);
            map.put(element, current == null ? 1 : current + 1);
        }
        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        return max.getKey();
    }
}
