package com.ntu.scse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
/**
 Represents a hotel Guest Manager that contains a list of all guests as well as methods for manipulating those guests.
 One GuestManager can contain many Guests.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class GuestMgr {
	/**
	 * An array of the master list of Guests for the hotel
	 */
	private List<Guest> guestList;
	/**
	 * An array of the possible identification document types for a guest
	 */
	private final String[] idTypeName = { "DRIVING LICENSE", "PASSPORT" };
	/**
	 * Creates a Guest Manager based on loaded data from a file. If no previous data was present, initialize an empty master list.
	 * @param guest The list received from Main. If there was previous data, use this, else, create an empty list.
	 */
	public GuestMgr(ArrayList guest) {
		if (guest == null){
			this.guestList = new ArrayList<>();
			initialize10Guests();
		}
		else
			this.guestList = guest;
		System.out.println(this.guestList.size() + " Guests loaded!");
	}
	/**
	 * Prints out a guest's first and last name based on a given guest ID, and returns the Guest object
	 * @param guestID the ID of the guest
	 * @return returns the Guest object if a match is found
	 */
	public Guest readGuestInfo(int guestID) {
		for (Guest g : guestList) {
			if (g.getGuestID() == guestID) {
				System.out.println("Last Name: " + g.getLastName() + " First name: " + g.getFirstName());
				return g;
			}
		}
		System.out.println("Guest does not exist!");
		return null;
	}
	/**
	 * Gets the guest ID given the guest's first and last name
	 * @param lastName the last name of the guest
	 * @param firstName the first name of the guest
	 * @return returns the Guest's ID
	 */
	public int getIDfromName(String lastName, String firstName) {
		for (Guest g : guestList) {
			if (g.getLastName().equals(lastName) && g.getFirstName().equals(firstName)) {
				return g.getGuestID();
			}
		}
		return -1;
	}
	/**
	 * Gets the guest first and last name given the guest's id
	 * @param id the ID of the guest
	 * @return returns the Guest's first and last name
	 */
	public String getNamefromID(int id) {
		for (Guest g : guestList) {
			if (g.getGuestID()== id) {
				return g.getFirstName() + " " + g.getLastName();
			}
		}
		return "";
	}
	/**
	 * Prints out a guest's full details based on a given guest ID, and returns the Guest object
	 * @param guestID the ID of the guest
	 * @return returns the Guest object if a match is found
	 */
	public Guest searchGuest(int guestID) {

		for (Guest g : guestList) {
			if (g.getGuestID() == guestID) {
				System.out.println("\nDisplaying " + g.getFirstName() + " " + g.getLastName() + " information: ");
				System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", "Guest ID", "First Name",
						"Last Name", "Gender", "Credit Card No.", "Address", "Country", "ID Type", "ID Number");

				System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", g.getGuestID(), g.getFirstName(),
						g.getLastName(), g.getGender(), g.getCreditCardNo(), g.getAddress(), g.getCountry(),
						g.getGuestID(), g.getIdNumber());
				return g;
			}
		}
		System.out.println("Guest does not exist!");
		return null;
	}
	/**
	 * Prints out a guest's first and last name based on a given guest first name, and returns the Guest object.
	 * If more than 1 have the same first name, prompt user to enter guest's last name as well.
	 * @param firstName the first name of the guest
	 * @return returns the Guest object if a match is found
	 */
	public Guest searchGuest(String firstName) {

		boolean searched = false;
		List<Guest> matchingList = new ArrayList<>();
		Guest tempGuest = null;

		for (Guest g : guestList) {
			if (g.getFirstName() == firstName) {
				matchingList.add(g);
			}
		}
		if (matchingList.size() == 0) {
			searched = false;
		} else if (matchingList.size() == 1) {
			tempGuest = matchingList.get(0);
			searched = true;
		} else {
			Scanner sc = new Scanner(System.in);
			String lastName;
			System.out.println("More than 1 guest found with first name = " + firstName);
			System.out.println("Please enter last name: ");
			lastName = sc.nextLine();
			for (Guest g : matchingList) {
				if (g.getLastName() == lastName) {
					tempGuest = g;
					searched = true;
					break;
				}
			}
		}
		if (searched) {
			System.out.println("\nDisplaying " + tempGuest.getFirstName() + " " + tempGuest.getLastName() + " information: ");
			System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", "Guest ID", "First Name",
					"Last Name", "Gender", "Credit Card No.", "Address", "Country", "ID Type", "ID Number");

			System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", tempGuest.getGuestID(), tempGuest.getFirstName(),
					tempGuest.getLastName(), tempGuest.getGender(), tempGuest.getCreditCardNo(), tempGuest.getAddress(), tempGuest.getCountry(),
					tempGuest.getGuestID(), tempGuest.getIdNumber());
		} else
			System.out.println("Guest does not exist!");
		return tempGuest;
	}
	/**
	 * Prints out the entire guest list.
	 */
	public void readGuestList() {

		if (guestList.size() < 1) {
			System.out.println("No guests registered!");
		} else
		{
			System.out.println("\nDisplay Guest List: \n");
			System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", "Guest ID", "First Name", "Last Name",
					"Gender", "Credit Card No.", "Address", "Country", "ID Type", "ID Number");

			for (Guest g : guestList) {
				System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", g.getGuestID(), g.getFirstName(),
						g.getLastName(), g.getGender(), g.getCreditCardNo(), g.getAddress(), g.getCountry(), g.getIdType(),
						g.getIdNumber());
			}
		}
	}
	/**
	 * Tries to create a new Guest with the given guest details.
	 * Gets a new unique identifier automatically
	 * @param firstName The Guest first name
	 * @param lastName The Guest last name
	 * @param gender The Guest gender
	 * @param creditCardNo The Guest credit card number
	 * @param address The Guest address
	 * @param country The Guest country
	 * @param idType The Guest identification document type
	 * @param idNumber The Guest national identification number
	 * @return returns the newly created guest
	 */
	public Guest addNewGuest(String firstName, String lastName, char gender, String creditCardNo, String address,
			String country, String idType, String idNumber) {

		int newGuestID = 0;

		if (checkGap() == false) { //no gap, add guest at back
			newGuestID = guestList.size() + 1;
		} else { //add guest in between
			for (int i = 0; i < guestList.size(); i++) {
				if (guestList.get(i).getGuestID() != (i + 1)) {
					newGuestID = i + 1;
					break;
				}
			}
		}
		try {

			Guest newGuest = new Guest(newGuestID, firstName, lastName, gender,
					creditCardNo, address, country, idType, idNumber);
			//NEED TO CHECK IF GUEST ALREADY EXIST?
			guestList.add(newGuest);
			System.out.println("Total number of guests: " + guestList.size());
			return newGuest;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * Checks if a given guest ID exists in the master guest list
	 * @param guestID the guest ID
	 * @return returns true if the guest exists, else false
	 */
	public boolean checkGuestExist(int guestID){
		for (Guest g : guestList){
			if (g.getGuestID() == guestID)
				return true;
		}
		return false;
	}
	/**
	 * Removes a guest from the master list
	 * @param guestID the guest ID
	 */
	public void removeGuest(int guestID) {
		boolean flag = false;
		String fName, lName;
		Iterator<Guest> iter = guestList.iterator();

		while (iter.hasNext()) {
			Guest str = iter.next();

			if (str.getGuestID() == guestID && !flag) {
				fName = str.getFirstName();
				lName = str.getLastName();
				iter.remove();
				System.out.println("Guest: " + fName + " " + lName + " has been removed!");
				flag = true;
			}
		}
		if (!flag)
			System.out.println("Guest does not exist!");
	}
	/**
	 * Gives the master list back to main to write to file
	 * @return the master guest list
	 */
	public List<Guest> saveToFile() {
		return guestList;
	}

	
	//-----------------------------------------------------------------//
	/**
	 * Shows the main menu for all guest-related operations such as
	 * Creating a new guest
	 * Updating guest details
	 * Searching for guests
	 * Removing guests
	 * Viewing all guests
	 * @param rm The reservation manager object
	 */
	public void mainGuestView(ReservationMgr rm){
		int choice, guestID;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("=>Please select from the following:");
			System.out.println("(1) Create New GUEST detail");
			System.out.println("(2) Update GUEST detail");
			System.out.println("(3) Search GUEST detail");
			System.out.println("(4) Remove GUEST detail");
			System.out.println("(5) View all GUEST detail");
			System.out.println("(6) Return to the previous menu");
			System.out.print("Enter the number of your choice: ");
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
					System.out.print("Enter the number of your choice: ");
					ch = sc.nextInt();
					sc.nextLine();

					switch (ch) {
					case 1:
						System.out.printf("Please key in the GuestID: ");
						guestID = sc.nextInt();
						if (checkGuestExist(guestID))
							updateGuestByID(guestID);
						else
							System.out.printf("Guest does not exist!");
						break;
					case 2:
						System.out.printf("Please key in the Guest's first name: ");
						String fName = sc.nextLine();
						System.out.printf("Please key in the Guest's last name: ");
						String lName = sc.nextLine();
						guestID = getIDfromName(lName,fName);
						if (checkGuestExist(guestID))
							updateGuestByID(guestID);
						else
							System.out.printf("Guest does not exist!");
						break;
					case 3:
						System.out.println("Returning to the previous menu.");
						break;
					default:
						System.out.println("Invalid input! Returning to the previous menu.");
						break;
					}
				} while (ch > 0 && ch < 3);
				break;
			case 3: /* (3) Search GUEST details */
				System.out.printf("Please key in the GuestID: ");
				guestID = sc.nextInt();
				if (checkGuestExist(guestID))
					searchGuest(guestID);
				else
					System.out.printf("Guest does not exist!");
				break;
			case 4: /* (4) Remove GUEST details */
				System.out.printf("Please key in the GuestID: ");
				guestID = sc.nextInt();
				if (checkGuestExist(guestID)){
					if (!rm.doesGuestHaveResv(guestID))
						removeGuest(guestID);
					else
						System.out.printf("Unable to remove guests that have confirmed reservations or already checked in!");
				}
				else
					System.out.printf("Guest does not exist!");
				break;
			case 5: /* (5) View all GUEST detail */
				readGuestList();
				break;
			case 6: /* (6) Return to the previous menu */
				System.out.println("Returning to the main menu.");
				break;

			default:
				System.out.println("Invalid input! Returning to the main menu.");
				break;
			}
			Collections.sort(guestList);
		} while (choice < 5 && choice > 0);
	}
	/**
	 * Shows the menu for updating guest details. Details of a guest that can be updated are:
	 * First & Last name
	 * Gender
	 * Credit card number
	 * Billing address
	 * Country
	 * Identification document type
	 * National identification number
	 * @param guestID The unique identifier of the guest
	 */
	public void updateGuestByID(int guestID) {
		Scanner sc = new Scanner(System.in);
		int ch;

		for (Guest g : guestList) {
			if (g.getGuestID() == guestID) {
				System.out.printf("\nYou are updating [" + g.getFirstName() + " " + g.getLastName()
						+ "]'s personal information.\n");

				do {
					System.out.printf("\n===>Please select from the following:\n");
					System.out.println("(1) Update First Name");
					System.out.println("(2) Update Last Name");
					System.out.println("(3) Update Gender");
					System.out.println("(4) Update Credit Card Number");
					System.out.println("(5) Update Billing Address");
					System.out.println("(6) Update Country");
					System.out.println("(7) Update ID Type");
					System.out.println("(8) Update ID Number");
					System.out.println("(9) Return to the previous menu");
					System.out.print("Enter the number of your choice: ");
					ch = sc.nextInt();
					sc.nextLine();
					updateGuestByID(g, ch);
				} while (ch > 0 && ch < 9);
				break;
			}
		}

	}
	/**
	 * Executes the updating of guest details based on choice of detail to update. Details of a guest that can be updated are:
	 * First & Last name
	 * Gender
	 * Credit card number
	 * Billing address
	 * Country
	 * Identification document type
	 * National identification number
	 * @param guest The guest to update
	 * @param choice The choice of detail to update
	 */
	private void updateGuestByID(Guest guest, int choice) {
		Scanner sc = new Scanner(System.in);

		switch (choice) {
		case 1:
			System.out.printf("Please enter the First Name, or press Enter to exit: ");
			String firstName = sc.nextLine();
			if (!firstName.equals("")) {
				guest.setFirstName(firstName);
				System.out.printf("* Successfully Updated the First Name to " + firstName + "\n");
			}
			break;
		case 2:
			System.out.printf("Please enter the Last Name, or press Enter to exit: ");
			String lastName = sc.nextLine();
			if (!lastName.equals("")) {
				guest.setLastName(lastName);
				System.out.printf("* Successfully Updated the Last Name to " + lastName + "\n");
			}
			break;
		case 3:
			char dummy;
			while (true) {
				try {
					System.out.printf("Please enter the Gender (M/F), or press Enter to exit: ");
					dummy = Character.toUpperCase(sc.nextLine().charAt(0));
					
					if (dummy == 'M' || dummy == 'F') {
						guest.setGender(dummy);
						System.out.printf("* Successfully Updated the Gender to \'" + dummy + "\'\n");
						break;
					} else {
						System.out.println("##Invalid Gender Input!\n");
					}
					
				} catch (StringIndexOutOfBoundsException e)
				{
					break;
				}
			}
			break;
		case 4:
			System.out.printf("Please enter the Credit Card Number, or press Enter to exit: ");
			String creditNo = sc.nextLine();
			if (!creditNo.equals("")) {
				guest.setCreditCardNo(creditNo);
				System.out.printf("* Successfully Updated the Credit Card Number to " + creditNo + "\n");
			}
			break;
		case 5:
			System.out.printf("Please enter the Address, or press Enter to exit: ");
			String address = sc.nextLine();
			if (!address.equals("")) {
				guest.setAddress(address);
				System.out.printf("* Successfully Updated the Address to " + address + "\n");
			}
			break;
		case 6:
			System.out.printf("Please enter the Country, or press Enter to exit: ");
			String country = sc.nextLine();
			if (!country.equals("")) {
				guest.setCountry(country);
				System.out.printf("* Successfully Updated the Country to " + country + "\n");
			}
			break;

		case 7:
			while (true) {
				System.out.printf("Please Select the ID Type, or enter -1 to exit: ");
				System.out.println("ID Type ([1]: DRIVINGLICENSE; [2]: PASSPORT):");
				int idType = sc.nextInt();
				sc.nextLine();
				if (idType == 1 || idType == 2) {
					guest.setIdType(idTypeName[idType - 1]);
					System.out.printf("* Successfully Updated the ID Type to " + idTypeName[idType - 1] + "\n");
					break;
				} else if (idType == -1) {
					break;
				} else {
					System.out.print("##Invalid idType Input!");
				}
			}

			break;
		case 8:
			System.out.printf("Please enter the ID Number, or press Enter to exit: ");
			String idNum = sc.nextLine();
			if (!idNum.equals("")) {
				guest.setIdNumber(idNum);
			}
			break;
		case 9:
			System.out.println("Returning to the previous menu...");
			break;
		default:
			System.out.println("Invalid input! Returning to the previous menu.");
			break;
		}

	}
	/**
	 * Asks the user for relevant information required for creating a new guest before proceeding to create the new guest
	 * @return returns the newly created Guest
	 */
	public Guest createNewGuest() {
		Scanner sc = new Scanner(System.in);
		String dummy, firstName, lastName, creditCardNo, address, country, idNumber;
		Guest newGuest;
		char gender;
		int idType;

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

		System.out.print("(6) Country: ");
		country = sc.nextLine();
		System.out.print("* Your Country is: " + country + "\n");

		System.out.print("(7) ID Type ([1]: DRIVINGLICENSE; [2]: PASSPORT): ");
		idType = sc.nextInt();
		sc.nextLine();
		if (idType != 1 && idType != 2) {
			idType = 1;
			System.out.print("##Invalid idType Input! idType set to 'DRIVING LICENSE'.\n");
			System.out.print("##Please modify it afterwards if needed.\n");
		}
		System.out.print("* Your IdType is: " + idTypeName[idType - 1] + "\n");

		System.out.print("(8) ID Number: ");
		idNumber = sc.nextLine();
		System.out.print("* Your idNumber is: " + idNumber + "\n");

		System.out.println("    Saving your particulars to the system...");
		newGuest = addNewGuest(firstName, lastName, gender, creditCardNo, address, country, idTypeName[idType-1], idNumber);

		System.out.println("    The information of " + firstName + " " + lastName + " is successfully saved.");
		System.out.println("");
		System.out.println("    Please press the RETURN key to return to the previous menu.");
		sc.nextLine();
		return newGuest;
	}
	/**
	 * Gets the number of guests in the hotel
	 * @return returns the number of guests in the hotel
	 */
	public int getNumGuest(){
		return guestList.size();
	}
	/**
	 * Initializes a list of 10 guests
	 */
	private void initialize10Guests(){
		guestList.add(new Guest(1,"Alan","Ang",'M',"12345","123 NTU","Singapore",idTypeName[0],"12345"));
		guestList.add(new Guest(2,"Ben","Boon",'M',"23456","246 NTU","Singapore",idTypeName[1],"23456"));
		guestList.add(new Guest(3,"Chris","Chee",'M',"34567","555 NTU","Singapore",idTypeName[0],"34567"));
		guestList.add(new Guest(4,"Denise","Dee",'F',"45678","100 NTU","Singapore",idTypeName[1],"45678"));
		guestList.add(new Guest(5,"Elaine","Eng",'F',"56789","999 NTU","Singapore",idTypeName[0],"56789"));
		guestList.add(new Guest(6,"Farhana","Fareed",'F',"67890","900 NTU","Singapore",idTypeName[1],"67890"));
		guestList.add(new Guest(7,"Gregory","Gan",'M',"09876","928 NTU","Singapore",idTypeName[0],"09876"));
		guestList.add(new Guest(8,"Hannah","Ho",'F',"98765","101 NTU","Singapore",idTypeName[1],"98765"));
		guestList.add(new Guest(9,"Iggrite","Inc",'F',"87654","567 NTU","Singapore",idTypeName[0],"87654"));
		guestList.add(new Guest(10,"Jack","Jo",'M',"76543","001 NTU","Singapore",idTypeName[1],"76543"));
	}
	/**
	 * Checks if there is any gap in guest ID due to previously deleted guests
	 * @return returns true if there is a gap in guest ID, returns false if all guest IDs are in consecutive order
	 */
	private boolean checkGap() {
		if (guestList.size() == 0)
			return false;
		else
			return !(guestList.get(guestList.size()-1).getGuestID() == guestList.size());
	}
}
