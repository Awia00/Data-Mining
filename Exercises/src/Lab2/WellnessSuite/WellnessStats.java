package Lab2.WellnessSuite;

/**
 * Created by aws on 13-02-2017.
 */
public class WellnessStats {

    final String title;
    final int truePositives, falsePositives, trueNegatives, falseNegatives;
    int positives() { return truePositives + falsePositives; }
    int negatives() { return falsePositives + falseNegatives; }

    public WellnessStats(String title, int truePositives, int falsePositives, int trueNegatives, int falseNegatives) {
        this.title = title;
        this.truePositives = truePositives;
        this.falsePositives = falsePositives;
        this.trueNegatives = trueNegatives;
        this.falseNegatives = falseNegatives;
    }


    public double accuracy() // recognition rate
    {
        return (truePositives+trueNegatives)/(positives()+negatives());
    }

    public double errorRate() // misclassification rate
    {
        return (falsePositives+falseNegatives)/(positives()+negatives());
    }

    public double sensitivity() // true positive rate, recall
    {
        return truePositives/positives();
    }

    public double specificity() // true negative rate
    {
        return trueNegatives/negatives();
    }

    @Override
    public String toString() {
        return "WellnessStats{" +
                title + ": \n\t" +
                "truePositives=" + truePositives +
                ", falsePositives=" + falsePositives +
                ", trueNegatives=" + trueNegatives +
                ", falseNegatives=" + falseNegatives +
                '}';
    }
}
