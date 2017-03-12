package Lab4.kMedoid;

import Common.Interfaces.NDimensionalPoint;

import java.util.*;

public class KMedoid {

    public static Collection<KMedoidCluster> KMedoidPartition(int k, List<NDimensionalPoint> data) {
        Collection<KMedoidCluster> clusters = new ArrayList<>();
        // pick random mediods
        List<NDimensionalPoint> toRemove = new ArrayList<>();
        List<NDimensionalPoint> toAdd = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            NDimensionalPoint point = data.get(random.nextInt(data.size()));
            toRemove.add(point);
            clusters.add(new KMedoidCluster(point));
        }
        data.removeAll(toRemove);

        // initial placement


        // create
        double lastConfigurationCost = Double.MAX_VALUE;
        double configurationCost = Double.MAX_VALUE - 1;
        int iterations = 0;
        while (configurationCost + 1E-10 <= lastConfigurationCost) {
            lastConfigurationCost = configurationCost;
            iterations++;
            // assignment
            for (KMedoidCluster cluster : clusters) {
                cluster.clearPoints();
            }
            for (NDimensionalPoint datum : data) {
                KMedoidCluster best = null;
                for (KMedoidCluster cluster : clusters) {
                    if (best == null || cluster.getMedoid().distance(datum) < best.getMedoid().distance(datum))
                        best = cluster;
                }
                best.add(datum);
            }
            toRemove = new ArrayList<>();
            toAdd = new ArrayList<>();
            outerloop:
            for (NDimensionalPoint point : data) {
                for (KMedoidCluster cluster : clusters) {
                    NDimensionalPoint oldMedoid = cluster.swapWithMediod(point);
                    double sumOfDistances = sumOfDistances(clusters);
                    if (sumOfDistances < configurationCost) {
                        configurationCost = sumOfDistances;
                        toAdd.add(oldMedoid);
                        toRemove.add(point);
                        break outerloop;
                    } else {
                        cluster.swapWithMediod(oldMedoid);
                    }
                }
            }
            data.removeAll(toRemove);
            data.addAll(toAdd);
        }
        System.out.println(iterations);
        return clusters;
    }

    private static double sumOfDistances(Collection<KMedoidCluster> clusters) {
        double result = 0;
        for (KMedoidCluster cluster : clusters) {
            result += cluster.sumOfDistances();
        }
        return result;
    }

}
