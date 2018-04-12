package com.ntu.scse;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.*;
import com.ntu.scse.InvalidInfoException.*;
import static com.ntu.scse.guestUpdateChoice.*;

public class Main {
    
    static Scanner sc = new Scanner(System.in);

    static final String roomFileName = "roomData.dat";
    static final String guestFileName = "guestData.dat";
    static final String billFileName = "billData.dat";
    static final String reservationFileName = "reservationData.dat";
    static final String roomServiceFileName = "roomServiceData.dat";
    static final String[] idTypeName = {"DRIVING LICENSE", "PASSPORT"};
    static GuestMgr guestMgr;
    static BillMgr billMgr;
    static ReservationMgr reservationMgr;
    static RoomServiceMgr roomServiceMgr = new RoomServiceMgr();
    static RoomMgr roomMgr = new RoomMgr();
    static SerializeDB sdb = new SerializeDB();

    public static void main(String[] args) throws InvalidInfoException {

        int choice;
        
        List rslist = new ArrayList();
	List rmlist = new ArrayList();
	
	initialize2(rslist, rmlist);
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
                case 1: /* (1) Create/Update/Search GUEST detail */
                    mainGuestView();
                    break;

                case 2: /* (2) Create/Update/Remove/Print RESERVATION */
                    break;

                case 3: /* (3) Check/Update ROOM details */
                    roomManager (roomMgr, roomFileName, rmlist, sdb);
                    break;

                case 4: /* (4) Create ROOM SERVICE orders */
                    roomServiceOrder(roomServiceMgr, roomMgr, roomServiceFileName, rslist, sdb);
                    break;

                case 5: /* (5) Create/Update/Remove ROOM SERVICE MENU items */
                    roomServiceMenu(roomServiceMgr, roomServiceFileName, rslist, sdb);
                    break;

                case 6: /* (7) Room CHECK-IN (for WALK-IN or RESERVATION) */
                    break;

                case 7: /* (8) Room CHECK-OUT and print BILL invoice */
                    break;

                case 8: /* (9) Print ROOM STATUS statistic report */
                    break;

                case 9: /* (10) Save to file */
                    saveToFile();
                    break;

                case 10: /* (11) Exit */
                    saveToFile();
                    System.out.println("    Program terminating ....");
                    break;

                default:
                    saveToFile();
                    System.out.println("    Program terminating ....");
            }
        } while (choice < 11 && choice > 0);
    }

    private static void Initialize() {

        FileInputStream fiStream;
        BufferedInputStream biStream;
        ObjectInputStream diStream;

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
        

//		======================================
        System.out.println("Initializing the guest information...");
//                Initialize the guest information;
        try {
            fiStream = new FileInputStream(guestFileName);
            biStream = new BufferedInputStream(fiStream);
            diStream = new ObjectInputStream(biStream);

            while (true) {
                obj = diStream.readObject();
                guestList.add((Guest) obj);
            }
        } catch (FileNotFoundException e) { //File does not exist, no data to load
            System.out.println("IOError: guest file not found! No existing guests...");
        } catch (EOFException e) {
            System.out.println("Loaded from " + guestFileName);
        } catch (IOException e) { //Other IO Exception
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

            while (true) {
                obj = diStream.readObject();
                reservationList.add((Reservation) obj);
            }
        } catch (FileNotFoundException e) { //File does not exist, no data to load
            System.out.println("IOError: reservation file not found! No existing reservations...");
        } catch (EOFException e) {
            System.out.println("Loaded from " + reservationFileName);
        } catch (IOException e) { //Other IO Exception
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

            while (true) {
                obj = diStream.readObject();
                billList.add((Bill) obj);
            }
        } catch (FileNotFoundException e) { //File does not exist, no data to load
            System.out.println("IOError: bill file not found! No existing bills...");
        } catch (EOFException e) {
            System.out.println("Loaded from " + billFileName);
        } catch (IOException e) { //Other IO Exception
            System.out.println("[Bill] File IO Error!" + e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("[Bill] Class not found!" + e.getMessage());
            e.printStackTrace();
        }
        billMgr = new BillMgr(billList); //Instantiate BillMgr object and pass bill list to it

        
        System.out.println("Successfully initialized the system!");
    }


    private static void saveToFile() {
        //Save guest info
        guestMgr.saveToFile(guestFileName);
        //Save bill info
        billMgr.saveToFile(billFileName);
        //Save reservation info
        reservationMgr.saveToFile(reservationFileName);

        System.out.println("Saved to file!");
    }

    private static void mainGuestView() throws InvalidInfoException {
        int choice;
        do {
            String dummy;
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            System.out.println("=>Please select from the following:");
            System.out.println("(1) Create New GUEST detail");
            System.out.println("(2) Update GUEST detail");
            System.out.println("(3) Search GUEST detail");
            System.out.println("(4) Return to the previous menu");
            System.out.print("        Enter the number of your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: /* (1) Create GUEST detail */
                    createNewGuest();
                    break;

                case 2: /* (2) Update GUEST detail */
                    int ch;

                    do {
                        System.out.println("\n==>Please select from the following:");
                        System.out.println("(1) Update via GuestID");
                        System.out.println("(2) Update via Guest's name");
                        System.out.println("(3) Return to the previous menu");
                        System.out.print("        Enter the number of your choice: ");
                        ch = sc.nextInt();
                        sc.nextLine();

                        switch (ch) {
                            case 1:
                                System.out.printf("Please key in the GuestID: ");
                                int guestID = sc.nextInt();
                                sc.nextLine();

                                updateGuestByID(guestID);

                                break;
                            case 2:
                                break;
                            case 3:
                                System.out.println("    Returning to the previous menu.");
                                break;
                            default:
                                System.out.println("    Invalid input! Returning to the previous menu.");
                                break;


                        }
                    } while (ch> 0 && ch < 3);
                    break;

                case 3: /* (3) Search ROOM details */
                    break;

                case 4: /* (4) Return to the previous menu */
                    System.out.println("    Returning to the main menu.");
                    break;

                default:
                    System.out.println("    Invalid input! Returning to the main menu.");
                    break;
            }

        } while (choice < 4 && choice > 0);
    }

    private static void updateGuestByID(int guestID) throws InvalidInfoException {
        Scanner sc = new Scanner(System.in);
        int ch;
        Guest theGuest = guestMgr.readGuestInfo(guestID);
        System.out.printf("\nYou are updating [" + theGuest.getFirstName() + " " +
                theGuest.getLastName() + "]'s personal information.\n");

        do {
        System.out.printf("\n===>Please select from the following:\n");
        System.out.println("(1) Update First Name");
        System.out.println("(2) Update Last Name");
        System.out.println("(3) Update Gender");
        System.out.println("(4) Update Credit Card Number");
        System.out.println("(5) Update Billing Address");
        System.out.println("(6) Update ID Type");
        System.out.println("(7) Update ID Number");
        System.out.println("(8) Return to the previous menu");
        System.out.print("        Enter the number of your choice: ");
        ch = sc.nextInt();
        sc.nextLine();


            switch (ch) {
                case FIRSTNAME:
                    System.out.printf("Please enter the First Name, or press Enter to exit: ");
                    String firstName = sc.nextLine();
                    if (!firstName.equals("")) {
                        guestMgr.writeGuestInfo(guestID, firstName, FIRSTNAME);
                        System.out.printf("* Successfully Updated the First Name to " + firstName + "\n");
                    }
                    break;
                case LASTNAME:
                    System.out.printf("Please enter the Last Name, or press Enter to exit: ");
                    String lastName = sc.nextLine();
                    if (!lastName.equals("")) {
                        guestMgr.writeGuestInfo(guestID, lastName, LASTNAME);
                        System.out.printf("* Successfully Updated the Last Name to " + lastName + "\n");
                    }
                    break;
                case GENDER:
                    System.out.printf("Please enter the Gender, or press Enter to exit: ");
                    String dummy = sc.nextLine();
                    if (!dummy.equals("")) {
                        if (dummy.length()>1){
                            guestMgr.writeGuestInfo(guestID, 'M', GENDER);
                            System.out.println("##Invalid Gender Input! Gender set to 'M'.");
                            System.out.println("##Please modify it afterwards if needed.");
                        } else try {
                            char gender = dummy.charAt(0);
                            guestMgr.writeGuestInfo(guestID, gender, GENDER);
                            System.out.printf("* Successfully Updated the Gender to \'" + gender + "\'\n");
                        } catch (InvalidInfoException e){
                            guestMgr.writeGuestInfo(guestID, 'M', GENDER);
                            System.out.println("##Invalid Gender Input! Gender set to 'M'.");
                            System.out.println("##Please modify it afterwards if needed.");
                        }
                    }
                    break;
                case CREDITCARDNO:
                    System.out.printf("Please enter the Credit Card Number, or press Enter to exit: ");
                    String creditNo = sc.nextLine();
                    if (!creditNo.equals("")) {
                        guestMgr.writeGuestInfo(guestID, creditNo, CREDITCARDNO);
                        System.out.printf("* Successfully Updated the Credit Card Number to "
                                + creditNo + "\n");
                    }
                    break;
                case ADDRESS:
                    System.out.printf("Please enter the Address, or press Enter to exit: ");
                    String address = sc.nextLine();
                    if (!address.equals("")) {
                        guestMgr.writeGuestInfo(guestID, address, ADDRESS);
                        System.out.printf("* Successfully Updated the Address to " + address + "\n");
                    }
                    break;
                case IDTYPE:
                    System.out.printf("Please enter the Address, or press Enter to exit: ");
                    int idType = sc.nextInt();
                    sc.nextLine();
                    if (idType == 1 || idType == 2) {
                        guestMgr.writeGuestInfo(guestID, idType, IDTYPE);
                        System.out.printf("* Successfully Updated the ID Type to " + idType + "\n");
                    } else {
                        guestMgr.writeGuestInfo(guestID, 1, IDTYPE);
                        System.out.print("##Invalid idType Input! idType set to 'DRIVING LICENSE'.\n");
                        System.out.print("##Please modify it afterwards if needed.\n");
                    }
                    break;
                case IDNUMBER:
                    System.out.printf("Please enter the ID Number, or press Enter to exit: ");
                    String idNum = sc.nextLine();
                    if (!idNum.equals("")) {
                        guestMgr.writeGuestInfo(guestID, idNum, IDNUMBER);
                    }
                    break;
                case 8:
                    System.out.println("    Returning to the previous menu.");
                    break;

                default:
                    System.out.println("    Invalid input! Returning to the previous menu.");
                    break;
            }

        }while (ch > 0 && ch < 8) ;
    }

    private static void createNewGuest() {
        Scanner sc = new Scanner(System.in);
        String dummy;
        String firstName;
        String lastName;
        char gender;
        String creditCardNo;
        String address;
        int idType;
        String idNumber;

        System.out.println("");
        System.out.println("==> Please provide the following information:");
        System.out.print("(1) First Name (Given Name): ");
        firstName = sc.nextLine();
        System.out.print("* Your First Name is: " + firstName + "\n");

        System.out.print("(2) Last Name (Family Name): ");
        lastName = sc.nextLine();
        System.out.print("* Your Last Name is: " + lastName + "\n");

        System.out.print("(3) Gender (M/F): ");
        dummy = sc.nextLine();
        gender = Character.toUpperCase(dummy.charAt(0));
        if ((gender != 'M' && gender != 'F') || dummy.length() > 1) {
            gender = 'M';
            System.out.println("##Invalid Gender Input! Gender set to 'M'.");
            System.out.println("##Please modify it afterwards if needed.");
        }
        System.out.print("* Your Gender is: " + gender + "\n");

        System.out.print("(4) Credit Card Number: ");
        creditCardNo = sc.nextLine();
        System.out.print("* Your Credit Card Number is: " + creditCardNo + "\n");

        System.out.print("(5) Billing Address: ");
        address = sc.nextLine();
        System.out.print("* Your Billing Address is: " + address + "\n");

        System.out.print("(6) ID Type ([1]: DRIVINGLICENSE; [2]: PASSPORT): ");
        idType = sc.nextInt();
        sc.nextLine();
        if (idType != 1 && idType != 2) {
            idType = 1;
            System.out.print("##Invalid idType Input! idType set to 'DRIVING LICENSE'.\n");
            System.out.print("##Please modify it afterwards if needed.\n");
        }
        System.out.print("* Your IdType is: " + idTypeName[idType-1] + "\n");

        System.out.print("(7) ID Number: ");
        idNumber = sc.nextLine();
        System.out.print("* Your idNumber is: " + idNumber + "\n");

        System.out.println("    Saving your particular to the system...");
        guestMgr.addNewGuest(firstName, lastName, gender, creditCardNo, address, idType, idNumber);
        System.out.print("    The information of " + firstName + " " + lastName + " have successfully saved.\n");
        System.out.println("");
        System.out.println("    Please press the RETURN button to return the main menu.");
        sc.nextLine();
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------//
    
    private static void initialize2(List rsList, List rmList)
    {
        File rsfile = new File (roomServiceFileName);
		if(!rsfile.exists())
		{
			rsList.add(roomServiceMgr);
			sdb.writeSerializedObject(roomServiceFileName, rsList);
		} else {
			rsList = (ArrayList) sdb.readSerializedObject(roomServiceFileName);
			for (int i = 0; i < rsList.size(); i++) {
				roomServiceMgr = (RoomServiceMgr) rsList.get(i);
			}
		}
        
        
        File rfile = new File (roomFileName);
		if(!rfile.exists())
        {
			rmList.add(roomMgr);
			sdb.writeSerializedObject(roomFileName, rmList);
		} else {
			rmList = (ArrayList) sdb.readSerializedObject(roomFileName);
			for (int i = 0; i < rmList.size(); i++) {
				roomMgr = (RoomMgr) rmList.get(i);
			}
		}
    }
    
     private static void roomServiceMenu(RoomServiceMgr rs, String fileName, List list, SerializeDB sdb)
	{
		int choice = 0;
         
		do {
			System.out.println("Room Service Menu: ");
			System.out.println("1. Read Menu");
			System.out.println("2. Add item to menu");
			System.out.println("3. Remove item from menu");
			System.out.println("4. Update menu item");
			System.out.println("5. Return main screen");
			choice = errorCheckingInt ("Select an option: ");

			switch (choice) {
			case 1:
				rs.viewMenu();
				break;

			case 2:
				rs.addMenu();
				System.out.println("");

				list.add(rs);
				sdb.writeSerializedObject(fileName, list);
				break;

			case 3:
				rs.viewMenu();
				list.remove(rs.removeMenuItem());
				list.add(rs);
				sdb.writeSerializedObject(fileName, list);
				break;

			case 4:
				rs.viewMenu();
				rs.updateMenuItem();
				list.add(rs);
				sdb.writeSerializedObject(fileName, list);
				break;

			case 5:
				System.out.println("Returning to main.....\n");
				break;

			default:
				System.out.println("Error sc\n");
				break;
			}
		} while (choice != 5);
	}

	private static void roomServiceOrder(RoomServiceMgr rs, RoomMgr rm, String fileName, List list, SerializeDB sdb)
	{
		int choice;
		String roomNo;
        
		do
		{

			System.out.println("Room Service Order: ");
			System.out.println("1. View order"); 			//option to view all orders or view search orders by roomID
			System.out.println("2. Add order");
			System.out.println("3. Remove order");
			System.out.println("4. Update order");
			System.out.println("5. Return");
			choice = errorCheckingInt ("Select an option: ");

			switch (choice) {
			case 1: 
				System.out.println("(1) view all orders");
				System.out.println("(2) view order by room no.");
				choice = errorCheckingInt("Select an option: ", 2);
				if(choice == 1)
					rs.viewAllOrder();
				else 
				{
					System.out.print("Enter room no: ");
					roomNo = sc.nextLine();
					rs.viewOrderByRoomID(roomNo);
				}
				break;

			case 2: 

				System.out.println("Enter Room no. : ");
				roomNo = sc.nextLine();
				if (rm.checkValidRoomForOrder(roomNo))
				{
					rs.viewMenu();
					rs.addOrderItem(roomNo);
					rs.finalizeOrder(roomNo);

					list.add(rs);
					sdb.writeSerializedObject(fileName, list);
				} else
				{
					System.out.println("Invalid Room no.\n");
				}

				break;

			case 3:
				System.out.println("Enter Room no. : ");
				roomNo = sc.nextLine();
				if (rm.checkValidRoomForOrder(roomNo))
				{
					rs.viewOrderByRoomID(roomNo);

					list.remove(rs.removeOrderItem(roomNo));
					list.add(rs);
					sdb.writeSerializedObject(fileName, list);
				} else
				{
					System.out.println("Invalid Room no. \n");
				}
				break;

			case 4: 
				//reuse case 3 variables
				System.out.println("Enter Room no. : ");
				roomNo = sc.nextLine();
				if (rm.checkValidRoomForOrder(roomNo))
				{
					rs.viewOrderByRoomID(roomNo);

					list.add(rs.updateOrderItem(roomNo));
					sdb.writeSerializedObject(fileName, list);
				} else
				{
					System.out.println("Invalid Room no. \n");
				}
				break;

			case 5:
				System.out.println("Returning to main.....\n");
				break;

			default:
				System.out.println("Error sc \n");
				break;	
			}

		}while (choice != 5);
	}

	private static void roomManager(RoomMgr rm, String fileName, List list, SerializeDB sdb)
	{
		int choice;
		String name, roomNo;

		do {
			System.out.println("Room option: ");
			System.out.println("(1) View All Room");
			System.out.println("(2) View selected room details");
			System.out.println("(3) Update Room Details");
			System.out.println("(4) View Room Statistic Report");
			System.out.println("(5) Return to main");
			choice = errorCheckingInt("Select an option: ");

			switch (choice) {
			case 1:
				rm.viewAllRoom();
				break;

			case 2:
				System.out.println("\nView selected room option:");
				System.out.println("(1) Enter room no");
				System.out.print("(2) Enter Guest");
				choice = errorCheckingInt("Select an option: ", 2);

				if(choice == 1)
				{
					System.out.print("\nEnter Room no. : ");
					roomNo = sc.nextLine();

					rm.viewSelectedRoom(choice, roomNo);
				}
				else if(choice == 2)
				{
					System.out.print("\nEnter first name: ");
					name = sc.nextLine();
					System.out.print("Enter last name: ");
					name = name +" " + sc.nextLine();
					rm.viewSelectedRoom(choice, name);
				}

				break;

			case 3:
				rm.updateRoom();
				list.add(rm);
				sdb.writeSerializedObject(fileName, list);
				break;

			case 4:
				System.out.println("\nRoom Status Statistic Option: ");
				System.out.println("(1) Room type occupancy rate");
				System.out.println("(2) Room Status");
				System.out.print("Select an option: ");
				choice = sc.nextInt();
				rm.RoomStatusStatic(choice);
				break;

			case 5:
				System.out.println("\nReturning to main.....\n");
				break;

			default:
				System.out.println("\nError sc \n");
				break;

			}

		}while (choice != 5);

	}


	//-------------------------------------------------Other Methods-------------------------------------------------------//
	private static int errorCheckingInt (String display)
	{
		int tempChoice;
		while (true) {
			System.out.print("\n" + display);
			try {
				tempChoice = sc.nextInt();
				if (tempChoice < 1)
					throw new IllegalArgumentException ("Error sc\n");
				break;
			} catch (InputMismatchException e)
			{
				System.out.println("Error sc \n");
				sc.next();
			}  catch (IllegalArgumentException e )
			{
				System.out.println(e);
			}
		}

		sc.nextLine();

		return tempChoice;
	}

	private static int errorCheckingInt(String display, int lastItem)
	{
		int index;
		while (true) {
			System.out.print("\n" + display);
			try {
				index = sc.nextInt();
				if (index < 1 || index > lastItem)
					throw new IllegalArgumentException ("Error sc\n");
				break;
			} catch (InputMismatchException e)
			{
				System.out.println("Error sc\n");
				sc.next();
			}  catch (IllegalArgumentException e )
			{
				System.out.println(e);
			}
		}

		sc.nextLine();

		return index;
	}
}


