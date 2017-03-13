package Lab1;

import Data.Answer.Answer;
import Data.CSVFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aws on 06-03-2017.
 */
public class Main {
    public static void main(String args[]) {
        try {
            List<Answer> answers = new ArrayList<>();
            String[][] data = CSVFileReader.readDataFile("Resources/Data Mining - Spring 2017.csv", "\",\"", "-", false);
            for (String[] datum: data) {
                answers.add(new Answer(datum));
            }
            for (Answer answer : answers) {
                System.out.println(answer + "\n");
            }
            System.out.println("Number of tuples loaded: " + answers.size());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
