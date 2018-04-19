package com.ntu.scse;

import java.util.ArrayList;
/**
 Represents a hotel Bill Manager that contains a list of all bills as well as methods for manipulating those bills.
 One BillManager can contain many Bills.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class BillMgr {
	/**
	 * An array of the master list of Bills for the hotel
	 */
	static ArrayList<Bill> billList = null;
	/**
	 * An array of the possible payment methods
	 */
	private String[] payMethod = { "Cash", "Credit Card" };
	/**
	 * Creates a Bill Manager based on loaded data from a file. If no previous data was present, initialize an empty master list.
	 * @param billList The list received from Main. If there was previous data, use this, else, create an empty list.
	 */
    public BillMgr(ArrayList<Bill> billList) {
    	if (billList == null) //Initialize
			this.billList = new ArrayList<>();
		else
			this.billList = new ArrayList<>(billList);

        System.out.println(this.billList.size() + " Bills loaded!");
    }
	/**
	 * Tries to create a new Bill with the given reservation details and room details.
	 * Gets a new unique identifier automatically
	 * @param resvNo The Reservation number associated with this bill.
	 * @param roomDays The number of nights of stay.
	 * @param roomType The room type associated with this bill.
	 * @param roomNo The room number associated with this bill.
	 * @param roomRate The room price per night associated with this bill.
	 * @param discount The discount associated with this bill.
	 * @return returns the newly created Bill
	 */
    public Bill addBill (
			int resvNo,
			int roomDays,
			String roomType,
			String roomNo,
			double roomRate,
			double discount
            ) {
		int newBillNo = billList.size() + 1;
		try {
			Bill newBill = new Bill(newBillNo, resvNo, roomDays, roomType, roomNo, roomRate, discount);
			billList.add(newBill);
			return newBill;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Tries to add a given order to a specified bill
	 * @param billNo The unique bill identifier
	 * @param order order to add into the bill
	 * @return returns true if succeed in adding order, else return false.
	 */
    public boolean addOrderToBill(int billNo, Order order){
    	try {
			billList.get(billNo - 1).addOrder(order);
			return true;
		}catch(IndexOutOfBoundsException e){
    		e.printStackTrace();
    		return false;
		}
	}
	/**
	 * Tries to remove a given order from a specified bill
	 * @param billNo The unique bill identifier
	 * @param orderNo order number to remove from the bill
	 * @return returns true if succeed in removing order, else return false.
	 */
	public boolean removeOrderFromBill(int billNo, int orderNo){
		for (Bill b : billList){
			if (b.getBillNo() == billNo){
				return(b.removeOrder(orderNo));
			}
		}
		return false;
	}
	/**
	 * Prints out the finalized bill including all room service orders
	 * Sets bill status to "Paid"
	 * @param resvNo The unique reservation identifier to find the bill from
	 * @param paymentMethod indicates how the guest is paying the bill
	 *                      1 = cash, 2 = credit card
	 */
	public void printBill(int resvNo, int paymentMethod){

		for (Bill b : billList){
			if (b.getResvNo() == resvNo){

				System.out.println("Printing bill no. " + b.getBillNo());
				b.printOrders();

				System.out.format("%-15s%-15s%-15s%-15s%-20s%-25s%-25s%-25s%-15s\n", "Bill No.", "Resv No.",
						"Room Type", "Room Rate", "No. of nights", "Discount", "Tax", "Room Price","Bill Status");

				System.out.format("%-15d%-15s%-15s%-15s%-20s%-25.4s%-25s%-25s%-15s\n", b.getBillNo(), b.getResvNo(),
						b.getRoomType(), b.getRoomRate(), b.getRoomDays(), (b.getDiscount()*100)+"%", (b.getTaxRate()*100)+"%",
						"$"+ (b.getRoomDays()*b.getRoomRate()),
						b.getBillStatus());

				System.out.println("\n\nTotal Due:");
				System.out.format("%-15.7s", "$"+ (b.getTotal() * (1.0+b.getTaxRate())));
				System.out.println();
				//SET TO PAID
				b.setBillStatus(1);
				System.out.format("%-7.7s", "$"+ (b.getTotal() * (1.0+b.getTaxRate())));
				System.out.println(" has been paid via " + payMethod[paymentMethod-1]);
				return;
			}
		}
    	System.out.println("Bill for reservation not found!");
	}
	/**
	 * Gets the bill number from a given room number
	 * @param roomNo The room number to find the bill from
	 * @return the Bill number tagged to the given room
	 */
	public int getBillNoFromRoomNo(String roomNo){
		for (Bill b : billList){
			if (b.getRoomNo().equals(roomNo)){
				return b.getBillNo();
			}
		}
    	return 0;
	}
	/**
	 * Gives the master list back to main to write to file
	 * @return the master bill list
	 */
	public ArrayList<Bill> saveToFile() {
		return billList;
    }

}
