package Lab4.kMean;

import Common.AttributeKey;
import Common.DataStructures.Cluster.Cluster;
import Common.EuclideanSpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.SpaceComparable;
import Common.NominalSpaceComparable;
import Lab4.data.IrisBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster extends Cluster {

    private NDimensionalPoint mean;

    public KMeanCluster() {
        super(new ArrayList<>());
    }

    public KMeanCluster(NDimensionalPoint mean) {
        super(new ArrayList<>());
        this.mean = mean;
    }


    public KMeanCluster(List<NDimensionalPoint> clusterMembers, NDimensionalPoint mean) {
        super(clusterMembers);
        this.mean = mean;
    }

    public void add(NDimensionalPoint p){
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

    public NDimensionalPoint getMean() {
        if(mean == null) mean = calculateMean();
        return mean;
    }

    private NDimensionalPoint calculateMean()
    {
        Map<AttributeKey, SpaceComparable> attributes = new HashMap<>();
        for (AttributeKey attributeKey : mean.getAttributes()) {
            SpaceComparable value = mean.getValueOfAttribute(attributeKey);
            if(value instanceof EuclideanSpaceComparable) {
                double meanOfAttribute = points.stream().mapToDouble(x -> ((EuclideanSpaceComparable)x.getValueOfAttribute(attributeKey)).getDoubleValue()).sum() / points.size();
                attributes.put(attributeKey, new EuclideanSpaceComparable(meanOfAttribute));
            } else if(value instanceof NominalSpaceComparable)
            {
                Map<Enum, Integer> map = new HashMap<>();
                for (NDimensionalPoint point : points) {
                    Enum key = ((NominalSpaceComparable)point.getValueOfAttribute(attributeKey)).getValue();
                    Integer current = map.get(key);
                    map.put(key, current==null ? 1: current+1);
                }
                Map.Entry<Enum, Integer> max = null;

                for (Map.Entry<Enum, Integer> e : map.entrySet()) {
                    if (max == null || e.getValue() > max.getValue())
                        max = e;
                }
                attributes.put(attributeKey, new NominalSpaceComparable(max.getKey()));
            }
        }
        // todo abstract that out
        return new IrisBuilder().buildPoint(attributes);
    }
}
