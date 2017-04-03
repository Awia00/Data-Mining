package Lab6.Layers;

import Common.DataTypes.Nominal;
import Lab6.ActivateFunctions.SigmoidFunction;
import Lab6.Neurons.Neuron;

import java.util.*;

/**
 * Created by aws on 13-03-2017.
 */
public class OutputLayer implements NeuralLayer{
    private NeuralLayer previousLayer;

    Map<Enum, Neuron> neurons;
    Nominal classification;
    public OutputLayer(Nominal classification) {
        neurons = new HashMap<>();
        this.classification = classification;
        for (Enum anEnum : classification.getPossibleValues()) {
            neurons.put(anEnum, new Neuron(new SigmoidFunction(), 0.0));
        }
    }

    @Override
    public Collection<? extends Neuron> getNeurons() {
        return neurons.values();
    }

    @Override
    public void propogate() {
        for (Neuron outputNeuron : neurons.values()) {
            outputNeuron.output();
        }
    }

    @Override
    public void backPropogate(Map<Neuron, Double> errors, double learningRate) {

    }

    public void backPropogate(Nominal classification, double learningRate) {
        Map<Neuron, Double> layerErrors = new HashMap<>();
        for (Map.Entry<Enum, Neuron> enumOutputNeuronEntry : neurons.entrySet()) {
            Neuron neuron = enumOutputNeuronEntry.getValue();
            double correctValue = classification.getValue().equals(enumOutputNeuronEntry.getKey()) ? 1.0 : 0.0;
            double value = neuron.lastOutput();
            double error = value*(1-value)*(correctValue-value);
            layerErrors.put(neuron, error);
        }
        previousLayer.backPropogate(layerErrors, learningRate);
    }

    public Map<Enum, Double> getResult(){
        Map<Enum, Double> result = new HashMap<>();
        for (Enum anEnum : classification.getPossibleValues()) {
            result.put(anEnum, neurons.get(anEnum).lastOutput());
        }
        return result;
    }

    public void setPreviousLayer(NeuralLayer previousLayer) {
        this.previousLayer = previousLayer;
    }
}
