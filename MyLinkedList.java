public class MyLinkedList<T> extends MyList<T> {

    private Node head;
    private Node tail;
    int length;
    public MyLinkedList(){
        head = null;
        tail = null;
        length = 0;
    }

    public void add(T newValue){
        if(head == null){
            head = new Node();
            head.value = newValue;
            tail = head;
        } else {
            Node newNode = new Node();
            newNode.value = newValue;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    @Override
    public void insert(int position, T newValue){
        if(!inBounds(position)){
            throw new IndexOutOfBoundsException(
                "position is " + position + 
                " length is " + length
            );
        }
        Node current = head;
        for(int i = 0; i < position - 1; i++){
            current = current.next;
        }
        Node after = current.next;

        Node newNode = new Node();
        newNode.value = newValue;
        current.next = newNode;
        newNode.next = after;
        length++;
    }

    private boolean inBounds(int ind){
        return 0 < ind && ind < length;
    }

    private class Node{
        T value;
        Node next;
    }

    public T remove(int position){return null;}
    
    public T get(int position){
        if(!inBounds(position)){
            throw new IndexOutOfBoundsException(
                "position is " + position + 
                " length is " + length
            );
        }
        Node current = head;
        for(int i = 0; i < position; i++){
            current = current.next;
        }
        return current.value;
    }
    
        public T set(int position, T newValue){return null;}
    public boolean contains(T query){return false;}
    public int size(){return this.length;}

    public void repeat(int k){
        Node current = this.head;
        Node next, newNode;
        while(current != null){
            next = current.next;
            for(int j = 0; j < k-1; j++){
                newNode = new Node();
                newNode.value = current.value;
                current.next = newNode;
                current = newNode;
            }
            current.next = next;
            if(current.next == null){
                tail = current;
            }
            current = next;
        }
        length = length * k;
    }

    public String toString(){
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

    public static void main(String[] args) {
        MyLinkedList<String> lst = new MyLinkedList<>();
        lst.add("first");
        lst.add("second");
        //System.out.println(lst);
        for(int i = 0; i < 10; i++){
            lst.add("third");
        }
        /*System.out.println(lst);
        lst.insert(3, "fourth");
        System.out.println(lst);
        System.out.println(lst.get(3));
        System.out.println(lst.get(6));*/
        lst.repeat(3);
        System.out.println(lst);
    }
}
