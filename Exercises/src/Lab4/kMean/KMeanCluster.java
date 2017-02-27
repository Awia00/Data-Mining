package Lab4.kMean;

import Lab4.data.Iris;

import java.util.ArrayList;


//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster {

    private ArrayList<Iris> ClusterMembers;

    public KMeanCluster() {
        this.ClusterMembers = new ArrayList<>();
    }

    public KMeanCluster(ArrayList<Iris> clusterMembers) {
        ClusterMembers = clusterMembers;
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");

        for (Iris i : this.ClusterMembers) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }

}
