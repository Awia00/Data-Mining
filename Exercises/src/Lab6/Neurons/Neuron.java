package Lab6.Neurons;

import Lab6.ActivateFunctions.IActivationFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 13-03-2017.
 */
public class Neuron {

    private IActivationFunction activationFunction;
    private Map<Neuron, Double> neuronConnections = new HashMap<>();
    protected double value = 0.0, output = 0.0;
    private double bias;

    public Neuron(IActivationFunction activationFunction, double bias) {
        this.bias = bias;
        this.activationFunction = activationFunction;
    }

    public void AddOutGoingNeuronConnection(Neuron neuron, double weight)
    {
        neuronConnections.put(neuron, weight);
    }

    // including the weight.
    public void activate(double input){
        value+=input;
    }

    public void output()
    {
        output = activationFunction.activate(value+bias);
        for (Map.Entry<Neuron, Double> neuronAndWeight : neuronConnections.entrySet()) {
            neuronAndWeight.getKey().activate(neuronAndWeight.getValue()*output);
        }
        value = 0.0;
    }
}
