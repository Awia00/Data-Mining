package Lab6;

import Common.Interfaces.NDimensionalPoint;
import Lab6.Layers.HiddenLayer;
import Lab6.Layers.InputLayer;
import Lab6.Layers.OutputLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aws on 13-03-2017.
 */
public class NeuralNetwork {
    InputLayer inputLayer;
    OutputLayer outputLayer;
    List<HiddenLayer> hiddenLayers;
    public NeuralNetwork(NDimensionalPoint attributes, double learningRate)
    {
        inputLayer = new InputLayer(attributes);
        outputLayer = new OutputLayer();
        hiddenLayers = new ArrayList<>();
        hiddenLayers.add(new HiddenLayer(3));
        inputLayer.setOutputLayer(hiddenLayers.get(0));
        hiddenLayers.get(0).setOutputLayer(outputLayer);

        inputLayer.propogate(attributes);
        System.out.println(outputLayer.getNeurons());
    }
}
