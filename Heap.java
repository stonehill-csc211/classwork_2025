public class Heap<T extends Comparable> {
    private Comparable[] data;
    int length;

    public Heap(){
        data = new Comparable[10];
        length = 0;
    }

    private void swap(int first, int second){
        Comparable temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    private void resize(int newSize){
        // make a new array that's twice as big
        Comparable[] newData = new Comparable[newSize];
        // move all the old values over
        for(int i = 0; i < data.length; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void add(T newElement){
        if(length == data.length - 1){
            resize(data.length * 2);
        }
        int current = length+1;
        data[current] = newElement;
        length++;
        while(current > 1){
            int parent = current / 2; // gets the parent using heap index math
            if(data[current].compareTo(data[parent]) > 0){
                swap(current, parent);
                current = parent;
            } else {
                break;
            }
        }
    }

    private void reheap(int current){
        // take a heap where the root is not the largest element (semiheap)
        // and fix it

        // get the values for current's left and right children
        int left = current * 2;
        int right = current * 2 + 1;
        if(left > length && right > length) return;
        // choose the largest of those children
        //int larger = data[left].compareTo(data[right]) < 0 ? right : left;
        int larger;
        if(data[right] == null) larger = left;
        else if(data[left].compareTo(data[right]) < 0) larger = right;
        else larger = left;
        // if the current value is larger than the larger child
        if(data[current].compareTo(data[larger]) > 0){
            return;
        }
        else{
            // swap current with the larger child
            swap(current, larger);
            // recurse on the child
            reheap(larger);
        }
    }

    private void heapify(T[] newData){
        // replace the data of the heap with the newData given
        this.data = new Comparable[newData.length+1];
        for(int i = 0; i < newData.length;i++){
            this.data[i+1] = newData[i];
        }
        this.length = newData.length;
        // then iteratively reheap each non-leaf node from back to front
        for(int k = length/2; k >= 1; k--){
            reheap(k);
        }
    }

    public T removeMax(){
        // remove and return the root, then correct
        // back to a heap
        T retval = (T) this.data[1];
        this.data[1] = this.data[length];
        reheap(1);
        length--;
        return retval;
    }

    public static void heapSort(Comparable[] arr){
        // sort arr using a heap
        Heap<Comparable> heap = new Heap<>();
        heap.heapify(arr);
        for(int i = arr.length - 1; i >= 0; i--){
            arr[i] = heap.removeMax();
        }
    }


    public static void main(String[] args){
        Heap<Integer> myHeap = new Heap<>();
        myHeap.add(6);
        myHeap.add(7);
        myHeap.add(1);
        myHeap.add(2);
        myHeap.add(5);
        myHeap.add(3);
        myHeap.add(4);
        System.out.println(myHeap.removeMax());
        System.out.println(myHeap.removeMax());
        System.out.println(myHeap.removeMax());
    }
}
