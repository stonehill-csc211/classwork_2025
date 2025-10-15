

public class BinaryTree<T> {
    Node root;

    private class Node{
        Node left;
        Node right;
        T value;

        private void setToValue(T value){
            this.value = value;
            if(this.left != null) this.left.setToValue(value);
            if(this.right != null) this.right.setToValue(value);
        }
    }

    public void setToValue(T value){
        // set all the nodes in this tree to the given value
        root.setToValue(value);
    }

    public static void main(String[] args){
        BinaryTree<Integer> t = new BinaryTree<>();
        t.root = t.new Node();
        t.root.left = t.new Node();
        t.root.right = t.new Node();
        t.root.left.left = t.new Node();
        t.root.left.right = t.new Node();
    }
}
