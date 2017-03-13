package Lab6.Layers;

import Common.Classification;
import Lab6.Neurons.Neuron;
import Lab6.Neurons.OutputNeuron;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 13-03-2017.
 */
public class OutputLayer implements NeuralLayer{
    Map<Classification, OutputNeuron> neurons;
    public OutputLayer() {
        neurons = new HashMap<>();
        neurons.put(Classification.negative, new OutputNeuron());
        neurons.put(Classification.positive, new OutputNeuron());
    }

    @Override
    public Collection<? extends Neuron> getNeurons() {
        return neurons.values();
    }

    @Override
    public void propogate() {
    }
}
