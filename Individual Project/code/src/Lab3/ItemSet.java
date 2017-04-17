package Lab3;

import java.util.*;

/***
 * The ItemSet class is used to store information concerning a single transaction.
 * Should not need any changes.
 *
 */
public class ItemSet<T extends Comparable<T>> implements Comparable<ItemSet<T>>, Iterable<T> {

    /***
     * The PRIMES array is internally in the ItemSet-class' hashCode method
     */
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 23, 27, 31, 37};
    final T[] set;
    final int length;

    /***
     * Creates a new instance of the ItemSet class.
     * @param set Transaction content
     */
    public ItemSet(T[] set) {
        this.set = set;
        this.length = set.length;
    }

    public T get(int index) {
        return set[index];
    }

    public void set(int index, T value) {
        set[index] = value;
    }

    public T getLast() {
        return set[length - 1];
    }

    @Override
    /**
     * hashCode functioned used internally in Hashtable
     */
    public int hashCode() {
        int code = 0;
        for (int i = 0; i < set.length; i++) {
            code += set[i].hashCode() * PRIMES[i];
        }
        return code;
    }


    /**
     * Used to determine whether two ItemSet objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemSet)) {
            return false;
        }
        ItemSet other = (ItemSet) o;
        if (other.set.length != this.set.length) {
            return false;
        }
        for (int i = 0; i < set.length; i++) {
            if (!Objects.equals(set[i], other.set[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(ItemSet<T> itemSet) {
        int lengthComparison = set.length - itemSet.set.length;
        if (lengthComparison != 0) return lengthComparison;

        for (int i = 0; i < set.length; i++) {
            if (set[i].compareTo(itemSet.set[i]) < 0) return -1;
            if (set[i].compareTo(itemSet.set[i]) > 0) return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(set);
    }

    @Override
    public Iterator<T> iterator() {
        List<T> iteratorList = Arrays.asList(set);
        return iteratorList.iterator();
    }

    public Iterable<ItemSet<T>> nonEmptySubsets() { return new NonEmptySubsetIterable<>(set); }

    private static class NonEmptySubsetIterable<T extends Comparable<T>> implements Iterable<ItemSet<T>> {
        private final T[] set;

        public NonEmptySubsetIterable(T[] set) {
            this.set = set;
        }

        @Override
        public Iterator<ItemSet<T>> iterator() {
            return new NonEmptySubsetIterator<>(set);
        }
    }

    private static class NonEmptySubsetIterator<T extends Comparable<T>> implements Iterator<ItemSet<T>> {
        private final Iterator<Set<T>> inner;

        public NonEmptySubsetIterator(T[] set) {
            Set<Set<T>> subsets = new HashSet<>();

            for (int subsetSize = 1; subsetSize < set.length; subsetSize++) {
                for (int i = 0; i <= set.length - subsetSize; i++) {
                    Set<T> subset = new HashSet<>();

                    for (int j = 0; j < subsetSize; j++) {
                        subset.add(set[i + j]);
                    }

                    if (!subset.isEmpty()) {
                        subsets.add(subset);
                    }
                }
            }

            inner = subsets.iterator();
        }

        @Override
        public boolean hasNext() {
            return inner.hasNext();
        }

        @Override
        @SuppressWarnings("unchecked")
        public ItemSet<T> next() {
            Set<T> subset = inner.next();
            return new ItemSet<>(subset.toArray((T[]) new Comparable[subset.size()]));
        }
    }

}

