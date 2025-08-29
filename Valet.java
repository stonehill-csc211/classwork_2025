public class Valet {
    Car[] garage;
    boolean[] taken;
    public Valet(){
        garage = new Car[50];
        taken = new boolean[50];
    }

    public int put(Car myCar){
        int nextSpot;
        for(nextSpot = 0; 
            nextSpot < taken.length && taken[nextSpot]; 
            nextSpot++){}

        if(nextSpot >= garage.length){
            Car[] newGarage = new Car[garage.length * 2];
            boolean[] newAvailable = new boolean[garage.length * 2];
            for(int i = 0; i < garage.length; i++){
                newGarage[i] = garage[i];
                newAvailable[i] = taken[i];
            }
            this.garage = newGarage;
            this.taken = newAvailable;
        }

        garage[nextSpot] = myCar;
        taken[nextSpot] = true;
        return nextSpot;
    }

    public Car get(int myTicket){
        taken[myTicket] = false;
        return garage[myTicket];
    }

    public static void main(String[] args) {
         Valet myvalet = new Valet();
         for(int i = 0; i < 100; i++){
            myvalet.put(new Car("color", 
            "make", "model"));
         }
         for(int i = 0; i < 100; i++){
            myvalet.get(i);
         }
         for(int i = 0; i < 100; i++){
            myvalet.put(new Car("color", 
            "make", "model"));
         }
    }
}