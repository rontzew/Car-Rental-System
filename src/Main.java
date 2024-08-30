import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
class CarRentalSystem {

    private final Map<String, String> rentedCars = new HashMap<>(100, 0.5f);


    private static String getPlateNo(Scanner sc) {
        System.out.print("Insert vehicle plate number: ");
        return sc.nextLine();
    }

    private static String getOwnerName(Scanner sc) {
        System.out.print("Insert owner name: ");
        return sc.nextLine();
    }

    private boolean isCarRent(String plateNo) {
        return rentedCars.containsKey(plateNo);
    }

    private String getCarRent(String plateNo) {
        return rentedCars.get(plateNo);
    }

    private void rentCar(String plateNo, String ownerName) {
        if(isCarRent(plateNo)){
            System.out.println("Vehicle " + plateNo + " is already rented by " + getCarRent(plateNo) + ".");
        }else{
            rentedCars.put(plateNo, ownerName);
            System.out.println("Vehicle assigned successfully.");
        }
    }

    private void returnCar(String plateNo) {
        String statement = "Vehicle not found. No changes were made.";
        if(isCarRent(plateNo)){
            statement = "Vehicle " + plateNo + ", rented by " + getCarRent(plateNo) + " was returned.";
            rentedCars.remove(plateNo);
        }
        System.out.println(statement);
    }

    private int totalCarsRented() {
        return rentedCars.size();
    }

    private void getCarsNo(String ownerName) {
        RentedCars rentedByOwner = new RentedCars();
        for(String plateNo : rentedCars.keySet()){
            if(rentedCars.get(plateNo).equals(ownerName)){
                rentedByOwner.add(plateNo);
            }
        }
        System.out.println("Number of vehicles currently rented: " + rentedByOwner.size() + ".");
    }

    private void getCarsList(String ownerName) {
        RentedCars rentedByOwner = new RentedCars();
        for(String plateNo : rentedCars.keySet()){
            if(rentedCars.get(plateNo).equals(ownerName)){
                rentedByOwner.add(plateNo);
            }
        }
        System.out.println("List of vehicles rented by " + ownerName + ": \n" + rentedByOwner.showCars());
    }

    private static void printCommandsList() {
        System.out.println("help         - Displays this command list");
        System.out.println("add          - Adds new (car, driver) pair");
        System.out.println("check        - Checks if a vehicle is already in use");
        System.out.println("remove       - Deletes an already existing vehicle from hashtable");
        System.out.println("getOwner     - Displays owner of vehicle");
        System.out.println("totalRented  - Displays number of rented vehicles");
        System.out.println("getCarsNo    - Displays no of vehicles rented by one specific client");
        System.out.println("getCarsList  - Displays the specific vehicles rented by a client");
        System.out.println("quit         - Exits app");
    }

    public void run(Scanner sc) {
        boolean quit = false;
        printCommandsList();
        while (!quit) {
            String command = sc.nextLine();

            switch (command) {
                case "help":
                    printCommandsList();
                    break;
                case "add":
                    String plateNo = getPlateNo(sc);
                    String ownerName = getOwnerName(sc);
                    rentCar(plateNo, ownerName);
                    break;
                case "check":
                    plateNo = getPlateNo(sc);
                    if(isCarRent(plateNo)){
                        System.out.println("Vehicle " + plateNo + " was rented by " + getCarRent(plateNo) + ".");
                    }else {
                        System.out.println("Vehicle " + plateNo + " is not rented.");
                    }
                    break;
                case "remove":
                    plateNo = getPlateNo(sc);
                    returnCar(plateNo);
                    break;
                case "getOwner":
                    plateNo = getPlateNo(sc);
                    if(getCarRent(plateNo)!= null){
                        System.out.println(getCarRent(plateNo));
                    }
                    else{
                        System.out.println("Vehicle " + plateNo + " is not rented.");
                    }
                    break;
                case "totalRented":
                    System.out.println(totalCarsRented());
                    break;
                case "getCarsNo":
                    getCarsNo(getOwnerName(sc));
                    break;
                case "getCarsList":
                    getCarsList(getOwnerName(sc));
                    break;
                case "quit":
                    System.out.println("App shutdown...");
                    quit = true;
                    break;
                default:
                    System.out.println("Unknown command. Try again.");
                    printCommandsList();
                    break;
            }
        }
    }
}


class RentedCars {

    private final ArrayList<String> rentedByPerson;
    public RentedCars() {
        this.rentedByPerson = new ArrayList<>();
    }

    private void orderList(){
        Collections.sort(rentedByPerson);
    }
    public void add(String plateNo) {
        rentedByPerson.add(plateNo);
    }

    public void remove(String plateNo) {
        rentedByPerson.remove(plateNo);
    }

    public int size() {
        return rentedByPerson.size();
    }

    public String showCars() {
        orderList();
        return rentedByPerson.toString();
    }

}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        carRentalSystem.run(scanner);
    }
}
