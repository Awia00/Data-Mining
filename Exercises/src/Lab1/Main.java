package Lab1;

import java.io.IOException;
import java.util.List;

/**
 * Created by aws on 06-03-2017.
 */
public class Main {
    public static void main(String args[]) {
        try {
            List<Answer> data = CSVFileReader.readDataFile("Resources/Data Mining - Spring 2017.csv", "\",\"", "-", false);
            for (Answer answer : data) {
                System.out.println(answer + "\n");
            }
            System.out.println("Number of tuples loaded: " + data.size());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
