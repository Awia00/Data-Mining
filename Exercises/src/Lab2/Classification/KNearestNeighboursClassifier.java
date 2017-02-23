package Lab2.Classification;

import Lab2.Interfaces.Attribute;
import Lab2.Interfaces.Classification;
import Lab2.Interfaces.SpaceComparable;
import Lab2.Interfaces.WithAttributes;
import Lab2.StatisticsSuite.EvaluationStatistics;
import Lab2.StatisticsSuite.EvaluationSuite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 13-02-2017.
 */
public class KNearestNeighboursClassifier implements Classifier<SpaceComparable> {

    private Collection<WithAttributes<SpaceComparable>> classifiedSet;
    private int _k;

    public KNearestNeighboursClassifier(int k) {
        this.classifiedSet = new ArrayList<>();
        this._k = k;
    }

    @Override
    public void trainWithSet(Collection<WithAttributes<SpaceComparable>> trainSet) {

        this.classifiedSet = trainSet;
    }

    @Override
    public EvaluationStatistics testWithSet(Collection<WithAttributes<SpaceComparable>> testSet) {
        return new EvaluationSuite().testClassifier(this, testSet);
    }

    @Override
    public Classification classify(WithAttributes<SpaceComparable> elementToClassify) {
        Map<WithAttributes<SpaceComparable>, Integer> kNearest = new HashMap<>();
        int firstK = 0;
        for (WithAttributes classifiedElement : classifiedSet) {
            int distance = 0;
            for (Attribute attribute : elementToClassify.getAttributes()) {
                distance += elementToClassify.getValueOfAttribute(attribute).distance(classifiedElement.getValueOfAttribute(attribute));
            }
            if (firstK < _k) {
                kNearest.put(classifiedElement, distance);
            } else {
                WithAttributes<SpaceComparable> worstOne = null;
                int currentDist = -1;
                for (Map.Entry<WithAttributes<SpaceComparable>, Integer> keyValue : kNearest.entrySet()) {
                    if (distance < keyValue.getValue() && keyValue.getValue() > currentDist) {
                        worstOne = keyValue.getKey();
                        currentDist = keyValue.getValue();
                    }
                }
                if (worstOne != null) {
                    kNearest.put(classifiedElement, distance);
                    kNearest.remove(worstOne);
                }
            }
            firstK++;
        }
        int negatives = 0, positives = 0;
        for (Map.Entry<WithAttributes<SpaceComparable>, Integer> keyValue : kNearest.entrySet()) {
            if (keyValue.getKey().getClassification() == Classification.negative)
                negatives++;
            else
                positives++;
        }
        return negatives >= positives ? Classification.negative : Classification.positive;
    }
}
