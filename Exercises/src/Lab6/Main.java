package Lab6;

import java.util.ArrayList;

import Common.Interfaces.NDimensionalPoint;
import Data.Iris.Iris;
import Data.Iris.IrisDataLoader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//First step load in iris data
		ArrayList<Iris> irisData = IrisDataLoader.LoadAllIrisData();
		System.out.println("Successfully loaded "+irisData.size() + " iris flowers");
		
		//Second step make perceptron or neural network 
	}

}
