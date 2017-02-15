package Lab2;

import Lab2.Classification.ID3DecisionTreeMushroomClassifier;
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

		IMushroomEvaluationSuite wellnessSuite = new MushroomEvaluationSuite();

		IMushroomClassifier classifierKNN = new KNearestNeighboursMushrooms(k);
		IMushroomClassifier classifierID3 = new ID3DecisionTreeMushroomClassifier();
		classifierKNN.trainWithSet(new ArrayList<>(trainingMushrooms));
		classifierID3.trainWithSet(new ArrayList<>(trainingMushrooms));

		EvaluationStatistics statsKNN = wellnessSuite.testClassifier(classifierKNN, new ArrayList<>(testMushrooms));
		EvaluationStatistics statsID3 = wellnessSuite.testClassifier(classifierID3, new ArrayList<>(testMushrooms));

        System.out.println(statsKNN);
		System.out.println(statsID3);


	}

}
