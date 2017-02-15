package Lab2.Classification;

import Lab2.Mushroom;
import Lab2.TreeDataStructure.Leaf;
import Lab2.TreeDataStructure.Node;
import Lab2.enums.Class_Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ander on 13-02-2017.
 */
public class ID3DecisionTreeMushroomClassifier implements IMushroomClassifier {

    private Node tree;

    @Override
    public void trainWithSet(List<Mushroom> mushroomList) {
        tree = buildTree(null, mushroomList, Mushroom.getAttributeList());
    }

    public Node buildTree(Node parent, List<Mushroom> mushrooms, List<Object> attributes)
    {
        if(belongToSameClass(mushrooms)) {
            return new Leaf(parent, mushrooms.get(0).m_Class);
        }
        if(attributes.isEmpty()) {
            return new Leaf(parent, mostCommon(mushrooms));
        }
        Object bestAttribute = findBestAttribute(mushrooms, attributes);
        List<Object> newAttributes = new ArrayList<>(attributes);
        newAttributes.remove(bestAttribute);

        Node n = new Node(parent, bestAttribute);
        n.addChild(Node.defaultObject, new Leaf(parent, mostCommon(mushrooms))); // default case.
        for(Map.Entry<Object, List<Mushroom>> entry: split(mushrooms, bestAttribute).entrySet()) {
            if(entry.getValue().isEmpty())
                n.addChild(entry.getKey(), new Leaf(parent, mostCommon(mushrooms)));
            else
                n.addChild(entry.getKey(), buildTree(n, entry.getValue(), newAttributes));
        }
        return n;
    }

    private Map<Object, List<Mushroom>> split(List<Mushroom> mushrooms, Object splitAttribute)
    {
        return mushrooms.stream().collect(Collectors.groupingBy(m -> m.getAttributeValue(splitAttribute)));
    }

    private Object findBestAttribute(List<Mushroom> mushrooms, List<Object> attributes)
    {
        Object bestAttribute = null;
        double infoGain = -1;
        for(Object attribute: attributes) {
            double newGain = informationGain(mushrooms, attribute);
            if(newGain > infoGain) {
                bestAttribute = attribute;
                infoGain = newGain;
            }
        }
        if(bestAttribute == null)
        {
            System.out.println("what?");
        }
        return bestAttribute;
    }

    private double informationGain(List<Mushroom> mushrooms, Object attribute)
    {
        Map<Object, List<Mushroom>> splittingOnAttribute = mushrooms.stream().collect(Collectors.groupingBy(m -> m.getAttributeValue(attribute)));
        double infoD = entropy(mushrooms), infoDA = 0.0;
        for(Map.Entry<Object, List<Mushroom>> entry: splittingOnAttribute.entrySet()) {
            infoDA += entropy(entry.getValue()) * entry.getValue().size()/mushrooms.size();
        }
        return infoD - infoDA;
    }

    private double entropy(List<Mushroom> mushrooms)
    {
        double size = mushrooms.size(), positives = 0, negatives = 0;
        for(Mushroom mushroom: mushrooms) {
            switch (mushroom.m_Class) {
                case edible:
                    negatives++;
                    break;
                case poisonous:
                    positives++;
                    break;
            }
        }
        double probPos = positives/size, probNeg = negatives/size;
        if(probPos==0.0) return -probNeg*log2(probNeg);
        else if (probNeg == 0.0) return -probPos*log2(probPos);
        else
            return -probPos*log2(probPos) - probNeg*log2(probNeg);
    }

    private boolean belongToSameClass(List<Mushroom> mushrooms)
    {
        Class_Label mLabel = null;
        for(Mushroom mushroom: mushrooms)
        {
            if(mLabel == null)
                mLabel = mushroom.m_Class;
            else if(mLabel != mushroom.m_Class)
                return false;
        }
        return true;
    }

    private Class_Label mostCommon(List<Mushroom> mushrooms)
    {
        int edible = 0;
        for(Mushroom mushroom: mushrooms)
        {
            if(mushroom.m_Class == Class_Label.edible)
                edible++;
        }
        return edible>=mushrooms.size()/2 ? Class_Label.edible : Class_Label.poisonous;
    }

    private double log2(double x){
        return Math.log(x) / Math.log(2);
    }

    @Override
    public Class_Label classify(Mushroom mushroom) {
        return iterateTree(tree, mushroom);
    }

    private Class_Label iterateTree(Node n, Mushroom mushroom)
    {
        if(n instanceof Leaf) {
            return ((Leaf)n).label;
        }
        Object label = mushroom.getAttributeValue(n.splitOn);
        return iterateTree(n.getChild(label), mushroom);
    }
}
