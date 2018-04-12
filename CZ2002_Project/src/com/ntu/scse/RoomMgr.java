package com.ntu.scse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RoomMgr implements Serializable{
	
	private List<Room> room;
	private String[] roomType = { "Single", "Double", "Deluxe", "VIP Suit" };
	private String[] bedType = { "Single", "Double" };
	private String[] facing = { "North", "South", "East", "West" };
	private String[] status = { "Vacant", "Occupied", "Reserved", "Under Maintenace" };
	private boolean[] trueFalse = { true, false };

	public RoomMgr() {
		room = new ArrayList<Room>();
		initalizeRoom();
	}

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

	public void viewSelectedRoom(int choice, String value) {
		boolean flag = false;
		boolean once = false;

		for (Room r : room) {
			if (r.getRoomNO().equals(value) || r.getGuest().equals(value)) {

				if (!once) {
					System.out.println("\nViewing Selected Room");
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Room no.", "Room Type",
							"Bed Type", "Room Facing", "WIFI", "Smoking", "Status", "Room Rate", "Guest");
					once = true;
				}
				System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15.2f%-15s\n", r.getRoomNO(), r.getRoomType(),
						r.getBedType(), r.getFacing(), r.isWIFI(), r.isSmoking(), r.getRoomStatus(), r.getRoomRate(),
						r.getGuest());

				System.out.println("");
				flag = true;
				break;
			}
		}

		if (!flag)
			System.out.println("\nInvalid input - result not found\n");
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

	public void RoomStatusStatic(int choice) {

		int lastIndex = 0;

		if (choice == 1) {

			int[] numRoom = new int[4];
			int[] numVacantRoom = new int[4];

			for (int i = 0; i < 4; i++) {
				numRoom[i] = getNumTypeRoom(roomType[i]);
				numVacantRoom[i] = getNumStatusByRoomType(roomType[i], status[0]);
			}

			for (int i = 0; i < 4; i++) {
				lastIndex = numVacantRoom[i];
				System.out.println("\n" + roomType[i] + ": Number: " + numVacantRoom[i] + " out of " + numRoom[i]);
				System.out.print("Rooms: ");

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

		} else {
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
		}

		System.out.println("");
	}

	public boolean checkValidRoomForOrder(String roomNo) {
		boolean result = false;

		for (Room r : room) {
			if (r.getRoomNO().equals(roomNo)) {
				if (r.getRoomStatus().equals(status[1])) // if room is occupied then can order
					result = true;
				else
					System.out.println("Room not available for odering");
			}
		}

		return result;
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

	private void initalizeRoom() {
		
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
				else if (tempRoomType.equals("Deluxe") || tempRoomType.equals("VIP Suit")) {
					tempBed = 1;
					isSmoking = true;
					isWIFI = true;
				} else
					tempBed = 1;

				tempStatus = randomNumber(4);
				if(tempStatus != 3)
					tempStatus = 0;
				else 
					tempStatus = 3;
				room.add(new Room(tempRoomNo, tempRoomType, bedType[tempBed], facing[randomNumber(4)], 
						status[tempStatus], isWIFI, isSmoking));
			}
		}
	}

	private int randomNumber(int max) {
		Random rand = new Random();
		int n = rand.nextInt(max);
		return n;
	}

}
