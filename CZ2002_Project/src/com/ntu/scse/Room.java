package com.ntu.scse;

import java.io.Serializable;
import java.util.ArrayList;
/**
 Represents a hotel Room that contains room details.
 One RoomManager can contain many Rooms.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class Room implements Serializable {
	/**
	 * The unique Room Number of this room
	 */
	private String roomNO;
	/**
	 * The unique Room Type of this room
	 */
	private String roomType;
	/**
	 * The bed type of this room
	 */
	private String bedType;
	/**
	 * The direction this room is facing
	 */
	private String facing;
	/**
	 * The status of this room
	 */
	private String roomStatus;
	/**
	 * The guest staying in this room
	 */
	private String guest;
	/**
	 * The price per night of this room
	 */
	private double roomRate;
	/**
	 * Does this room have wifi?
	 */
	private boolean isWIFI;
	/**
	 * Does this room allow smoking?
	 */
	private boolean isSmoking;

	/**
	 * Creates a new Room with the given Room details.
	 * Initializes the guest as empty.
	 * @param rNo The unique Room number.
	 * @param rType The room type of this room.
	 * @param bType The bed type of this room.
	 * @param f The direction this room is facing.
	 * @param rStatus the status of this room.
	 * @param wifi Does this room have wifi?
	 * @param smoke Does this room allow smoking?
	 */
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
	/**
	 * Gets the default room price per night based on the room type
	 * @param roomType The base room type to get the base room price per night
	 * @return the default room price per night
	 */
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
	/**
	 * Updates the room price per night based on wifi availability and smoking privileges
	 * @param roomRate The base room type to get the base room price per night
	 * @return the updated room price per night
	 */
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
	/**
	 * Gets the unique Room Number.
	 * @return the unique Room Number.
	 */
	public String getRoomNO() {
		return roomNO;
	}
	/**
	 * Gets the Room Type.
	 * @return the Room Type.
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * Gets the Bed Type for this room.
	 * @return the Bed Type for this room.
	 */
	public String getBedType() {
		return bedType;
	}
	/**
	 * Gets the direction which this room is facing.
	 * @return the direction which this room is facing.
	 */
	public String getFacing() {
		return facing;
	}
	/**
	 * Gets the status of this room.
	 * @return the status of this room.
	 */
	public String getRoomStatus() {
		return roomStatus;
	}
	/**
	 * Gets the price per night for this room.
	 * @return the price per night for this room.
	 */
	public double getRoomRate() {
		return roomRate;
	}
	/**
	 * Gets the wifi availability of this room.
	 * @return the wifi availability of this room.
	 */
	public boolean isWIFI() {
		return isWIFI;
	}
	/**
	 * Gets the smoking privileges for this room.
	 * @return the smoking privileges for this room.
	 */
	public boolean isSmoking() {
		return isSmoking;
	}
	/**
	 * Gets the registered guest name who is staying in this room.
	 * @return the registered guest name who is staying in this room.
	 */
	public String getGuest() {
		return guest;
	}

	/**
	 * Sets the Guest name who is staying in this room.
	 * @param guest the Guest name who is staying in this room.
	 */
	public void setGuest(String guest) {
		this.guest = guest;
	}
	/**
	 * Sets the room status of this room.
	 * @param roomStatus the room status to set this room to.
	 */
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	/**
	 * Sets the room type of this room, and also the default room price per night based on the room type set.
	 * @param roomType the room type to set this room to.
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;

		if (this.roomType.equals("Single"))
			this.bedType = "Single";
		else
			this.bedType = "Double";
		
		setRoomRate(defaultRoomRate(this.roomType));	//set appropriate room rate
	}
	/**
	 * Sets the room price per night of this room based on wifi availability and smoking privileges.
	 * @param roomRate the base price per night of the room without wifi and smoking
	 */
	public void setRoomRate(double roomRate) {
		this.roomRate = plusWIFISmokingRate(roomRate);
	}
	/**
	 * Sets the wifi availability of the room.
	 * @param isWIFI the wifi availability of the room to set.
	 */
	public void setWIFI(boolean isWIFI) {
		this.isWIFI = isWIFI;
	}
	/**
	 * Sets the smoking privileges of the room.
	 * @param isSmoking the smoking privilege of the room to set.
	 */
	public void setSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	}
}
