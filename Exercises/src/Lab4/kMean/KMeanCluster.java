package Lab4.kMean;

import Common.DataStructures.Cluster.Cluster;
import Common.DataTypes.*;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster extends Cluster {

    private final NDimensionalPoint mean;
    NDimensionalPointBuilder meanBuilder;

    public KMeanCluster(NDimensionalPoint mean, NDimensionalPointBuilder meanBuilder) {
        super(new ArrayList<>());
        this.mean = mean;
        this.meanBuilder = meanBuilder;
    }

    public NDimensionalPoint getMean() {
        return mean;
    }

    public NDimensionalPoint calculateMean() {
        Map<Integer, SpaceComparable> attributes = new HashMap<>();
        for (Integer attributeKey : mean.keySet()) {
            SpaceComparable value = mean.get(attributeKey);
            if (value instanceof Numeric) {
                attributes.put(attributeKey, EuclidianSpaceToolbox.mean(points.stream().map(point -> (Numeric) point.get(attributeKey)).collect(Collectors.toList())));
            } else if (value instanceof Nominal) {
                attributes.put(attributeKey, NominalSpaceToolbox.mostCommon(points.stream().map(point -> (Nominal) point.get(attributeKey)).collect(Collectors.toList())));
            }
        }
        return meanBuilder.buildPointOnlyFrom(attributes);
    }

    @Override
    public String toString() {
        String toPrintString = "mean: " + this.mean.toString() + System.getProperty("line.separator");
        return toPrintString + super.toString();
    }
}
