package Lab6;

import Common.Interfaces.NDimensionalPoint;
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
		List<? extends NDimensionalPoint> irisData = IrisDataLoader.LoadAllIrisData();
		irisData = (ArrayList<NDimensionalPoint>) new Normalizer().NormilizeData(irisData, new IrisBuilder());
		System.out.println("Successfully loaded "+irisData.size() + " iris flowers");
		
		//Second step make perceptron or neural network
		NeuralNetwork neuralNetwork = new NeuralNetwork(irisData.get(0), 1.0);
	}

}
