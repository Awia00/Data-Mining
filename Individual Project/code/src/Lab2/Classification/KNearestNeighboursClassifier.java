package Lab2.Classification;

import Common.DataTypes.Nominal;
import Common.DataTypes.NominalSpaceToolbox;
import Common.Interfaces.Classifiable;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.Classifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by aws on 13-02-2017.
 */
public class KNearestNeighboursClassifier<T extends Nominal> implements Classifier<ClassifiablePoint<T>, T> {

    private Collection<ClassifiablePoint<T>> classifiedSet;
    private int _k;

    public KNearestNeighboursClassifier(int k) {
        this.classifiedSet = new ArrayList<>();
        this._k = k;
    }

    @Override
    public void trainWithSet(Collection<ClassifiablePoint<T>> trainSet) {

        this.classifiedSet = trainSet;
    }

    @Override
    public T classify(ClassifiablePoint<T> elementToClassify) {
        Map<ClassifiablePoint<T>, Double> kNearest = new HashMap<>();
        int firstK = 0;
        for (ClassifiablePoint<T> classifiedElement : classifiedSet) {
            double distance = elementToClassify.keySet().stream().mapToDouble(attribute -> elementToClassify.get(attribute).distance(classifiedElement.get(attribute))).sum();
            if (firstK < _k) {
                kNearest.put(classifiedElement, distance);
            } else {
                ClassifiablePoint<T> worstOne = null;
                double currentDist = -1;
                for (Map.Entry<ClassifiablePoint<T>, Double> keyValue : kNearest.entrySet()) {
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
        return (T) NominalSpaceToolbox.mostCommon(kNearest.keySet().stream().map(Classifiable::getClassification).collect(Collectors.toList()));
    }
}
