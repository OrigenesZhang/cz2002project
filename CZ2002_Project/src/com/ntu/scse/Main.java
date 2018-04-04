package com.ntu.scse;

import java.util.Scanner;

import com.ntu.scse.InvalidInfoException.*;

public class Main {

    public static void main(String[] args) {

        int choice;
        Scanner sc = new Scanner(System.in);

        Initialize();

        do {
            System.out.println("");
            System.out.println("============================================");
            System.out.println("======Please Select from the Following:=====");
            System.out.println("============================================");

            System.out.println("(1) Create/Update/Search GUEST detail;");
            System.out.println("(2) Create/Update/Remove/Print RESERVATION;");
            System.out.println("(3) Update ROOM details;");
            System.out.println("(4) Create ROOM SERVICE orders;");
            System.out.println("(5) Create/Update/Remove ROOM SERVICE MENU items;");
            System.out.println("(6) Check ROOM Availability;");
            System.out.println("(7) Room CHECK-IN (for WALK-IN or RESERVATION)");
            System.out.println("(8) Room CHECK-OUT and print BILL invoice");
            System.out.println("(9) Print ROOM STATUS statistic report");
            System.out.println("(10) Exit");
            System.out.println("");
            System.out.printf("        Enter the number of your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: /* (1) Create/Update/Search GUEST detail */
                    break;

                case 2: /* (2) Create/Update/Remove/Print RESERVATION */
                    break;

                case 3: /* (3) Update ROOM details */
                    break;

                case 4: /* (4) Create ROOM SERVICE orders */
                    break;

                case 5: /* (5) Create/Update/Remove ROOM SERVICE MENU items */
                    break;

                case 6: /* (6) Check ROOM Availability */
                    break;

                case 7: /* (7) Room CHECK-IN (for WALK-IN or RESERVATION) */
                    break;

                case 8: /* (8) Room CHECK-OUT and print BILL invoice */
                    break;

                case 9: /* (9) Print ROOM STATUS statistic report */
                    break;

                case 10: /* (10) Exit */
                    break;

                default:
                    System.out.println("    Program terminating ....");


            }
        } while (choice < 10 && choice > 0);
    }

    private static void Initialize() {
        System.out.println("__        __   _");
        System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___");
        System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
        System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/");
        System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");

        System.out.println("");
        System.out.println("Welcome to HRPS for SCSE Hotel!");
        System.out.println("");

        System.out.println("Initializing the room information...");
//                Initialize the room information;
        System.out.println("Initializing the room service information...");
//                Initialize the room serivce information;
        System.out.println("Initializing the guest information...");
//                Initialize the guest information;
        System.out.println("Initializing the reservation information...");
//                Initialize the reservation information;
        System.out.println("Initializing the billing information...");
//                Initialize the billing information;
        System.out.println("Successfully initialized the system!");
    }
}


