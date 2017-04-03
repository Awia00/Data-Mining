package Lab6.Layers;

/**
 * Created by aws on 13-03-2017.
 */

import Lab6.Neurons.Neuron;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface NeuralLayer {

    Collection<? extends Neuron> getNeurons();
    void propogate();
    void backPropogate(Map<Neuron, Double> errors, double learningRate);
}
