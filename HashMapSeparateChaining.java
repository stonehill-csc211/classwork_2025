public class HashMapSeparateChaining<K extends Comparable<K>,V> {
    private class Record{
        K key;
        V value;
        Record next;
        private Record(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
        public String toString(){
            return this.key.toString() + ":" + this.value.toString();
        }

        private Record get(K key){
            // helper method to return the record associated with a given key
            // from the chain
            if(key.compareTo(this.key) == 0) return this;
            else if(this.next == null) return null;
            else return this.next.get(key);
        }

        private void remove(K key){
            // left as an exercise to the reader
        }
    }

    private Record[] data;
    private int n;
    private int size;

    public HashMapSeparateChaining(){
        n = 100;
        this.data = new HashMapSeparateChaining.Record[n];
        this.size = 0;
    }

    private void resize(int newSize){
        //TODO
    }

    public void put(K key, V value){
        if(this.size == n - 1){
            this.resize(n * 2);
        }
        int hash = key.hashCode();
        hash = ((hash % n) + n) % n;
        if(this.data[hash % n] == null)
            this.data[hash % n] = new Record(key, value);
        else{
            Record current = this.data[hash % n];
            while(current.next != null){
                current = current.next;
            }
            current.next = new Record(key, value);
        }
    }

    private Record getRecordSeparateChaining(K query){
        int hash = query.hashCode();
        hash = ((hash % n) + n) % n;
        if(this.data[hash] == null) return null;
        else return this.data[hash].get(query);
    }

    public boolean contains(K query){
        Record r = getRecordSeparateChaining(query);
        return r != null;
    }

    public V get(K query){
        Record r = getRecordSeparateChaining(query);
        if(r == null) return null;
        else return r.value;
    }

    public void set(K key, V value){
        Record r = getRecordSeparateChaining(key);
        if(r == null){
            this.put(key, value);
        }
        else r.value = value;
    }

    public V remove(K key){
        Record r = getRecordSeparateChaining(key);
        if(r == null) return null;
        else{
            V value = r.value;
            int hash = key.hashCode();
            hash = ((hash % n) + n) % n;
            this.data[hash].remove(key);
            return value;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            if(data[i] == null) continue;
            else{
                sb.append(i);
                sb.append(" ");
                sb.append(data[i].toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        HashMapSeparateChaining<Integer,String> map = new HashMapSeparateChaining<>();

        map.put(10, "Alice");
        map.put(20, "Bob");
        map.put(180, "Carol");
        map.put(-150, "Dave");
        map.put(110, "Evan");
        System.out.println(map);
        System.out.println(map.get(110));
    }


}
