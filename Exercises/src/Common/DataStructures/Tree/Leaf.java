package Common.DataStructures.Tree;

import Common.TwoWayClassification;
import Common.Interfaces.WithAttributes;

/**
 * Created by aws on 15-02-2017.
 */
public class Leaf extends Node {
    public final TwoWayClassification label;

    public Leaf(Node parent, TwoWayClassification label) {
        super(parent);
        this.label = label;
    }

    @Override
    public TwoWayClassification find(WithAttributes element) {
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
