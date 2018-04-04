package com.ntu.scse;

import java.util.Date;
import com.ntu.scse.InvalidInfoException.*;
import com.ntu.scse.Status.*;


public class Reservation {
    private int resvNo, resvStatus;
    private Guest guest;
    private Date dateCheckIn, dateCheckOut;

    public Reservation(int resvNo,
                       int resvStatus,
                       Guest guest,
                       Date dateCheckIn) {
        this.resvNo = resvNo;
        this.resvStatus = resvStatus;
        this.guest = guest;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = null;
    }

    public int getResvNo() {
        return resvNo;
    }

    public void setResvNo(int resvNo) {
        this.resvNo = resvNo;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getResvStatus() {
        return resvStatus;
    }

    public void setResvStatus(int resvStatus) {
        this.resvStatus = resvStatus;
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
