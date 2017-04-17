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

    public void add(NDimensionalPoint p) {
        points.add(p);
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");

        for (NDimensionalPoint i : points) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }
}
