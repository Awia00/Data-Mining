package Lab3;

import java.util.List;
import java.util.Set;

/**
 * Created by aws on 27-02-2017.
 */
public class Main {

    /***
     * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
     */
    static final Integer[][] TRANSACTIONS = new Integer[][]{{1, 2, 3, 4, 5}, {1, 3, 5}, {2, 3, 5}, {1, 5}, {1, 3, 4}, {2, 3, 5}, {2, 3, 5},
            {3, 4, 5}, {4, 5}, {2}, {2, 3}, {2, 3, 4}, {3, 4, 5}};

    static final Integer[][] BOOK_TRANSACTIONS = new Integer[][]{{1, 2, 5}, {2, 4}, {2, 3}, {1, 2, 4}, {1, 3}, {2, 3}, {1, 3},
            {1, 2, 3, 5}, {1, 2, 3}};

    public static void main(String[] args) {
        Apriori<Integer> integerApriori = new Apriori<>();
        List<ItemSet<Integer>> transactionsFrequentSets = integerApriori.runApriori(TRANSACTIONS, 5);
        List<ItemSet<Integer>> booksFrequentSets = integerApriori.runApriori(BOOK_TRANSACTIONS, 4);

        System.out.println("Transactions: " + transactionsFrequentSets);
        System.out.println("Books: " + booksFrequentSets);
    }
}
