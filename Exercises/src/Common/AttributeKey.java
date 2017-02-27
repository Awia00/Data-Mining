package Common;

/**
 * Created by aws on 22-02-2017.
 */
public class AttributeKey<T> {
    private final T key;

    public AttributeKey(T key) {
        this.key = key;
    }

    public T getKey(){
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeKey attributeKey = (AttributeKey) o;

        return key != null ? key.equals(attributeKey.key) : attributeKey.key == null;
    }

    @Override
    public String toString() {
        return key.toString();
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
