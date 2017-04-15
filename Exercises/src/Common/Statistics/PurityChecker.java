package Common.Statistics;

import Common.DataStructures.Cluster.Cluster;
import Common.DataTypes.Nominal;
import Common.Interfaces.Classifiable;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.Classifier;
import Common.Interfaces.NDimensionalPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ander on 20-03-2017.
 */
public class PurityChecker {

    public <T extends Nominal> PurityStatistics checkCluster(List<ClassifiablePoint<T>> points){

        Map<T, Integer> classificationAmtMap = new HashMap<T, Integer>();
        for (ClassifiablePoint<T> point : points) {
            T classification = point.getClassification();
            if(classificationAmtMap.containsKey(classification)) {
                classificationAmtMap.put(classification, classificationAmtMap.get(classification)+1);
            }
            else {
                classificationAmtMap.put(classification, 1);
            }
        }
        int sum = 0;
        T best = null;
        int former = 0;
        for (Map.Entry<T, Integer> tIntegerEntry : classificationAmtMap.entrySet()) {
            int amt = tIntegerEntry.getValue();
            T key = tIntegerEntry.getKey();
            if(best==null || amt > former)
            {
                best = key;
                former = amt;
            }
            sum += amt;
        }
        return new PurityStatistics<>((double)former/(double)sum, best);
    }

    public void checkClassifier(Classifier classifier, List<ClassifiablePoint<Nominal>> points){
        Map<Nominal, List<ClassifiablePoint<Nominal>>> map = new HashMap<>();
        for (ClassifiablePoint<Nominal> nominalClassifiablePoint : points) {
            Nominal classification = classifier.classify(nominalClassifiablePoint);
            List<ClassifiablePoint<Nominal>> pointsClassifiedAsThus = map.get(classification);
            if(pointsClassifiedAsThus == null) {
                List<ClassifiablePoint<Nominal>> newList = new ArrayList<>();
                newList.add(nominalClassifiablePoint);
                map.put(classification, newList);
            }
            else{
                pointsClassifiedAsThus.add(nominalClassifiablePoint);
            }
        }
        for (List<ClassifiablePoint<Nominal>> classifiablePoints : map.values()) {
            System.out.println(checkCluster(classifiablePoints));
        }
    }
}
