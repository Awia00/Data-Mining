package Lab6;

/**
 * Created by aws on 13-03-2017.
 */
public class SigmoidFunction implements IActivationFunction {
    double d;
    public SigmoidFunction(){
        this(1.0);
    }
    public SigmoidFunction(double d)
    {
        this.d = d;
    }

    @Override
    public double activate(double input) {
        return 1/(1+Math.exp(-d*input));
    }

    @Override
    public double derivative(double input) {
        double functionValue = activate(input);
        return functionValue *(1-functionValue);
    }
}
