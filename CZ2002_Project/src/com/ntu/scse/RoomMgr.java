package com.ntu.scse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RoomMgr implements Serializable {

	private List<Room> room;
	private String[] roomType = { "Single", "Double", "Deluxe", "VIP Suite" };
	private String[] bedType = { "Single", "Double" };
	private String[] facing = { "North", "South", "East", "West" };
	private String[] status = { "Vacant", "Occupied", "Reserved", "Under Maintenance" };
	private boolean[] trueFalse = { true, false };

	public RoomMgr(ArrayList al) {
		if (al == null) {
			this.room = new ArrayList<>();
			initializeRoom();
			System.out.println("Room data initialized!");
		} else {
			this.room = al;
			System.out.println("Room data loaded!");
		}
	}

	public void ShowRoomMgrMenuOption() {
		int choice;
		String name, roomNo;

		Scanner input = new Scanner(System.in);

		do {
			System.out.println("\nRoom option: ");
			System.out.println("(1) View Room");
			System.out.println("(2) Update Room Details");
			System.out.println("(3) View Room Statistic Report");
			System.out.println("(4) Return to main");
			choice = errorCheckingInt("Select an option: ", 4);

			switch (choice) {
			case 1:
				System.out.println("\nView Room Option: ");
				System.out.println("(1) View All Room");
				System.out.println("(2) View selected room details");
				System.out.println("(3) View all room by status");
				System.out.println("(4) Return to room option");
				choice = errorCheckingInt("Select an option: ", 4);

				switch (choice) {
				case 1:
					viewAllRoom();
					break;
				case 2:
					viewSelectedRoom();
					break;
				case 3:
					System.out.println("\nView all Room by status option:");
					System.out.println("(1) Vacant");
					System.out.println("(2) Occupied");
					System.out.println("(3) Reserved");
					System.out.println("(4) Under Maintenance");
					System.out.println("(5) Exit");
					choice = errorCheckingInt("Select an option: ", 5);

					if (choice != 5)
						viewAllRoomByStatus(choice);
					break;
				case 4:
					break;
				default:
					System.out.println("Error input");
					break;
				}

				break; // case 1 break

			case 2:
				updateRoom();
				break;

			case 3:
				RoomStatusStatic();
				break;

			case 4:
				System.out.println("\nReturning to main.....\n");
				break;

			default:
				System.out.println("\nError input \n");
				break;

			}

		} while (choice != 4);

	}

	// ---------------------------------------------------------------------------------------------------------------------------//

	public void viewAllRoom() {

		System.out.println("\nViewing all Room: \n");
		System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15s%-15s\n", "Room no.", "Room Type", "Bed Type",
				"Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
		for (Room r : room) {

			System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15.2f%-15s\n", r.getRoomNO(), r.getRoomType(),
					r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(), r.getRoomStatus(), r.getRoomRate(),
					r.getGuest());
		}

		System.out.println("");
	}

	public void viewSelectedRoom() {

		boolean flag = false;
		boolean displayOnce = false;
		String roomNo = null, name = null;
		int choice;

		Scanner input = new Scanner(System.in);

		System.out.println("\nView selected room option:");
		System.out.println("(1) Enter room no");
		System.out.println("(2) Enter Guest");
		System.out.print("(3) Exit");
		choice = errorCheckingInt("Select an option: ", 3);

		if (choice == 1) {
			System.out.print("\nEnter Room no. : ");
			roomNo = input.nextLine();
		} else if (choice == 2) {
			System.out.print("\nEnter first name: ");
			name = input.nextLine();
			System.out.print("Enter last name: ");
			name = name + " " + input.nextLine();
		}
		if (choice != 3) {
			for (Room r : room) {
				if (r.getRoomNO().equals(roomNo) || r.getGuest().equals(name)) {

					if (!displayOnce) {
						System.out.println("\nViewing Selected Room");
						System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Room no.", "Room Type",
								"Bed Type", "Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
						displayOnce = true;
					}
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15.2f%-15s\n", r.getRoomNO(),
							r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
							r.getRoomStatus(), r.getRoomRate(), r.getGuest());

					System.out.println("");
					flag = true;
					break;
				}
			}
			if (!flag)
				System.out.println("\nInvalid input - result not found\n");
		}

	}

	public void updateRoom() {
		int choice = 0;
		double roomRate = 0;
		String roomNo = null;
		boolean flag;

		Scanner input = new Scanner(System.in);

		System.out.println("\nUpdate Menu");
		System.out.println("(1) Room Type");
		System.out.println("(2) Room Rate");
		System.out.println("(3) Status");
		System.out.println("(4) Smoking/WIFI");
		System.out.println("(5) Return to main");
		System.out.print("Select option: ");
		choice = input.nextInt();
		input.nextLine();

		switch (choice) {
		case 1:
			flag = false;
			while (!flag) {
				System.out.print("\nEnter room no: ");
				roomNo = input.nextLine();

				for (Room r : room) {
					if (r.getRoomNO().equals(roomNo)) {
						System.out.println("\nAvailable Room Type");
						System.out.println("(1) " + roomType[0]);
						System.out.println("(2) " + roomType[1]);
						System.out.println("(3) " + roomType[2]);
						System.out.println("(4) " + roomType[3]);
						System.out.print("Select option: ");
						choice = input.nextInt();
						r.setRoomType(roomType[choice - 1]);
						flag = true;
						break;
					}
				}

				if (!flag)
					System.out.println("Invalid room no\n");
			}
			break;

		case 2:
			System.out.println("\nRoom rate type ");
			System.out.println("(1) " + roomType[0]);
			System.out.println("(2) " + roomType[1]);
			System.out.println("(3) " + roomType[2]);
			System.out.println("(4) " + roomType[3]);
			System.out.print("Select option to edit: ");
			choice = input.nextInt();
			input.nextLine();
			String rType = roomType[choice - 1];

			System.out.print("Enter new room rate: ");
			roomRate = input.nextDouble();

			for (Room r : room) {
				if (r.getRoomType().equals(rType)) {
					r.setRoomRate(roomRate);
				}
			}
			break;

		case 3:
			flag = false;
			while (!flag) {
				System.out.print("\nEnter room no: ");
				roomNo = input.nextLine();

				for (Room r : room) {
					if (r.getRoomNO().equals(roomNo)) {
						if (r.getRoomStatus().equals(status[0]) || r.getRoomStatus().equals(status[3])) {
							System.out.println("\nStatus Menu");
							System.out.println("(1) " + status[0]);
							System.out.println("(2) " + status[1]);
							System.out.println("(3) " + status[2]);
							System.out.println("(4) " + status[3]);
							System.out.print("Select option: ");
							choice = input.nextInt();
							r.setRoomStatus(status[choice - 1]);
							flag = true;
						}
					}
				}

				if (!flag)
					System.out.println("Invalid room no");
			}
			System.out.println("");
			break;

		case 4:
			flag = false;
			while (!flag) {
				System.out.print("\nEnter room no: ");
				roomNo = input.nextLine();

				for (Room r : room) {
					if (r.getRoomNO().equals(roomNo)) {
						if (r.getRoomStatus().equals(status[0]) || r.getRoomStatus().equals(status[3])) {
							System.out.println("\nEnabled WIFI?");
							System.out.println("(1) true");
							System.out.println("(2) false");

							while (true) {
								try {
									System.out.print("Select option: ");
									choice = input.nextInt();
									if (choice < 1 || choice > 2)
										throw new IllegalArgumentException("Error input\n");
									else {
										r.setWIFI(trueFalse[choice - 1]);
										System.out.println("\nAllow smoking?");
										System.out.println("(1) true");
										System.out.println("(2) false");

										while (true) {
											try {
												System.out.print("Select option: ");
												choice = input.nextInt();
												if (choice < 1 || choice > 2)
													throw new IllegalArgumentException("Error input\n");
												else
													r.setWIFI(trueFalse[choice - 1]);
												break;

											} catch (InputMismatchException e) {
												System.out.println("Error input \n");
												input.next();
											} catch (IllegalArgumentException e) {
												System.out.println(e);
											}
										}

										break;
									}
								} catch (InputMismatchException e) {
									System.out.println("Error input \n");
									input.next();
								} catch (IllegalArgumentException e) {
									System.out.println(e);
								}
							}
							flag = true;
						}
					}
				}

				if (!flag)
					System.out.println("Invalid room no");
			}

			break;

		case 5:
			System.out.println("\nReturning to main....\n");
			break;

		default:
			System.out.println("Error input\n");
			break;
		}
	}

	public void RoomStatusStatic() {

		int lastIndex = 0;
		int choice = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("\nRoom Status Statistic Option: ");
		System.out.println("(1) Room type occupancy rate");
		System.out.println("(2) Room Status");
		System.out.print("Select an option: ");
		choice = input.nextInt();

		if (choice == 1) {

			int[] numRoom = new int[4];
			int[] numVacantRoom = new int[4];

			for (int i = 0; i < 4; i++) {
				numRoom[i] = getNumTypeRoom(roomType[i]);
				numVacantRoom[i] = getNumStatusByRoomType(roomType[i], status[0]);
			}

			for (int i = 0; i < 4; i++) {
				lastIndex = numVacantRoom[i];
				System.out.println("\n" + roomType[i] + ": " + numVacantRoom[i] + " Vacant rooms out of " + numRoom[i]);
				System.out.print("Vacant Rooms: ");

				if (lastIndex > 1) {
					for (Room r : room) {

						if (r.getRoomType().equals(roomType[i]) && r.getRoomStatus().equals(status[0])) {
							if (lastIndex > 1)
								System.out.print(r.getRoomNO() + ", ");
							else
								System.out.print(r.getRoomNO() + "\n");

							lastIndex -= 1;
						}

					}
				} else
					System.out.print(" - \n");

			}

		} else if (choice == 2) {
			int[] numStatusRoom = new int[4];

			for (int i = 0; i < 4; i++) {
				numStatusRoom[i] = getNumStatusRoom(status[i]);
			}

			for (int i = 0; i < 4; i++) {
				lastIndex = numStatusRoom[i];
				System.out.println("\n" + status[i] + " :");
				System.out.print("Rooms: ");

				if (lastIndex > 1) {
					for (Room r : room) {

						for (int j = 0; j < 4; j++) {
							if (r.getRoomType().equals(roomType[j]) && r.getRoomStatus().equals(status[i])) {
								if (lastIndex > 1)
									System.out.print(r.getRoomNO() + ", ");
								else
									System.out.print(r.getRoomNO() + "\n");

								lastIndex -= 1;
							}
						}

					}
				}

				else
					System.out.println(" - \n");
			}
		} else
			System.out.println("Invalid Choice!");

		System.out.println();
	}

	public int viewAllRoomByStatus(int choice) {
		int count = 0;
		for (Room r : room)
			if (r.getRoomStatus().equals(status[choice - 1]))
				count++;

		if (count != 0) {
			System.out.println("\nViewing " + status[choice - 1] + " Room: \n");
			System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15s%-15s\n", "Room no.", "Room Type", "Bed Type",
					"Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
			for (Room r : room) {
				if (r.getRoomStatus().equals(status[choice - 1])) {
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15.2f%-15s\n", r.getRoomNO(),
							r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
							r.getRoomStatus(), r.getRoomRate(), r.getGuest());
				}
			}
		} else
			System.out.println("No " + status[choice - 1] + " Rooms");
		return count;
	}

	public void viewAllVacantRoom(int numGuest) {
		
		System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15s%-15s\n", "Room no.", "Room Type", "Bed Type",
				"Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
		
		for (Room r : room) {
			if(numGuest > 1)
			{
				//if guest more than 1, cannot book Single room
				if (!r.getRoomType().equals(roomType[0]) && r.getRoomStatus().equals(status[0]))
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15.2f%-15s\n", r.getRoomNO(),
							r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
							r.getRoomStatus(), r.getRoomRate(), r.getGuest());
					
			} else
			{
				if (r.getRoomStatus().equals(status[0]))
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15.2f%-15s\n", r.getRoomNO(),
							r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
							r.getRoomStatus(), r.getRoomRate(), r.getGuest());
			}
			
		}
		/*
		 * int numVacant = 0;
		if (numVacant == 0)
			System.out.println("Hotel fully occupied!");
		else {
			viewAllRoomByStatus(1);
		}*/
	}
	

	public boolean checkValidRoomForOrder(String roomNo) {
		boolean result = false;

		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				if (r.getRoomStatus().equals(status[1])) // if room is occupied then can order
					result = true;
			}
		}

		return result;
	}

	public boolean checkRoomEmpty(String roomNo) {
		boolean result = false;

		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				if (r.getRoomStatus().equals(status[0])) // if room is vacant
					return true;
				else
					return false;
			}
		}
		System.out.println("Room Number does not exist!");
		return false;
	}

	public void assignRoom(String roomNo, int status, String name) {
		// "Vacant", "Occupied", "Reserved", "Under Maintenance"
		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				if (r.getRoomStatus().equals(this.status[status])) {
					System.out.println("Room is already " + this.status[status]);
					return;
				} else {
					r.setRoomStatus(this.status[status]);
					r.setGuest(name);
					System.out.println("Room " + roomNo + " has been set to " + this.status[status]);
					return;
				}
			}
		}
		System.out.println("Room does not exist!");
	}

	public void assignRoom(String roomNo, int status) {
		// "Vacant", "Occupied", "Reserved", "Under Maintenance"
		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				if (r.getRoomStatus().equals(this.status[status])) {
					System.out.println("Room is already " + this.status[status]);
					return;
				} else {
					r.setRoomStatus(this.status[status]);
					System.out.println("Room " + roomNo + " has been set to " + this.status[status]);
					return;
				}
			}
		}
		System.out.println("Room does not exist!");
	}

	public void checkIn(ReservationMgr resvMgr, GuestMgr guestMgr) {
		Scanner sc = new Scanner(System.in);
		Reservation resv;
		int choice, resvNo, numResv;
		do {
			System.out.println("(1) Walk-in");
			System.out.println("(2) Reservation");
			System.out.println("(3) Back");
			choice = sc.nextInt();

			if (choice == 1) { // WALK IN
				resv = resvMgr.createNewResv(guestMgr, this);
				if (resv == null) { // failed
					System.out.println("Operation aborted!");
				} else { // succeed
					resv.setResvStatus("CHECKED-IN");
					assignRoom(resv.getRoomNo(), 1); // change room to occupied
					System.out.println(guestMgr.getNamefromID(resv.getGuestID()) + " has been checked into room "
							+ resv.getRoomNo());
				}

			} else if (choice == 2) { // RESERVATION
				numResv = resvMgr.readReservationListByStatus(1); // show all confirmed resv
				if (numResv != 0) {
					System.out.println("Enter reservation no. to check in:");
					resvNo = sc.nextInt();
					resv = resvMgr.searchReservation(resvNo);
					if (resv != null) {
						if (resv.getResvStatus().equals("CONFIRMED")) {
							assignRoom(resv.getRoomNo(), 1); // change room to occupied
							resv.setResvStatus("CHECKED-IN");
							System.out.println(guestMgr.getNamefromID(resv.getGuestID())
									+ " has been checked into room " + resv.getRoomNo());
						} else
							System.out.println("Please enter a valid confirmed reservation no.!");
					}
				}
			} else if (choice == 3) // BACK
				break;
			else
				System.out.println("Invalid Input!");
		} while (choice != 3);
	}

	public void checkOut(ReservationMgr resvMgr, GuestMgr guestMgr) {
		int resvNo, numResv;
		Scanner sc = new Scanner(System.in);
		Reservation resv;
		numResv = resvMgr.readReservationListByStatus(3); // show all checked-in resv
		if (numResv != 0) {
			System.out.println("Enter reservation no. to check out:");
			resvNo = sc.nextInt();
			resv = resvMgr.searchReservation(resvNo);

			if (resv != null) {
				if (resv.getResvStatus().equals("CHECKED-IN")) {
					assignRoom(resv.getRoomNo(), 0); // set room to vacant
					resv.setResvStatus("CHECKED-OUT");
					System.out.println(guestMgr.getNamefromID(resv.getGuestID()) + " has been checked out of room "
							+ resv.getRoomNo());
				} else
					System.out.println("Please enter a valid checked-in reservation no.!");
			}
		}
	}

	private int getNumTypeRoom(String roomType) {
		int numRoom = 0;
		for (Room r : room) {
			if (r.getRoomType().equals(roomType)) {
				numRoom += 1;
			}
		}

		return numRoom;
	}

	private int getNumStatusByRoomType(String roomType, String roomStatus) {
		int numRoom = 0;
		for (Room r : room) {
			if (r.getRoomType().equals(roomType) && r.getRoomStatus().equals(roomStatus)) {
				numRoom += 1;
			}
		}

		return numRoom;
	}

	private int getNumStatusRoom(String roomStatus) {
		int numRoom = 0;
		for (Room r : room) {
			if (r.getRoomStatus().equals(roomStatus)) {
				numRoom += 1;
			}
		}

		return numRoom;
	}

	private void initializeRoom() {

		int tempBed, tempStatus;
		boolean isSmoking, isWIFI;
		String tempRoomType = null, tempRoomNo = null;

		for (int i = 2; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				tempRoomNo = "0" + i + "0" + j;
				tempRoomType = roomType[randomNumber(4)];
				isSmoking = trueFalse[randomNumber(2)];
				isWIFI = trueFalse[randomNumber(2)];

				if (tempRoomType.equals("Single"))
					tempBed = 0;
				else if (tempRoomType.equals("Deluxe") || tempRoomType.equals("VIP Suite")) {
					tempBed = 1;
					isSmoking = true;
					isWIFI = true;
				} else
					tempBed = 1;

				tempStatus = randomNumber(4);
				if (tempStatus != 3)
					tempStatus = 0;
				else
					tempStatus = 3;
				room.add(new Room(tempRoomNo, tempRoomType, bedType[tempBed], facing[randomNumber(4)],
						status[tempStatus], isWIFI, isSmoking));
			}
		}
	}

	// ----------------------Other Section -----------------------//

	private int randomNumber(int max) {
		Random rand = new Random();
		int n = rand.nextInt(max);
		return n;
	}

	public List<Room> saveToFile() {
		return room;
	}

	private int errorCheckingInt(String display) {

		int tempChoice;
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.print("\n" + display);
			try {
				tempChoice = input.nextInt();
				if (tempChoice < 1)
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

	private float errorCheckingFloat(String display) {
		float price;
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.print(display);
			try {
				price = input.nextFloat();
				if (price <= 0f)
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

		return price;
	}
}
