public class FunWithObjects {

    public static void myMethod(int obj){
        obj++;
    }

    public static void main(String[] args) {
        int x = 5;
        //x.myValue = 5;
        myMethod(x);
        System.out.println(x);
    }


    private static class MyWrapper {
        int myValue;
    }
}
