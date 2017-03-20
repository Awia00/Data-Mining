package Lab6.Layers;

import Common.DataTypes.Nominal;
import Lab6.Neurons.Neuron;
import Lab6.Neurons.OutputNeuron;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 13-03-2017.
 */
public class OutputLayer implements NeuralLayer{
    Map<Enum, OutputNeuron> neurons;
    public OutputLayer(Nominal classification) {
        neurons = new HashMap<>();
        for (Enum anEnum : classification.getPossibleValues()) {
            neurons.put(anEnum, new OutputNeuron());
        }
    }

    @Override
    public Collection<? extends Neuron> getNeurons() {
        return neurons.values();
    }

    @Override
    public void propogate() {
    }
}
