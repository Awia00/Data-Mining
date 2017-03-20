package Common.DataTypes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ander on 06-03-2017.
 */
public class NominalSpaceToolbox {

    public static Nominal mostCommon(Collection<Nominal> nominalElements) {
        Map<Enum, Integer> map = new HashMap<>();
        for (Nominal element : nominalElements) {
            Enum key = element.getValue();
            Integer current = map.get(key);
            map.put(key, current == null ? 1 : current + 1);
        }
        Map.Entry<Enum, Integer> max = null;

        for (Map.Entry<Enum, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        return new Nominal(max.getKey());
    }
}
