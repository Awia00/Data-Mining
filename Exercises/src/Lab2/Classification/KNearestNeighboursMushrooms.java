package Lab2.Classification;

import Lab2.Mushroom;
import Lab2.enums.Class_Label;

import java.util.*;

/**
 * Created by aws on 13-02-2017.
 */
public class KNearestNeighboursMushrooms implements IMushroomClassifier {

    private List<Mushroom> classifiedMushrooms;
    private int _k;

    public KNearestNeighboursMushrooms(int k) {
        this.classifiedMushrooms = new ArrayList<>();
        this._k = k;
    }

    @Override
    public void trainWithSet(List<Mushroom> mushroomList) {
        this.classifiedMushrooms = mushroomList;
    }

    @Override
    public Class_Label classify(Mushroom mushroom) {
        Map<Mushroom, Integer> kNearest = new HashMap<>();
        int firstK = 0;
        for (Mushroom classifiedMushroom: classifiedMushrooms) {
            int distance = 0;
            for (Object attribute: Mushroom.getAttributeList()){
                if (mushroom.getAttributeValue(attribute) != classifiedMushroom.getAttributeValue(attribute))
                    distance++;
            }
            if(firstK < _k)
            {
                kNearest.put(classifiedMushroom, distance);
            }
            else
            {
                Mushroom worstOne = null;
                int currentDist = -1;
                for(Map.Entry<Mushroom, Integer> keyValue: kNearest.entrySet())
                {
                    if(distance < keyValue.getValue() && keyValue.getValue()>currentDist)
                    {
                        worstOne = keyValue.getKey();
                        currentDist = keyValue.getValue();
                    }
                }
                if(worstOne != null)
                {
                    kNearest.put(classifiedMushroom, distance);
                    kNearest.remove(worstOne);
                }
            }
            firstK++;
        }
        int edible = 0, poisonous = 0;
        for(Map.Entry<Mushroom, Integer> keyValue: kNearest.entrySet())
        {
            if(keyValue.getKey().m_Class == Class_Label.edible)
                edible++;
            else
                poisonous++;
        }
        return edible >= poisonous ? Class_Label.edible : Class_Label.poisonous;
    }
}
