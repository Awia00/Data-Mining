package Lab4.kMedoid;

import Common.Interfaces.NDimensionalPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class KMedoid {

    public static Collection<KMedoidCluster> KMedoidPartition(int k, List<NDimensionalPoint> data) {
        Collection<KMedoidCluster> clusters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            clusters.add(new KMedoidCluster(data.get(random.nextInt(data.size()))));
        }
        for (NDimensionalPoint datum : data) {
            KMedoidCluster best = null;
            for (KMedoidCluster cluster : clusters) {
                if (best == null || cluster.getMedoid().distance(datum) < best.getMedoid().distance(datum))
                    best = cluster;
            }
            best.add(datum);
        }
        double lastConfigurationCost = Double.MAX_VALUE;
        double configurationCost = Double.MAX_VALUE - 1;
        int iterations = 0;
        while (configurationCost + 1E-10 <= lastConfigurationCost) {
            lastConfigurationCost = configurationCost;
            iterations++;
            // assignment
            for (NDimensionalPoint point : data) {
                for (KMedoidCluster cluster : clusters) {
                    NDimensionalPoint oldMedoid = cluster.swapWithMediod(point);
                    double sumOfDistances = sumOfDistances(clusters);
                    if (sumOfDistances < configurationCost) {
                        configurationCost = sumOfDistances;
                    } else {
                        cluster.swapWithMediod(oldMedoid);
                    }
                }
            }
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
