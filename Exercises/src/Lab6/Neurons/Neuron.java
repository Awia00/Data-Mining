package Lab6.Neurons;

import Lab6.ActivateFunctions.IActivationFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public double lastOutput(){
        return output;
    }

    public void output()
    {
        output = activationFunction.activate(value+bias);
        for (Map.Entry<Neuron, Double> neuronAndWeight : neuronConnections.entrySet()) {
            neuronAndWeight.getKey().activate(neuronAndWeight.getValue()*output);
        }
        value = 0.0;
    }

    public double correctAndCalcError(Map<Neuron, Double> corrections, double learningRate) {

        neuronConnections = corrections;
        Double sumError = 0.0;
        Double derivative = activationFunction.derivative(output);
        for (Map.Entry<Neuron, Double> correctionEntry : corrections.entrySet()) {
            Double currentWeight = neuronConnections.get(correctionEntry.getKey());
            sumError += correctionEntry.getValue()*currentWeight;
        }
        double error = derivative*sumError;
        for (Map.Entry<Neuron, Double> neuronDoubleEntry : corrections.entrySet()) {
            Neuron outputNeuron = neuronDoubleEntry.getKey();
            double deltaWeight = learningRate*outputNeuron.lastOutput()*error;
            neuronConnections.put(outputNeuron, neuronConnections.get(outputNeuron)+deltaWeight);
        }

        double deltaBias = learningRate*error;
        bias = bias + deltaBias;

        return error;
    }

    @Override
    public String toString() {
        return "Neuron" +
                "output=" + output +
                ", bias=" + bias +
                "";
    }
}
