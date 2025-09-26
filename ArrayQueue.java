public class ArrayQueue<T> extends Queue<T> {
    Object[] data;
    int size;
    int end;
    int start;

    public ArrayQueue(){
        data = new Object[10];
        size = 0;
        end = 0;
        start = 0;
    }

    private void resize(){
        this.resize(this.size() * 2);
    }

    private void resize(int newSize){
        // make a new array that's twice as big
        Object[] newData = new Object[newSize];
        // move all the old values over
        for(int i = start; i < end; i++){
            newData[i] = data[i % data.length];
        }
        data = newData;
    }
    
    public void enqueue(T item){
        if(size >= data.length){
            resize();
        }
        data[end % data.length] = item;
        size++;
        end++;
    }
    public T dequeue(){
        T retval = (T)data[start % data.length];
        start++;
        size--;
        return retval;
    }
    public T peek(){
        return (T)data[start % data.length];
    }
    public int size(){
        return size;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = start; i < end; i++){
            sb.append(data[i % data.length].toString());
            sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue() + " should be 1");
        System.out.println(q.dequeue() + " should be 2");
        for(int i = 0; i < 100; i++){
            q.enqueue(i);
        }
        System.out.println(q.dequeue() + " should be 3");
    }

}
