package Common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ander on 06-03-2017.
 */
public class NominalSpaceToolbox {

    public static <T extends Enum> NominalSpaceComparable<T> mostCommon(Collection<NominalSpaceComparable<T>> nominalElements) {
        Map<T, Integer> map = new HashMap<>();
        for (NominalSpaceComparable<T> element : nominalElements) {
            T key = element.getValue();
            Integer current = map.get(key);
            map.put(key, current == null ? 1 : current + 1);
        }
        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        return new NominalSpaceComparable(max.getKey());
    }
}
