public abstract class Queue<T> {
    public abstract void enqueue(T item);
    public abstract T dequeue();
    public abstract T peek();
    public abstract int size();
    public abstract String toString();
}
