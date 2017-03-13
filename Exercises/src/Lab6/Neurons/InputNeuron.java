package Lab6.Neurons;

import Lab6.ActivateFunctions.EmptyFunction;

/**
 * Created by ander on 13-03-2017.
 */
public class InputNeuron extends Neuron {
    public InputNeuron() {
        super(new EmptyFunction(), 0.0);
    }
    @Override
    public void activate(double input) {
        value = input;
    }
}
