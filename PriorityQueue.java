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
        // create nodes for each new element 
        // with the corresponding priority
        Node head = null, tail = null, newNode;
        for(int i = 0; i < newElements.length; i++){
            newNode = new Node(newElements[i], newPriorities[i]);
            if(head == null){
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.previous = tail;
                tail = newNode;
            }

        }
        // sort the new elements
        head = mergeSort(head);
        // merge the two node strands
        Node newHead = merge(this.head, head);
        this.head = newHead;
        Node newTail = newHead;
        while(newTail.next != null) newTail = newTail.next;
        this.tail = newTail;
        this.length += newElements.length;
    }

    private Node merge(Node n1, Node n2){
        /*
         * Merge the strands between n1 and n2 in priority order
           returns the head of the new queue
         */
        PriorityQueue<T> newStrand = new PriorityQueue<>();
        if(n1 == null) return n2;
        if(n2 == null) return n1;
        if(n1.next == null || n2.next == null){
            if(n1.priority > n2.priority){
                n1.next = n2;
                n2.previous = n1;
                return n1;
            } else {
                n2.next = n1;
                n1.previous = n2;
                return n2;
            }
        }
        while(n1.next != null || n2.next != null){
            if(n2 == null || n1.priority > n2.priority){
                newStrand.enqueue(n1.value, n1.priority);
                if(n1.next !=null)n1 = n1.next;
            } else {
                newStrand.enqueue(n2.value, n2.priority);
                if(n1.next !=null)n2 = n2.next;
            }
        }
        return newStrand.head;
    }

    private Node mergeSort(Node head){
        /*
         * Use merge to sort a strand of nodes
         * Returns the head of the resulting structure
         */
        // if length is 1 return the head
        if(head.next == null) return head;
        // split the sequence in half
        int count = 0;
        Node current = head;
        while(current.next != null){
            current = current.next;
            count++;
        }
        int middle = count / 2;
        current = head;
        for(int i = 0; i < middle; i++) current = current.next;
        // sever the links
        Node secondHead = current.next;
        secondHead.previous = null;
        current.next = null;
        // call mergesort on the first half
        head = mergeSort(head);
        // and the second half separately
        secondHead = mergeSort(secondHead);
        // then merge them together and return the head
        return merge(head, secondHead);
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
        pq.enqueue("Religion", 1);

        PriorityQueue<String> pq2 = new PriorityQueue<>();
        pq2.enqueue("Stats", 2);
        pq2.enqueue("Life after Stonehill", 1);
        pq2.enqueue("Physics", -1);

        String[] newElements = new String[]{
            "Stats", "Life after Stonehill", "Physics"
        };
        int[] newPriorities = new int[]{
            2, 1, -1
        };
        pq.enqueueAll(newElements, newPriorities);

        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
    }
}
