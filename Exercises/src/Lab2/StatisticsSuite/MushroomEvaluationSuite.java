package Lab2.StatisticsSuite;

import Lab2.Classification.IMushroomClassifier;
import Lab2.Mushroom;
import Lab2.enums.Class_Label;

import java.util.List;

/**
 * Created by aws on 13-02-2017.
 */
public class MushroomEvaluationSuite implements IMushroomEvaluationSuite {
    @Override
    public EvaluationStatistics testClassifier(IMushroomClassifier classifier, List<Mushroom> testSet) {
        int truePositives = 0, falsePositives = 0, trueNegatives = 0, falseNegatives = 0;
        for(Mushroom mushroom: testSet)
        {
            Class_Label result = classifier.classify(mushroom);
            if(result == Class_Label.edible && mushroom.m_Class == Class_Label.edible)
            {
                trueNegatives++;
            }
            else if(result == Class_Label.poisonous && mushroom.m_Class == Class_Label.edible)
            {
                falseNegatives++;
            }
            else if(result == Class_Label.poisonous && mushroom.m_Class == Class_Label.poisonous)
            {
                truePositives++;
            }
            else if(result == Class_Label.edible && mushroom.m_Class == Class_Label.poisonous)
            {
                falsePositives++;
            }
        }
        return new EvaluationStatistics(classifier.getClass().toString(), truePositives, falsePositives, trueNegatives, falseNegatives);
    }
}
