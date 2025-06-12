package com.ps;

import com.ps.dealership.Dealership;
import com.ps.dealership.DealershipDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);
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
            }

        } while (mainMenuCommand != 0);
    }

    private static void dealership() {
        DealershipDAO dealershipDAO = new DealershipDAO(basicDataSource);

        int dealershipCommand;
        do {
            System.out.println("Your options are:");
            System.out.println("1) Show All Dealerships");
//            System.out.println("2) Filter by Dealership Id");
            System.out.print("Option:");
            //todo add all CURD

            dealershipCommand = scanner.nextInt();
            switch (dealershipCommand) {
                case 1:
                    List<Dealership> dealerships = dealershipDAO.getAll();
                    System.out.println(dealerships);
                    break;
                case 0:
                    System.out.println("Going back to Main Menu");
            }
        } while (dealershipCommand != 0);
    }
}
