package Lab4.kMedoid;

import Common.DataStructures.Cluster.Cluster;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.SpaceComparable;

import java.util.ArrayList;

public class KMedoidCluster extends Cluster {

    public SpaceComparable Medoid;

    public KMedoidCluster(NDimensionalPoint medoid) {
        super(new ArrayList<>());
        this.Medoid = medoid;
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
        toPrintString += "Medoid: " + this.Medoid.toString() + System.getProperty("line.separator");
        for (SpaceComparable i : this.points) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }
}
