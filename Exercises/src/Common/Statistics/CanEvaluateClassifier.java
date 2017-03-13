package Common.Statistics;

import Common.Interfaces.Classifier;
import Common.Interfaces.TwoWayClassifiable;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface CanEvaluateClassifier {

    <T> EvaluationStatistics testClassifier(Classifier<T, TwoWayClassifiable> classifier, Collection<TwoWayClassifiable> testSet);
}
