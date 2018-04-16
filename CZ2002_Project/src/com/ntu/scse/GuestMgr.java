package com.ntu.scse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class GuestMgr {

	// Functional Requirement:
	// 1. Generate a Lookup table for guestID and first name, last name;
	// (INITIALIZE)
	// 2. Given a guestID, read/write his/her information from file;
	// 3. ASK to create a new guest if not found or required

	private List<Guest> guestList;
	private final String[] idTypeName = { "DRIVING LICENSE", "PASSPORT" };

	public GuestMgr(ArrayList guest) {
		if (guest == null)
			this.guestList = new ArrayList<>();
		else
			this.guestList = guest;
		System.out.println(this.guestList.size() + " Guests loaded!");
	}	

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

	public int getIDfromName(String lastName, String firstName) {
		for (Guest g : guestList) {
			if (g.getLastName().equals(lastName) && g.getFirstName().equals(firstName)) {
				return g.getGuestID();
			}
		}
		return -1;
	}
	public String getNamefromID(int id) {
		for (Guest g : guestList) {
			if (g.getGuestID()== id) {
				return g.getFirstName() + " " + g.getLastName();
			}
		}
		return "";
	}

	public void searchGuest(int guestID) {

		boolean searched = false;
		for (Guest g : guestList) {
			if (g.getGuestID() == guestID) {
				System.out.println("\nDisplaying " + g.getFirstName() + " " + g.getLastName() + " information: ");
				System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", "Guest ID", "First Name",
						"Last Name", "Gender", "Credit Card No.", "Address", "Country", "ID Type", "ID Number");

				System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", g.getGuestID(), g.getFirstName(),
						g.getLastName(), g.getGender(), g.getCreditCardNo(), g.getAddress(), g.getCountry(),
						g.getGuestID(), g.getIdNumber());
				searched = true;
				break;
			}
		}
		if (!searched)
			System.out.println("Guest does not exist!");
	}

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

	public boolean checkGuestExist(int guestID){
		for (Guest g : guestList){
			if (g.getGuestID() == guestID)
				return true;
		}
		return false;
	}

	public void removeGuest(int guestID) {
		//TODO CHECK IF ANY RESERVATIONS/OCCUPANCY BEFORE REMOVING
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

	public List<Guest> saveToFile() {
		return guestList;
	}

	
	//-----------------------------------------------------------------//
	
	public void mainGuestView(RoomMgr rm){
		int choice, guestID;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("");
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
				break;
			case 4: /* (4) Remove GUEST details */
				System.out.printf("Please key in the GuestID: ");
				guestID = sc.nextInt();
				if (checkGuestExist(guestID))
					removeGuest(guestID);
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
				System.out.printf("Please enter the Gender (M/F), or press Enter to exit: ");
				dummy = Character.toUpperCase(sc.nextLine().charAt(0));
				if (dummy == ' ') {
					break;
				} else if (dummy == 'M' || dummy == 'F') {
					guest.setGender(dummy);
					System.out.printf("* Successfully Updated the Gender to \'" + dummy + "\'\n");
					break;
				} else {
					System.out.println("##Invalid Gender Input!\n");
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
	public int getNumGuest(){
		return guestList.size();
	}

	private boolean checkGap() { //Checks if any gap due to previously deleted guest
		if (guestList.size() == 0)
			return false;
		else
			return !(guestList.get(guestList.size()-1).getGuestID() == guestList.size());
	}
}
