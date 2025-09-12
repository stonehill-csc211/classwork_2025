public class MajorityElement {

    

    public int majority(Comparable[] arr){
        // returns the index of the majority element
        // or -1 if there is none
        int count = 0, ind = -1, bestCount = arr.length/2;
        for(int i = 0; i < arr.length; i++){
            count = 0;
            for(int j = 0; j < arr.length; j++){
                if(arr[i].compareTo(arr[j])==0){count++;}
            }
            if(count > bestCount){
                ind = i;
                bestCount = count;
            }
        }
        return ind;
    }
}
