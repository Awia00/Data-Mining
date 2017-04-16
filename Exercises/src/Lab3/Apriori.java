package Lab3;

import java.util.*;


public class Apriori {

    public static List<ItemSet> apriori(int[][] transactions, int supportThreshold) {
        int k;
        List<ItemSet> itemSets = new ArrayList<>();
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1(transactions, supportThreshold);
        for (k = 1; frequentItemSets.size() > 0; k++) {
            System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");
            frequentItemSets = generateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

            System.out.println(" found " + frequentItemSets.size());
            itemSets.addAll(frequentItemSets.keySet());
        }
        return itemSets;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSets(int supportThreshold, int[][] transactions,
                                                                        Hashtable<ItemSet, Integer> lowerLevelItemSets) {
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
        if (first.getLast() < second.getLast()) {
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
                table.put(transactions[i][j], table.getOrDefault(transactions[i][j], 0) + 1);
            }
        }

        Hashtable<ItemSet, Integer> result = new Hashtable<>();

        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            if (entry.getValue() >= supportThreshold) {
                result.put(new ItemSet(new int[]{entry.getKey()}), entry.getValue());
            }
        }
        return result;
    }

    private static int countSupport(int[] itemSet, int[][] transactions) {
        // Assumes that items in ItemSets and transactions are both unique
        int result = 0;
        for (int[] transaction : transactions) {
            int i = 0;
            for (int item : transaction) {
                for (int freqItem : itemSet) {
                    if(Objects.equals(item, freqItem)){
                        i++;
                        break;
                    }
                }
                if(i == itemSet.length){
                    result++;
                    break;
                }
            }
        }
        return result;
    }

}
