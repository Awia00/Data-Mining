package Common.DataTypes;

/**
 * Created by aws on 16-04-2017.
 */
public class ComparerWrapper<T> implements Comparable<ComparerWrapper> {
    private static int globalId = 0;
    public final Integer Id;
    public final T element;

    public ComparerWrapper(T element) {
        Id = globalId++;
        this.element = element;
    }

    @Override
    public int compareTo(ComparerWrapper o) {
        if(element.equals(o.element)) return 0;
        return Id.compareTo(o.Id);
    }
}
