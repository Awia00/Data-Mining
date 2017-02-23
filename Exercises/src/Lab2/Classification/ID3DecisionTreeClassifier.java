package Lab2.Classification;

import Lab2.Interfaces.Attribute;
import Lab2.Interfaces.Classification;
import Lab2.Interfaces.WithAttributes;
import Lab2.StatisticsSuite.EvaluationStatistics;
import Lab2.StatisticsSuite.EvaluationSuite;
import Lab2.TreeDataStructure.Leaf;
import Lab2.TreeDataStructure.Node;

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
    public void trainWithSet(Collection<WithAttributes<Object>> trainSet) {
        List<WithAttributes<Object>> elements = new ArrayList<>(trainSet);
        if (!trainSet.isEmpty())
            tree = buildTree(null, elements, elements.get(0).getAttributes());
        tree.print();
    }

    private Node buildTree(Node parent, List<WithAttributes<Object>> elements, Collection<Attribute> attributes) {
        if (belongToSameClass(elements)) {
            return new Leaf(parent, elements.get(0).getClassification());
        }
        if (attributes.isEmpty()) {
            return new Leaf(parent, mostCommon(elements));
        }
        Attribute bestAttribute = findBestAttribute(elements, attributes);
        List<Attribute> newAttributes = new ArrayList<>(attributes);
        newAttributes.remove(bestAttribute);

        Node n = new Node(parent, bestAttribute);
        n.addChild(Node.defaultObject, new Leaf(parent, mostCommon(elements))); // default case.
        for (Map.Entry<Object, List<WithAttributes<Object>>> entry : split(elements, bestAttribute).entrySet()) {
            if (entry.getValue().isEmpty())
                n.addChild(entry.getKey(), new Leaf(parent, mostCommon(elements)));
            else
                n.addChild(entry.getKey(), buildTree(n, entry.getValue(), newAttributes));
        }
        return n;
    }

    private Map<Object, List<WithAttributes<Object>>> split(Collection<WithAttributes<Object>> elements, Attribute splitAttribute) {
        return elements.stream().collect(Collectors.groupingBy(m -> m.getValueOfAttribute(splitAttribute)));
    }

    private Attribute findBestAttribute(Collection<WithAttributes<Object>> elements, Collection<Attribute> attributes) {
        Attribute bestAttribute = null;
        double infoGain = -1;
        for (Attribute attribute : attributes) {
            double newGain = informationGain(elements, attribute);
            if (newGain > infoGain) {
                bestAttribute = attribute;
                infoGain = newGain;
            }
        }
        if (bestAttribute == null) {
            System.out.println("what?");
        }
        return bestAttribute;
    }

    private double informationGain(Collection<WithAttributes<Object>> elements, Attribute attribute) {
        Map<Object, List<WithAttributes<Object>>> splittingOnAttribute = elements.stream().collect(Collectors.groupingBy(m -> m.getValueOfAttribute(attribute)));
        double infoD = entropy(elements), infoDA = 0.0;
        for (Map.Entry<Object, List<WithAttributes<Object>>> entry : splittingOnAttribute.entrySet()) {
            infoDA += entropy(entry.getValue()) * entry.getValue().size() / elements.size();
        }
        return infoD - infoDA;
    }

    private double entropy(Collection<WithAttributes<Object>> elements) {
        double size = elements.size(), positives = 0, negatives = 0;
        for (WithAttributes element : elements) {
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

    private boolean belongToSameClass(Collection<WithAttributes<Object>> elements) {
        Classification classification = null;
        for (WithAttributes element : elements) {
            if (classification == null)
                classification = element.getClassification();
            else if (classification != element.getClassification())
                return false;
        }
        return true;
    }

    private Classification mostCommon(Collection<WithAttributes<Object>> elements) {
        int negative = 0;
        for (WithAttributes element : elements) {
            if (element.getClassification() == Classification.negative)
                negative++;
        }
        return negative >= elements.size() / 2 ? Classification.negative : Classification.positive;
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    private Classification iterateTree(Node n, WithAttributes element) {
        if (n instanceof Leaf) {
            return ((Leaf) n).label;
        }
        Object label = element.getValueOfAttribute(n.splitOn);
        return iterateTree(n.getChild(label), element);
    }

    @Override
    public Classification classify(WithAttributes element) {
        return iterateTree(tree, element);
    }

    @Override
    public EvaluationStatistics testWithSet(Collection<WithAttributes<Object>> testSet) {
        return new EvaluationSuite().testClassifier(this, testSet);
    }
}
