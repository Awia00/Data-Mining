package Common.Statistics;

import Common.Interfaces.Classifier;
import Common.Interfaces.TwoWayClassifiable;
import Common.TwoWayClassification;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public class EvaluationSuite implements CanEvaluateClassifier {
    @Override
    public <T> EvaluationStatistics testClassifier(Classifier<T, TwoWayClassifiable> classifier, Collection<TwoWayClassifiable> testSet) {
        int truePositives = 0, falsePositives = 0, trueNegatives = 0, falseNegatives = 0;
        for (TwoWayClassifiable element : testSet) {
            TwoWayClassification result = classifier.classify(element);
            if (result.equals(TwoWayClassification.negative()) && element.checkClassification(TwoWayClassification.negative())) {
                trueNegatives++;
            } else if (result.equals(TwoWayClassification.positive()) && !element.checkClassification(TwoWayClassification.positive())) {
                falseNegatives++;
            } else if (result.equals(TwoWayClassification.positive()) && element.checkClassification(TwoWayClassification.positive())) {
                truePositives++;
            } else if (result.equals(TwoWayClassification.negative()) && !element.checkClassification(TwoWayClassification.negative())) {
                falsePositives++;
            }
        }
        return new EvaluationStatistics(classifier.getClass().toString(), truePositives, falsePositives, trueNegatives, falseNegatives);
    }
}
