package DataTypes;

/**
 * Created by ander on 20-03-2017.
 */
public interface SpaceComparable<T extends SpaceComparable> {
    double distance(T other);
}
