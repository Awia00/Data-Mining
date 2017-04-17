package Lab2;

import Common.DataTypes.Binary;
import Common.Interfaces.Classifiable;
import Common.Interfaces.Classifier;
import Common.Statistics.ConfusionMatrixStatistics;
import Common.Statistics.ConfusionMatrixSuite;
import Data.Mushroom.Mushroom;
import Data.Mushroom.MushroomDataLoader;
import Lab2.Classification.ID3DecisionTreeClassifier;
import Lab2.Classification.KNearestNeighboursClassifier;

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
        List<Mushroom> mushrooms = MushroomDataLoader.LoadData();
        System.out.println("MushroomDataLoader loaded " + mushrooms.size() + " mushrooms");
        int k = 5;

        Collections.shuffle(mushrooms);
        List<Mushroom> trainingMushrooms = mushrooms.subList(0, (int) (0.5 * mushrooms.size()));
        List<Mushroom> testMushrooms = mushrooms.subList((int) (0.5 * mushrooms.size()), mushrooms.size());
        System.out.println("Training size: " + trainingMushrooms.size() + " test size: " + testMushrooms.size());

        Classifier classifierKNN = new KNearestNeighboursClassifier(k);
        Classifier classifierID3 = new ID3DecisionTreeClassifier();

        classifierKNN.trainWithSet(trainingMushrooms);
        classifierID3.trainWithSet(trainingMushrooms);

        ConfusionMatrixStatistics statsKNN = new ConfusionMatrixSuite().testClassifier(classifierKNN, new ArrayList<Classifiable<Binary>>(trainingMushrooms));
        ConfusionMatrixStatistics statsID3 =  new ConfusionMatrixSuite().testClassifier(classifierID3, new ArrayList<Classifiable<Binary>>(trainingMushrooms));

        System.out.println(statsKNN);
        System.out.println(statsID3);


    }

}
