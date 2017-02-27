package Lab4.kMean;

import Common.Interfaces.WithAttributes;

import java.util.ArrayList;


//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster<SpaceComparable> {

    private ArrayList<WithAttributes<SpaceComparable>> clusterMembers;

    public KMeanCluster() {
        this.clusterMembers = new ArrayList<>();
    }

    public KMeanCluster(ArrayList<WithAttributes<SpaceComparable>> clusterMembers) {
        this.clusterMembers = clusterMembers;
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");

        for (WithAttributes<SpaceComparable> i : this.clusterMembers) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }

}
