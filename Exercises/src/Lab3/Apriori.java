package Lab3;

import java.util.*;


public class Apriori {
    /***
     * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
     */
    static final int[][] TRANSACTIONS = new int[][]{{1, 2, 3, 4, 5}, {1, 3, 5}, {2, 3, 5}, {1, 5}, {1, 3, 4}, {2, 3, 5}, {2, 3, 5},
            {3, 4, 5}, {4, 5}, {2}, {2, 3}, {2, 3, 4}, {3, 4, 5}};

    static final int[][] BOOK_TRANSACTIONS = new int[][]{{1, 2, 5}, {2, 4}, {2, 3}, {1, 2, 4}, {1, 3}, {2, 3}, {1, 3},
            {1, 2, 3, 5}, {1, 2, 3}};

    public static void main(String[] args) {
        // TODO: Select a reasonable support threshold via trial-and-error. Can either be percentage or absolute value.
        final int supportThreshold = 42;
        apriori(TRANSACTIONS, supportThreshold);
    }

    public static List<ItemSet> apriori(int[][] transactions, int supportThreshold) {
        int k;
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1(transactions, supportThreshold);
        for (k = 1; frequentItemSets.size() > 0; k++) {
            System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");
            frequentItemSets = generateFrequentItemSets(supportThreshold, transactions, frequentItemSets);
            // TODO: add to list

            System.out.println(" found " + frequentItemSets.size());
        }
        // TODO: create association rules from the frequent itemsets

        // TODO: return something useful
        return null;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSets(int supportThreshold, int[][] transactions,
                                                                        Hashtable<ItemSet, Integer> lowerLevelItemSets) {
        // TODO: first generate candidate itemsets from the lower level itemsets
        /*
         * TODO: now check the support for all candidates and add only those
         * that have enough support to the set
         */
        Set<ItemSet> candidates = new HashSet<>();
        Hashtable<ItemSet, Integer> result = new Hashtable<>();
        for (Map.Entry<ItemSet, Integer> entry1 : lowerLevelItemSets.entrySet()) {
            ItemSet set1 = entry1.getKey();
            for (Map.Entry<ItemSet, Integer> entry2 : lowerLevelItemSets.entrySet()) {
                ItemSet set2 = entry2.getKey();
                boolean isSame = true;
                for (int i = 0; i < set1.length - 1; i++) {
                    if (set1.get(i) != set2.get(i)) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame && set1.getLast() != set2.getLast()) {
                    candidates.add(joinSets(set1, set2));
                }
            }
        }
        for (ItemSet candidate : candidates) {
            int support = countSupport(candidate.set, transactions);
            if (support >= supportThreshold)
                result.put(candidate, support);
        }

        return result;
    }

    // we know the sets are already sorted
    private static ItemSet joinSets(ItemSet first, ItemSet second) {
        if (first.length != second.length) throw new RuntimeException("Join sets not of equal length");
        if (first.getLast() == second.getLast()) throw new RuntimeException("Join sets last element is equal");

        int[] set = new int[first.length + 1];
        for (int i = 0; i < first.length - 1; i++) { // assumes that every element besides the last one can be the same.
            set[i] = first.get(i);
        }
        if (first.set[first.length] < second.set[second.length]) {
            set[set.length - 2] = first.getLast();
            set[set.length - 1] = second.getLast();
        } else {
            set[set.length - 1] = second.getLast();
            set[set.length - 2] = first.getLast();
        }
        return new ItemSet(set);
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1(int[][] transactions, int supportThreshold) {
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; j < transactions[i].length; j++) {
                if (!table.containsKey(transactions[i][j]))
                    table.put(transactions[i][j], 1);
                else
                    table.put(transactions[i][j], table.get(transactions[i][j]) + 1);
            }
        }
        Hashtable<ItemSet, Integer> result = new Hashtable<>();
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            if (entry.getValue() >= supportThreshold)
                result.put(new ItemSet(new int[]{entry.getKey()}), entry.getValue());

        }
        return result;
    }

    private static int countSupport(int[] itemSet, int[][] transactions) {
        // Assumes that items in ItemSets and transactions are both unique

        // TODO: return something useful
        int result = 0;
        for (int i = 0; i < transactions.length; i++) {
            int lastIndex = 0;
            for (int j = 0; j < transactions[i].length; j++) {
                for (int k = lastIndex; k < itemSet.length; k++) {
                    if (itemSet[k] == transactions[i][j]) {
                        lastIndex = k;
                        break;
                    }
                }
            }
            if (lastIndex == itemSet.length)
                result++;
        }
        return result;
    }

}
