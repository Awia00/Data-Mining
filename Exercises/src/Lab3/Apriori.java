package Lab3;

import java.util.*;


public class Apriori<T extends Comparable<T>> {

    public List<ItemSet<T>> apriori(T[][] transactions, int supportThreshold) {
        int k;
        List<ItemSet<T>> itemSets = new ArrayList<>();
        Hashtable<ItemSet<T>, Integer> frequentItemSets = generateFrequentItemSetsLevel1(transactions, supportThreshold);
        for (k = 1; frequentItemSets.size() > 0; k++) {
            //System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");
            frequentItemSets = generateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

            //System.out.println(" found " + frequentItemSets.size());
            itemSets.addAll(frequentItemSets.keySet());
        }
        return itemSets;
    }

    private Hashtable<ItemSet<T>, Integer> generateFrequentItemSets(int supportThreshold, T[][] transactions,
                                                                        Hashtable<ItemSet<T>, Integer> lowerLevelItemSets) {
        Set<ItemSet<T>> candidates = new HashSet<>();
        Hashtable<ItemSet<T>, Integer> result = new Hashtable<>();
        for (Map.Entry<ItemSet<T>, Integer> entry1 : lowerLevelItemSets.entrySet()) {
            ItemSet<T> set1 = entry1.getKey();
            for (Map.Entry<ItemSet<T>, Integer> entry2 : lowerLevelItemSets.entrySet()) {
                ItemSet<T> set2 = entry2.getKey();
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
        for (ItemSet<T> candidate : candidates) {
            int support = countSupport(candidate.set, transactions);
            if (support >= supportThreshold)
                result.put(candidate, support);
        }

        return result;
    }

    // we know the sets are already sorted
    private ItemSet<T> joinSets(ItemSet<T> first, ItemSet<T> second) {
        if (first.length != second.length) throw new RuntimeException("Join sets not of equal length");
        if (first.getLast() == second.getLast()) throw new RuntimeException("Join sets last element is equal");

        T[] set = (T[]) new Comparable[first.length + 1];
        for (int i = 0; i < first.length - 1; i++) { // assumes that every element besides the last one can be the same.
            set[i] = first.get(i);
        }
        if (first.getLast().compareTo(second.getLast()) < 0 ) {
            set[set.length - 2] = first.getLast();
            set[set.length - 1] = second.getLast();
        } else {
            set[set.length - 1] = second.getLast();
            set[set.length - 2] = first.getLast();
        }
        return new ItemSet(set);
    }

    private Hashtable<ItemSet<T>, Integer> generateFrequentItemSetsLevel1(T[][] transactions, int supportThreshold) {
        Hashtable<T, Integer> table = new Hashtable<>();

        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; j < transactions[i].length; j++) {
                table.put(transactions[i][j], table.getOrDefault(transactions[i][j], 0) + 1);
            }
        }

        Hashtable<ItemSet<T>, Integer> result = new Hashtable<>();

        for (Map.Entry<T, Integer> entry : table.entrySet()) {
            if (entry.getValue() >= supportThreshold) {
                result.put(new ItemSet<>((T[])new Comparable[]{entry.getKey()}), entry.getValue());
            }
        }
        return result;
    }

    private int countSupport(T[] itemSet, T[][] transactions) {
        // Assumes that items in ItemSets and transactions are both unique
        int result = 0;
        for (T[] transaction : transactions) {
            int i = 0;
            for (T item : transaction) {
                for (T freqItem : itemSet) {
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
