package Lab2.Classification;

import Common.DataStructures.Tree.Leaf;
import Common.DataStructures.Tree.Node;
import Common.DataTypes.Binary;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.Classifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ander on 13-02-2017.
 */
public class ID3DecisionTreeClassifier implements Classifier<ClassifiablePoint<Binary>, Binary> {

    private Node<Binary> tree;

    @Override
    public void trainWithSet(Collection<ClassifiablePoint<Binary>> trainSet) {
        List<ClassifiablePoint<Binary>> elements = new ArrayList<>(trainSet);
        if (!trainSet.isEmpty())
            tree = buildTree(null, elements, elements.get(0).keySet());
        //tree.print();
    }

    private Node buildTree(Node parent, List<ClassifiablePoint<Binary>> elements, Collection<Integer> attributeKeys) {
        if (belongToSameClass(elements)) {
            return new Leaf(parent, elements.get(0).getClassification());
        }
        if (attributeKeys.isEmpty()) {
            return new Leaf(parent, mostCommon(elements));
        }
        Integer bestAttributeKey = findBestAttribute(elements, attributeKeys);
        List<Integer> newAttributeKeys = new ArrayList<>(attributeKeys);
        newAttributeKeys.remove(bestAttributeKey);

        Node n = new Node(parent, bestAttributeKey);
        n.addChild(null, new Leaf(parent, mostCommon(elements))); // default case.
        for (Map.Entry<Object, List<ClassifiablePoint<Binary>>> entry : split(elements, bestAttributeKey).entrySet()) {
            if (entry.getValue().isEmpty())
                n.addChild(entry.getKey(), new Leaf(parent, mostCommon(elements)));
            else
                n.addChild(entry.getKey(), buildTree(n, entry.getValue(), newAttributeKeys));
        }
        return n;
    }

    private Map<Object, List<ClassifiablePoint<Binary>>> split(Collection<ClassifiablePoint<Binary>> elements, Integer splitAttributeKey) {
        return elements.stream().collect(Collectors.groupingBy(m -> m.get(splitAttributeKey)));
    }

    private Integer findBestAttribute(Collection<ClassifiablePoint<Binary>> elements, Collection<Integer> attributeKeys) {
        Integer bestAttributeKey = null;
        double infoGain = -1;
        for (Integer attributeKey : attributeKeys) {
            double newGain = informationGain(elements, attributeKey);
            if (newGain > infoGain) {
                bestAttributeKey = attributeKey;
                infoGain = newGain;
            }
        }
        if (bestAttributeKey == null) {
            System.out.println("what?");
        }
        return bestAttributeKey;
    }

    private double informationGain(Collection<ClassifiablePoint<Binary>> elements, Integer attributeKey) {
        Map<Object, List<ClassifiablePoint<Binary>>> splittingOnAttribute = elements.stream().collect(Collectors.groupingBy(m -> m.get(attributeKey)));
        double infoD = entropy(elements), infoDA = 0.0;
        for (Map.Entry<Object, List<ClassifiablePoint<Binary>>> entry : splittingOnAttribute.entrySet()) {
            infoDA += entropy(entry.getValue()) * entry.getValue().size() / elements.size();
        }
        return infoD - infoDA;
    }

    private double entropy(Collection<ClassifiablePoint<Binary>> elements) {
        double size = elements.size(), positives = 0, negatives = 0;
        for (ClassifiablePoint<Binary> element : elements) {
            Binary classification = element.getClassification();
            if(classification.equals(Binary.negative()))
                negatives++;
            else
                positives++;
        }
        double probPos = positives / size, probNeg = negatives / size;
        if (probPos == 0.0) return -probNeg * log2(probNeg);
        else if (probNeg == 0.0) return -probPos * log2(probPos);
        else
            return -probPos * log2(probPos) - probNeg * log2(probNeg);
    }

    private boolean belongToSameClass(Collection<ClassifiablePoint<Binary>> elements) {
        Binary twoWayClassification = null;
        for (ClassifiablePoint<Binary> element : elements) {
            if (twoWayClassification == null)
                twoWayClassification = element.getClassification();
            else if (!twoWayClassification.equals(element.getClassification()))
                return false;
        }
        return true;
    }

    private Binary mostCommon(Collection<ClassifiablePoint<Binary>> elements) {
        int negative = 0;
        for (ClassifiablePoint<Binary> element : elements) {
            if (element.getClassification().equals(Binary.negative()))
                negative++;
        }
        return negative >= elements.size() / 2 ? Binary.negative() : Binary.positive();
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }


    @Override
    public Binary classify(ClassifiablePoint<Binary> classifiable) {
        return tree.find(classifiable);
    }
}
