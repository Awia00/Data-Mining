package Lab3;

import java.util.List;

import static Lab3.Apriori.apriori;

/**
 * Created by aws on 27-02-2017.
 */
public class Main {

    /***
     * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
     */
    static final int[][] TRANSACTIONS = new int[][]{{1, 2, 3, 4, 5}, {1, 3, 5}, {2, 3, 5}, {1, 5}, {1, 3, 4}, {2, 3, 5}, {2, 3, 5},
            {3, 4, 5}, {4, 5}, {2}, {2, 3}, {2, 3, 4}, {3, 4, 5}};

    static final int[][] BOOK_TRANSACTIONS = new int[][]{{1, 2, 5}, {2, 4}, {2, 3}, {1, 2, 4}, {1, 3}, {2, 3}, {1, 3},
            {1, 2, 3, 5}, {1, 2, 3}};

    public static void main(String[] args) {
        List<ItemSet> transactionsFrequentSets = apriori(TRANSACTIONS, 5);
        List<ItemSet> booksFrequentSets = apriori(BOOK_TRANSACTIONS, 4);

        System.out.println("Transactions: " + transactionsFrequentSets);
        System.out.println("Books: " + booksFrequentSets);
    }
}
