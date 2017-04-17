package Common.DataStructures.Tree;

import Common.Interfaces.NDimensionalPoint;

/**
 * Created by aws on 15-02-2017.
 */
public class Leaf<T> extends Node {
    public final T label;

    public Leaf(Node parent, T label) {
        super(parent);
        this.label = label;
    }

    @Override
    public T find(NDimensionalPoint element) {
        return label;
    }

    @Override
    public void print() {
        System.out.println(label);
    }

    @Override
    protected void print(String prefix, String key, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + key + label);
    }
}
