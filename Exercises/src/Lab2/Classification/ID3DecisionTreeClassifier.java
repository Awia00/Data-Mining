package Lab2.Classification;

import Common.AttributeKey;
import Common.Classification;
import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;
import Common.Interfaces.WithAttributes;
import Common.Statistics.EvaluationStatistics;
import Common.Statistics.EvaluationSuite;
import Common.DataStructures.Tree.Leaf;
import Common.DataStructures.Tree.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ander on 13-02-2017.
 */
public class ID3DecisionTreeClassifier implements Classifier<Object> {

    private Node tree;

    @Override
    public void trainWithSet(Collection<Classifiable> trainSet) {
        List<Classifiable> elements = new ArrayList<>(trainSet);
        if (!trainSet.isEmpty())
            tree = buildTree(null, elements, elements.get(0).getAttributes());
        //tree.print();
    }

    private Node buildTree(Node parent, List<Classifiable> elements, Collection<AttributeKey> attributeKeys) {
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
        for (Map.Entry<Object, List<Classifiable>> entry : split(elements, bestAttributeKey).entrySet()) {
            if (entry.getValue().isEmpty())
                n.addChild(entry.getKey(), new Leaf(parent, mostCommon(elements)));
            else
                n.addChild(entry.getKey(), buildTree(n, entry.getValue(), newAttributeKeys));
        }
        return n;
    }

    private Map<Object, List<Classifiable>> split(Collection<Classifiable> elements, AttributeKey splitAttributeKey) {
        return elements.stream().collect(Collectors.groupingBy(m -> m.getValueOfAttribute(splitAttributeKey)));
    }

    private AttributeKey findBestAttribute(Collection<Classifiable> elements, Collection<AttributeKey> attributeKeys) {
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

    private double informationGain(Collection<Classifiable> elements, AttributeKey attributeKey) {
        Map<Object, List<Classifiable>> splittingOnAttribute = elements.stream().collect(Collectors.groupingBy(m -> m.getValueOfAttribute(attributeKey)));
        double infoD = entropy(elements), infoDA = 0.0;
        for (Map.Entry<Object, List<Classifiable>> entry : splittingOnAttribute.entrySet()) {
            infoDA += entropy(entry.getValue()) * entry.getValue().size() / elements.size();
        }
        return infoD - infoDA;
    }

    private double entropy(Collection<Classifiable> elements) {
        double size = elements.size(), positives = 0, negatives = 0;
        for (Classifiable element : elements) {
            switch (element.getClassification()) {
                case negative:
                    negatives++;
                    break;
                case positive:
                    positives++;
                    break;
            }
        }
        double probPos = positives / size, probNeg = negatives / size;
        if (probPos == 0.0) return -probNeg * log2(probNeg);
        else if (probNeg == 0.0) return -probPos * log2(probPos);
        else
            return -probPos * log2(probPos) - probNeg * log2(probNeg);
    }

    private boolean belongToSameClass(Collection<Classifiable> elements) {
        Classification classification = null;
        for (Classifiable element : elements) {
            if (classification == null)
                classification = element.getClassification();
            else if (classification != element.getClassification())
                return false;
        }
        return true;
    }

    private Classification mostCommon(Collection<Classifiable> elements) {
        int negative = 0;
        for (Classifiable element : elements) {
            if (element.getClassification() == Classification.negative)
                negative++;
        }
        return negative >= elements.size() / 2 ? Classification.negative : Classification.positive;
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    @Override
    public Classification classify(WithAttributes element) {
        return tree.find(element);
    }

    @Override
    public EvaluationStatistics testWithSet(Collection<Classifiable> testSet) {
        return new EvaluationSuite().testClassifier(this, testSet);
    }
}
