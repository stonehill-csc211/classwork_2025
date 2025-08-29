public class Car {
    String color, myMake, myModel;

    public Car(String myColor, String myMake, String myModel){
        color = myColor;
        this.myMake = myMake;
        this.myModel = myModel;
    }

    public String toString(){
        String retval = "";

        retval += "Color: " + color;
        retval += " Make: " + myMake;
        retval += " Model: " + myModel;

        return retval;
    }

    public static void main(String[] args) {
        Car myCar = new Car(
            "Red", "Toyota", "Prius"
        );
        System.out.println(myCar);
    }
}
