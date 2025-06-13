package com.ps;

import com.ps.dealership.Dealership;
import com.ps.dealership.DealershipDAO;
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
                //todo add rest of the command
                case 2:
                    break;

                case 0:
                    System.out.println("Exit Program...");
                    break;
            }

        } while (mainMenuCommand != 0);
    }

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
                    String id= scanner.nextLine();

                    Dealership dealership=dealershipDAO.getById(id);
                    System.out.println(dealership);
                    break;
                case 3:
                    System.out.println("Enter Prompts to Create New Dealership");
                    scanner.nextLine();
                    System.out.print("Enter Dealership name:");
                    String name= scanner.nextLine();
                    System.out.print("Enter Dealership Address:");
                    String address= scanner.nextLine();
                    System.out.print("Enter Dealership Phone:");
                    String phone= scanner.nextLine();

                    Dealership dealershipToCreate= new Dealership(0,name,address,phone);
                    DealershipDAO.create(dealershipToCreate);
                    break;
                case 4:
                    System.out.println("Enter Prompts to modify New Dealership");
                    scanner.nextLine();
                    System.out.print("Enter Dealership name to modify:");
                    String updateName= scanner.nextLine();

                    System.out.print("Enter Dealership Address:");
                    String updateAddress= scanner.nextLine();
                    System.out.print("Enter Dealership Phone:");
                    String updatePhone= scanner.nextLine();

                    Dealership dealershipToModify = new Dealership(0,"",updateAddress,updatePhone);
                    DealershipDAO.update(updateName,dealershipToModify);
                    break;
                case 5:
                    System.out.println("Enter Prompt to Delete Existing Dealership");
                    scanner.nextLine();
                    System.out.println("Enter Name:");
                    String deleteName=scanner.nextLine();

                    DealershipDAO.delete(deleteName);
                    break;

                case 0:
                    System.out.println("Going back to Main Menu");
                    break;
                default:
                    System.out.println("Invalid command, Try Again!");
            }
        } while (dealershipCommand != 0);
    }
}
