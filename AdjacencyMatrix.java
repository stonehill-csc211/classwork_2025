public class AdjacencyMatrix {
    int[][] mat;

    public AdjacencyMatrix(int nodes){
        mat = new int[nodes][nodes];
    }

    public void addEdge(int n1, int n2, boolean directed){
        mat[n1][n2] = 1;
        if(directed) mat[n2][n1] = 1;
    }
}
