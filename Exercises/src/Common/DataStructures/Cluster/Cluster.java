package Common.DataStructures.Cluster;

import Common.Interfaces.NDimensionalPoint;

import java.util.List;

/**
 * Created by ander on 27-02-2017.
 */
public abstract class Cluster {
    public List<NDimensionalPoint> points;

    public Cluster(List<NDimensionalPoint> points) {
        this.points = points;
    }
}
