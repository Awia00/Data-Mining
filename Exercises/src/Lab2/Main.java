package Lab2;

import Lab2.Classification.IMushroomClassifier;
import Lab2.Classification.KNearestNeighboursMushrooms;
import Lab2.StatisticsSuite.EvaluationStatistics;
import Lab2.StatisticsSuite.IMushroomEvaluationSuite;
import Lab2.StatisticsSuite.MushroomEvaluationSuite;

import java.util.ArrayList;
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


		List<Mushroom> trainingMushrooms =  mushrooms.subList(0, (int)(0.66*mushrooms.size()));
		List<Mushroom> testMushrooms =  mushrooms.subList((int)(0.66*mushrooms.size()), mushrooms.size());

		IMushroomClassifier classifier = new KNearestNeighboursMushrooms(k, trainingMushrooms);
        IMushroomEvaluationSuite wellnessSuite = new MushroomEvaluationSuite();
        EvaluationStatistics stats = wellnessSuite.testClassifier(classifier, testMushrooms);
        System.out.println(stats);


	}

}
