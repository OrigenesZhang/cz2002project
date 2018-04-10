package com.ntu.scse;

import java.io.Serializable;
import java.util.Date;

class ResvStatus {
    public static int
            VACANT = 1,
            OCCUPIED = 2,
            RESERVED = 3,
            UNDER_MT = 4;
}

public class Reservation implements Serializable{
    private int resvNo, resvStatus, adultNo, kidNo, roomFloor, roomNo, guestID;
    private Date dateCheckIn, dateCheckOut;


    public Reservation(int resvNo,
                       int roomFloor,
                       int roomNo,
                       int guestID,
                       Date dateCheckIn,
                       int adultNo,
                       int kidNo
                       ) {
        this.resvNo = resvNo;
        this.adultNo = adultNo;
        this.kidNo = kidNo;
        this.roomFloor = roomFloor;
        this.roomNo = roomNo;
        this.guestID = guestID;
        this.dateCheckIn = dateCheckIn;
    }
    
    public int getResvNo() {
        return resvNo;
    }

    public int getResvStatus() {
        return resvStatus;
    }

    public void setResvStatus(int resvStatus) {
        this.resvStatus = resvStatus;
    }

    public int getAdultNo() {
        return adultNo;
    }

    public void setAdultNo(int adultNo) {
        this.adultNo = adultNo;
    }

    public int getKidNo() {
        return kidNo;
    }

    public void setKidNo(int kidNo) {
        this.kidNo = kidNo;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }
}
