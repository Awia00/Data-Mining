package Lab4.kMedoid;

import Common.DataStructures.Cluster.Cluster;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.SpaceComparable;

import java.util.ArrayList;
import java.util.Collection;

public class KMedoidCluster extends Cluster {

    private NDimensionalPoint medoid;

    public KMedoidCluster(NDimensionalPoint medoid) {
        super(new ArrayList<>());
        this.medoid = medoid;
    }

    public SpaceComparable getMedoid() {
        return medoid;
    }

    public void add(NDimensionalPoint p) {
        points.add(p);
    }


    public NDimensionalPoint swapWithMediod(NDimensionalPoint candidate) {
        NDimensionalPoint temp = medoid;
        medoid = candidate;
        return temp;
    }

    public double sumOfDistances() {
        double result = 0.0;
        for (NDimensionalPoint point : points) {
            result += point.distance(medoid);
        }
        return result;
    }

    public double configurationCost(Collection<NDimensionalPoint> points) {
        return 0.0;
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
        toPrintString += "medoid: " + this.medoid.toString() + System.getProperty("line.separator");
        for (SpaceComparable i : this.points) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }
}
