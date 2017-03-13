package Lab4.kMedoid;

import Common.Interfaces.NDimensionalPoint;

import java.util.*;

public class KMedoid {

    public static Collection<KMedoidCluster> KMedoidPartition(int k, List<? extends NDimensionalPoint> data) {

        Collection<KMedoidCluster> clusters = new ArrayList<>();
        // pick random mediods
        List<NDimensionalPoint> input = new ArrayList<>(data);
        List<NDimensionalPoint> toRemove = new ArrayList<>();
        List<NDimensionalPoint> toAdd;
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            NDimensionalPoint point = input.get(random.nextInt(input.size()));
            toRemove.add(point);
            clusters.add(new KMedoidCluster(point));
        }
        input.removeAll(toRemove);

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
            for (NDimensionalPoint datum : input) {
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
            for (NDimensionalPoint point : input) {
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
            input.removeAll(toRemove);
            input.addAll(toAdd);
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
