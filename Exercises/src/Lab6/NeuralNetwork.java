package Lab6;

import Common.DataTypes.Nominal;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.Classifier;
import Lab6.Layers.HiddenLayer;
import Lab6.Layers.InputLayer;
import Lab6.Layers.OutputLayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by aws on 13-03-2017.
 */
public class NeuralNetwork<V extends Nominal> implements Classifier<ClassifiablePoint<V>,V>{
    private final double learningRate;
    private final int amtHiddenLayers;
    private final int iterations;

    public NeuralNetwork(int iterations, double learningRate, int amtHiddenLayers)
    {
        this.iterations = iterations;
        this.learningRate = learningRate;
        this.amtHiddenLayers = amtHiddenLayers;
    }

    InputLayer inputLayer;
    OutputLayer outputLayer;
    List<HiddenLayer> hiddenLayers;
    @Override
    public void trainWithSet(Collection<ClassifiablePoint<V>> trainSet) {
        List<ClassifiablePoint<V>> points = new ArrayList<>(trainSet);
        ClassifiablePoint<V> point = points.get(0);

        inputLayer = new InputLayer(point.getDefaultPoint().entrySet());
        outputLayer = new OutputLayer(point.getClassification());
        hiddenLayers = new ArrayList<>();
        for (int i = 0; i < amtHiddenLayers; i++) {
            hiddenLayers.add(new HiddenLayer(10));
        }

        inputLayer.setOutputLayer(hiddenLayers.get(0));
        hiddenLayers.get(0).setPreviousLayer(inputLayer);
        for (int i = 0; i < amtHiddenLayers-1; i++) {
            hiddenLayers.get(i).setOutputLayer(hiddenLayers.get(i+1));
            hiddenLayers.get(i+1).setPreviousLayer(hiddenLayers.get(i));
        }
        hiddenLayers.get(amtHiddenLayers-1).setOutputLayer(outputLayer);
        outputLayer.setPreviousLayer(hiddenLayers.get(amtHiddenLayers-1));

        for (int i = 0; i < iterations; i++) {
            for (ClassifiablePoint<V> classifiablePoint : trainSet) {
                inputLayer.propogate(classifiablePoint);
                System.out.println(outputLayer.getNeurons());
                outputLayer.backPropogate(classifiablePoint.getClassification(), 1/(i+1));
            }
        }
    }

    @Override
    public V classify(ClassifiablePoint<V> classifiable) {
        System.out.println("Point is: " + classifiable.getClassification());
        inputLayer.propogate(classifiable);
        double bestValue = -1;
        Enum best = null;
        for (Map.Entry<Enum, Double> enumDoubleEntry : outputLayer.getResult().entrySet()) {
            System.out.println(enumDoubleEntry);
            if(best == null || bestValue< enumDoubleEntry.getValue()){
                best = enumDoubleEntry.getKey();
                bestValue = enumDoubleEntry.getValue();
            }
        }
        System.out.println("Network classifies it as: " + best);
        return (V)new Nominal(best);
    }
}
