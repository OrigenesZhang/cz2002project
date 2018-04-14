package com.ntu.scse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import com.ntu.scse.InvalidInfoException.*;

public class Main {

	static final String dataFileName = "data.dat";
	static RoomMgr roomMgr = null;
	static GuestMgr guestMgr = null;
	static BillMgr billMgr = null;
	static ReservationMgr reservationMgr = null;
	static RoomService roomService = null;
	
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
            //System.out.println("(3) Update ROOM details;");
            System.out.println("(3) Print/Check/Update ROOM details");
            System.out.println("(4) Create ROOM SERVICE orders;");
            System.out.println("(5) Create/Update/Remove ROOM SERVICE MENU items;");
            System.out.println("(6) Check ROOM Availability;");
            System.out.println("(7) Room CHECK-IN (for WALK-IN or RESERVATION)");
            System.out.println("(8) Room CHECK-OUT and print BILL invoice");
            System.out.println("(9) Print ROOM STATUS statistic report");
            System.out.println("(10) Save to file");
            System.out.println("(11) Exit");
            System.out.println("");
            System.out.print("        Enter the number of your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: /* (1) Create/Update/Search GUEST detail */
                    guestMgr.addNewGuest("Christopher", "Lim", 'M',"12345","54321",1,"24680");//TEMPORARY
                    break;

                case 2: /* (2) Create/Update/Remove/Print RESERVATION */
                    break;

                case 3: /* (3) Print/Check/Update ROOM details */
                	roomMgr.ShowRoomMgrMenuOption();
                    break;

                case 4: /* (4) Print/Create/Update/Remove ROOM SERVICE orders */
                	roomService.ShowRoomServiceOrderOption(roomMgr);
                	saveToFile();
                    break;

                case 5: /* (5) Print/Create/Update/Remove ROOM SERVICE MENU items */
                    roomService.ShowRoomServiceMenuOption();
                    break;

                case 6: /* (6) Check ROOM Availability */
                	
                    break;

                case 7: /* (7) Room CHECK-IN (for WALK-IN or RESERVATION) */
                    break;

                case 8: /* (8) Room CHECK-OUT and print BILL invoice */
                    break;

                case 9: /* (9) Print ROOM STATUS statistic report */
                    break; 
                    
                case 11: /* (11) Exit */
                    break;

                default:
                    System.out.println("    Program terminating ....");
            }
            saveToFile();
        } while (choice < 11 && choice > 0);
        sc.close();
    }

    private static void Initialize() {
    	
    	FileInputStream fiStream;
		BufferedInputStream biStream;
		ObjectInputStream diStream;
    	
        System.out.println("__        __   _");
        System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___");
        System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
        System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/");
        System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");

        System.out.println("");
        System.out.println("Welcome to HRPS for SCSE Hotel!");
        System.out.println("");

//      ======================================
        System.out.println("Initializing data from file...");
//			Try reading from file, if file doesn't exist or if exception, use defaults
        try {
        	
        	fiStream = new FileInputStream(dataFileName);
    		biStream = new BufferedInputStream(fiStream);
    		diStream = new ObjectInputStream(biStream);

            roomMgr = new RoomMgr((ArrayList)diStream.readObject()); //Instantiate RoomMgr object and pass room array to it
            guestMgr = new GuestMgr((ArrayList)diStream.readObject()); //Instantiate GuestMgr object and pass guest list to it
            reservationMgr = new ReservationMgr((ArrayList)diStream.readObject()); //Instantiate ReservationMgr object and pass reservation list to it
            billMgr = new BillMgr((ArrayList)diStream.readObject()); //Instantiate BillMgr object and pass bill list to it
            roomService = new RoomService((ArrayList)diStream.readObject(), (ArrayList)diStream.readObject());
            diStream.close();
        }
    	catch (FileNotFoundException e) { //File does not exist, no data to load
    		System.out.println("No data file found! Initializing using default settings...");

            roomMgr = new RoomMgr(null); //Instantiate default RoomMgr
            guestMgr = new GuestMgr(null); //Instantiate default GuestMgr
            reservationMgr = new ReservationMgr(null); //Instantiate default ReservationMgr
            billMgr = new BillMgr(null); //Instantiate default BillMgr
            roomService = new RoomService(null, null); //Instantiate default Menu
            
    	}
    	catch (IOException e) { //Other IO Exception
    		System.out.println("File IO Error!" + e.getMessage());
    	} catch (ClassNotFoundException e) {
    		System.out.println("Class not found!" + e.getMessage());
			e.printStackTrace();
		}
    }


	private static void saveToFile() {
        //ROOM GUEST RESV BILL ROOMSVC
        try {
            FileOutputStream foStream = new FileOutputStream(dataFileName);
            BufferedOutputStream boStream = new BufferedOutputStream(foStream);
            ObjectOutputStream doStream = new ObjectOutputStream(boStream);

            doStream.writeObject(roomMgr.saveToFile());
            doStream.writeObject(guestMgr.saveToFile());
            doStream.writeObject(reservationMgr.saveToFile());
            doStream.writeObject(billMgr.saveToFile());
            doStream.writeObject(roomService.saveMenuToFile());				//for menu
            doStream.writeObject(roomService.saveOrderToFile());			//for order

            doStream.close();
            foStream.close();
        }
        catch (IOException e){
            System.out.println("File IO Error! " + e.getMessage());
        }
        /*
		//Save room info
		roomMgr.saveToFile(roomFileName);
		//Save guest info
		guestMgr.saveToFile(guestFileName);
		//Save bill info
		billMgr.saveToFile(billFileName);
		//Save reservation info
		reservationMgr.saveToFile(reservationFileName);
		//Save room service menu info
		roomService.saveToFile(menuFileName);
		*/
		System.out.println("Saved to file!");
	}
}

