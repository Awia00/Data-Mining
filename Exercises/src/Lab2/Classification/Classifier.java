package Lab2.Classification;

import Lab2.Interfaces.Classification;
import Lab2.Interfaces.WithAttributes;
import Lab2.StatisticsSuite.EvaluationStatistics;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface Classifier<T> {

    void trainWithSet(Collection<WithAttributes<T>> trainSet);

    EvaluationStatistics testWithSet(Collection<WithAttributes<T>> testSet);

    Classification classify(WithAttributes<T> element);

}
