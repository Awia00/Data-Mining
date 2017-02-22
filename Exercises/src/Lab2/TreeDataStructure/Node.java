package Lab2.TreeDataStructure;

import Lab2.Interfaces.Attribute;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 15-02-2017.
 */
public class Node {
    public final static Object defaultObject = new Object();
    public final Attribute splitOn;
    public final Map<Object, Node> children;
    public final Node parent;

    public Node(Node parent) {
        this.parent = parent;
        this.splitOn = null;
        children = new HashMap<>();
    }
    public Node(Node parent, Attribute splitOn) {
        this.parent = parent;
        this.splitOn = splitOn;
        children = new HashMap<>();
    }

    public void addChild(Object label, Node node)
    {
        children.put(label, node);
    }

    public Node getChild(Object label)
    {
        Node child = children.get(label);
        if(child == null)
            return children.get(defaultObject);
        else
            return child;
    }

    public void print() {
        print("","", true);
    }

    protected void print(String prefix, String key, boolean isTail) {
        System.out.println(prefix +  (isTail ? "└── " : "├── ") + key +  splitOn);
        String whiteSpace = "    ";
        for (int i = 0; i < key.length()-1; i++) {
            whiteSpace += " ";
        }
        for (Map.Entry<Object, Node> child :
                children.entrySet()) {
            if(child.getKey()!=defaultObject)
            {
                child.getValue().print(prefix + (isTail ? " " + whiteSpace: "│"  + whiteSpace),  child.getKey() + " - ", false);
            }
        }
        children.get(defaultObject)
                .print(prefix + (isTail ? " " + whiteSpace: "│"  + whiteSpace), "mean -> ", true);
    }
}
