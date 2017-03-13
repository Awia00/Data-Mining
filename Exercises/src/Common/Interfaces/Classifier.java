package Common.Interfaces;

import Common.TwoWayClassification;
import Common.Statistics.EvaluationStatistics;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface Classifier<T, V extends Classifiable> {

    void trainWithSet(Collection<V> trainSet);

    EvaluationStatistics testWithSet(Collection<V> testSet);

    TwoWayClassification classify(WithAttributes<T> element);

}
