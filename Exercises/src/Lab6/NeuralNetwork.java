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

        inputLayer = new InputLayer(point.getDefaultPoint());
        outputLayer = new OutputLayer(point.getClassification());
        hiddenLayers = new ArrayList<>();
        for (int i = 0; i < amtHiddenLayers; i++) {
            hiddenLayers.add(new HiddenLayer(3));
        }

        inputLayer.setOutputLayer(hiddenLayers.get(0));
        for (int i = 0; i < amtHiddenLayers-1; i++) {
            hiddenLayers.get(i).setOutputLayer(hiddenLayers.get(i+1));
        }
        hiddenLayers.get(amtHiddenLayers-1).setOutputLayer(outputLayer);

        for (int i = 0; i < iterations; i++) {
            for (ClassifiablePoint<V> vClassifiablePoint : trainSet) {
                inputLayer.propogate(point);
                System.out.println(outputLayer.getNeurons());
            }
        }
    }

    @Override
    public V classify(ClassifiablePoint<V> classifiable) {
        return null;
    }
}
