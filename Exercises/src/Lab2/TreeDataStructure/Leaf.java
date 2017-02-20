package Lab2.TreeDataStructure;

import Lab2.enums.Class_Label;

/**
 * Created by aws on 15-02-2017.
 */
public class Leaf extends Node {
    public final Class_Label label;

    public Leaf(Node parent, Class_Label label) {
        super(parent, Class_Label.class);
        this.label = label;
    }

    @Override
    public void print() {
        System.out.println(label);
    }

    @Override
    protected void print(String prefix, String key, boolean isTail) {
        System.out.println(prefix +  (isTail ? "└── " : "├── ") + key +  label);
    }
}
