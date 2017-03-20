package Common.Statistics;

import Common.DataTypes.Nominal;

/**
 * Created by ander on 20-03-2017.
 */
public class PurityStatistics<T extends Nominal> {
    private double purity;
    private T classification;

    public PurityStatistics(double purity, T classification) {
        this.purity = purity;
        this.classification = classification;
    }

    public double getPurity() {
        return purity;
    }

    public T getClassification() {
        return classification;
    }

    @Override
    public String toString() {
        return "PurityStatistics{" +
                "purity=" + purity +
                ", classification=" + classification +
                '}';
    }
}
