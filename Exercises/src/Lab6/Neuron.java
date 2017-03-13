package Lab6;

/**
 * Created by aws on 13-03-2017.
 */
public abstract class Neuron {

    protected IActivationFunction activationFunction;
    double value = 0.0;

    public Neuron(IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }


    public void Activate(double input){
        value+=activationFunction.activate(input);
    }

    public double Read()
    {
        return value;
    }
}
