package com.ntu.scse;

import java.io.Serializable;
import java.util.ArrayList;
/**
 Represents a hotel Bill object that contains the hotel room price as well as the room service orders for that room.
 One BillManager can contain many Bills.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class Bill implements Serializable, Comparable<Bill> {
    /**
     * An array of possible Bill statuses
     */
    private String[] bStatus = { "OPEN", "PAID" };
    /**
     * The status of the Bill
     */
    private String billStatus;
    /**
     * The Type of room associated with this bill
     */
    private String roomType;
    /**
     * The Room Number associated with this Bill
     */
    private String roomNo;
    /**
     * The unique bill identifier
     */
	private int billNo;
    /**
     * The unique reservation identifier for this bill
     */
    private int resvNo;
    /**
     * The number of nights of stay associated with this bill
     */
    private int roomDays;
    /**
     * The price per night for the room
     */
    private double roomRate;
    /**
     * The discount applied to the bill
     */
    private double discount;
    /**
     * The total price of the Bill
     */
    private double total;
    /**
     * Fixed tax rate
     */
    private final double taxRate = 0.07;
    /**
     * A List of orders made for this Bill
     */
    private ArrayList<Order> orders;
    /**
     * Creates a new Bill with the given ID, reservation details, room details.
     * Calculates the running total price as the number of nights * the price per night
     * @param billNo This Bill's unique identifier.
     * @param resvNo The Reservation number associated with this bill.
     * @param roomDays The number of nights of stay.
     * @param roomType The room type associated with this bill.
     * @param roomNo The room number associated with this bill.
     * @param roomRate The room price per night associated with this bill.
     * @param discount The discount associated with this bill.
     */
    public Bill (
                int billNo,
                int resvNo,
                int roomDays,
                String roomType,
                String roomNo,
                double roomRate,
                double discount
                ) {
    	this.billNo = billNo;
    	this.resvNo = resvNo;
    	this.roomType = roomType;
    	this.roomNo = roomNo;
    	this.roomDays = roomDays;
    	this.roomRate = roomRate;
        this.discount = discount;
        billStatus = bStatus[0];
        this.orders = new ArrayList<>();
        this.total = roomRate*roomDays;
    }
    /**
     * Gets the unique Bill identifier.
     * @return this Bill number.
     */
    public int getBillNo() { return billNo; }
    /**
     * Gets the discount percentage.
     * @return this Bill's discount.
     */
    public double getDiscount() { return discount; }
    /**
     * Gets the Bill tax rate.
     * @return this Bill's tax rate.
     */
    public double getTaxRate() { return taxRate; }
    /**
     * Gets the room price per night associated with this bill.
     * @return room price per night associated with this bill.
     */
    public double getRoomRate(){ return roomRate; }
    /**
     * Gets the number of nights stay associated with this bill.
     * @return number of nights stay associated with this bill.
     */
    public int getRoomDays() { return roomDays; }
    /**
     * Gets the unique Reservation identifier associated with this bill.
     * @return the Reservation number associated with this bill.
     */
    public int getResvNo() { return resvNo; }
    /**
     * Gets the total price for this bill.
     * @return total price for this bill.
     */
    public double getTotal() { return total; }
    /**
     * Gets the status for this bill.
     * @return status for this bill.
     */
    public String getBillStatus (){ return billStatus; }
    /**
     * Gets the room type for this bill.
     * @return room type for this bill.
     */
    public String getRoomType (){ return roomType; }
    /**
     * Gets the room number for this bill.
     * @return room number for this bill.
     */
    public String getRoomNo (){ return roomNo; }
    /**
     * Sets the bill status.
     * @param choice choice of status to set bill to
     *               0 = "OPEN", 1 = "PAID"
     */
    public void setBillStatus (int choice){ this.billStatus = bStatus[choice]; }
    /**
     * Adds an order to this bill.
     * Adds cost of items to running total of bill.
     * @param item The order to add to the bill
     */
    public void addOrder(Order item) {
        this.orders.add(item);
        total += (item.getPrice()*item.getQuan());
    }
    /**
     * Removes an order from this bill.
     * Reduces cost of items from running total of bill.
     * @param orderID The order identifier to remove from the bill.
     * @return Returns true if an order was removed, else return false.
     */
    public boolean removeOrder(int orderID){
        for (Order o : orders){
            if (o.getID() == orderID) {
                orders.remove(o);
                total -= (o.getPrice()*o.getQuan());
                return true;
            }
        }
        return false;
    }
    /**
     * Prints out all orders for this bill.
     */
    public void printOrders (){
        if(orders.size() == 0)
        {
            System.out.println("No orders placed.\n");
        } else
        {
            System.out.println("\nOrders placed:");
            System.out.format("%-15s%-15s%-40s%-20s%-20s%-40s%-15s%-15s\n", "Room No.", "Item No.", "Food Name",
                    "Price (S$)", "Quantity", "Remarks", "Status", "Date/Time");
            for (Order oo : orders) {

                System.out.format("%-15s%-15d%-40s%-20.2f%-20d%-40s%-15s%-15s\n", oo.getRoomNo(), oo.getID(),
                        oo.getOrdFName(), oo.getPrice(), oo.getQuan(), oo.getOrdRemarks(), oo.getStatus(),
                        oo.getDateTime());
            }
            System.out.println();
        }
    }
    /**
     * Overwritten method for sorting Bills according to their ID
     */
    @Override
    public int compareTo(Bill comparesTo) {
        int compareID=(comparesTo).getBillNo();
        return this.billNo-compareID;
    }
}
