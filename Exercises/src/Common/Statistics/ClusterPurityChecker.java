package Common.Statistics;

import Common.DataStructures.Cluster.Cluster;
import Common.DataTypes.Nominal;
import Common.Interfaces.Classifiable;
import Common.Interfaces.NDimensionalPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ander on 20-03-2017.
 */
public class ClusterPurityChecker {

    public <T extends Nominal> PurityStatistics checkCluster(Cluster cluster){

        Map<T, Integer> classificationAmtMap = new HashMap<T, Integer>();
        for (NDimensionalPoint point : cluster.points) {
            T classification = ((Classifiable<T>) (point)).getClassification();
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
}
