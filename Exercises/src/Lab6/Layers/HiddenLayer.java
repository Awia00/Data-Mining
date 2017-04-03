package Lab6.Layers;

import Lab6.ActivateFunctions.SigmoidFunction;
import Lab6.Neurons.Neuron;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by aws on 13-03-2017.
 */
public class HiddenLayer implements NeuralLayer {
    private NeuralLayer outputLayer;
    private NeuralLayer previousLayer;
    protected Neuron[] neurons;

    public HiddenLayer(int topology) {
        this.neurons = new Neuron[topology];
        Random random = new Random();
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new Neuron(new SigmoidFunction(), 0.1*random.nextDouble());
        }
    }

    public void setOutputLayer(NeuralLayer outputLayer) {
        this.outputLayer = outputLayer;
        Random random = new Random();
        for (int i = 0; i < neurons.length; i++) {
            for (Neuron neuron : outputLayer.getNeurons()) {
                neurons[i].AddOutGoingNeuronConnection(neuron, 0.1*random.nextDouble());
            }
        }
    }

    @Override
    public Collection<? extends Neuron> getNeurons() {
        return Arrays.stream(neurons).collect(Collectors.toList());
    }

    @Override
    public void propogate() {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].output();
        }
        outputLayer.propogate();
    }

    @Override
    public void backPropogate(Map<Neuron, Double> errors, double learningRate) {
        Map<Neuron, Double> layerErrors = new HashMap<>();
        for (Neuron neuron : neurons) {
            double error = neuron.correctAndCalcError(errors, learningRate);
            layerErrors.put(neuron, error);
        }
        previousLayer.backPropogate(layerErrors, learningRate);
    }

    public void setPreviousLayer(NeuralLayer previousLayer) {
        this.previousLayer = previousLayer;
    }
}
