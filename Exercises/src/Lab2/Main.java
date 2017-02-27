package Lab2;

import Common.Interfaces.Classifier;
import Lab2.Classification.ID3DecisionTreeClassifier;
import Lab2.Classification.KNearestNeighboursClassifier;
import Common.Statistics.CanEvaluateClassifier;
import Common.Statistics.EvaluationStatistics;
import Common.Statistics.EvaluationSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main class to run program from.
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // First step - Load data and convert to Mushroom objects.
        ArrayList<Mushroom> mushrooms = DataManager.LoadData();
        System.out.println("DataManager loaded " + mushrooms.size() + " mushrooms");
        int k = 5;

        Collections.shuffle(mushrooms);
        List<Mushroom> trainingMushrooms = mushrooms.subList(0, (int) (0.5 * mushrooms.size()));
        List<Mushroom> testMushrooms = mushrooms.subList((int) (0.5 * mushrooms.size()), mushrooms.size());
        System.out.println("Training size: " + trainingMushrooms.size() + " test size: " + testMushrooms.size());

        CanEvaluateClassifier wellnessSuite = new EvaluationSuite();

        Classifier classifierKNN = new KNearestNeighboursClassifier(k);
        Classifier classifierID3 = new ID3DecisionTreeClassifier();

        classifierKNN.trainWithSet(trainingMushrooms);
        classifierID3.trainWithSet(trainingMushrooms);

        EvaluationStatistics statsKNN = classifierKNN.testWithSet(new ArrayList<>(trainingMushrooms));
        EvaluationStatistics statsID3 = classifierID3.testWithSet(new ArrayList<>(trainingMushrooms));

        System.out.println(statsKNN);
        System.out.println(statsID3);


    }

}
