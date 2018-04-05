package com.ntu.scse;

import java.util.Date;

import static com.ntu.scse.roomUpdateChoice.*;
import static com.ntu.scse.RoomStatus.*;

public class ReservationMgr {
    private Reservation[] reservations;

    private Reservation[] readResv() {
        int n = 20;//TEST
//        Find how many records in the file
        Reservation[] reservations = new Reservation[n];
//        Read in the files to the reservations;
        return reservations;
    }//NEED READ IN FILES!

    public ReservationMgr() {
        reservations = readResv();
    }

    public Reservation addReservation (
            int resvNo,
            int roomFloor,
            int roomNo,
            int guestID,
            Date dateCheckIn,
            int adultNo,
            int kidNo,
            RoomMgr roomMgr
            ) throws InvalidInfoException {

        Reservation newResv = new Reservation(resvNo, roomFloor, roomNo, guestID, dateCheckIn, adultNo, kidNo);
        roomMgr.updateRoom(roomFloor, roomNo, ROOMSTATUS, RESERVED);
        return newResv;
    }

    public void deleteReservation (){}


}
