package Lab6.Neurons;

import Lab6.ActivateFunctions.EmptyFunction;

/**
 * Created by ander on 13-03-2017.
 */
public class OutputNeuron extends Neuron {
    public OutputNeuron() {
        super(new EmptyFunction(), 0.0);
    }

    @Override
    public String toString() {
        output();
        return "OutputNeuron reads: " + output;
    }
}
