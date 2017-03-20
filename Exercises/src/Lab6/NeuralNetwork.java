package Lab6;

import Common.DataTypes.Nominal;
import Common.Interfaces.ClassifiablePoint;
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
    public NeuralNetwork(ClassifiablePoint<Nominal> points, double learningRate, int amtHiddenLayers)
    {
        inputLayer = new InputLayer(points.getDefaultPoint());
        outputLayer = new OutputLayer(points.getClassification());
        hiddenLayers = new ArrayList<HiddenLayer>();
        for (int i = 0; i < amtHiddenLayers; i++) {
            hiddenLayers.add(new HiddenLayer(3));
        }

        inputLayer.setOutputLayer(hiddenLayers.get(0));
        for (int i = 0; i < amtHiddenLayers-1; i++) {
            hiddenLayers.get(i).setOutputLayer(hiddenLayers.get(i+1));
        }
        hiddenLayers.get(amtHiddenLayers-1).setOutputLayer(outputLayer);

        inputLayer.propogate(points);
        System.out.println(outputLayer.getNeurons());
    }
}
