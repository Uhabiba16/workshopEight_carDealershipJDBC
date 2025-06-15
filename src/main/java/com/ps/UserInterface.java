package com.ps;

import com.ps.dealership.Dealership;
import com.ps.dealership.DealershipDAO;
import com.ps.vehicle.Vehicle;
import com.ps.vehicle.VehicleDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static BasicDataSource basicDataSource;

    public static void setDataSource(BasicDataSource dataSource) {
        basicDataSource = dataSource;
    }

    public static void init() {
        display();
    }

    public static void display() {
        int mainMenuCommand;
        do {
            System.out.println("1)Dealership");
            System.out.println("2)Inventory");
            System.out.println("3)Vehicle");
            System.out.println("4)Sales Contract");
            System.out.println("5)Lease Contract");
            System.out.print("Option:");
            mainMenuCommand = scanner.nextInt();

            switch (mainMenuCommand) {
                case 1:
                    dealership();
                    break;
                case 2:
//                    inventories();
                    break;
                case 3:
                    vehicles();
                    break;
                case 0:
                    System.out.println("Exit Program...");
                    break;
                default:
                    System.out.println("Invalid command, Try Again!");
            }
        } while (mainMenuCommand != 0);
    }

    private static void vehicles() {
        VehicleDAO vehicleDAO = new VehicleDAO(basicDataSource);

        int vehicleCommand;
        do {
            System.out.println("Your options are:");
            System.out.println("1) Show All Vehicles");
            System.out.println("2) Filter by Vehicle Detail");
            System.out.println("3) Add A New Vehicle");
            System.out.println("4) Modify Existing Vehicle");
            System.out.println("5) Delete Existing Vehicle");
            System.out.println("0) Back to Main Menu");
            System.out.print("Option:");

            vehicleCommand = scanner.nextInt();
            switch (vehicleCommand) {
                case 1:
                    List<Vehicle> vehicles = vehicleDAO.getAll();
                    System.out.println(vehicles);
                    break;
                case 2:
                    filterVehicle();
                    break;
                case 3:
                    createVehicle(vehicleDAO);
                    break;
                case 4:
                    updateVehicle(vehicleDAO);
                    break;
                case 5:
                    deleteVehicle(vehicleDAO);
                    break;
                case 0:
                    System.out.println("Going back to Main Menu");
                    break;
                default:
                    System.out.println("Invalid command, Try Again!");
            }
        } while (vehicleCommand != 0);
    }//Done

    private static void deleteVehicle(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please provide the Vehicle VIN to delete: ");
        String vin = scanner.nextLine();

        vehicleDAO.deleteVehicle(vin);
    }//Done

    private static void updateVehicle(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please provide vehicle VIN to update:");
        String vin=scanner.nextLine();

        System.out.print("Year:");
        int year=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make:");
        String make=scanner.nextLine();
        System.out.print("Model:");
        String model=scanner.nextLine();
        System.out.print("Type:");
        String type=scanner.nextLine();
        System.out.print("Color:");
        String color=scanner.nextLine();
        System.out.print("Odometer:");
        int odometer=scanner.nextInt();
        System.out.print("Price:");
        int price=scanner.nextInt();
        System.out.print("Sold:");
        boolean sold=scanner.nextBoolean();
        Vehicle vehicleToUpdate= new Vehicle("",year,make,model,type,color,odometer,price,sold);

        vehicleDAO.updateVehicle(vin,vehicleToUpdate);
    }//Done

    private static void createVehicle(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.println("Please Provide Following Details:");
        System.out.print("VIN:");
        String vin=scanner.nextLine();
        System.out.print("Year:");
        int year=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make:");
        String make=scanner.nextLine();
        System.out.print("Model:");
        String model=scanner.nextLine();
        System.out.print("Type:");
        String type=scanner.nextLine();
        System.out.print("Color:");
        String color=scanner.nextLine();
        System.out.print("Odometer:");
        int odometer=scanner.nextInt();
        System.out.print("Price:");
        int price=scanner.nextInt();
        System.out.print("Sold:");
        boolean sold=scanner.nextBoolean();

        Vehicle vehToCreate= new Vehicle(vin,year,make,model,type,color,odometer,price,sold);

        vehicleDAO.createVehicle(vehToCreate);

    }//Done

    private static void filterVehicle() {
        VehicleDAO vehicleDAO = new VehicleDAO(basicDataSource);
        scanner.nextLine();
        int filterMenuCommand;
        do {
            System.out.println("Filter By:");
            System.out.println("1) Filter by Color");
            System.out.println("2) Filter by Type");
            System.out.println("3) Filter by Make/Model");
            System.out.println("4) Filter by Year Range");
            System.out.println("5) Filter by Price Range");
            System.out.println("6) Filter by Mileage Range");
            System.out.println("0) Back to Vehicle Menu");
            System.out.print("Option:");

            filterMenuCommand = scanner.nextInt();
            switch (filterMenuCommand) {
                case 1:
                    filerByColor(vehicleDAO);
                    break;
                case 2:
                    filterByType(vehicleDAO);
                    break;
                case 3:
                    filterByMakeModel(vehicleDAO);
                    break;
                case 4:
                    filterByYear(vehicleDAO);
                    break;
                case 5:
                    filterByPrice(vehicleDAO);
                    break;
                case 6:
                    filterByMileage(vehicleDAO);
                    break;
                case 0:
                    System.out.println("Going Back to Vehicle Menu...");
                    break;
                default:
                    System.out.println("Invalid command, Try Again!");
            }
        } while (filterMenuCommand != 0);
    }//Done

    private static void filerByColor(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please Provide Vehicle Color:");
        String color = scanner.nextLine();

        List<Vehicle> vehiclesByColor = vehicleDAO.getByColor(color);
        System.out.println(vehiclesByColor);
    }//Done

    private static void filterByType(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please Provide Vehicle Type:");
        String type = scanner.nextLine();

        List<Vehicle> vehiclesByType = vehicleDAO.getByType(type);
        System.out.println(vehiclesByType);
    }//Done

    private static void filterByMakeModel(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please Provide Vehicle Make:");
        String  make = scanner.nextLine();
        System.out.print("Please Provide Vehicle Model:");
        String model = scanner.nextLine();

        List<Vehicle> vehiclesByMakeModel = vehicleDAO.getByMakeModel(make, model);
        System.out.println(vehiclesByMakeModel);
    }//Done

    private static void filterByYear(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please Provide Vehicle Year From:");
        int fromYear = scanner.nextInt();
        System.out.print("Please Provide Vehicle Year To:");
        int toYear = scanner.nextInt();

        List<Vehicle> vehiclesByYear = vehicleDAO.getByYear(fromYear, toYear);
        System.out.println(vehiclesByYear);
    }//Done

    private static void filterByPrice(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please Provide Vehicle Minimum Price:");
        int min = scanner.nextInt();
        System.out.print("Please Provide Vehicle Maximum Price:");
        int max = scanner.nextInt();

        List<Vehicle> vehiclesByPrice = vehicleDAO.getByPrice(min, max);
        System.out.println(vehiclesByPrice);
    }//Done

    private static void filterByMileage(VehicleDAO vehicleDAO) {
        scanner.nextLine();
        System.out.print("Please Provide Vehicle Starting Mileage:");
        String startMileage = scanner.nextLine();
        System.out.print("Please Provide Vehicle Ending Mileage:");
        String endMileage = scanner.nextLine();

        List<Vehicle> vehiclesByMileage = vehicleDAO.getByMileage(startMileage, endMileage);
        System.out.println(vehiclesByMileage);
    }//Done

    private static void dealership() {
        DealershipDAO dealershipDAO = new DealershipDAO(basicDataSource);

        int dealershipCommand;
        do {
            System.out.println("Your options are:");
            System.out.println("1) Show All Dealerships");
            System.out.println("2) Filter by Dealership Id");
            System.out.println("3) Create a New Dealership");
            System.out.println("4) Modify Existing Dealership");
            System.out.println("5) Delete Existing Dealership");
            System.out.println("0) Back to Main Menu");
            System.out.print("Option:");

            dealershipCommand = scanner.nextInt();
            switch (dealershipCommand) {
                case 1:
                    List<Dealership> dealerships = dealershipDAO.getAll();
                    System.out.println(dealerships);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Please Provide Dealership ID:");
                    String id = scanner.nextLine();

                    Dealership dealership = dealershipDAO.getById(id);
                    System.out.println(dealership);
                    break;
                case 3:
                    createDealership();
                    break;
                case 4:
                    updateDealership();
                    break;
                case 5:
                    deleteDealership();
                    break;
                case 0:
                    System.out.println("Going back to Main Menu");
                    break;
                default:
                    System.out.println("Invalid command, Try Again!");
            }
        } while (dealershipCommand != 0);
    }//Done

    private static void createDealership() {
        System.out.println("Enter Prompts to Create New Dealership");
        scanner.nextLine();
        System.out.print("Enter Dealership name:");
        String name = scanner.nextLine();
        System.out.print("Enter Dealership Address:");
        String address = scanner.nextLine();
        System.out.print("Enter Dealership Phone:");
        String phone = scanner.nextLine();

        Dealership dealershipToCreate = new Dealership(0, name, address, phone);
        DealershipDAO.create(dealershipToCreate);
    }//Done

    private static void updateDealership() {
        System.out.println("Enter Prompts to modify New Dealership");
        scanner.nextLine();
        System.out.print("Enter Dealership name to modify:");
        String updateName = scanner.nextLine();

        System.out.print("Enter Dealership Address:");
        String updateAddress = scanner.nextLine();
        System.out.print("Enter Dealership Phone:");
        String updatePhone = scanner.nextLine();

        Dealership dealershipToModify = new Dealership(0, "", updateAddress, updatePhone);
        DealershipDAO.update(updateName, dealershipToModify);
    }//Done

    private static void deleteDealership() {
        System.out.println("Enter Prompt to Delete Existing Dealership");
        scanner.nextLine();
        System.out.println("Enter Name:");
        String deleteName = scanner.nextLine();

        DealershipDAO.delete(deleteName);
    }//Done
}
