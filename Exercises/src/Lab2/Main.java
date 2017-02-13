package Lab2;

import Lab2.Classification.IMushroomClassifier;
import Lab2.Classification.KNearestNeighboursMushrooms;
import Lab2.DataManager;

import java.util.ArrayList;
import java.util.EnumSet;
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
        System.out.println("DataManager loaded "+mushrooms.size() + " mushrooms");


		List<Mushroom> trainingMushrooms =  mushrooms.subList(0, (int)(0.66*mushrooms.size()));
		List<Mushroom> testMushrooms =  mushrooms.subList((int)(0.66*mushrooms.size()), mushrooms.size());


		IMushroomClassifier classifier = new KNearestNeighboursMushrooms(3, trainingMushrooms);
		int wrongClass = 0, testSize = testMushrooms.size();
		for(Mushroom mushroom: testMushrooms)
		{
			if(classifier.classify(mushroom) != mushroom.m_Class)
            {
                //System.out.println("Wrong classification");
                wrongClass++;
            }
		}
        System.out.println("Wrong classification: " + wrongClass + " out of " + testSize);


	}

}
