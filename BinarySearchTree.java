
import java.util.ArrayList;
import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>> {
    Node root;

    private class Node{
        T data;
        Node left;
        Node right;

        private Node(T data){
            this.data = data;
        }

        private void add(T newElement){
            if(newElement.compareTo(data) < 0){
                if(left == null){
                    left = new Node(newElement);
                } else {
                    left.add(newElement);
                }
            } else if(newElement.compareTo(data) > 0){
                if(right == null){
                    right = new Node(newElement);
                } else {
                    right.add(newElement);
                }
            }
        }

        private boolean contains(T query){
            int comparison = query.compareTo(data);
            if(comparison == 0){
                return true;
            }
            if(comparison > 0){
                if(right != null){
                    return right.contains(query);
                } else return false;
            } else {
                if(left != null){
                    return left.contains(query);
                } else return false;
            }
        }

        private boolean isLeaf(){
            return (left == null && right == null);
        }

        private void swapLargestLeftChild(){
            // assuming left is not null
            Node current = left;
            Node parent = this;
            while(current.right != null){
                parent = current;
                current = current.right;
            }
            T temp = current.data;
            current.data = this.data;
            this.data = temp;
            if(parent == this) parent.left = null;
            else parent.right = null;
        }
        private void swapSmallestRightChild(){
            // assuming right is not null
            Node current = right;
            Node parent = this;
            while(current.left != null){
                parent = current;
                current = current.left;
            }
            T temp = current.data;
            current.data = this.data;
            this.data = temp;
            if(parent == this) parent.right = null;
            else parent.left = null;
        }

        private boolean remove(T element){
            if(element.compareTo(data) < 0){
                if(left == null) return false;
                if(left.data.compareTo(element) == 0){
                    if(left.isLeaf()){
                        left = null;
                        return true;
                    } else {
                        if(left.left != null){
                            left.swapLargestLeftChild();
                        } else {
                            left.swapSmallestRightChild();
                        }
                        return true;
                    }
                } else {
                    if(left == null) return false;
                    else return left.remove(element);
                }
            } else if(element.compareTo(data) > 0){
                if(right == null) return false;
                if(right.data.compareTo(element) == 0){
                    if(right.isLeaf()){
                        right = null;
                        return true;
                    } else {
                        if(right.left != null){
                            right.swapLargestLeftChild();
                        } else {
                            right.swapSmallestRightChild();
                        }
                        return true;
                    }
                } else {
                    if(right == null) return false;
                    else return right.remove(element);
                }
            } else return false;
            
        }

        public ArrayList<Node> toListPreorder(){
            // return a list of the nodes in the bst in pre-order
            ArrayList<Node> values = new ArrayList<Node>();
            values.add(this);
            if(this.left != null) values.addAll(this.left.toListPreorder());
            if(this.right != null) values.addAll(this.right.toListPreorder());
            return values;
        }

        public ArrayList<Node> toListPostorder(){
            // return a list of the nodes in the bst in post-order
            ArrayList<Node> values = new ArrayList<Node>();
            
            if(this.left != null) values.addAll(this.left.toListPostorder());
            if(this.right != null) values.addAll(this.right.toListPostorder());
            values.add(this);
            return values;
        }

        public ArrayList<Node> toListInOrder(){
            // return a list of the nodes in the bst in post-order
            ArrayList<Node> values = new ArrayList<Node>();
            
            if(this.left != null) values.addAll(this.left.toListInOrder());
            values.add(this);
            if(this.right != null) values.addAll(this.right.toListInOrder());
            
            return values;
        }

        



        private String toString(int depth){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < depth; i++){
                sb.append("  ");
            }
            sb.append(data.toString() + "\n");
            if(left != null) sb.append(left.toString(depth + 1));
            if(right != null) sb.append(right.toString(depth + 1));
            return sb.toString();
        }
    }

    public void add(T newElement){
        if(root == null){
            root = new Node(newElement);
        } else {
            root.add(newElement);
        }
    }

    public boolean contains(T query){
        return root.contains(query);
    }

    public boolean remove(T element){
        if(element == root.data 
        && root.left == null 
        && root.right == null){
            root = null;
            return true;
        }
        return root.remove(element);
    }

    public String toString(){
        return root.toString(0);
    }

    private ArrayList<Node> toListLevelOrder(){
        ArrayList<Node> values = new ArrayList<Node>();
        Queue<Node> q = new ArrayQueue<Node>();
        q.enqueue(root);
        Node current;
        while(q.size() != 0){
            current = q.dequeue();
            values.add(current);
            if(current.left != null) q.enqueue(current.left);
            if(current.right != null) q.enqueue(current.right);
        }
        return values;

    }

    

    public static void main(String[] args){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(10);
        bst.add(5);
        bst.add(12);
        bst.add(3);
        bst.add(7);
        bst.add(8);
        //System.out.println(bst);
        //System.out.println(bst.contains(7) + " should be true");
        //System.out.println(bst.contains(4) + " should be false");

        //bst.remove(1);
        //bst.remove(8);
        //bst.remove(5);
        //System.out.println(bst);
        for(BinarySearchTree<Integer>.Node n: bst.root.toListPreorder()){
            System.out.print(n.data + " ");
        }
        System.out.println();
        for(BinarySearchTree<Integer>.Node n: bst.root.toListPostorder()){
            System.out.print(n.data + " ");
        }
        System.out.println();
        for(BinarySearchTree<Integer>.Node n: bst.root.toListInOrder()){
            System.out.print(n.data + " ");
        }
        System.out.println();
        for(BinarySearchTree<Integer>.Node n: bst.toListLevelOrder()){
            System.out.print(n.data + " ");
        }
    }
}
