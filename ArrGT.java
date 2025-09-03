public class ArrGT {
    public static double[] arrGT(double[] arr, double t){
        double[] bigValues = new double[arr.length];
        int end = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > t){
                bigValues[end] = arr[i];
                end++;
            }
        }
        double[] returnValue = new double[end];
        for(int i = 0; i < end; i++){
            returnValue[i] = bigValues[i];
        }
        return returnValue;
    }

    public static void main(String[] args){
        double[] arr = {1., 9., 2., 8.};
        double[] big = arrGT(arr, 5.);
        for(int i = 0; i < big.length; i++){
            System.out.print(big[i] + " ");
        }
    }
}
