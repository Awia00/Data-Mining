package Lab6;

/**
 * Created by aws on 13-03-2017.
 */
public class StepFunction implements IActivationFunction {

    double threshold;

    public StepFunction()
    {
        this(0.5);
    }
    public StepFunction(double threshold)
    {
        this.threshold = threshold;
    }
    @Override
    public double activate(double input) {
        return input>threshold ? 1:0;
    }

    @Override
    public double derivative(double input) {
        return 0;
    }
}
