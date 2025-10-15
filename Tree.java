
import java.util.ArrayList;

public class Tree<T> {
    Node root;

    private class Node{
        ArrayList<Node> children;
        T value;
    }
}
