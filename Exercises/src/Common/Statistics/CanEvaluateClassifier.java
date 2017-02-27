package Common.Statistics;

import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface CanEvaluateClassifier {

    <T> EvaluationStatistics testClassifier(Classifier<T> classifier, Collection<Classifiable> testSet);
}
