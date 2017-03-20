package Common.Statistics;

import Common.DataTypes.BooleanNominal;
import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public class BooleanEvaluationSuite implements CanEvaluateClassifier<Classifiable<BooleanNominal>, BooleanNominal> {

    @Override
    public EvaluationStatistics testClassifier(Classifier<Classifiable<BooleanNominal>, BooleanNominal> classifier, Collection<Classifiable<BooleanNominal>> testSet) {
        int truePositives = 0, falsePositives = 0, trueNegatives = 0, falseNegatives = 0;
        for (Classifiable<BooleanNominal> element : testSet) {
            BooleanNominal result = classifier.classify(element);
            if (result.equals(BooleanNominal.negative()) && element.checkClassification(BooleanNominal.negative())) {
                trueNegatives++;
            } else if (result.equals(BooleanNominal.positive()) && !element.checkClassification(BooleanNominal.positive())) {
                falseNegatives++;
            } else if (result.equals(BooleanNominal.positive()) && element.checkClassification(BooleanNominal.positive())) {
                truePositives++;
            } else if (result.equals(BooleanNominal.negative()) && !element.checkClassification(BooleanNominal.negative())) {
                falsePositives++;
            }
        }
        return new EvaluationStatistics(classifier.getClass().toString(), truePositives, falsePositives, trueNegatives, falseNegatives);
    }
}
