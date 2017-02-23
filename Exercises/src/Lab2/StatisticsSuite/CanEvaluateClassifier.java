package Lab2.StatisticsSuite;

import Lab2.Classification.Classifier;
import Lab2.Interfaces.WithAttributes;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface CanEvaluateClassifier {

    <T> EvaluationStatistics testClassifier(Classifier<T> classifier, Collection<WithAttributes<T>> testSet);
}
