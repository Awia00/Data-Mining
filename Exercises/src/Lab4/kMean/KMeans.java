package Lab4.kMean;

import Common.Interfaces.NDimensionalPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class KMeans {

    public Collection<KMeanCluster> KMeansPartition(int k, List<NDimensionalPoint> data) {
        Collection<KMeanCluster> clusters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            clusters.add(new KMeanCluster(data.get(random.nextInt(data.size()))));
        }
        boolean converged = false;
        while (!converged)
        {
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
                NDimensionalPoint mean = c.getMean();
                if (!c.getMean().almostEquals(mean))
                    converged = false;
                if (mean != null)
                    newClusters.add(new KMeanCluster(mean));
                else
                    System.out.printf("===> Empty cluster at %s%n", c.getMean());
            }
        }
        return clusters;
    }

}
