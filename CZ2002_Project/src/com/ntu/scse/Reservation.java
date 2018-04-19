package com.ntu.scse;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Reservation implements Serializable, Comparable<Reservation>{
    private int resvNo, adultNo, kidNo, guestID, roomDays;
	String roomNo, roomType, resvStatus;
    private LocalDate dateCheckIn, dateCheckOut;
    private LocalTime resvTime;


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


	public int getResvNo() {
		return resvNo;
	}


	public int getAdultNo() {
		return adultNo;
	}


	public int getKidNo() {
		return kidNo;
	}


	public int getGuestID() {
		return guestID;
	}


	public int getRoomDays(){ return roomDays; }


	public String getRoomNo() {
		return roomNo;
	}


	public String getRoomType() {
		return roomType;
	}


	public String getResvStatus() {
		return resvStatus;
	}


	public LocalDate getDateCheckIn() {
		return dateCheckIn;
	}


	public LocalDate getDateCheckOut() {
		return dateCheckOut;
	}


	public LocalTime getResvTime() {
		return resvTime;
	}


	public void setResvNo(int resvNo) {
		this.resvNo = resvNo;
	}


	public void setAdultNo(int adultNo) {
		this.adultNo = adultNo;
	}


	public void setKidNo(int kidNo) {
		this.kidNo = kidNo;
	}


	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}


	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public void setResvStatus(String resvStatus) {
		this.resvStatus = resvStatus;
	}


	public void setDateCheckIn(LocalDate dateCheckIn) {
		this.dateCheckIn = dateCheckIn;
	}


	public void setDateCheckOut(LocalDate dateCheckOut) {
		this.dateCheckOut = dateCheckOut;
	}


	public void setResvTime(LocalTime resvTime) {
		this.resvTime = resvTime;
	}

	@Override
	public int compareTo(Reservation comparesTo) {
		int compareID=(comparesTo).getResvNo();
		return this.resvNo-compareID;
	}
}
