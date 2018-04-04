package com.ntu.scse;

import java.util.Date;
import com.ntu.scse.InvalidInfoException.*;
import com.ntu.scse.Status.*;


public class Reservation {
    private int resvNo, resvStatus, guestID;
    private Date dateCheckIn, dateCheckOut;

    public Reservation(int resvNo,
                       int resvStatus,
                       int guestID,
                       Date dateCheckIn) {
        this.resvNo = resvNo;
        this.resvStatus = resvStatus;
        this.guestID = guestID;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = null;
    }

    public int getResvNo() {
        return resvNo;
    }

    public void setResvNo(int resvNo) {
        this.resvNo = resvNo;
    }

    public int getResvStatus() {
        return resvStatus;
    }

    public void setResvStatus(int resvStatus) {
        this.resvStatus = resvStatus;
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
