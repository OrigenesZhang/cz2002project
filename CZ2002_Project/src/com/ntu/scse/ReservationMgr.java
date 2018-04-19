package com.ntu.scse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ReservationMgr {

	private List<Reservation> rList = null;
	private String[] rStatus = { "CONFIRMED", "IN WAITLIST", "CHECKED-IN", "CHECKED-OUT" , "EXPIRED"};
	private int numOfReservation = 0, resNo;

	public ReservationMgr(ArrayList reservationList) {
		if (reservationList == null) { // Initialize
			this.rList = new ArrayList<>();
			numOfReservation = 0;
		} else {
			this.rList = new ArrayList<>(reservationList);
			numOfReservation = this.rList.size();
		}
		System.out.println(numOfReservation + " Reservations loaded!");
	}

	public void resvOptions(GuestMgr gm, RoomMgr rm) {
		int choice;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("");
			System.out.println("=>Please select from the following:");
			System.out.println("(1) Create New RESERVATION");
			System.out.println("(2) Update RESERVATION detail");
			System.out.println("(3) Search RESERVATION detail");
			System.out.println("(4) Remove RESERVATION detail");
			System.out.println("(5) View all RESERVATION detail");
			System.out.println("(6) Return to the previous menu");
			choice = errorCheckingInt("Enter the number of your choice: ");

			switch (choice) {
				case 1: //(1) Create New RESERVATION
					createNewResv(gm, rm, false);
					break;

				case 2:	//(2) Update RESERVATION detail
					updateResvOption(rm);
					break;

				case 3: //(3) Search RESERVATION detail
					int resvNo = errorCheckingInt("Enter Reservation No.: ");
					readReservation(searchReservation(resvNo));
					break;

				case 4: //(4) Remove RESERVATION detail
					removeReservation(rm);
					break;

				case 5: //(5) View all RESERVATION detail
					readReservationList();
					break;

				case 6: //(6) Return to the previous menu
					break;

				default:
					System.out.println("Invalid Input!");
					break;
			}
			refreshReservations(rm);
			Collections.sort(rList);
		} while (choice != 6);
	}

	public Reservation createNewResv(GuestMgr gm, RoomMgr rm, boolean walkIn) {
		int adultNo, kidNo, dayNo, ch;
		boolean valid = false;
		String roomNo, dateIn, dateOut;
		LocalDate localDateIn, localDateOut, localDateNow;
		LocalTime resvTime;
		Guest guest = null;
		Reservation resv = null;

		Scanner sc = new Scanner(System.in);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		localDateNow = LocalDate.now();
		do {
			System.out.println("(1) NEW Guest");
			System.out.println("(2) EXISTING Guest");
			System.out.println("(3) Back");
			ch = sc.nextInt();
			if (ch == 1) {
				guest = gm.createNewGuest();
				if (guest != null)
					valid = true;
			}
			else if (ch == 2) {
				if (gm.getNumGuest() == 0)
					System.out.println("No existing guests!");
				else{
					gm.readGuestList();
					System.out.println();
					ch = errorCheckingInt("Enter guest ID: ");
					if (gm.checkGuestExist(ch)) {
						guest = gm.readGuestInfo(ch);
						valid = true;
					}
					else
						System.out.println("Guest does not exist!");
				}
			} else if (ch == 3)
				return null;
			else
				System.out.println("Invalid Input!");
		} while (!valid);
		adultNo = errorCheckingInt("Enter number of adults: ");
		kidNo = errorCheckingInt("Enter number of kids: ");
		sc.nextLine();
		rm.viewAllVacantRoom(adultNo+kidNo);
		while (true) {
			System.out.print("Enter Room No to book: ");
			roomNo = sc.nextLine();
			if (rm.checkRoomEmpty(roomNo)) {
				break;
			} else {
				System.out.println("Error input!\n");
			}
		}
		if (walkIn){
			localDateIn = localDateNow;
		}else{
			while (true) {
				System.out.print("Enter date check-in (dd-MM-yyyy): ");
				dateIn = sc.nextLine();
				try {
					localDateIn = LocalDate.parse(dateIn, format);
					if (localDateIn.isBefore(localDateNow)) {
						System.out.println("Error input. Check in date must be after today!");
						continue;
					}
					System.out.println(format.format(localDateIn));
					break;
				} catch (DateTimeParseException e) {
					System.out.println("Error input. dd-MM-yyyy = 01-02-2018");
				}
			}
		}

		while (true) {
			System.out.print("Enter date check-out (dd-MM-yyyy): ");
			dateOut = sc.nextLine();

			try {
				localDateOut = LocalDate.parse(dateOut, format);
				if (localDateOut.isBefore(localDateIn)) {
					System.out.println("Error input. Check out date must be after check in date!");
					continue;
				}
				System.out.println(format.format(localDateOut));
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Error input. dd-MM-yyyy = 01-02-2018");
			}
		}

		resvTime = LocalTime.now();

		resv = addResv(roomNo, rm.getRoomTypeFromNum(roomNo), guest.getGuestID(), adultNo, kidNo, localDateIn, localDateOut, rStatus[0],
				resvTime);
		rm.assignRoom(roomNo,2, guest.getFirstName()+ " " +guest.getLastName());
		System.out.println("Reservation confirmed! Receipt of reservation:");
		readReservation(resv);
		return resv;
	}

	public Reservation addResv(String roomNo, String roomType,int gID, int aNo, int kNo, LocalDate dIn, LocalDate dOut,
								 String rStatus, LocalTime time) {

		int newResvNo =0;
		Reservation newResv = null;
		if (checkGap() == false) { //no gap, add resv at back
			newResvNo = numOfReservation + 1;
		}
		else { //add resv in between
			for (int i = 0; i < rList.size(); i++) {
				if (rList.get(i).getResvNo() != (i+1)) {
					newResvNo = i+1;
					break;
				}
			}
		}
		try {
			newResv = new Reservation(newResvNo, roomNo, roomType, gID, aNo, kNo, dIn, dOut, rStatus, time);
			rList.add(newResv);
			numOfReservation++;
			System.out.println("Total number of reservations: " + numOfReservation);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return newResv;
	}

	private boolean checkGap() { //Checks if any gap due to previously deleted resv
		if (numOfReservation == 0)
			return false;
		else
			return !(rList.get(rList.size()-1).getResvNo() == numOfReservation);
	}

	public void readReservationList() {

		if (rList.size() == 0) {
			System.out.println("No reservations available!");
		} else{
			System.out.println("\nDisplaying Reservation List: \n");
			System.out.format("%-20s%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", "Reservation No.", "Guest ID", "Num Adult",
					"Num Kid", "Room No.", "Reservation Status", "Check in", "Check out", "Reservation time");

			for (Reservation r : rList) {
				System.out.format("%-20d%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15.8s\n", r.getResvNo(), r.getGuestID(),
						r.getAdultNo(), r.getKidNo(), r.getRoomNo(), r.getResvStatus(), r.getDateCheckIn(), r.getDateCheckOut(),
						r.getResvTime());
			}
		}
	}
	public int readReservationListByStatus(int choice) {
		//"CONFIRMED", "IN WAITLIST", "CHECKED-IN", "CHECKED-OUT", EXPIRED
		int count = 0;
		for (Reservation r : rList)
			if (r.getResvStatus().equals(rStatus[choice - 1]))
				count++;
		if (count != 0) {
			System.out.println("\nDisplaying " + rStatus[choice - 1] + " reservation List: \n");
			System.out.format("%-20s%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", "Reservation No.", "Guest ID", "Num Adult",
					"Num Kid", "Room No.", "Reservation Status", "Check in", "Check out", "Reservation time");
			for (Reservation r : rList) {
				if (r.getResvStatus().equals(rStatus[choice - 1])) {
					System.out.format("%-20d%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15.8s\n", r.getResvNo(), r.getGuestID(),
							r.getAdultNo(), r.getKidNo(), r.getRoomNo(), r.getResvStatus(), r.getDateCheckIn(), r.getDateCheckOut(),
							r.getResvTime());
				}
			}
		}
		else
			System.out.println("No " + rStatus[choice - 1] + " Reservations");
		return count;
	}
	public void readReservation(Reservation r) {

		if (r != null){
			System.out.println("\nDisplaying Reservation No. " + r.getResvNo() + ": \n");
			System.out.format("%-15s%-15s%-15s%-15s%-25s%-15s%-15s%-15s%-15s\n", "Guest ID", "Num Adult",
					"Num Kid", "Room No.", "Reservation Status", "Check in", "Check out", "Num of nights", "Reservation time");

				System.out.format("%-15d%-15s%-15s%-15s%-25s%-15s%-15s%-15s%-15.8s\n", r.getGuestID(),
						r.getAdultNo(), r.getKidNo(), r.getRoomNo(), r.getResvStatus(), r.getDateCheckIn(), r.getDateCheckOut(),
						r.getRoomDays(), r.getResvTime());
		}
	}

	public void updateResvOption(RoomMgr rm) {
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		int ch, rNo;

		System.out.printf("Please key in the Reservation no: ");
		rNo = sc.nextInt();
		sc.nextLine();

		for (Reservation r : rList) {
			if (r.getResvNo() == rNo) {
				do {
					System.out.printf("\n===>Please select from the following:\n");
					System.out.println("(1) Update Room no");
					System.out.println("(2) Update Adult No");
					System.out.println("(3) Update kids No");
					System.out.println("(4) Update Date Check-In");
					System.out.println("(5) Update Date Check-Out");
					System.out.println("(6) Update Reservation Status");
					System.out.println("(7) Return to the previous menu");
					System.out.print("Enter the number of your choice: ");
					ch = sc.nextInt();
					sc.nextLine();
					updateResv(rm, r, ch,r.getAdultNo()+r.getKidNo());
				} while (ch > 0 && ch < 9);
				flag = true;
				break;
			}
		}
		
		if(!flag)
			System.out.println("Reservation no does not exist");
	}

	public void updateResv(RoomMgr rm, Reservation r, int choice, int numGuest) {

		Scanner sc = new Scanner(System.in);
		LocalDate localDateNow = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		
		switch (choice) {
		case 1:
			rm.viewAllVacantRoom(numGuest);
			while (true) {
				System.out.print("Enter Room No to book: ");
				String roomNo = sc.nextLine();
				if (rm.checkRoomEmpty(roomNo)) {
					r.setRoomNo(roomNo);
					break;
				} else {
					System.out.println("Error input!\n");
				}
			}
			break;

		case 2:
			int adultNo = errorCheckingInt("Enter number of adult: ");
			r.setAdultNo(adultNo);
			break;

		case 3:
			int kidNo = errorCheckingInt("Enter number of kids: ");	
			sc.nextLine();
			r.setKidNo(kidNo);
			break;

		case 4:
			while (true) {
				System.out.print("Enter date check-in (dd-MM-yyyy): ");
				String dateIn = sc.nextLine();

				try {
					LocalDate localDateIn = LocalDate.parse(dateIn, format);
					if (localDateIn.isBefore(localDateNow)) {
						System.out.println("Error input. Check in date must be after today!");
						continue;
					}
					System.out.println(format.format(localDateIn));
					r.setDateCheckIn(localDateIn);
					break;
				} catch (DateTimeParseException e) {
					System.out.println("Error input. dd-MM-yyyy = 01-02-2018");
				}
			}
			break;

		case 5:
			while (true) {
				System.out.print("Enter date check-out (dd-MM-yyyy): ");
				String dateOut = sc.nextLine();

				try {

					LocalDate localDateOut = LocalDate.parse(dateOut, format);
					if (localDateOut.isBefore(localDateNow)) {
						System.out.println("Error input. Check in date must be after today!");
						continue;
					}
					System.out.println(format.format(localDateOut));
					r.setDateCheckOut(localDateOut);
					break;
				} catch (DateTimeParseException e) {
					System.out.println("Error input. dd-MM-yyyy = 01-02-2018");
				}
			}

		case 6:
			System.out.printf("\n===>Please select from the following:\n");
			System.out.println("(1) " + rStatus[0]);
			System.out.println("(2) " + rStatus[1]);
			System.out.println("(3) " + rStatus[2]);
			System.out.println("(4) " + rStatus[3]);
			System.out.println("(5) Return");
			choice = errorCheckingInt("Select option: ", 5);
			sc.nextLine();

			if (choice != 5) {
				r.setResvStatus(rStatus[choice - 1]);
			} else {
				break;
			}
			break;

		case 7:
			System.out.println("Returning.....");
			break;
			
		default:
			System.out.println("Error input");
			break;

		}
	}

	public Reservation searchReservation(int resvNo) {
		Reservation resv = null;
		for (int i = 0; i < rList.size(); i++) {
			if (rList.get(i).getResvNo() == resvNo) {
				resv = rList.get(i);
				return resv;
			}
		}
		System.out.println("Reservation number does not exist!");
		return resv;
	}

	public boolean doesGuestHaveResv(int guestID){
		for (Reservation r : rList){
			if (r.getGuestID() == guestID && (r.getResvStatus().equals("CONFIRMED") || r.getResvStatus().equals("CHECKED-IN")))
				return true;
		}
		return false;
	}

	public void removeReservation(RoomMgr rm) {

		int rNo = 0;
		boolean flag = false;
		Scanner input = new Scanner(System.in);
		Iterator<Reservation> iter = rList.iterator();

		if (rList.size() < 1) {
			System.out.println("No Reservations to remove\n");
		} else {
			readReservationList();
			System.out.println("Enter the reservation no: ");
			rNo = input.nextInt();

			while (iter.hasNext()) {
				Reservation str = iter.next();

				if (str.getResvNo() == rNo && !flag) {
					//CHECK IF GUEST IN RESERVATION IS STILL CHECKED-IN
					if (str.getResvStatus() == rStatus[2]){
						System.out.println("Reservation number " + rNo + " cannot be removed as guest is still checked-in!");
					}
					else {
						iter.remove();
						rm.assignRoom(str.getRoomNo(),0); //Make room vacant
						numOfReservation--;
						System.out.println("Reservation number " + rNo + " removed!");
					}
					flag = true;
				}
			}
			if (!flag)
				System.out.println("Reservation no. does not exist!");
		}
	}
	public void refreshReservations(RoomMgr rm){
		int count=0;
		for (Reservation r : rList){
			if ((r.getResvStatus() == rStatus[0] || r.getResvStatus() == rStatus[1]) && r.getDateCheckIn().isBefore(LocalDate.now())){
				r.setResvStatus(rStatus[4]);
				rm.assignRoom(r.getRoomNo(),0);
				count++;
			}
		}
		if (count > 0)
			System.out.println(count + " Reservations expired!");
	}
	
	// ----------------------Other Section -----------------------//
	public List<Reservation> saveToFile() {
		return rList;
	}
	
	private int errorCheckingInt(String display) {

		int tempChoice;
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.print("\n" + display);
			try {
				tempChoice = input.nextInt();
				if(display.equals("Enter number of kids: ")) { //only for kids
					if(tempChoice < 0)
						throw new IllegalArgumentException("Error input\n");
				}
				else if (tempChoice < 1)
					throw new IllegalArgumentException("Error input\n");
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error input \n");
				input.next();
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			}
		}

		input.nextLine();

		return tempChoice;
	}

	private int errorCheckingInt(String display, int lastItem) {
		int index;
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.print("\n" + display);
			try {
				index = input.nextInt();
				if (index < 1 || index > lastItem)
					throw new IllegalArgumentException("Error input\n");
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error input\n");
				input.next();
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			}
		}

		input.nextLine();

		return index;
	}
}
