package Lab2.TreeDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aws on 15-02-2017.
 */
public class Node {
    public final static Object defaultObject = new Object();
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
        Node child = children.get(label);
        if(child == null)
            return children.get(defaultObject);
        else
            return child;
    }
}
