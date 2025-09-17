public class LinkedStack<T> extends Stack<T>{
    Node head;
    Node tail;
    int length;

    public LinkedStack(){
        length = 0;
        head = tail = null;
    }

    private class Node {
        T value;
        Node next;
    }
    @Override
    public void push(T item) {
        if(head == null){
            Node newNode = new Node();
            newNode.value = item;
            head = newNode;
            tail = newNode;
        } else{
            Node newNode = new Node();
            newNode.value = item;
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    @Override
    public T pop() {
        if(head == null) 
            throw new NullPointerException("Pop from empty stack");
        else{
            T retval = head.value;
            head = head.next;
            length--;
            return retval;
        }
    }

    @Override
    public T peek() {
        if(head == null) return null;
        else return head.value;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while(current != null){
            sb.append(current.value.toString()).append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
}
