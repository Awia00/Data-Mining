package Lab2.StatisticsSuite;

import Lab2.Classification.Classifier;
import Lab2.Interfaces.Classification;
import Lab2.Interfaces.WithAttributes;

import java.util.Collection;
import java.util.List;

/**
 * Created by aws on 13-02-2017.
 */
public class EvaluationSuite implements CanEvaluateClassifier {
    @Override
    public <T> EvaluationStatistics testClassifier(Classifier<T> classifier, Collection<WithAttributes<T>> testSet) {
        int truePositives = 0, falsePositives = 0, trueNegatives = 0, falseNegatives = 0;
        for(WithAttributes element: testSet)
        {
            Classification result = classifier.classify(element);
            if(result == Classification.negative && element.checkClassification(Classification.negative))
            {
                trueNegatives++;
            }
            else if(result == Classification.positive && !element.checkClassification(Classification.positive))
            {
                falseNegatives++;
            }
            else if(result == Classification.positive && element.checkClassification(Classification.positive))
            {
                truePositives++;
            }
            else if(result == Classification.negative && !element.checkClassification(Classification.negative))
            {
                falsePositives++;
            }
        }
        return new EvaluationStatistics(classifier.getClass().toString(), truePositives, falsePositives, trueNegatives, falseNegatives);
    }
}
