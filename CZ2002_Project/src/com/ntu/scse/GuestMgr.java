package com.ntu.scse;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuestMgr {

	// Functional Requirement:
	// 1. Generate a Lookup table for guestID and first name, last name;
	// (INITIALIZE)
	// 2. Given a guestID, read/write his/her information from file;
	// 3. ASK to create a new guest if not found or required

	private List<Guest> guestList;
	private final String[] idTypeName = { "DRIVING LICENSE", "PASSPORT" };
	private int guestID; 

	public GuestMgr(ArrayList guest) {
		if (guest == null) {
			this.guestList = new ArrayList<>();
			guestID = 1000;
			System.out.println("Guest initialized!");
		} else {
			this.guestList = guest;
			System.out.println("Room Service Menu loaded!");
		}
	}	

	public Object readGuestInfo(int guestID) {
		boolean read = false;

		for (Guest g : guestList) {
			if (g.getGuestID() == guestID && !read) {
				System.out.println("Last Name: " + g.getLastName() + " First name: " + g.getFirstName());
				read = true;
				break;
			}
		}

		if (!read)
			System.out.println("Guest ID does not exist!");

		return this;
	}

	public void readGuestInfo(String lastName, String firstName) {
		boolean read = false;

		for (Guest g : guestList) {
			if (g.getLastName().equals(lastName) && g.getFirstName().equals(firstName) && !read) {
				System.out.println("Last Name: " + g.getLastName() + " First name: " + g.getFirstName());
				read = true;
				break;
			}
		}

		if (!read)
			System.out.println("Guest ID does not exist!");

	}

	public void searchGuest(String lastName, String firstName) {

		boolean searched = false;
		for (Guest g : guestList) {
			if (g.getLastName().equals(lastName) && g.getFirstName().equals(firstName)) {
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
		{
			System.out.println("Guest does not exist!");
		}
	}

	public void readGuestList() {
		
		if (guestList.size() < 1) {
			System.out.println("No GUEST details available: " + guestList.size());
		} else 
		{
			System.out.println("\nDisplay Guest List: \n");
			System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", "Guest ID", "First Name", "Last Name",
					"Gender", "Credit Card No.", "Address", "Country", "ID Type", "ID Number");

			for (Guest g : guestList) {
				System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-15s%-15s\n", g.getGuestID(), g.getFirstName(),
						g.getLastName(), g.getGender(), g.getCreditCardNo(), g.getAddress(), g.getCountry(), g.getIdType(),
						g.getIdNumber());
			}
		} 
	}

	public void addNewGuest(String firstName, String lastName, char gender, String creditCardNo, String address,
			String country, String idType, String idNumber) {

		guestList.add(
				new Guest(getNewGuestID(), firstName, lastName, gender, creditCardNo, address, country, idType, idNumber));
	} 
	
	public int getNewGuestID() {

		int id = 1000;

		try {
			Guest gg = guestList.get(guestList.size() - 1);
			if (guestList.size() < 1)
				throw new IndexOutOfBoundsException();
			
			return gg.getGuestID()+1;
		} catch (IndexOutOfBoundsException e)
		{
			return id;
		}
	}

	public void removeGuest(int guestID) {
		boolean flag = false;
		Iterator<Guest> iter = guestList.iterator();

		while (iter.hasNext()) {
			Guest str = iter.next();

			if (str.getGuestID() == guestID && !flag) {
				iter.remove();
				flag = true;
			}
		}

		if (!flag)
			System.out.println("Guest ID does not exist!");
	}

	public List<Guest> saveToFile() {
		return guestList;
	}

	
	//-----------------------------------------------------------------//
	
	public void mainGuestView(RoomMgr rm){
		int choice;
		do {
			String dummy;
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			System.out.println("=>Please select from the following:");
			System.out.println("(1) Create New GUEST detail");
			System.out.println("(2) Update GUEST detail");
			System.out.println("(3) Search GUEST detail");
			System.out.println("(4) View all GUEST detail");
			System.out.println("(5) Return to the previous menu");
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
						int guestID = sc.nextInt();
						sc.nextLine();
						updateGuestByID(guestID);
						break;
					case 2:
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

			case 3: /* (3) Search ROOM details */
				rm.viewSelectedRoom();
				break;
				
			case 4: /* (4) Return to the previous menu */
				readGuestList();
				break;

			case 5: /* (4) Return to the previous menu */
				System.out.println("Returning to the main menu.");
				break;

			default:
				System.out.println("Invalid input! Returning to the main menu.");
				break;
			}

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

		default:
			System.out.println("Invalid input! Returning to the previous menu.");
			break;
		}

	}

	public void createNewGuest() {
		Scanner sc = new Scanner(System.in);
		String dummy, firstName, lastName, creditCardNo, address, country, idNumber;
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

		System.out.println("    Saving your particular to the system...");
		addNewGuest(firstName, lastName, gender, creditCardNo, address, country, idTypeName[idType-1], idNumber);
		System.out.println("idTypeName: " + idTypeName[idType-1] );
		System.out.print("    The information of " + firstName + " " + lastName + " have successfully saved.\n");
		System.out.println("");
		System.out.println("    Please press the RETURN button to return the main menu.");
		sc.nextLine();
	}
}
