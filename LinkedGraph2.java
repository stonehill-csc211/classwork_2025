
import java.util.HashSet;

public class LinkedGraph2 {

    HashSet<Node> nodes;

    private class Node{
        HashSet<Edge> edges;

        private Node(){
            edges = new HashSet<Edge>();
        }

        private void addEdge(Edge e){
            edges.add(e);
        }

        private void removeEdge(Edge e){
            edges.remove(e);
        }
    }

    private class Edge{
        Node[] endpoints;

        private Edge(Node node1, Node node2){
            endpoints = new Node[]{node1, node2};
            node1.addEdge(this);
            node2.addEdge(this);
        }
    }

    public LinkedGraph2(){
        nodes = new HashSet<>();
    }

    public boolean areNeighbors(Node n1, Node n2){
        for(Edge e : n1.edges){ // O(d) where d is the max degree
            if(e.endpoints[0] == n2 || e.endpoints[1] == n2) return true;
        }
        return false;
    }
}
