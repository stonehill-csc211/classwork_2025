public abstract class MyList<T> {

    public abstract void add(T newValue);
    public abstract void insert(int position, T newValue);
    public abstract T remove(int position);
    public abstract T get(int position);
    public abstract T set(int position, T newValue);
    public abstract boolean contains(T query);
    public abstract int size();
    public abstract String toString();

    public abstract void repeat(int k);

}
