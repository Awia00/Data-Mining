package Lab6;

import Common.Interfaces.NDimensionalPoint;
import Data.Iris.Iris;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by aws on 13-03-2017.
 */
public class NeuralNetwork {
    NeuralLayer inputLayer, outputLayer;
    Collection<NeuralLayer> hiddenLayers;
    public NeuralNetwork(Collection<Iris> points, double learningRate, int[] hiddenLayersTopology)
    {
        inputLayer = new InputLayer();
        outputLayer = new OutputLayer();
        hiddenLayers = new ArrayList<>();
    }
}
