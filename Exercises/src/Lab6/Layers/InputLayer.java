package Lab6.Layers;

import Common.AttributeKey;
import Common.EuclideanSpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Common.Interfaces.SpaceComparable;
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
    private Map<AttributeKey, Neuron> neurons;
    public InputLayer(NDimensionalPoint attribute) {
        neurons = new HashMap<>();
        Random random = new Random();
        for (AttributeKey attributeKey : attribute.getAttributes()) {
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
        for (AttributeKey attributeKey : point.getAttributes()) {
            SpaceComparable value = point.getValueOfAttribute(attributeKey);
            if(value instanceof EuclideanSpaceComparable)
            {
                neurons.get(attributeKey).activate(((EuclideanSpaceComparable) value).getDoubleValue());
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
