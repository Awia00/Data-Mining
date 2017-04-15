package Common.DataTypes;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ander on 15-04-2017.
 */
public class MultipleSpaceToolbox {

    public static <T extends SpaceComparable> Multiple mean(Collection<Multiple<T>> multipleElements) {
        Map<Class, List<SpaceComparable>> map = new HashMap<>();
        for (Multiple<T> multipleElement : multipleElements) {
            for (SpaceComparable o : multipleElement.getElements()) {
                if(map.containsKey(o.getClass())){
                    map.get(o.getClass()).add(o);
                }
                else{
                    List<SpaceComparable> newList = new ArrayList<>();
                    newList.add(o);
                }
            }
        }
        List<SpaceComparable> newList = new ArrayList<>();
        for (Map.Entry<Class, List<SpaceComparable>> classListEntry : map.entrySet()) {
            if(classListEntry.getKey() == Nominal.class){
                NominalSpaceToolbox.mostCommon(classListEntry.getValue().stream().map(spaceComparable -> (Nominal) spaceComparable).collect(Collectors.toList()));
            }
            else if(classListEntry.getKey() == Numeric.class){
                EuclidianSpaceToolbox.mean(classListEntry.getValue().stream().map(spaceComparable -> (Numeric) spaceComparable).collect(Collectors.toList()));
            }
        }
        return new Multiple(newList);
    }
}
