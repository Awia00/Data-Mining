package Lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The CSVFileReader class is used to load a csv file
 */
public class CSVFileReader {
    /**
     * The read method reads in a csv file as a two dimensional string array.
     * This method is utilizes the string.split method for splitting each line of the data file.
     * String tokenizer bug fix provided by Martin Marcher.
     *
     * @param csvFile        File to load
     * @param seperationChar Character used to seperate entries
     * @param nullValue      What to insert in case of missing values
     * @return Data file content as a 2D string array
     * @throws IOException
     */
    public static List<Answer> readDataFile(String csvFile, String seperationChar, String nullValue, boolean skipHeaderRow) throws IOException {

        List<Answer> lines = new ArrayList<Answer>();
        BufferedReader bufRdr = new BufferedReader(new FileReader(new File(csvFile)));

        // read the header
        String line = bufRdr.readLine();
        while ((line = bufRdr.readLine()) != null) {
            String[] arr = line.substring(1, line.length() - 1).split(seperationChar);

            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("")) {
                    arr[i] = nullValue;
                }
            }
            if (!skipHeaderRow) {
                lines.add(new Answer(arr));
            }
        }

        bufRdr.close();
        return lines;
    }
}
