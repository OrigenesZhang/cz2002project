package com.ntu.scse;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
/**
 Represents a hotel Reservation that contains reservation details as well as relevant room and guest details.
 One ReservationManager can contain many Reservations.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class Reservation implements Serializable, Comparable<Reservation>{
	/**
	 * The unique identifier of this reservation
	 */
    private int resvNo;
	/**
	 * The number of adults in this reservation
	 */
	private int adultNo;
	/**
	 * The number of kids in this reservation
	 */
	private int kidNo;
	/**
	 * The registered Guest ID for this reservation
	 */
	private int guestID;
	/**
	 * The number of nights of stay for this reservation
	 */
	private int roomDays;
	/**
	 * The room number associated with this reservation
	 */
	String roomNo;
	/**
	 * The room type associated with this reservation
	 */
	String roomType;
	/**
	 * The status of the current reservation
	 */
	String resvStatus;
	/**
	 * The check in date of this reservation
	 */
    private LocalDate dateCheckIn;
	/**
	 * The check out date of this reservation
	 */
	private LocalDate dateCheckOut;
	/**
	 * The time this reservation was placed
	 */
    private LocalTime resvTime;

	/**
	 * Creates a new Reservation with the given ID, reservation details, room details, guest details.
	 * Automatically calculates the number of nights stay using the check in and out dates.
	 * @param resvNo The unique Reservation number.
	 * @param roomNo The room number associated with this reservation.
	 * @param roomType The room type associated with this reservation.
	 * @param guestID The guest ID associated with this reservation.
	 * @param adultNo The number of adults in this reservation.
	 * @param kidNo TThe number of kids in this reservation.
	 * @param dateCheckIn The check in date of this reservation.
	 * @param dateCheckOut The check out date of this reservation.
	 * @param resvStatus The status of this reservation.
	 * @param resvTime The time that this reservation was placed.
	 */
    public Reservation(int resvNo,
    				   String roomNo,
                       String roomType,
                       int guestID,
                       int adultNo,
                       int kidNo,
                       LocalDate dateCheckIn,
                       LocalDate dateCheckOut,
                       String resvStatus,
                       LocalTime resvTime
                       ) {
        this.resvNo = resvNo;
        this.guestID = guestID;
        this.adultNo = adultNo;
        this.kidNo = kidNo;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.roomDays = (int) ChronoUnit.DAYS.between(dateCheckIn, dateCheckOut);
        this.resvStatus = resvStatus;
        this.resvTime = resvTime;
    }

	/**
	 * Gets the unique Reservation identifier.
	 * @return the unique Reservation identifier.
	 */
	public int getResvNo() {
		return resvNo;
	}
	/**
	 * Gets the number of adults in this reservation.
	 * @return the number of adults in this reservation.
	 */
	public int getAdultNo() {
		return adultNo;
	}
	/**
	 * Gets the number of kids in this reservation.
	 * @return the number of kids in this reservation.
	 */
	public int getKidNo() {
		return kidNo;
	}
	/**
	 * Gets the guest ID associated with this reservation.
	 * @return the guest ID associated with this reservation.
	 */
	public int getGuestID() {
		return guestID;
	}
	/**
	 * Gets the number of nights stay of this reservation.
	 * @return the number of nights stay of this reservation.
	 */
	public int getRoomDays(){ return roomDays; }
	/**
	 * Gets the room number associated with this reservation.
	 * @return the room number associated with this reservation.
	 */
	public String getRoomNo() {
		return roomNo;
	}
	/**
	 * Gets the room type associated with this reservation.
	 * @return the room type associated with this reservation.
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * Gets the status of this reservation.
	 * @return the status of this reservation.
	 */
	public String getResvStatus() {
		return resvStatus;
	}
	/**
	 * Gets the check in date of this reservation.
	 * @return the check in date of this reservation.
	 */
	public LocalDate getDateCheckIn() {
		return dateCheckIn;
	}
	/**
	 * Gets the check out date of this reservation.
	 * @return the check out date of this reservation.
	 */
	public LocalDate getDateCheckOut() {
		return dateCheckOut;
	}
	/**
	 * Gets the time when this reservation was placed.
	 * @return the time when this reservation was placed.
	 */
	public LocalTime getResvTime() {
		return resvTime;
	}

	/**
	 * Sets the unique identifier of this reservation.
	 * @param resvNo the unique identifier to set for this reservation.
	 */
	public void setResvNo(int resvNo) {
		this.resvNo = resvNo;
	}
	/**
	 * Sets the number of adults for this reservation.
	 * @param adultNo the number of adults to set for this reservation.
	 */
	public void setAdultNo(int adultNo) {
		this.adultNo = adultNo;
	}
	/**
	 * Sets the number of kids for this reservation.
	 * @param kidNo the number of kids to set for this reservation.
	 */
	public void setKidNo(int kidNo) {
		this.kidNo = kidNo;
	}
	/**
	 * Sets the room number of this reservation.
	 * @param roomNo the room number to set for this reservation.
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	/**
	 * Sets the status of this reservation.
	 * @param resvStatus the status to set for this reservation.
	 */
	public void setResvStatus(String resvStatus) {
		this.resvStatus = resvStatus;
	}
	/**
	 * Sets the check in date of this reservation.
	 * @param dateCheckIn the check in date to set for this reservation.
	 */
	public void setDateCheckIn(LocalDate dateCheckIn) {
		this.dateCheckIn = dateCheckIn;
	}
	/**
	 * Sets the check out date of this reservation.
	 * @param dateCheckOut the check out date to set for this reservation.
	 */
	public void setDateCheckOut(LocalDate dateCheckOut) {
		this.dateCheckOut = dateCheckOut;
	}

	/**
	 * Overwritten method for sorting Reservations according to their ID
	 */
	@Override
	public int compareTo(Reservation comparesTo) {
		int compareID=(comparesTo).getResvNo();
		return this.resvNo-compareID;
	}
}
