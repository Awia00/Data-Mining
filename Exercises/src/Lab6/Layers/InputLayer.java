package Lab6.Layers;

import Common.DataTypes.Nominal;
import Common.DataTypes.Numeric;
import Common.Interfaces.NDimensionalPoint;
import Common.DataTypes.SpaceComparable;
import Lab6.ActivateFunctions.SigmoidFunction;
import Lab6.Neurons.Neuron;

import java.util.*;

/**
 * Created by aws on 13-03-2017.
 */
public class InputLayer implements NeuralLayer{
    private NeuralLayer outputLayer;
    private Map<Integer, Neuron> neurons;
    private Map<Enum, Integer> nominalToKey;
    public InputLayer(Set<Map.Entry<Integer, SpaceComparable>> attributes) {
        neurons = new HashMap<>();
        nominalToKey = new HashMap<>();
        Random random = new Random();
        Integer max = attributes.stream().max((x,y) -> x.getKey()).get().getKey() + 1;
        for (Map.Entry<Integer, SpaceComparable> attributePair : attributes) {
            if(attributePair.getValue() instanceof Numeric)
                neurons.put(attributePair.getKey(), new Neuron(new SigmoidFunction(), 0.1*random.nextDouble()));
            else if(attributePair.getValue() instanceof Nominal) {
                for (Enum anEnum : ((Nominal) attributePair.getValue()).getPossibleValues()) {
                    nominalToKey.put(anEnum, max);
                    neurons.put(max, new Neuron(new SigmoidFunction(), 0.1*random.nextDouble()));
                    max++;
                }
            }
        }
    }

    public void setOutputLayer(NeuralLayer outputLayer) {
        this.outputLayer = outputLayer;
        Random random = new Random();
        for (Neuron neuron : neurons.values()) {
            for (Neuron neuron1 : outputLayer.getNeurons()) {
                neuron.AddOutGoingNeuronConnection(neuron1, 1.0);
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
            if(value instanceof Nominal)
            {
                Integer key = nominalToKey.get(((Nominal) value).getValue());
                neurons.get(key).activate(1.0);
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

    @Override
    public void backPropogate(Map<Neuron, Double> errors, double learningRate) {
        Map<Neuron, Double> layerErrors = new HashMap<>();
        for (Neuron neuron : neurons.values()) {
            double error = neuron.correctAndCalcError(errors, learningRate);
            layerErrors.put(neuron, error);
        }
    }
}
