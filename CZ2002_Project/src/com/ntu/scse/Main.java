package com.ntu.scse;

import java.util.Scanner;
import java.io.*;
import com.ntu.scse.InvalidInfoException.*;

public class Main {

	static Room[][] roomList = new Room[6][8];
	static final String roomFileName = "roomData.dat";
	static final String guestFileName = "guestData.dat";
	static final String billFileName = "billData.dat";
	static RoomMgr roomMgr;
	static GuestMgr guestMgr;
	static BillMgr billMgr;
	
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
            System.out.println("(10) Save to file");
            System.out.println("(11) Exit");
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

                case 10: /* (10) Save to file */
                	saveToFile();
                    break;  
                    
                case 11: /* (11) Exit */
                    break;

                default:
                    System.out.println("    Program terminating ....");


            }
        } while (choice < 11 && choice > 0);
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
//				  Set all rooms to default
        for (int i=0 ; i<6 ; i++) {
    		for (int j=0 ; j<8 ; j++) {
    			try {
					roomList[i][j] = new Room(i+2,j+1); //USING TEMP CONSTRUCTOR, NOT SURE WHAT THE OTHER DEFAULTS SHOULD BE
				} catch (InvalidInfoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
//			Try reading from file, if file doesn't exist or if exception, use defaults
        try {
        	FileInputStream fiStream = new FileInputStream(roomFileName);
    		BufferedInputStream biStream = new BufferedInputStream(fiStream);
    		ObjectInputStream diStream = new ObjectInputStream(biStream);
        	
    		for (int i=2 ; i<8 ; i++) { //Reads in 48 Rooms by level: 02-01, 02-02, 02-03, ... , 07-07, 07-08
    			for (int j=1 ; j<9 ; j++) { //Reads in 48 Rooms by level: 02-01, 02-02, 02-03, ... , 07-07, 07-08
        			Object obj = diStream.readObject();
    				roomList[i-2][j-1] = (Room) obj;
        		}
    		}
    		diStream.close();
        }
    	catch (FileNotFoundException e) { //File does not exist, no data to load
    		System.out.println("IOError: Room file not found! Using defaults...");
    	}
    	catch (IOException e) { //Other IO Exception
    		System.out.println("File IO Error!" + e.getMessage());
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
    		System.out.println("Class not found!" + e.getMessage());
			e.printStackTrace();
		}
        roomMgr = new RoomMgr(roomList); //Instantiate RoomMgr object and pass room array to it
        
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


	private static void saveToFile() {
		//Save room info
		roomMgr.saveToFile(roomFileName);
		//Save guest info
		//guestMgr.saveToFile(guestFileName);
		//Save bill info
		//billMgr.saveToFile(billFileName);
		
		System.out.println("Saved to file!");
	}
}

