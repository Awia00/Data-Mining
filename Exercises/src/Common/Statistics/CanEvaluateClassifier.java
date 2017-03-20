package Common.Statistics;

import Common.DataTypes.Nominal;
import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface CanEvaluateClassifier<T extends Classifiable<V>, V extends Nominal> {

    EvaluationStatistics testClassifier(Classifier<Classifiable<V>, V> classifier, Collection<T> testSet);
}
