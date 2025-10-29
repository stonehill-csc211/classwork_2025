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

    public HashMap(){
        n = 100;
        this.data = new HashMap.Record[n];
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
            int i = hash % n;
            while(this.data[i] != null){
                i++;
            }
            this.data[i] = new Record(key,value);
        }
    }

    private int getIndexLinearProbing(K query){
        int hash = query.hashCode();
        hash = ((hash % n) + n) % n;
        while(this.data[hash % n] != null 
        && !this.data[hash % n].key.equals(query)){
            hash++;
        }
        if(this.data[hash % n] == null) return -1;
        return hash % n;
    }

    public boolean contains(K query){
        int hash = getIndexLinearProbing(query);
        return hash == -1;
    }

    public V get(K query){
        int hash = getIndexLinearProbing(query);
        if(hash == -1) return null;
        else return this.data[hash].value;
    }

    public void set(K key, V value){
        int hash = getIndexLinearProbing(key);
        if(hash == -1) this.put(key, value);
        else this.data[hash].value = value;
    }

    public V remove(K key){
        int hash = getIndexLinearProbing(key);
        if(hash == -1) return null;
        else{
            V value = this.data[hash].value;
            this.data[hash] = null;
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
    }


}
