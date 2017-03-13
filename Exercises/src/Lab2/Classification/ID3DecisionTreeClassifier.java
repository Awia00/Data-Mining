package Lab2.Classification;

import Common.AttributeKey;
import Common.DataStructures.Tree.Leaf;
import Common.DataStructures.Tree.Node;
import Common.Interfaces.Classifier;
import Common.Interfaces.TwoWayClassifiable;
import Common.Interfaces.WithAttributes;
import Common.Statistics.EvaluationStatistics;
import Common.Statistics.EvaluationSuite;
import Common.TwoWayClassification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ander on 13-02-2017.
 */
public class ID3DecisionTreeClassifier implements Classifier<Object, TwoWayClassifiable> {

    private Node tree;

    @Override
    public void trainWithSet(Collection<TwoWayClassifiable> trainSet) {
        List<TwoWayClassifiable> elements = new ArrayList<>(trainSet);
        if (!trainSet.isEmpty())
            tree = buildTree(null, elements, elements.get(0).getAttributes());
        //tree.print();
    }

    private Node buildTree(Node parent, List<TwoWayClassifiable> elements, Collection<AttributeKey> attributeKeys) {
        if (belongToSameClass(elements)) {
            return new Leaf(parent, elements.get(0).getClassification());
        }
        if (attributeKeys.isEmpty()) {
            return new Leaf(parent, mostCommon(elements));
        }
        AttributeKey bestAttributeKey = findBestAttribute(elements, attributeKeys);
        List<AttributeKey> newAttributeKeys = new ArrayList<>(attributeKeys);
        newAttributeKeys.remove(bestAttributeKey);

        Node n = new Node(parent, bestAttributeKey);
        n.addChild(null, new Leaf(parent, mostCommon(elements))); // default case.
        for (Map.Entry<Object, List<TwoWayClassifiable>> entry : split(elements, bestAttributeKey).entrySet()) {
            if (entry.getValue().isEmpty())
                n.addChild(entry.getKey(), new Leaf(parent, mostCommon(elements)));
            else
                n.addChild(entry.getKey(), buildTree(n, entry.getValue(), newAttributeKeys));
        }
        return n;
    }

    private Map<Object, List<TwoWayClassifiable>> split(Collection<TwoWayClassifiable> elements, AttributeKey splitAttributeKey) {
        return elements.stream().collect(Collectors.groupingBy(m -> m.getValueOfAttribute(splitAttributeKey)));
    }

    private AttributeKey findBestAttribute(Collection<TwoWayClassifiable> elements, Collection<AttributeKey> attributeKeys) {
        AttributeKey bestAttributeKey = null;
        double infoGain = -1;
        for (AttributeKey attributeKey : attributeKeys) {
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

    private double informationGain(Collection<TwoWayClassifiable> elements, AttributeKey attributeKey) {
        Map<Object, List<TwoWayClassifiable>> splittingOnAttribute = elements.stream().collect(Collectors.groupingBy(m -> m.getValueOfAttribute(attributeKey)));
        double infoD = entropy(elements), infoDA = 0.0;
        for (Map.Entry<Object, List<TwoWayClassifiable>> entry : splittingOnAttribute.entrySet()) {
            infoDA += entropy(entry.getValue()) * entry.getValue().size() / elements.size();
        }
        return infoD - infoDA;
    }

    private double entropy(Collection<TwoWayClassifiable> elements) {
        double size = elements.size(), positives = 0, negatives = 0;
        for (TwoWayClassifiable element : elements) {
            TwoWayClassification classification = element.getClassification();
            if(classification.equals(TwoWayClassification.negative()))
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

    private boolean belongToSameClass(Collection<TwoWayClassifiable> elements) {
        TwoWayClassification twoWayClassification = null;
        for (TwoWayClassifiable element : elements) {
            if (twoWayClassification == null)
                twoWayClassification = element.getClassification();
            else if (!twoWayClassification.equals(element.getClassification()))
                return false;
        }
        return true;
    }

    private TwoWayClassification mostCommon(Collection<TwoWayClassifiable> elements) {
        int negative = 0;
        for (TwoWayClassifiable element : elements) {
            if (element.getClassification().equals(TwoWayClassification.negative()))
                negative++;
        }
        return negative >= elements.size() / 2 ? TwoWayClassification.negative() : TwoWayClassification.positive();
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    @Override
    public TwoWayClassification classify(WithAttributes element) {
        return tree.find(element);
    }

    @Override
    public EvaluationStatistics testWithSet(Collection<TwoWayClassifiable> testSet) {
        return new EvaluationSuite().testClassifier(this, testSet);
    }
}
