package Lab4.kMean;

import Common.DataStructures.Cluster.ClusterBuilder;
import Common.Interfaces.NDimensionalPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 27-02-2017.
 */
public class KMeanClusterBuilder implements ClusterBuilder<KMeanCluster> {
    List<NDimensionalPoint> points;

    public KMeanClusterBuilder() {
        this.points = new ArrayList<>();
    }

    @Override
    public void add(NDimensionalPoint point) {
        points.add(point);
    }

    @Override
    public KMeanCluster buildCluster() {
        List<NDimensionalPoint> clusterMembers = points;
        NDimensionalPoint mean = computeMean();
        points = new ArrayList<>();
        return new KMeanCluster(clusterMembers, mean);
    }

    public NDimensionalPoint computeMean()
    {
        return null;
    }
}
