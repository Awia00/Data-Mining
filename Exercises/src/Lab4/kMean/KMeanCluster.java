package Lab4.kMean;

import Common.*;
import Common.DataStructures.Cluster.Cluster;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.NDimensionalPointBuilder;
import Common.Interfaces.SpaceComparable;

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
        // Todo this should be done in a NdimensionalPoint specific class - maybe builder.
        Map<AttributeKey, SpaceComparable> attributes = new HashMap<>();
        for (AttributeKey attributeKey : mean.getAttributes()) {
            SpaceComparable value = mean.getValueOfAttribute(attributeKey);
            if (value instanceof EuclideanSpaceComparable) {
                attributes.put(attributeKey, EuclidianSpaceToolbox.mean(points.stream().map(point -> (EuclideanSpaceComparable) point.getValueOfAttribute(attributeKey)).collect(Collectors.toList())));
            } else if (value instanceof NominalSpaceComparable) {
                attributes.put(attributeKey, NominalSpaceToolbox.mostCommon(points.stream().map(point -> (NominalSpaceComparable<Enum>) point.getValueOfAttribute(attributeKey)).collect(Collectors.toList())));
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
