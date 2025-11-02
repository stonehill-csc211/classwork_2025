public class HashMap<K extends Comparable<K>,V> {
    private class Record{
        K key;
        V value;
        private Record(K key, V value){
            this.key = key;
            this.value = value;
        }
        public String toString(){
            return this.key.toString() + ":" + this.value.toString();
        }
    }

    private Record[] data;
    private int n;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap(int size){
        n = size;
        this.data = new HashMap.Record[n];
        this.size = 0;
    }

    public HashMap(){
        this(100);
    }

    private void resize(int newSize){
        Record[] oldData = this.data;
        this.data = new HashMap.Record[newSize];
        int hash;
        this.n = newSize;
        for(int i = 0; i < oldData.length; i++){
            if(oldData[i] != null){
                hash = oldData[i].key.hashCode();
                hash = getIndexLinearProbing(oldData[i].key);
                data[hash] = oldData[i];
            }
        }
    }

    private int fixHash(int hash){
        return ((hash % n) + n) % n;
    }

    public void put(K key, V value){
        if(this.size >= n / 2){
            this.resize(n * 2);
        }
        int hash = getIndexLinearProbing(key);
        this.data[hash] = new Record(key, value);
        this.size++;
    }

    private int getIndexLinearProbing(K query){
        int hash = query.hashCode();
        hash = fixHash(hash);
        while(this.data[hash % n] != null 
        && !this.data[hash % n].key.equals(query)){
            hash++;
        }
        return fixHash(hash);
    }

    public boolean contains(K query){
        int hash = getIndexLinearProbing(query);
        return data[hash] == null;
    }

    public V get(K query){
        int hash = getIndexLinearProbing(query);
        if(data[hash] == null) return null;
        else return this.data[hash].value;
    }

    public void set(K key, V value){
        int hash = getIndexLinearProbing(key);
        if(data[hash] == null) this.put(key, value);
        else this.data[hash].value = value;
    }

    public V remove(K key){
        int hash = getIndexLinearProbing(key);
        if(data[hash] == null) return null;
        else{
            V value = this.data[hash].value;
            this.data[hash] = null;
            this.size--;
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
        HashMap<Integer,String> map = new HashMap<>();

        map.put(10, "Alice");
        map.put(20, "Bob");
        map.put(180, "Carol");
        map.put(-150, "Dave");
        System.out.println(map);
        System.out.println(map.get(-150));
        for(int i = 0; i < 1000; i++){
            map.put(i, Integer.toString(i));
        }
        for(int i = 0; i < 1000; i++){
            map.set(i, Integer.toString(i) + "new");
        }
        for(int i = 0; i < 1000; i++){
            System.out.println(map.remove(i));
        }
    }


}
