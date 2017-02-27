package Common;

/**
 * Created by aws on 22-02-2017.
 */
public class Attribute {
    public final Class key;

    public Attribute(Class key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        return key != null ? key.equals(attribute.key) : attribute.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Attribute: " + key.toString().substring(5);
    }
}
