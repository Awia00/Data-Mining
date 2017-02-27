package Lab4.kMedoid;

import Common.Interfaces.SpaceComparable;

import java.util.ArrayList;

public class KMedoidCluster {

    public ArrayList<SpaceComparable> ClusterMembers;
    public SpaceComparable Medoid;

    public KMedoidCluster(SpaceComparable medoid) {
        this.ClusterMembers = new ArrayList<SpaceComparable>();
        this.Medoid = medoid;
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
        toPrintString += "Medoid: " + this.Medoid.toString() + System.getProperty("line.separator");
        for (SpaceComparable i : this.ClusterMembers) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }

}
