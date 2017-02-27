package Lab2.Classification;

import Common.Classification;
import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;
import Common.Interfaces.SpaceComparable;
import Common.Interfaces.WithAttributes;
import Common.Statistics.EvaluationStatistics;
import Common.Statistics.EvaluationSuite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 13-02-2017.
 */
public class KNearestNeighboursClassifier implements Classifier<SpaceComparable> {

    private Collection<Classifiable> classifiedSet;
    private int _k;

    public KNearestNeighboursClassifier(int k) {
        this.classifiedSet = new ArrayList<>();
        this._k = k;
    }

    @Override
    public void trainWithSet(Collection<Classifiable> trainSet) {

        this.classifiedSet = trainSet;
    }

    @Override
    public EvaluationStatistics testWithSet(Collection<Classifiable> testSet) {
        return new EvaluationSuite().testClassifier(this, testSet);
    }

    @Override
    public Classification classify(WithAttributes<SpaceComparable> elementToClassify) {
        Map<Classifiable, Double> kNearest = new HashMap<>();
        int firstK = 0;
        for (Classifiable classifiedElement : classifiedSet) {
            double distance = elementToClassify.getAttributes().stream().mapToDouble(attribute -> elementToClassify.getValueOfAttribute(attribute).distance(classifiedElement.getValueOfAttribute(attribute))).sum();
            if (firstK < _k) {
                kNearest.put(classifiedElement, distance);
            } else {
                Classifiable worstOne = null;
                double currentDist = -1;
                for (Map.Entry<Classifiable, Double> keyValue : kNearest.entrySet()) {
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
        for (Map.Entry<Classifiable, Double> keyValue : kNearest.entrySet()) {
            if (keyValue.getKey().getClassification() == Classification.negative)
                negatives++;
            else
                positives++;
        }
        return negatives >= positives ? Classification.negative : Classification.positive;
    }
}
