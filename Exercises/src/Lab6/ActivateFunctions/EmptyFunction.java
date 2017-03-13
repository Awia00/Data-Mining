package Lab6.ActivateFunctions;

/**
 * Created by ander on 13-03-2017.
 */
public class EmptyFunction implements IActivationFunction {
    @Override
    public double activate(double input) {
        return input;
    }

    @Override
    public double derivative(double input) {
        return input;
    }
}
