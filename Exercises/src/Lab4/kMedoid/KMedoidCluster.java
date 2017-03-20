package Lab4.kMedoid;

import Common.DataStructures.Cluster.Cluster;
import Common.Interfaces.NDimensionalPoint;
import Common.DataTypes.SpaceComparable;

import java.util.ArrayList;

public class KMedoidCluster extends Cluster {

    private NDimensionalPoint medoid;

    public KMedoidCluster(NDimensionalPoint medoid) {
        super(new ArrayList<>());
        this.medoid = medoid;
    }

    public SpaceComparable getMedoid() {
        return medoid;
    }

    public void clearPoints(){points.clear();}


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

    @Override
    public String toString() {
        String toPrintString = "medoid: " + this.medoid.toString() + System.getProperty("line.separator");
        return toPrintString + super.toString();
    }
}
