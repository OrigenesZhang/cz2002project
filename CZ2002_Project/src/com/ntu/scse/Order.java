package com.ntu.scse;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 Represents one hotel Room Service Order that contains information on the item.
 One RoomService can contain many Orders.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class Order implements Serializable, Comparable<Order> {
	/**
	 The date and time an order was placed
	 */
	private LocalDateTime dt;
	/**
	 An array to store the possible Order statuses
	 */
	private String[] arrayStatus = { "Confirmed", "Preparing", "Delivered" };
	/**
	 The room number associated with this order
	 */
	private String roomNo;
	/**
	 The name of the food in this order
	 */
	private String ordFName;
	/**
	 The remarks (if any) of the food in this order
	 */
	private String ordRemarks;
	/**
	 The date and time the order was placed
	 */
	private String dateTime;
	/**
	 The status of this order
	 */
	private String status;
	/**
	 The price of the food in this order
	 */
	private float price;
	/**
	 The quantity of the food in this order
	 */
	private int quan;
	/**
	 The ID number of this order
	 */
	private int orderID;
	/**
	 * Creates a new Order with the given food details.
	 * @param rNo The room number associated with this order
	 * @param oID The order identification number
	 * @param fn The food name in the order
	 * @param c The food's price in the order
	 * @param q The quantity of the food in the order
	 * @param r The food's remarks (if any) in the order
	 */
	public Order(String rNo, int oID, String fn, float c, int q, String r) {
		roomNo = rNo;
		orderID = oID;
		ordFName = fn;
		price = c;
		quan = q;
		ordRemarks = r;
		status = arrayStatus[0];
	}
	/**
	 * Gets the food name in the order.
	 * @return the food name in the order.
	 */
	public String getOrdFName() {
		return ordFName;
	}
	/**
	 * Gets the food remarks (if any) in the order.
	 * @return the food remarks (if any) in the order.
	 */
	public String getOrdRemarks() {
		return ordRemarks;
	}
	/**
	 * Gets the price of the food.
	 * @return the price of the food.
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Gets the current status of the order.
	 * @return the current status of the order.
	 */
	public String getStatus() { return arrayStatus[checkTiming()]; }
	/**
	 * Gets the quantity of the food item in this order.
	 * @return the quantity of the food item in this order.
	 */
	public int getQuan() {
		return quan;
	}
	/**
	 * Gets the room number associated with this order.
	 * @return the room number associated with this order.
	 */
	public String getRoomNo() {
		return roomNo;
	}
	/**
	 * Gets the unique identifier of this order.
	 * @return the unique identifier of this order.
	 */
	public int getID() {
		return orderID;
	}
	/**
	 * Gets the date and time the order was placed.
	 * @return the date and time the order was placed.
	 */
	public String getDateTime() { return dateTime; }

	/**
	 * Sets the status of this order.
	 * @param choice the choice of status (from the array) to set.
	 */
	public void setStatus(int choice) {
		this.status = arrayStatus[choice];
	}
	/**
	 * Sets the remarks of this order.
	 * @param ordRemarks the new remarks to set.
	 */
	public void setOrdRemarks(String ordRemarks) {
		this.ordRemarks = ordRemarks;
	}
	/**
	 * Sets the quantity of food in this order.
	 * @param quan the new quantity of food to set.
	 */
	public void setQuan(int quan) {
		this.quan = quan;
	}
	/**
	 * Sets the date and time of this order.
	 * @param time the time of the order to set.
	 * @param lDateTime the date of the order to set.
	 */
	public void setOrderDateTime(String time, LocalDateTime lDateTime) {
		dt = lDateTime;
		dateTime = time;
		status = arrayStatus[0];
	}
	/**
	 * Formats the date and time of this order into dd-MM-yyyy HH:mm:ss.
	 * @param lDateTime the date and time of the order to format.
	 * @return returns the formatted date and time of the order
	 */
	public String setOrderDateTime(LocalDateTime lDateTime) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = lDateTime.format(format);

		return formatDateTime;
	}
	/**
	 * Checks how long has passed since an order has been placed
	 * @return returns the status to set an order to based on time passed since order placed
	 */
	private int checkTiming() {
		LocalDateTime tempDateTime = LocalDateTime.now();

		long minutes = ChronoUnit.MINUTES.between(dt, tempDateTime);

		if (minutes > 1)
			return 2;
		else if (minutes > 0)
			return 1;
		else
			return 0;
	}
	/**
	 * Overwritten method for sorting Orders according to their ID
	 */
	@Override
	public int compareTo(Order comparesTo) {
		int compareID=(comparesTo).getID();
		return this.orderID-compareID;
	}
}

