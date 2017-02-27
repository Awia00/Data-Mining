package Common.Interfaces;

import Common.Classification;
import Common.Statistics.EvaluationStatistics;

import java.util.Collection;

/**
 * Created by aws on 13-02-2017.
 */
public interface Classifier<T> {

    void trainWithSet(Collection<Classifiable> trainSet);

    EvaluationStatistics testWithSet(Collection<Classifiable> testSet);

    Classification classify(WithAttributes<T> element);

}
