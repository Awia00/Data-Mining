package Common.Statistics;

import Common.DataTypes.Binary;
import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public class ConfusionMatrixSuite implements CanEvaluateClassifier<Classifiable<Binary>, Binary> {

    @Override
    public ConfusionMatrixStatistics testClassifier(Classifier<Classifiable<Binary>, Binary> classifier, Collection<Classifiable<Binary>> testSet) {
        int truePositives = 0, falsePositives = 0, trueNegatives = 0, falseNegatives = 0;
        for (Classifiable<Binary> element : testSet) {
            Binary result = classifier.classify(element);
            if (result.equals(Binary.negative()) && element.checkClassification(Binary.negative())) {
                trueNegatives++;
            } else if (result.equals(Binary.positive()) && !element.checkClassification(Binary.positive())) {
                falseNegatives++;
            } else if (result.equals(Binary.positive()) && element.checkClassification(Binary.positive())) {
                truePositives++;
            } else if (result.equals(Binary.negative()) && !element.checkClassification(Binary.negative())) {
                falsePositives++;
            }
        }
        return new ConfusionMatrixStatistics(classifier.getClass().toString(), truePositives, falsePositives, trueNegatives, falseNegatives);
    }
}
