package com.ntu.scse;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import com.ntu.scse.InvalidInfoException.*;

public class Main {

	static final String roomFileName = "roomData.dat";
	static final String guestFileName = "guestData.dat";
	static final String billFileName = "billData.dat";
	static final String reservationFileName = "reservationData.dat";
	static final String roomServiceFileName = "data.dat";
	static RoomMgr roomMgr;
	static GuestMgr guestMgr;
	static BillMgr billMgr;
	static ReservationMgr reservationMgr;
	static RoomServiceMgr roomServiceMgr;
	
    public static void main(String[] args) throws InvalidInfoException {

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
            System.out.print("        Enter the number of your choice: ");
            choice = sc.nextInt();
            switch (choice) {


                case 1:
                    System.out.println("-1. new GUEST \n -2.n");
                    int guestChoice = sc.nextInt();
                    switch(guestChoice){
                        case 1:
                            guestMgr.addNewGuest(sc.nextInt(),sc.next(),sc.next(),sc.next().charAt(0),
                                    sc.next(),sc.next(),sc.nextInt(),sc.next());
                }
                    /* (1) Create/Update/Search GUEST detail */
                   /* while(guestChoice >0 && guestChoice<4){
                        switch(guestChoice){
                            case 1:
                                //Create a new GUEST object
                                /*Guest newGuest = new Guest();
                                System.out.println("Create GUEST detail");
                                System.out.println("Please input your First Name(eg:Peter)");
                                newGuest.setFirstName(sc.next());
                                System.out.println("Please input your Last Name(eg:Chua)");
                                newGuest.setLastName(sc.next());
                                System.out.println("gender: input M for male, F for female");
                                newGuest.setGender(sc.next().charAt(0));
                                System.out.println("Please choose id type: 1_DRIVING LICENSE  2_PASSPORT");
                                newGuest.setIdType(sc.nextInt());
                                System.out.println("Please input id number");
                                newGuest.setIdNumber(sc.next());
                                System.out.println("Please input your Credit card number");
                                newGuest.setCreditCardNo(sc.next());
                                System.out.println("");
                                break;
                            case 2:
                                System.out.println("Update GUEST detail");
                                break;
                            case 3:
                                System.out.println("Search GUEST detail");
                                break;
                        }

					}
                    break;
        */
                case 2: /* (2) Create/Update/Remove/Print RESERVATION */
					/*switch(){
						case 1:
						case 2:
						case 3:

					}
                    break;*/

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
        sc.close();
    }

    private static void Initialize() {
    	
    	FileInputStream fiStream;
		BufferedInputStream biStream;
		ObjectInputStream diStream;
		
    	Room[][] roomList = new Room[6][8];
    	ArrayList<Guest> guestList = new ArrayList<>();
    	ArrayList<Bill> billList = new ArrayList<>();
    	ArrayList<Reservation> reservationList = new ArrayList<>();
    	Object obj;
    	
        System.out.println("__        __   _");
        System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___");
        System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
        System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/");
        System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");

        System.out.println("");
        System.out.println("Welcome to HRPS for SCSE Hotel!");
        System.out.println("");

//      ======================================
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
        	fiStream = new FileInputStream(roomFileName);
    		biStream = new BufferedInputStream(fiStream);
    		diStream = new ObjectInputStream(biStream);
        	
    		for (int i=2 ; i<8 ; i++) { //Reads in 48 Rooms by level: 02-01, 02-02, 02-03, ... , 07-07, 07-08
    			for (int j=1 ; j<9 ; j++) { //Reads in 48 Rooms by level: 02-01, 02-02, 02-03, ... , 07-07, 07-08
        			obj = diStream.readObject();
    				roomList[i-2][j-1] = (Room) obj;
        		}
    		}
    		diStream.close();
    		System.out.println("Loaded from " + roomFileName);
        }
    	catch (FileNotFoundException e) { //File does not exist, no data to load
    		System.out.println("IOError: Room file not found! Using default settings...");
    	}
    	catch (IOException e) { //Other IO Exception
    		System.out.println("[Room] File IO Error!" + e.getMessage());
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
    		System.out.println("[Room] Class not found!" + e.getMessage());
			e.printStackTrace();
		}
        roomMgr = new RoomMgr(roomList); //Instantiate RoomMgr object and pass room array to it


//		======================================
        System.out.println("Initializing the guest information...");
//                Initialize the guest information;
        try {
        	fiStream = new FileInputStream(guestFileName);
    		biStream = new BufferedInputStream(fiStream);
    		diStream = new ObjectInputStream(biStream);
        	
    		while (true){
    			obj = diStream.readObject();
    			guestList.add((Guest) obj);
    		}
        }
    	catch (FileNotFoundException e) { //File does not exist, no data to load
    		System.out.println("IOError: guest file not found! No existing guests...");
    	}
        catch (EOFException e) {
        	System.out.println("Loaded from " + guestFileName);
        }
    	catch (IOException e) { //Other IO Exception
    		System.out.println("[Guest] File IO Error!" + e.getMessage());
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
    		System.out.println("[Guest] Class not found!" + e.getMessage());
			e.printStackTrace();
		}
        guestMgr = new GuestMgr(guestList); //Instantiate GuestMgr object and pass guest list to it
        

//		======================================
        System.out.println("Initializing the reservation information...");
//                Initialize the reservation information;
        try {
        	fiStream = new FileInputStream(reservationFileName);
    		biStream = new BufferedInputStream(fiStream);
    		diStream = new ObjectInputStream(biStream);
        	
    		while (true){
    			obj = diStream.readObject();
    			reservationList.add((Reservation) obj);
    		}
        }
    	catch (FileNotFoundException e) { //File does not exist, no data to load
    		System.out.println("IOError: reservation file not found! No existing reservations...");
    	}
        catch (EOFException e) {
        	System.out.println("Loaded from " + reservationFileName);
        }
    	catch (IOException e) { //Other IO Exception
    		System.out.println("[Reservation] File IO Error!" + e.getMessage());
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
    		System.out.println("[Reservation] Class not found!" + e.getMessage());
			e.printStackTrace();
		}
        reservationMgr = new ReservationMgr(reservationList); //Instantiate ReservationMgr object and pass reservation list to it
        

//		======================================
        System.out.println("Initializing the billing information...");
//                Initialize the billing information;
        try {
        	fiStream = new FileInputStream(billFileName);
    		biStream = new BufferedInputStream(fiStream);
    		diStream = new ObjectInputStream(biStream);
        	
    		while (true){
    			obj = diStream.readObject();
    			billList.add((Bill) obj);
    		}
        }
    	catch (FileNotFoundException e) { //File does not exist, no data to load
    		System.out.println("IOError: bill file not found! No existing bills...");
    	}
        catch (EOFException e) {
        	System.out.println("Loaded from " + billFileName);
        }
    	catch (IOException e) { //Other IO Exception
    		System.out.println("[Bill] File IO Error!" + e.getMessage());
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
    		System.out.println("[Bill] Class not found!" + e.getMessage());
			e.printStackTrace();
		}
        billMgr = new BillMgr(billList); //Instantiate BillMgr object and pass bill list to it


//		======================================
        System.out.println("Initializing the Room Service (Menu) information...");
//      Initialize the Room Service Info
        roomServiceMgr = new RoomServiceMgr();
      
        System.out.println("Successfully initialized the system!");
    }


	private static void saveToFile() {
		//Save room info
		roomMgr.saveToFile(roomFileName);
		//Save guest info
		guestMgr.saveToFile(guestFileName);
		//Save bill info
		billMgr.saveToFile(billFileName);
		//Save reservation info
		reservationMgr.saveToFile(reservationFileName);
		
		System.out.println("Saved to file!");
	}
}

