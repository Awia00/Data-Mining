package Lab6;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by aws on 13-03-2017.
 */
public class NeuralLayer {
    Neuron[] neurons;
    private NeuralLayer inputLayer;
    private NeuralLayer outputLayer;

    public NeuralLayer(int topology) {
        this.neurons = new Neuron[topology];
    }

    public void setInputLayer(NeuralLayer inputLayer) {
        this.inputLayer = inputLayer;
    }

    public void setOutputLayer(NeuralLayer outputLayer) {
        this.outputLayer = outputLayer;
    }
}
