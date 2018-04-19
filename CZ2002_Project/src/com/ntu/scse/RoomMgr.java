package com.ntu.scse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 Represents the hotel's Room Manager that contains a list of all rooms and the 
 methods for manipulating room facilities. One Room Manager contains an entire list of room

 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
*/

public class RoomMgr implements Serializable {


	/**
	 * An array of list of room
	 */
	private List<Room> room;
	
	/**
	 * An array of String for roomType 
	 */
	private String[] roomType = { "Single", "Double", "Deluxe", "VIP Suite" };
	
	/**
	 * An array of String for bedType 
	 */
	private String[] bedType = { "Single", "Double" };
	
	/**
	 * An array of String for room facing view 
	 */
	private String[] facing = { "North", "South", "East", "West" };
	
	/**
	 * An array of String for room status 
	 */
	private String[] status = { "Vacant", "Occupied", "Reserved", "Under Maintenance" };
	
	/**
	 * An array of boolean for setting true false option 
	 */
	private boolean[] trueFalse = { true, false };
	
	
	/**
	 * Create a new Room Manager with a list of rooms
	 * The constructor will check for existing list data
	 * @param al The list received from Main. If there was previous data, use this, else, create an empty list.
	 */
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

	/**
	 * Display all the available Room option for client
	 * 1) View Room allow client to view all room, by status, or selected room
	 * 2) Update Room Details allow client to make changes to room 
	 * 3) View Room Statistic Report allow client to view all statistic by room status or room type
	 * 4) Return to main, return client to main screen
	 * @param gm from Guest Manager class
	 */
	public void ShowRoomMgrMenuOption(GuestMgr gm) {
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
				System.out.println("(2) View all room by status");
				System.out.println("(3) View selected room details");
				System.out.println("(4) Return to room option");
				choice = errorCheckingInt("Select an option: ", 4);

				switch (choice) {
				case 1:
					viewAllRoom();
					break;
				case 2:
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
				case 3:
					viewSelectedRoom(gm);
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
				RoomStatusStatistic();
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


	/**
	 * Display all room and details
	 */
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

	/**
	 * View selected room based on room no or guest name
	 * @param gm from Guest Manager class
	 */
	public void viewSelectedRoom(GuestMgr gm) {

		boolean flag = false;
		String roomNo = null, name = null, firstName;
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
			Guest g;
			System.out.print("\nEnter guest first name: ");
			firstName = input.nextLine();
			g = gm.searchGuest(firstName);
			name = g.getFirstName() + " " + g.getLastName();
		}
		if (choice != 3) {
			for (Room r : room) {
				if (r.getRoomNO().equals(roomNo) || r.getGuest().equals(name)) {
					System.out.println("\nViewing Selected Room");
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Room no.", "Room Type",
								"Bed Type", "Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15.2f%-15s\n", r.getRoomNO(),
							r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
							r.getRoomStatus(), r.getRoomRate(), r.getGuest());
					System.out.println();
					flag = true;
					break;
				}
			}
			if (!flag)
				System.out.println("\nInvalid input - result not found\n");
		}

	}

	/**
	 * Options to update room 
	 * 1) Room type allow client to change the room type
	 * 2) Room rate allow client to change the default room rate
	 * 3) Status allow client to change the current room status
	 * 4) Smoking/WIFI allow client to set whether room is allow for smoking/WIFI
	 * 5) Return to main 
	 */
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
						System.out.print("Room type updated!");
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
					System.out.print("Room rate updated!");
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
							System.out.print("Room status updated!");
							flag = true;
						}
					}
				}

				if (!flag)
					System.out.println("Invalid room no");
			}
			System.out.println();
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
										System.out.print("Room WIFI availability updated!");
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
													r.setSmoking(trueFalse[choice - 1]);
													System.out.print("Room smoking permission updated!");
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

	/**
	 * Display Room Status Statistic by room type occupancy or by room status
	 */
	public void RoomStatusStatistic() {

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

	
	/**
	 * View all Room by status "Vacant", "Occupied", "Reserved", "Under Maintenance"
	 * @param choice the option to view by selected room status
	 */
	public void viewAllRoomByStatus(int choice) {
		boolean flag = false;
		for (Room r : room) //Check at least 1 of selected room type
			if (r.getRoomStatus().equals(status[choice - 1])){
                flag = true;
                break;
            }

		if (flag) {
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
	}

	/**
	 * Display all vacant room for reservation to select
	 * Based on the number of Guest, certain room will be displayed. 
	 * @param numGuest the total number of guest (adult+kids)
	 */
	public void viewAllVacantRoom(int numGuest) {
        boolean flag = false;
        for (Room r : room) { //Check at least 1 suitable room
            if (numGuest > 1) {
                //if guest more than 1, cannot book Single room
                if (!r.getRoomType().equals(roomType[0]) && r.getRoomStatus().equals(status[0])) {
					flag = true;
					break;
				}
            } else {
                if (r.getRoomStatus().equals(status[0])) {
					flag = true;
					break;
				}
            }
        }

        if (flag && numGuest > 1) {
            System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15s%-15s\n", "Room no.", "Room Type", "Bed Type",
                    "Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
            for (Room r : room)
            if (!r.getRoomType().equals(roomType[0]) && r.getRoomStatus().equals(status[0]))
                System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15.2f%-15s\n", r.getRoomNO(),
                        r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
                        r.getRoomStatus(), r.getRoomRate(), r.getGuest());

        }else if (flag && numGuest == 1){
            System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15s%-15s\n", "Room no.", "Room Type", "Bed Type",
                    "Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
            for (Room r : room)
            if (r.getRoomStatus().equals(status[0]))
                System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-25s%-15.2f%-15s\n", r.getRoomNO(),
                        r.getRoomType(), r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(),
                        r.getRoomStatus(), r.getRoomRate(), r.getGuest());
        }else{
            System.out.println("No suitable rooms are vacant!");
        }
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

	public String getRoomTypeFromNum(String roomNo){
		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				return r.getRoomType();
			}
		}
		return null;
	}

	private double getRoomRateFromNum(String roomNo){
		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				return r.getRoomRate();
			}
		}
		return 0;
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

	public void checkIn(ReservationMgr resvMgr, GuestMgr guestMgr, BillMgr bm) {
		//TODO CREATE BILL
		Scanner sc = new Scanner(System.in);
		Reservation resv;
		int choice, resvNo, numResv;
		do {
			System.out.println("(1) Walk-in");
			System.out.println("(2) Reservation");
			System.out.println("(3) Back");
			choice = sc.nextInt();

			if (choice == 1) { // WALK IN
				resv = resvMgr.createNewResv(guestMgr, this, true);
				if (resv == null) { // failed
					System.out.println("Operation aborted!");
				} else { // succeed
					resv.setResvStatus("CHECKED-IN");
					assignRoom(resv.getRoomNo(), 1, guestMgr.getNamefromID(resv.getGuestID())); // change room to occupied
					System.out.println(guestMgr.getNamefromID(resv.getGuestID()) + " has been checked into room "
							+ resv.getRoomNo());
					bm.addBill(resv.getResvNo(), resv.getRoomDays(),resv.roomType, resv.getRoomNo(), getRoomRateFromNum(resv.getRoomNo()),0);
				}

			} else if (choice == 2) { // RESERVATION
				numResv = resvMgr.readReservationListByStatus(1); // show all confirmed resv
				if (numResv != 0) {
					System.out.println("Enter reservation no. to check in:");
					resvNo = sc.nextInt();
					resv = resvMgr.searchReservation(resvNo);
					if (resv != null) {
						if (resv.getResvStatus().equals("CONFIRMED")) {
							assignRoom(resv.getRoomNo(), 1, guestMgr.getNamefromID(resv.getGuestID())); // change room to occupied
							resv.setResvStatus("CHECKED-IN");
							System.out.println(guestMgr.getNamefromID(resv.getGuestID())
									+ " has been checked into room " + resv.getRoomNo());
							bm.addBill(resv.getResvNo(), resv.getRoomDays(),resv.roomType, resv.getRoomNo(), getRoomRateFromNum(resv.getRoomNo()),0);
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

	public void checkOut(ReservationMgr resvMgr, GuestMgr guestMgr, BillMgr bm, RoomService rs, int paymentMethod) {
		int resvNo, numResv, choice;
		Scanner sc = new Scanner(System.in);
		Reservation resv;
		numResv = resvMgr.readReservationListByStatus(3); // show all checked-in resv
		if (numResv != 0) {
			System.out.println("Enter reservation no. to check out:");
			resvNo = sc.nextInt();
			resv = resvMgr.searchReservation(resvNo);

			if (resv != null) {
				if (resv.getResvStatus().equals("CHECKED-IN")) {
					do {
						System.out.println("Payment via: ");
						System.out.println("(1) Cash");
						System.out.println("(2) Credit Card");
						System.out.println("(3) Back");
						choice = sc.nextInt();
					} while (choice < 1 && choice > 3);
					if (choice != 3) {
						bm.printBill(resvNo, choice);
						assignRoom(resv.getRoomNo(), 0); // set room to vacant
						//REMOVE ALL ORDERS FROM ROOM
						rs.removeAllOrderFromRoom(resv.getRoomNo());
						resv.setResvStatus("CHECKED-OUT");
						System.out.println(guestMgr.getNamefromID(resv.getGuestID()) + " has been checked out of room "
								+ resv.getRoomNo());
					}
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
