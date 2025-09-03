public class MyArrayList<T> extends MyList<T> {
    private Object[] data;
    private int end;

    public MyArrayList(){
        data = new Object[10];
        end = 0;
    }

    private void resize(){
        // make a new array that's twice as big
        Object[] newData = new Object[2 * data.length];
        // move all the old values over
        for(int i = 0; i < data.length; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public void add(T newValue){

        if(end >= data.length){
            resize();
        }
        data[end] = newValue;
        end++;
    }

    /*public void insert(int position, T newValue){
        Object temp;
        Object temp2 = newValue;
        if(end >= data.length){
            resize();
        }
        end++;
        for(int i = position; i < end; i++){
            temp = data[i];
            data[i] = temp2;
            temp2 = temp;
        }
    }*/
    public void insert(int position, T newValue){
        if(end >= data.length){
            resize();
        }
        for(int i = end; i >= position; i--){
            data[i+1] = data[i];
        }
        data[position] = newValue;
        end++;
    }
    public T remove(int position){return null;}
    public T get(int position){return null;}
    public T set(int position, T newValue){return null;}
    public boolean contains(T query){return false;}
    public int size(){return 0;}
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < end; i++){
            sb.append(data[i].toString());
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<String> lst = new MyArrayList<>();
        lst.add("first");
        lst.add("second");
        for(int i = 0; i < 10; i++){
            lst.add("third");
        }
        System.out.println(lst);
        lst.insert(3, "fourth");
        System.out.println(lst);
    }
}
