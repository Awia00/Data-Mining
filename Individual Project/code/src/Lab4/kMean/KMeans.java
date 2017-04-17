package Lab4.kMean;

import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class KMeans {

    public Collection<KMeanCluster> KMeansPartition(int k, List<? extends NDimensionalPoint> data, NDimensionalPointBuilder dataBuilder) {
        return KMeansPartition(k, data, dataBuilder, new Random());
    }

    public Collection<KMeanCluster> KMeansPartition(int k, List<? extends NDimensionalPoint> data, NDimensionalPointBuilder dataBuilder, Random random) {
        Collection<KMeanCluster> clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            clusters.add(new KMeanCluster(data.get(random.nextInt(data.size())), dataBuilder));
        }
        boolean converged = false;
        int iterations = 0;
        while (!converged) {
            iterations++;
            // assignment
            for (NDimensionalPoint point : data) {
                KMeanCluster best = null;
                for (KMeanCluster c : clusters)
                    if (best == null || point.distance(c.getMean()) < point.distance(best.getMean()))
                        best = c;
                best.add(point);
            }
            // update
            ArrayList<KMeanCluster> newClusters = new ArrayList<>();
            converged = true;
            for (KMeanCluster c : clusters) {
                NDimensionalPoint mean = c.calculateMean();
                if (!c.getMean().almostEquals(mean))
                    converged = false;
                if (mean != null)
                    newClusters.add(new KMeanCluster(mean, dataBuilder));
                else
                    System.out.printf("===> Empty cluster at %s%n", c.getMean());
            }
            if (!converged)
                clusters = newClusters;
        }
        System.out.println("iterations: " + iterations);
        return clusters;
    }

}
