package Common.Statistics;

import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;
import Common.Classification;
import Common.Interfaces.WithAttributes;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public class EvaluationSuite implements CanEvaluateClassifier {
    @Override
    public <T> EvaluationStatistics testClassifier(Classifier<T> classifier, Collection<Classifiable> testSet) {
        int truePositives = 0, falsePositives = 0, trueNegatives = 0, falseNegatives = 0;
        for (Classifiable element : testSet) {
            Classification result = classifier.classify(element);
            if (result == Classification.negative && element.checkClassification(Classification.negative)) {
                trueNegatives++;
            } else if (result == Classification.positive && !element.checkClassification(Classification.positive)) {
                falseNegatives++;
            } else if (result == Classification.positive && element.checkClassification(Classification.positive)) {
                truePositives++;
            } else if (result == Classification.negative && !element.checkClassification(Classification.negative)) {
                falsePositives++;
            }
        }
        return new EvaluationStatistics(classifier.getClass().toString(), truePositives, falsePositives, trueNegatives, falseNegatives);
    }
}
