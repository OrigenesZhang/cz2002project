package RoomServiceDemo;

import java.io.Serializable;

public class Menu implements Serializable{

	private String fName, desc;
	private float price;
	private int id;
	
	public Menu (int idd, String food, String d, float p)
	{
		id = idd;
		fName = food;
		desc = d;
		price = p;
	}
	
	public String getFood() {
		return this.fName;
	}
	
	public String getDesc() { 
		return this.desc;
	}
	
	public float getPrice() {
		return this.price;
	}
	public int getID() {
		return this.id;
	}

	public void setId(int i) {
		this.id = i;
		
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
