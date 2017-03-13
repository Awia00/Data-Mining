package Lab6.Layers;

import Lab6.ActivateFunctions.SigmoidFunction;
import Lab6.Neurons.Neuron;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by aws on 13-03-2017.
 */
public class HiddenLayer implements NeuralLayer {
    private NeuralLayer outputLayer;
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
}
