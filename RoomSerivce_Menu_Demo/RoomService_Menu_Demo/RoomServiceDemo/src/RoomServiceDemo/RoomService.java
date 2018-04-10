package RoomServiceDemo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;;

public class RoomService implements Serializable{
	private ArrayList<Menu> menus;
	//private ArrayList<Order> orders;
	
	
	public RoomService ()
	{
		menus = new ArrayList<Menu>();
		//orders = new ArrayList<Order>();
	}
	
	public void addMenu(Menu mm)
	{
		menus.add(mm);
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
			}
		}
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
		int i = 0;
		
		for(Menu mm : menus)
		{
			i = mm.getID();
		}
		
		return i;
	}
}
