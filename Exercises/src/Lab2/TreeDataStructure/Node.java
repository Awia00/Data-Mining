package Lab2.TreeDataStructure;

import Lab2.enums.Class_Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aws on 15-02-2017.
 */
public class Node {
    public final Object splitOn;
    public final Map<Object, Node> children;
    public final Node parent;

    public Node(Node parent, Object splitOn) {
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
        return children.get(label);
    }
}
