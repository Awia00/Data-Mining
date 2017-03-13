package Lab6.ActivateFunctions;

/**
 * Created by aws on 13-03-2017.
 */
public class SigmoidFunction implements IActivationFunction {
    private double d;
    public SigmoidFunction(){
        this(1.0);
    }
    private SigmoidFunction(double d)
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
