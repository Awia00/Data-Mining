package Common.DataStructures.Cluster;

import Common.Interfaces.NDimensionalPoint;

/**
 * Created by ander on 27-02-2017.
 */
public interface ClusterBuilder<T extends Cluster> {

    void add(NDimensionalPoint point);

    T buildCluster();
}
