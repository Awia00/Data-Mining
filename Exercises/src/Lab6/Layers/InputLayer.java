package Lab6.Layers;

import Common.DataTypes.Numeric;
import Common.Interfaces.NDimensionalPoint;
import Common.DataTypes.SpaceComparable;
import Lab6.ActivateFunctions.SigmoidFunction;
import Lab6.Neurons.Neuron;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by aws on 13-03-2017.
 */
public class InputLayer implements NeuralLayer{
    private NeuralLayer outputLayer;
    private Map<Integer, Neuron> neurons;
    public InputLayer(NDimensionalPoint attribute) {
        neurons = new HashMap<>();
        Random random = new Random();
        for (Integer attributeKey : attribute.keySet()) {
            neurons.put(attributeKey, new Neuron(new SigmoidFunction(), 0.1*random.nextDouble()));
        }
    }

    public void setOutputLayer(NeuralLayer outputLayer) {
        this.outputLayer = outputLayer;
        Random random = new Random();
        for (Neuron neuron : neurons.values()) {
            for (Neuron neuron1 : outputLayer.getNeurons()) {
                neuron.AddOutGoingNeuronConnection(neuron1, 0.1*random.nextDouble());
            }
        }
    }

    public void propogate(NDimensionalPoint point)
    {
        for (Integer attributeKey : point.keySet()) {
            SpaceComparable value = point.get(attributeKey);
            if(value instanceof Numeric)
            {
                neurons.get(attributeKey).activate(((Numeric) value).getValue());
            }
        }
        outputLayer.propogate();
    }


    @Override
    public Collection<? extends Neuron> getNeurons() {
        return neurons.values();
    }

    @Override
    public void propogate() {

    }
}
