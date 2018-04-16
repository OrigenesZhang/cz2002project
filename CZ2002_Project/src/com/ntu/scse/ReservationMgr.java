package com.ntu.scse;

import javax.naming.spi.ResolveResult;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ReservationMgr {

	private List<Reservation> rList = null;
	private String[] rStatus = { "CONFIRMED", "IN WAITLIST", "CHECKED-IN", "CHECKED-OUT" };
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
			System.out.print("Enter the number of your choice: ");
			choice = sc.nextInt();

			switch (choice) {
				case 1: //(1) Create New RESERVATION
					createNewResv(gm, rm);
					break;

				case 2:	//(2) Update RESERVATION detail
					break;

				case 3: //(3) Search RESERVATION detail
					System.out.println("Enter Reservation No.: ");
					int resvNo = sc.nextInt();
					readReservation(searchReservation(resvNo));
					break;

				case 4: //(4) Remove RESERVATION detail
					removeReservation();
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
			Collections.sort(rList);
		} while (choice != 6);
	}

	public Reservation createNewResv(GuestMgr gm, RoomMgr rm) {
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
					System.out.println("(1) Guest ID");
					System.out.println("(2) Guest Name");
					ch = sc.nextInt();
					if (ch == 1){
						System.out.println("Enter guest ID: ");
						ch = sc.nextInt();
						if (gm.checkGuestExist(ch)) {
							guest = gm.readGuestInfo(ch);
							valid = true;
						}
						else
							System.out.println("Guest does not exist!");
					}
					else if (ch == 2){
						sc.nextLine();
						System.out.printf("Enter guest first name: ");
						String fName = sc.nextLine();
						System.out.printf("Enter guest last name: ");
						String lName = sc.nextLine();
						ch = gm.getIDfromName(lName,fName);
						if (gm.checkGuestExist(ch)) {
							guest = gm.readGuestInfo(ch);
							valid = true;
						}
						else
							System.out.println("Guest does not exist!");
					}
					else{
						System.out.println("Invalid Input!");
						continue;
					}
				}
			} else if (ch == 3)
				return null;
			else
				System.out.println("Invalid Input!");
		} while (!valid);
		System.out.print("Enter number of adults: ");
		adultNo = sc.nextInt();
		System.out.print("Enter number of kids: ");
		kidNo = sc.nextInt();
		sc.nextLine();
		rm.viewAllVacantRoom();
		while (true) {
			System.out.print("Enter Room No to book: ");
			roomNo = sc.nextLine();
			if (rm.checkRoomEmpty(roomNo)) {
				break;
			} else {
				System.out.println("Error input!\n");
			}
		}

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
		resv = addResv(roomNo, guest.getGuestID(), adultNo, kidNo, localDateIn, localDateOut, rStatus[0],
				resvTime);
		rm.assignRoom(roomNo,2);
		return resv;
	}

	public Reservation addResv(String roomNo, int gID, int aNo, int kNo, LocalDate dIn, LocalDate dOut,
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
			newResv = new Reservation(newResvNo, roomNo, gID, aNo, kNo, dIn, dOut, rStatus, time);
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

		if (rList.size() < 1) {
			System.out.println("No reservations available!");
		} else{
			System.out.println("\nDisplaying Reservation List: \n");
			System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", "Reservation No.", "Guest ID", "Num Adult",
					"Num Kid", "Room No.", "Reservation Status", "Check in", "Check out", "Reservation time");

			for (Reservation r : rList) {
				System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", r.getResvNo(), r.getGuestID(),
						r.getAdultNo(), r.getKidNo(), r.getRoomNo(), r.getResvStatus(), r.getDateCheckIn(), r.getDateCheckOut(),
						r.getResvTime());
			}
		}
	}
	public int readReservationListByStatus(int choice) {
		//"CONFIRMED", "IN WAITLIST", "CHECKED-IN", "CHECKED-OUT"
		int count = 0;
		for (Reservation r : rList)
			if (r.getResvStatus().equals(rStatus[choice - 1]))
				count++;
		if (count != 0) {
			System.out.println("\nDisplaying " + rStatus[choice - 1] + " reservation List: \n");
			System.out.format("%-15s%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", "Reservation No.", "Guest ID", "Num Adult",
					"Num Kid", "Room No.", "Reservation Status", "Check in", "Check out", "Reservation time");
			for (Reservation r : rList) {
				if (r.getResvStatus().equals(rStatus[choice - 1])) {
					System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", r.getResvNo(), r.getGuestID(),
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
			System.out.format("%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", "Guest ID", "Num Adult",
					"Num Kid", "Room No.", "Reservation Status", "Check in", "Check out", "Reservation time");

				System.out.format("%-15d%-15s%-15s%-15s%-20s%-35s%-25s%-25s%-15s\n", r.getGuestID(),
						r.getAdultNo(), r.getKidNo(), r.getRoomNo(), r.getResvStatus(), r.getDateCheckIn(), r.getDateCheckOut(),
						r.getResvTime());
		}
	}

	public void updateResv(RoomMgr rm) {
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
					updateResv(rm, r, ch);
				} while (ch > 0 && ch < 9);
				break;
			}
		}
	}

	public void updateResv(RoomMgr rm, Reservation r, int choice) {

		Scanner sc = new Scanner(System.in);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		switch (choice) {
		case 1:
			rm.viewAllVacantRoom();
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
			System.out.print("Enter number of adult: ");
			int adultNo = sc.nextInt();
			r.setAdultNo(adultNo);
			break;

		case 3:
			System.out.print("Enter number of kids: ");
			int kidNo = sc.nextInt();
			sc.nextLine();
			r.setKidNo(kidNo);
			break;

		case 4:
			while (true) {
				System.out.print("Enter date check-in (dd-MM-yyyy): ");
				String dateIn = sc.nextLine();

				try {
					LocalDate localDateIn = LocalDate.parse(dateIn, format);
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
			choice = sc.nextInt();
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
			System.out.println("Eror input");
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

	public void removeReservation() {

		int rNo = 0;
		boolean flag = false;
		Scanner input = new Scanner(System.in);
		Iterator<Reservation> iter = rList.iterator();

		if (rList.size() < 1) {
			System.out.println("No Reservations to remove\n");
		} else {
			System.out.println("Enter the reservation no: ");
			rNo = input.nextInt();

			while (iter.hasNext()) {
				Reservation str = iter.next();

				if (str.getResvNo() == rNo && !flag) {
					iter.remove();
					System.out.println("Reservation number " + rNo + " removed!");
					flag = true;
				}
			}

			if (!flag)
				System.out.println("Reservation no. does not exist!");
		}
	}

	public List<Reservation> saveToFile() {
		return rList;
	}

}
