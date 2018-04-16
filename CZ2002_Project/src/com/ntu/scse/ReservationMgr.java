package com.ntu.scse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReservationMgr {

	private List<Reservation> rList = null;
	private String[] rStatus = { "CONFIRMED", "IN WAITLIST", "CHECKED-IN", "EXPIRED" };
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
			case 1:
				createNewResv(gm, rm);
				break;

			}

		} while (choice != 5);
	}

	public void createNewResv(GuestMgr gm, RoomMgr rm) {
		int adultNo, kidNo, dayNo;
		String roomNo, dateIn, dateOut;
		LocalDate localDateIn, localDateOut;
		LocalTime resvTime;

		Scanner sc = new Scanner(System.in);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		gm.createNewGuest();
		System.out.print("Enter number of adult: ");
		adultNo = sc.nextInt();
		System.out.print("Enter number of kids: ");
		kidNo = sc.nextInt();
		sc.nextLine();
		rm.viewResvVacantRoom(kidNo + adultNo);
		while (true) {
			System.out.print("Enter Room No to book: ");
			roomNo = sc.nextLine();
			if (rm.checkRoomExists(roomNo)) {
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
				System.out.println(format.format(localDateOut));
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Error input. dd-MM-yyyy = 01-02-2018");
			}
		}

		resvTime = LocalTime.now();
		addResv(setNewResvNo(), roomNo, gm.getGuestID(), adultNo, kidNo, localDateIn, localDateOut, rStatus[0],
				resvTime);

	}

	public void addResv(int rNo, String roomNo, int gID, int aNo, int kNo, LocalDate dIn, LocalDate dOut,
			String rStatus, LocalTime time) {

		rList.add(new Reservation(rNo, roomNo, gID, aNo, kNo, dIn, dOut, rStatus, time));
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
			rm.viewResvVacantRoom(r.getAdultNo() + r.getKidNo());
			while (true) {
				System.out.print("Enter Room No to book: ");
				String roomNo = sc.nextLine();
				if (rm.checkRoomExists(roomNo)) {
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
			System.out.println("No Reservation\n");
		} else {
			System.out.println("Enter the reservation no: ");
			rNo = input.nextInt();

			while (iter.hasNext()) {
				Reservation str = iter.next();

				if (str.getResvNo() == rNo && !flag) {
					iter.remove();
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

	private int setNewResvNo() {

		int id = 10000;

		try {
			Reservation r = rList.get(rList.size() - 1);
			if (rList.size() < 1)
				throw new IndexOutOfBoundsException();

			return r.getGuestID() + 1;
		} catch (IndexOutOfBoundsException e) {
			return id;
		}
	}
}
