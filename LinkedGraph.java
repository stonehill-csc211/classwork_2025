
import java.util.HashSet;

public class LinkedGraph {

    HashSet<Node> nodes;

    private class Node{
        HashSet<Node> neighbors;

        private Node(){
            neighbors = new HashSet<Node>();
        }

        private void addEdge(Node other){
            neighbors.add(other);
            other.addEdge(this);
        }

        private void removeEdge(Node other){
            neighbors.remove(other);
        }
    }

    public LinkedGraph(){
        nodes = new HashSet<>();
    }

    public boolean isNeighbor(Node n1, Node n2){
        return n1.neighbors.contains(n2); // O(1)
    }
}
