package Lab4.kMean;
import Lab4.data.Iris;

import java.util.ArrayList;


//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster {

	public ArrayList<Iris> ClusterMembers;
	
	public KMeanCluster()
	{
		this.ClusterMembers = new ArrayList<Iris>();
	}
	
	@Override
	public String toString() {
		String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
		
		for(Iris i : this.ClusterMembers)
		{
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");
		
		return toPrintString;
	}

}
