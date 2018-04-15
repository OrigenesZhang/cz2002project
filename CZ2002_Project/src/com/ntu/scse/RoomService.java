package com.ntu.scse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RoomService implements Serializable, Comparable {
	private List<Menu> menus;
	private List<Order> orders;

	public RoomService(ArrayList<Menu> m, ArrayList<Order> o) {
		if (m == null) {// Initialize
			this.menus = new ArrayList<Menu>();
			initializeMenu();
			System.out.println("Room Service Menu initialized!");
		} else {
			this.menus = m;
			System.out.println("Room Service Menu loaded!");
		}

		if (o == null) {// Initialize
			this.orders = new ArrayList<Order>();
		} else {
			this.orders = o;
			System.out.println("Room Service Order loaded!");
		}
	}

	public void ShowRoomServiceMenuOption() {

		int choice = 0;
		do {
			System.out.println("Room Service Menu: ");
			System.out.println("1. Read Menu");
			System.out.println("2. Add item to menu");
			System.out.println("3. Remove item from menu");
			System.out.println("4. Update menu item");
			System.out.println("5. Return main screen");
			choice = errorCheckingInt("Select an option: ");

			switch (choice) {
			case 1:
				viewMenu();
				break;

			case 2:
				addMenu();
				System.out.println("");
				break;

			case 3:
				removeMenuItem();
				break;

			case 4:
				updateMenuItem();
				break;

			case 5:
				System.out.println("Returning to main.....\n");
				break;

			default:
				System.out.println("Error input\n");
				break;
			}
		} while (choice != 5);
	}

	public void ShowRoomServiceOrderOption(RoomMgr rm) {
		int choice;
		String roomNo;

		Scanner input = new Scanner(System.in);

		do {

			System.out.println("Room Service Order: ");
			System.out.println("1. View order"); // option to view all orders or view search orders by roomID
			System.out.println("2. Add order");
			System.out.println("3. Remove order");
			System.out.println("4. Update order");
			System.out.println("5. Return");
			choice = errorCheckingInt("Select an option: ");

			switch (choice) {
			case 1:
				System.out.println("(1) view all orders");
				System.out.println("(2) view order by room no.");
				choice = errorCheckingInt("Select an option: ", 2);
				if (choice == 1)
					viewAllOrder();
				else {
					System.out.print("Enter room no: ");
					roomNo = input.nextLine();
					viewOrderByRoomID(roomNo);
				}
				break;

			case 2:
				System.out.print("Enter Room no. : ");
				roomNo = input.nextLine();

				// can only add if room is occupied
				if (rm.checkValidRoomForOrder(roomNo)) {
					viewMenu();
					addOrderItem(roomNo);
					finalizeOrder(roomNo);
				} else {
					System.out.println("Invalid Room no. \n");
					System.out.println("Room not available for odering");
				}

				break;

			case 3:
				System.out.print("Enter Room no. : ");
				roomNo = input.nextLine();
				if (rm.checkValidRoomForOrder(roomNo)) {
					viewOrderByRoomID(roomNo);
					removeOrderItem(roomNo);
				} else {
					System.out.println("Invalid Room no. \n");
				}
				break;

			case 4:
				System.out.println("Enter Room no. : ");
				roomNo = input.nextLine();
				if (rm.checkValidRoomForOrder(roomNo)) {
					viewOrderByRoomID(roomNo);
					updateOrderItem(roomNo);
				} else {
					System.out.println("Invalid Room no. \n");
				}
				break;

			case 5:
				System.out.println("Returning to main.....\n");
				break;

			default:
				System.out.println("Error input \n");
				break;
			}

		} while (choice != 5);
	}

	// ----------------------Menu Section -----------------------//

	private void initializeMenu() {
		menus.add(new Menu(1, "Chicken Chop", "Plain-grilled chicken with black pepper", 6));
		menus.add(new Menu(2, "Fish & Chip", "Fried battered fish with french fries", 6));
		menus.add(new Menu(3, "Aglio E Olio", "Freshly grounded garlic with chili flakes", 7));

	}

	public void viewMenu() {
		
		if(menus.size() < 1)
		{
			System.out.println("No menu to display\n");
		} else 
		{
			System.out.println("\nMenu list");
			System.out.format("%-10s%-40s%-100s%s\n", "Item No.", "Food Name", "Description", "Price (S$)");

			for (Menu mm : menus) {
				System.out.format("%s%-9d%-40s%-100s%.2f\n", " ", mm.getID(), mm.getFood(), mm.getDesc(), mm.getPrice());
			}
			System.out.println("");
		}
	}

	public void addMenu() {

		int tempID = getLastItemID(), noItem;
		String fn, desc;
		float p;

		Scanner input = new Scanner(System.in);

		noItem = errorCheckingInt("Enter amount of item to add: ");

		for (int i = 1; i <= noItem; i++) {
			System.out.println("\nEnter information for item " + i);
			System.out.print("Enter food name: ");
			fn = input.nextLine();
			System.out.print("Enter food description: ");
			desc = input.nextLine();

			p = errorCheckingFloat("Enter food price: ");
			tempID += 1;

			menus.add(new Menu(tempID, fn, desc, p));
		}
	}

	public void removeMenuItem() {

		boolean flag = false;
		int id = errorCheckingInt("Select the index to remove item from menu: ", getLastItemID());
		Iterator<Menu> iter = menus.iterator();

		while (iter.hasNext()) {
			Menu str = iter.next();

			if (str.getID() == id && !flag) {
				iter.remove();
				flag = true;

			} else if (flag) {
				str.setId(str.getID() - 1);
			}
		}
	}

	public void updateMenuItem() {

		Scanner input = new Scanner(System.in);
		int index = errorCheckingInt("Select the index to update item from menu: ", getLastItemID());
		boolean flag = false;
		int choice = 0;

		Iterator<Menu> iter = menus.iterator();

		while (iter.hasNext()) {
			Menu str = iter.next();

			if (str.getID() == index) {

				System.out.println("\nContent to edit");
				System.out.println("1. Food name");
				System.out.println("2. Food description");
				System.out.println("3. Food price");
				System.out.println("4. Update all the above");
				System.out.println("5. Return ");

				do {
					switch (errorCheckingInt("Enter index to edit content: ")) {
					case 1:
						System.out.println("\nCurrent food name: " + str.getFood());
						System.out.print("New food name: ");
						str.setfName(input.nextLine());
						flag = true;
						break;

					case 2:
						System.out.println("\nCurrent food description: " + str.getDesc());
						System.out.print("New food description: ");
						str.setDesc(input.nextLine());
						flag = true;
						break;

					case 3:
						System.out.println("\nCurrent food price: " + str.getPrice());
						str.setPrice(errorCheckingFloat("New food price: "));
						flag = true;
						break;

					case 4:
						System.out.println("\nCurrent food name: " + str.getFood());
						System.out.print("New food name: ");
						str.setfName(input.nextLine());

						System.out.println("\nCurrent food description: " + str.getDesc());
						System.out.print("New food description: ");
						str.setDesc(input.nextLine());

						System.out.println("\nCurrent food price: " + str.getPrice());
						str.setPrice(errorCheckingFloat("New food price: "));
						flag = true;
						break;
					case 5:
						flag = true;
						break;
					default:
						System.out.println("Error input\n");
						break;
					}
				} while (!flag);

				System.out.println("");

			}
		}
	}

	// ----------------------Order Section -----------------------//

	public void viewAllOrder() {
		
		if(orders.size() < 1)
		{
			System.out.println("No orders to display\n");
		} else 
		{
			Collections.sort(orders, new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					String oo1 = o1.getRoomNo();
					String oo2 = o2.getRoomNo();
					return oo1.compareTo(oo2);
				}
			});

			System.out.println("\nViewing all Order");
			System.out.format("%-15s%-15s%-40s%-20s%-20s%-40s%-15s%-15s\n", "Room No.", "Item No.", "Food Name",
					"Price (S$)", "Quantity", "Remarks", "Status", "Date/Time");
			for (Order oo : orders) {

				System.out.format("%-15s%-15d%-40s%-20.2f%-20d%-40s%-15s%-15s\n", oo.getRoomNo(), oo.getItemID(),
						oo.getOrdFName(), oo.getPrice(), oo.getQuan(), oo.getOrdRemarks(), oo.getStatus(),
						oo.getDateTime());
			}
			System.out.println("");
		}
	}

	public void viewOrderByRoomID(String roomID) {

		boolean flag = false;

		for (Order oo : orders) {
			if (oo.getRoomNo().equals(roomID)) {
				flag = true;
				break;
			}
		}

		if (flag) {
			System.out.println("\nViewing Room no. " + roomID + " Orders");
			System.out.format("%-15s%-40s%-20s%-20s%-40s%-15s%-15s\n", "Item No.", "Food Name", "Price (S$)",
					"Quantity", "Remarks", "Status", "Date/Time");
			for (Order oo : orders) {
				if (oo.getRoomNo().equals(roomID)) {
					System.out.format("%-15d%-40s%-20.2f%-20d%-40s%-15s%-15s\n", oo.getItemID(), oo.getOrdFName(),
							oo.getPrice(), oo.getQuan(), oo.getOrdRemarks(), oo.getStatus(), oo.getDateTime());
				}
			}
			System.out.println("");
		} else
			System.out.println("\nNo order made from Room no. " + roomID);

	}

	public void addOrderItem(String roomNo) {

		Scanner input = new Scanner(System.in);
		int menuID, quantity;
		char yesNo;
		String remarks;

		int orderNo = errorCheckingInt("Enter the number of order: ");

		for (int i = 1; i <= orderNo; i++) {
			System.out.println("");
			System.out.println("Order item " + i);
			menuID = errorCheckingInt("Enter menu index to add item to order: ", getLastItemID());
			quantity = errorCheckingInt("Enter quanity: ");

			while (true) {
				System.out.print("Include remarks (Y/N)? : ");
				yesNo = Character.toUpperCase(input.next().charAt(0));
				input.nextLine();
				if (yesNo == 'Y') {
					System.out.println("Enter remarks: ");
					remarks = input.nextLine();
					break;
				} else if (yesNo == 'N') {
					remarks = "-";
					break;
				} else {
					System.out.println("Error input\n");
				}
			}

			addOrder(roomNo, menuID, quantity, remarks);
		}

		System.out.println("");

	}

	private void addOrder(String roomNo, int menuID, int quan, String r) {
		// get the last itemID;
		int index = getLastItemID(roomNo);

		for (Menu mm : menus) {

			if (mm.getID() == menuID) {
				orders.add(new Order(roomNo, index, mm.getFood(), mm.getPrice(), quan, r));
			}
		}
	}

	public void removeOrderItem(String roomNo) {

		boolean flag = false;
		int index = errorCheckingInt("Enter index to remove item from order: ", getLastItemID(roomNo));
		Iterator<Order> iter = orders.iterator();
		
		while (iter.hasNext()) {
			Order str = iter.next();

			if (str.getRoomNo().equals(roomNo) && str.getItemID() == index && !str.getStatus().equals("Delivered")
					&& !flag) {
				iter.remove();
				flag = true;
			} else if (str.getRoomNo().equals(roomNo) && str.getItemID() == index && str.getStatus().equals("Delivered")
					&& !flag) {
				System.out.println("Delivered order cannot be removed\n");
			}
			
			
			else if (str.getRoomNo().equals(roomNo) && flag) {
				str.setItemID(str.getItemID() - 1);
			}
		}

	}

	public void finalizeOrder(String roomNo) {
		boolean flag = false;
		LocalDateTime tempDT = null;
		String tempDateTime = null;

		for (Order oo : orders) {
			if (!flag) {
				tempDT = LocalDateTime.now();
				tempDateTime = oo.setOrderDateTime(tempDT);
				flag = true;
			}

			if (oo.getRoomNo().equals(roomNo) && oo.getDateTime() == null && flag) {
				oo.setOrderDateTime(tempDateTime, tempDT);
			}
		}
	}

	public void updateOrderItem(String roomNo) {

		Scanner input = new Scanner(System.in);

		boolean flag = false;
		int choice = 0;
		int orderIndex = errorCheckingInt("Enter index to update item from order: ", getLastItemID(roomNo));

		Iterator<Order> iter = orders.iterator();

		while (iter.hasNext()) {
			Order str = iter.next();

			if (str.getRoomNo().equals(roomNo) && str.getItemID() == orderIndex
					&& (str.getStatus().equals("Confirmed") || str.getStatus().equals("Preparing"))) {

				System.out.println("\nContent to edit");
				System.out.println("1. Quantity");
				System.out.println("2. Remarks");
				System.out.println("3. Update all the above");
				System.out.println("4. Return ");
				choice = errorCheckingInt("Enter index to edit content: ");

				do {
					switch (choice) {
					case 1:
						System.out.println("\nCurrent food quantity: " + str.getQuan());
						str.setQuan(errorCheckingInt("New food quantity: "));
						flag = true;
						break;

					case 2:
						System.out.println("\nCurrent order remarks: " + str.getOrdRemarks());
						System.out.print("New remarks: ");
						str.setOrdRemarks(input.nextLine());
						flag = true;
						break;

					case 3:
						System.out.println("\nCurrent food quantity: " + str.getQuan());
						System.out.print("New food quantity: ");
						str.setQuan(errorCheckingInt("New food quantity: "));

						System.out.println("\nCurrent order remarks: " + str.getOrdRemarks());
						System.out.print("New remarks: ");
						str.setOrdRemarks(input.nextLine());
						flag = true;
						break;

					case 4:
						flag = true;
						break;

					default:
						System.out.println("Error input\n");
						break;
					}
				} while (!flag);

				System.out.println("");

				if (choice != 4 && !str.getStatus().equals("Delivered")) {
					LocalDateTime tempDT = LocalDateTime.now();
					String tempDateTime = str.setOrderDateTime(tempDT);
					str.setOrderDateTime(tempDateTime, tempDT);

				}

			}

		}
	}

	// ----------------------Other Section -----------------------//

	public int getLastItemID() {

		return (menus.get(menus.size() - 1).getID());
	}

	public int getLastItemID(String roomNo) {

		int i = 1;

		for (Order oo : orders) {
			if (oo.getRoomNo().equals(roomNo))
				i += 1;
		}
		return i;
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

	@Override
	public int compareTo(Object arg0) {
		// required for comparable to override
		return 0;
	}

	public List<Menu> saveMenuToFile() {
		return menus;
	}

	public List<Order> saveOrderToFile() {
		return orders;
	}

}
