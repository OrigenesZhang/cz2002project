package com.ntu.scse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.ntu.scse.Main.*;


public class RoomServiceMgr {
    private SerializeDB sdb;
    private ArrayList list;
    private RoomService rsM;

    public RoomServiceMgr() {
        sdb = new SerializeDB();
        list = new ArrayList();
        rsM = new RoomService();

        //!!!!!!!!!!
        try {
            list = (ArrayList) sdb.readSerializedObject(roomServiceFileName);
            for (int i = 0; i < list.size(); i++) {
                rsM = (RoomService) list.get(i);
            }

        }catch (Exception e) { //File does not exist, no data to load
            System.out.println("IOError: Menu file not found! Using default settings...");
            this.initialize(rsM, list, sdb);
        }

    }

    public void ShowRoomServiceOption() {
        int choice = 0;
        do {
            boolean errorInput = false;
            int noItem = 0;

            Scanner input = new Scanner(System.in);
            System.out.println("1. Read Menu");
            System.out.println("2. Add item to menu");
            System.out.println("3. Delete item from menu");
            System.out.println("4. Update menu item");
            System.out.println("5. Return main screen");

            do {
                System.out.print("Select an option: ");
                try {
                    errorInput = false;
                    choice = input.nextInt();
                } catch (InputMismatchException e) {
                    input.next();
                    System.out.println("Error input\n");
                    errorInput = true;
                }
            } while (errorInput);


            switch (choice) {
                case 1:
                    rsM.viewMenu();
                    break;

                case 2:
                    int tempID = rsM.lastItemID();
                    String fn, desc;
                    float p;

                    System.out.print("\nEnter amount of item to add: ");
                    noItem = input.nextInt();
                    input.nextLine();

                    for (int i = 1; i <= noItem; i++) {
                        System.out.println("\nEnter information for item " + i);
                        System.out.print("Enter food name: ");
                        fn = input.nextLine();
                        System.out.print("Enter food description: ");
                        desc = input.nextLine();
                        System.out.print("Enter food price: ");
                        p = input.nextFloat();
                        input.nextLine();
                        tempID += 1;
                        rsM.addMenu(new Menu(tempID, fn, desc, p));
                    }
                    System.out.println("");

                    list.add(rsM);
                    sdb.writeSerializedObject(roomServiceFileName, list);
                    break;

                case 3:
                    rsM.viewMenu();
                    System.out.print("Select the index to delete item from menu: ");
                    int index = input.nextInt();
                    input.nextLine();

                    list.remove(rsM.removeItem(index));
                    sdb.writeSerializedObject(roomServiceFileName, list);
                    break;

                case 4:
                    rsM.viewMenu();
                    System.out.print("Select the index to update item from menu: ");
                    int index1 = input.nextInt();
                    input.nextLine();

                    list.add(rsM.updateItem(index1));
                    sdb.writeSerializedObject(roomServiceFileName, list);
                    break;

                case 5:
                    System.out.println("Returning to main screen...");
                    break;

                default:
                    System.out.println("Error input");
                    break;
            }
        } while (choice != 5);
    }


    //To set menu for the very first time or return to default
    //initialize(rsM, list, sdb);
    //once menu has been set, meaning program has run once,
    //initialize() must be comment out
    //then uncomment the section below (must), to read menu of all existing changes made

    private void initialize(RoomService rs, ArrayList al, SerializeDB sdb) {
        rs.addMenu(new Menu(1, "Chicken Chop", "Plain-grilled chicken with black pepper", 6));
        rs.addMenu(new Menu(2, "Fish & Chip", "Fried battered fish with french fries", 6));
        rs.addMenu(new Menu(3, "Aglio E Olio", "Freshly grounded garlic with chili flakes", 7));
        al.add(rs);

        sdb.writeSerializedObject(roomServiceFileName, al);
    }
}

