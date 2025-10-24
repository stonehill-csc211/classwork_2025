import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class TestTreeMaps {

    public static void testArrayList(int tableSize, int searchLength){
        ArrayList<Rec> records = new ArrayList<>();
        char c;
        String name;
        Random r = new Random();
        for(int i = 0; i < tableSize; i++){
            name = "";
            for(int j = 0; j < 20; j++){
                c = (char)(r.nextInt(128) + 40);
                name = (name + Character.toString(c));
            }
            records.add(new TestTreeMaps.Rec(name, r.nextInt(100)));
        }

        for(int i = 0; i < searchLength; i++){
            name = "";
            for(int j = 0; j < 20; j++){
                c = (char)(r.nextInt(128) + 40);
                name = (name + Character.toString(c));
            }
            boolean found = false;
            for(int j = 0; j < records.size(); j++){
                if(records.get(j).name.equals(name)){
                    System.out.println("Found it!");
                    found = true;
                }
            }
            if(!found){System.out.println("Didn't find it.");}
        }
    }

    public static void testTreeMap(int tableSize, int searchLength){
        TreeMap<String, Integer> records = new TreeMap<>();
        char c;
        String name;
        Random r = new Random();
        for(int i = 0; i < tableSize; i++){
            name = "";
            for(int j = 0; j < 20; j++){
                c = (char)(r.nextInt(128) + 40);
                name = (name + Character.toString(c));
            }
            records.put(name, r.nextInt(100));
        }

        for(int i = 0; i < searchLength; i++){
            name = "";
            for(int j = 0; j < 20; j++){
                c = (char)(r.nextInt(128) + 40);
                name = (name + Character.toString(c));
            }
            boolean found = records.containsKey(name);
            if(found){System.out.println("Found it!");}
            if(!found){System.out.println("Didn't find it.");}
        }
    }



    public static void main(String[] args){
        testArrayList(1000000, 10000);
    }

    private static class Rec{
        String name;
        int grade;
        private Rec(String name, int grade){
            this.name = name;
            this.grade = grade;
        }
        public String toString(){
            return this.name + " " + this.grade;
        }
    }

}
