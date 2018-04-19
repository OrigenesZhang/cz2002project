package com.ntu.scse;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill implements Serializable, Comparable<Bill> {

    private String[] bStatus = { "OPEN", "PAID" };
    private String billStatus, roomType, roomNo;
	private int billNo, resvNo, roomDays;
    private double roomRate, discount, total;
    private final double taxRate = 0.07;
    private ArrayList<Order> orders;

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

    public int getBillNo() {
    	return billNo;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getRoomRate(){return roomRate;}

    public int getRoomDays() {
        return roomDays;
    }

    public int getResvNo() {
        return resvNo;
    }

    public double getTotal() {
        return total;
    }

    public String getBillStatus (){return billStatus;}

    public String getRoomType (){return roomType;}

    public String getRoomNo (){return roomNo;}

    public Order getOrder(int index) {
        return orders.get(index);
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setBillStatus (int choice){this.billStatus = bStatus[choice];}

    public void setRoomType (String roomType){this.roomType = roomType;}

    public void addOrder(Order item) {
        this.orders.add(item);
        total += (item.getPrice()*item.getQuan());
    }

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

    @Override
    public int compareTo(Bill comparesTo) {
        int compareID=(comparesTo).getBillNo();
        return this.billNo-compareID;
    }
}
