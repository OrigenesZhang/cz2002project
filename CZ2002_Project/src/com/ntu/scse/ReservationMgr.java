package com.ntu.scse;

<<<<<<< HEAD
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


=======
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReservationMgr {
	
	static ArrayList<Reservation> reservationList;
	int numOfReservation;
	
    public ReservationMgr(ArrayList<Reservation> reservationList) { //Initialize
        this.reservationList = new ArrayList<>(reservationList);
        numOfReservation = this.reservationList.size();
        System.out.println("Number of reservationss: " + numOfReservation); //FOR IO TESTING
    }
    
    public void saveToFile(String reservationFileName) { 
    	reservationList.add(new Reservation(1)); //FOR IO TESTING
    	reservationList.add(new Reservation(2)); //FOR IO TESTING
    	numOfReservation = reservationList.size();
    	
    	try {
			FileOutputStream foStream = new FileOutputStream(reservationFileName);
			BufferedOutputStream boStream = new BufferedOutputStream(foStream);
			ObjectOutputStream doStream = new ObjectOutputStream(boStream);
			
			for (int i = 0 ; i < numOfReservation ; i++) {
				doStream.writeObject(reservationList.get(i)); //Write reservation list into file
			}
			doStream.close();
		}
		catch (IOException e){
			System.out.println("[Reservation] File IO Error!" + e.getMessage());
		}
    }
>>>>>>> 79c26be47a76d2106884d5922c80c49939392d83
}
