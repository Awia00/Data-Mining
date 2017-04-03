package Lab6;

import Common.DataTypes.Nominal;
import Common.Interfaces.ClassifiablePoint;
import Common.Preprocessing.Normalizer;
import Data.Iris.IrisBuilder;
import Data.Iris.IrisDataLoader;

import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//First step load in iris data
		List<? extends ClassifiablePoint<Nominal>> irisData = IrisDataLoader.LoadAllIrisData();
		irisData = (ArrayList<ClassifiablePoint<Nominal>>) new Normalizer().NormilizeData(irisData, new IrisBuilder());
		System.out.println("Successfully loaded "+irisData.size() + " iris flowers");
		
		//Second step make perceptron or neural network
		NeuralNetwork neuralNetwork = new NeuralNetwork(1, 1.0, 1);
		neuralNetwork.trainWithSet(irisData);

		for (ClassifiablePoint<Nominal> irisDatum : irisData) {
			neuralNetwork.classify(irisDatum);
		}
	}

}
