package Lab2.Classification;

import Lab2.Mushroom;
import Lab2.enums.Class_Label;

import java.util.List;

/**
 * Created by ander on 13-02-2017.
 */
public class C45DecisionTreeMushroomClassifier implements IMushroomClassifier {
    @Override
    public void classifySet(List<Mushroom> mushroomList) {

    }

    private boolean belongToSameClass(List<Mushroom> mushrooms)
    {
        Class_Label mLabel = null;
        for(Mushroom mushroom: mushrooms)
        {
            if(mLabel == null)
                mLabel = mushroom.m_Class;
            else if(mLabel != mushroom.m_Class)
                return false;
        }
        return true;
    }

    private Class_Label mostCommon(List<Mushroom> mushrooms)
    {
        int edible = 0;
        for(Mushroom mushroom: mushrooms)
        {
            if(mushroom.m_Class == Class_Label.edible)
                edible++;
        }
        return edible>=mushrooms.size()/2 ? Class_Label.edible : Class_Label.poisonous;
    }

    @Override
    public Class_Label classify(Mushroom mushroom) {
        return null;
    }
}
