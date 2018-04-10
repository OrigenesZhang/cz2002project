package com.ntu.scse;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;;

public class RoomService implements Serializable{
    static ArrayList<Menu> menus = null;


    public RoomService (ArrayList<Menu> menus)
    {
        this.menus = menus;
        if (menus == null){
            this.menus = new ArrayList<Menu>();
            initialize();
        }

    }

    public void viewMenu()
    {
        System.out.println("\nMenu list");
        //System.out.println("Index\t Food Name \t Description  \t\t\tPrice (S$)");
        System.out.format("%-10s%-40s%-100s%s\n", "Index", "Food Name", "Description", "Price (S$)");

        for(Menu mm : menus)
        {
            System.out.format("%s%-9d%-40s%-100s%.2f\n", " ",  mm.getID(), mm.getFood(), mm.getDesc(), mm.getPrice());
            //System.out.println("  " + mm.getID() + "\t " + mm.getFood() + "\t " + mm.getDesc() + "\t\t\t" + mm.getPrice());
        }
        System.out.println("");
    }

    public void searchItem(int id)
    {
        for(Menu mm : menus)
        {
            if(mm.getID() == id)
            {
                System.out.println("You have selected: ");
                System.out.println(mm.getFood() + ", " + mm.getDesc() + ", " + mm.getPrice());
                return;
            }
        }
        System.out.println("Item does not exist!");
    }

    public Object removeItem(int id)
    {
        boolean flag = false;
        Iterator<Menu> iter = menus.iterator();

        while (iter.hasNext()) {
            Menu str = iter.next();

            if (str.getID() == id && !flag) {
                iter.remove(); flag = true;

            } else if (flag)
            {
                str.setId(str.getID()-1);
            }
        }

        return this;
    }

    public Object updateItem(int id) {

        boolean flag = false;
        int choice = 0;

        Scanner input  = new Scanner (System.in);			//scanner for String

        Iterator<Menu> iter = menus.iterator();

        while (iter.hasNext()) {
            Menu str = iter.next();

            if (str.getID() == id) {

                System.out.println("\nContent to edit");
                System.out.println("1. Food name");
                System.out.println("2. Food description");
                System.out.println("3. Food price");
                System.out.println("4. Update all the above");
                System.out.println("5. Return ");
                System.out.print("Enter index to edit content: ");
                choice = input.nextInt(); input.nextLine();


                do {
                    switch (choice)
                    {
                        case 1: System.out.println("\nCurrent food name: " + str.getFood());
                            System.out.print("New food name: ");
                            str.setfName(input.nextLine());
                            flag = true;
                            break;

                        case 2: System.out.println("\nCurrent food description: " + str.getDesc());
                            System.out.print("New food description: ");
                            str.setDesc(input.nextLine());
                            flag = true;
                            break;

                        case 3: System.out.println("\nCurrent food price: " + str.getPrice());
                            System.out.print("New food price: ");
                            str.setPrice(input.nextFloat());
                            flag = true;
                            break;

                        case 4: System.out.println("\nCurrent food name: " + str.getFood());
                            System.out.print("New food name: ");
                            str.setfName(input.nextLine());

                            System.out.println("\nCurrent food description: " + str.getDesc());
                            System.out.print("New food description: ");
                            str.setDesc(input.nextLine());

                            System.out.println("\nCurrent food price: " + str.getPrice());
                            System.out.print("New food price: ");
                            str.setPrice(input.nextFloat());
                            flag = true;
                            break;
                        case 5: flag = true; break;
                        default: System.out.println("Error - Wrong input"); break;
                    }
                } while (!flag);

                System.out.println("");

            }
        }

        return this;
    }

    public int lastItemID()
    {
        return (menus.get(menus.size()-1).getID());
    }

    public void ShowRoomServiceOption() {
        int index, choice = 0;
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
                    viewMenu();
                    break;

                case 2:
                    int tempID = lastItemID();
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
                        menus.add(new Menu(tempID, fn, desc, p));
                    }
                    System.out.println("");
                    break;

                case 3:
                    viewMenu();
                    System.out.print("Select the index to delete item from menu: ");
                    index = input.nextInt();
                    input.nextLine();
                    menus.remove(index);
                    break;

                case 4:
                    viewMenu();
                    System.out.print("Select the index to update item from menu: ");
                    index = input.nextInt();
                    input.nextLine();
                    updateItem(index);
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

    public void saveToFile(String menuFileName){
        try {
            FileOutputStream foStream = new FileOutputStream(menuFileName);
            BufferedOutputStream boStream = new BufferedOutputStream(foStream);
            ObjectOutputStream doStream = new ObjectOutputStream(boStream);

            doStream.writeObject(menus); //Write guest list into file

            System.out.println("Menu saved to " + menuFileName);
            doStream.close();
        }
        catch (IOException e){
            System.out.println("[Menu] File IO Error!" + e.getMessage());
        }
    }


    //To set menu for the very first time or return to default
    //initialize(rsM, list, sdb);
    //once menu has been set, meaning program has run once,
    //initialize() must be comment out
    //then uncomment the section below (must), to read menu of all existing changes made

    private void initialize() {
        menus.add(new Menu(1, "Chicken Chop", "Plain-grilled chicken with black pepper", 6));
        menus.add(new Menu(2, "Fish & Chip", "Fried battered fish with french fries", 6));
        menus.add(new Menu(3, "Aglio E Olio", "Freshly grounded garlic with chili flakes", 7));
    }
}
