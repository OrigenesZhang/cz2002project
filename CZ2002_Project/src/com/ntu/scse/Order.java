package com.ntu.scse;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Order implements Serializable, Comparable<Order> {

	private LocalDateTime dt;
	private String[] arrayStatus = { "Confirmed", "Preparing", "Delivered" };
	private String roomNo, ordFName, ordRemarks, dateTime, status;
	private float price;
	private int quan, orderID;

	public Order(String rNo, int oID, String fn, float c, int q, String r) {
		roomNo = rNo;
		orderID = oID;
		ordFName = fn;
		price = c;
		quan = q;
		ordRemarks = r;
	}

	public String getOrdFName() {
		return ordFName;
	}

	public String getOrdRemarks() {
		return ordRemarks;
	}

	public float getPrice() {
		return price;
	}

	public String getStatus() {
		return status = arrayStatus[checkTiming()];
	}

	public int getQuan() {
		return quan;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public int getID() {
		return orderID;
	}

	public void setID(int id) {
		orderID = id;
	}

	public void setOrdRemarks(String ordRemarks) {
		this.ordRemarks = ordRemarks;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setOrderDateTime(String time, LocalDateTime lDateTime) {

		dt = lDateTime;
		dateTime = time;
		status = arrayStatus[0];
	}

	public String setOrderDateTime(LocalDateTime lDateTime) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = lDateTime.format(format);

		return formatDateTime;
	}

	private int checkTiming() {
		LocalDateTime tempDateTime = LocalDateTime.now();

		long minutes = dt.until(tempDateTime, ChronoUnit.MINUTES);
		minutes = ChronoUnit.MINUTES.between(dt, tempDateTime);

		long seconds = dt.until(dt, ChronoUnit.SECONDS);
		seconds = ChronoUnit.SECONDS.between(dt, tempDateTime);

		// for checking purpose
		// System.out.println("current timing: " + tempDateTime);
		// System.out.println(minutes + " min \t" + seconds + " seconds " );

		if (minutes > 1)
			return 2;
		else if (minutes > 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int compareTo(Order comparesTo) {
		int compareID=(comparesTo).getID();
		return this.orderID-compareID;
	}
}

