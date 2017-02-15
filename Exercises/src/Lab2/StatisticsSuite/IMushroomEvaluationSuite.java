package Lab2.StatisticsSuite;

import Lab2.Classification.IMushroomClassifier;
import Lab2.Mushroom;

import java.util.List;

/**
 * Created by aws on 13-02-2017.
 */
public interface IMushroomEvaluationSuite {

    EvaluationStatistics testClassifier(IMushroomClassifier classifier, List<Mushroom> testSet);
}
