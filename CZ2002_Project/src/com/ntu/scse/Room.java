package com.ntu.scse;

import java.io.Serializable;

public class Room implements Serializable {

	// Reminder:
	// room rate also changes during weekend or public holiday

	private String roomNO, roomType, bedType, facing, roomStatus, guest;
	private double roomRate;
	private boolean isWIFI, isSmoking;

	public Room(String rNo, String rType, String bType, String f, String rStatus,  boolean wifi, boolean smoke) {
		roomNO = rNo;
		roomType = rType;
		bedType = bType;
		facing = f;
		roomStatus = rStatus;
		isWIFI = wifi;
		isSmoking = smoke;
		roomRate = defaultRoomRate(roomType);
		guest = "-";

	}

	private double defaultRoomRate(String roomType) {
		if (roomType.equals("Single"))
			return plusWIFISmokingRate (100);
		else if (roomType.equals("Double"))
			return plusWIFISmokingRate(250);
		else if (roomType.equals("Deluxe"))
			return plusWIFISmokingRate(480);
		else
			return plusWIFISmokingRate(830);
	}

	private double plusWIFISmokingRate (double roomRate)
	{
		double percentage = 5;
		double upPrice;
		double finalPrice;

		if (this.isSmoking && this.isWIFI)
		{
			upPrice = (roomRate/100)*(percentage + percentage);
			finalPrice = roomRate + upPrice;
		} else if (this.isSmoking || this.isWIFI)
		{
			upPrice = (roomRate/100)*(percentage);
			finalPrice = roomRate + upPrice;
		} else
		{
			finalPrice = roomRate;
		}

		return finalPrice;
	}

	public String getRoomNO() {
		return roomNO;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getBedType() {
		return bedType;
	}

	public String getFacing() {
		return facing;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public double getRoomRate() {
		return roomRate;
	}

	public boolean isWIFI() {
		return isWIFI;
	}

	public boolean isSmoking() {
		return isSmoking;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;

		if (this.roomType.equals("Single"))
			this.bedType = "Single";
		else
			this.bedType = "Double";

		setRoomRate(defaultRoomRate(this.roomType));	//set appropriate room rate

	}

	public void setRoomRate(double roomRate) {
		this.roomRate = plusWIFISmokingRate(roomRate);
	}

	public void setWIFI(boolean isWIFI) {
		this.isWIFI = isWIFI;
	}

	public void setSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	}

}
