public class PriorityQueue<T> {
    Node head, tail;
    int length;

    private class Node {
        int priority;
        T value;
        Node next;
        Node previous;

        private Node(T value, int priority){
            this.priority = priority;
            this.value = value;
        }
    }

    public PriorityQueue(){
        length = 0;
    }

    public void enqueue(T item, int priority){
        /*
         * Add a new node to the priorityqueue 
         * with the given priority
         */
        Node newNode = new Node(item, priority);
        if(length == 0){
            head = newNode;
            tail = newNode;
            length++;
            return;
        } else if(length == 1){
            if(head.priority > priority){
                tail = newNode;
            } else{
                head = newNode;
            }
            head.next = tail;
            tail.previous = head;
            length++;
            return;
        }
        Node current = head;
        while(current != null && current.priority > priority){
            current = current.next;
        }
        Node prev;
        if(current == null){
            prev = tail;
            tail = newNode;
        } else {
            prev = current.previous;
        }
        if(prev != null){
            prev.next = newNode;
            newNode.previous = prev;
        }
        newNode.next = current;
        if(current != null){
            current.previous = newNode;
        }
        length++;
    }

    public T dequeue(){
        /*
         * Return the element with the highest priority
         * and remove it from the queue
         */
        if(head == null) return null;
        T returnValue = head.value;
        if(head.next != null){
            head.next.previous = null;
        }
        head = head.next;
        length--;
        return returnValue;
    }

    public T peek(){
        /*
         * Look at the highest priority element
         */
        if(head == null) return null;
        return head.value;
    }

    public void enqueueAll(T[] newElements, int[] newPriorities){
        /*
         * Use mergeSort to add a lot of new elements efficiently
         */
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

    public static void main(String[] args){
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.enqueue("Data Structures", 4);
        pq.enqueue("Discrete", 3);
        pq.enqueue("Calculus", -1);
        pq.enqueue("Religion", 2);
        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
    }
}
