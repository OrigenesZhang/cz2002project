package com.ntu.scse;

import java.io.Serializable;
/**
 Represents one hotel Room Service Menu Item that contains information on the item.
 One RoomService can contain many Menus.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class Menu implements Serializable, Comparable<Menu>{
    /**
     The name of the food item
     */
    private String fName;
    /**
     The description of the food item
     */
    private String desc;
    /**
     The price of the food item
     */
    private float price;
    /**
     The menu ID of the food item
     */
    private int id;
    /**
     * Creates a new Menu Item with the given food details.
     * @param idd This Menu Item's unique identifier.
     * @param food The food item's name
     * @param d The food item's description
     * @param p The food item's price
     */
    public Menu (int idd, String food, String d, float p)
    {
        id = idd;
        fName = food;
        desc = d;
        price = p;
    }
    /**
     * Gets the food name.
     * @return the food name.
     */
    public String getFood() {
        return this.fName;
    }
    /**
     * Gets the food description.
     * @return the food description.
     */
    public String getDesc() {
        return this.desc;
    }
    /**
     * Gets the food price.
     * @return the food price.
     */
    public float getPrice() {
        return this.price;
    }
    /**
     * Gets the food ID.
     * @return the food ID.
     */
    public int getID() {
        return this.id;
    }
    /**
     * Sets the unique food item identifier.
     * @param i the unique food item identifier to set.
     */
    public void setId(int i) {
        this.id = i;
    }
    /**
     * Sets the food item name.
     * @param fName the food item name to set.
     */
    public void setfName(String fName) {
        this.fName = fName;
    }
    /**
     * Sets the food item description.
     * @param desc the food item description to set.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * Sets the food item price.
     * @param price the food item price to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Overwritten method for sorting Menu Items according to their ID
     */
    @Override
    public int compareTo(Menu comparesTo) {
        int compareID=(comparesTo).getID();
        return this.id-compareID;
    }

}