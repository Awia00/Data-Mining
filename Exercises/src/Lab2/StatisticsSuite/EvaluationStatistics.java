package Lab2.StatisticsSuite;

/**
 * Created by aws on 13-02-2017.
 */
public class EvaluationStatistics {

    final String title;
    final int truePositives, falsePositives, trueNegatives, falseNegatives;
    int positives() { return truePositives + falsePositives; }
    int negatives() { return trueNegatives + falseNegatives; }

    public EvaluationStatistics(String title, int truePositives, int falsePositives, int trueNegatives, int falseNegatives) {
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

    public double precision()
    {
        return truePositives/(truePositives+falsePositives);
    }

    public double fScore()
    {
        return (2*precision()*sensitivity())/(precision()+sensitivity());
    }

    @Override
    public String toString() {
        String properties = "" +
                "\n\t" + title +
                "\n\ttruePositives=" + truePositives +
                "\n\tfalsePositives=" + falsePositives +
                "\n\ttrueNegatives=" + trueNegatives +
                "\n\tfalseNegatives=" + falseNegatives;

        String calculations = "" +
                "\n\taccuracy=" + accuracy() +
                "\n\terrorRate=" + errorRate() +
                "\n\tsensitivity=" + sensitivity() +
                "\n\tspecificity=" + specificity() +
                "\n\tprecision=" + precision() +
                "\n\tf-score=" + fScore();

        return "EvaluationStatistics{" + properties + calculations + "\n}";
    }
}
