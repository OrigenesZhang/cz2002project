package com.ntu.scse;

//<<<<<<< HEAD
import java.util.Date;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.ntu.scse.roomUpdateChoice.*;
import static com.ntu.scse.RoomStatus.*;

public class ReservationMgr {

    static ArrayList<Reservation> reservationList = null;
    int numOfReservation =0;

    public ReservationMgr(ArrayList<Reservation> reservationList) {
    	if (reservationList == null){ //Initialize
			this.reservationList = new ArrayList<>();
    		numOfReservation = 0;
		}
		else{
			this.reservationList = new ArrayList<>(reservationList);
			numOfReservation = this.reservationList.size();
		}
		System.out.println(numOfReservation + " Reservations loaded!");
    }

    public Reservation addReservation (
            int roomFloor,
            int roomNo,
            int guestID,
            Date dateCheckIn,
            int adultNo,
            int kidNo,
            RoomMgr roomMgr
            ) throws InvalidInfoException {
    	int newResvNo =0;
    	
    	if (checkGap() == false) { //no gap, add resv at back
    		newResvNo = numOfReservation + 1;
    	}
    	else { //add resv in between
    		for (int i = 0; i < reservationList.size(); i++) {
    			if (reservationList.get(i).getResvNo() != (i+1)) {
    				newResvNo = i+1;
    				break;
    			}
    		}
    	}
    	try {
        Reservation newResv = new Reservation(newResvNo, roomFloor, roomNo, guestID, dateCheckIn, adultNo, kidNo);
        reservationList.add(newResv);
        numOfReservation++;
        roomMgr.updateRoom(roomFloor, roomNo, ROOMSTATUS, RESERVED);
        System.out.println("Total number of reservations: " + numOfReservation);
        return newResv;
    	}
    	catch (InvalidInfoException e) {
            e.printStackTrace();
        }
        
        return null;
    	
        
    }
    
    public Reservation searchReservation(int resvNo) {
    	Reservation resv = null;
    	for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getResvNo() == resvNo) {
				resv = reservationList.get(i);
				return resv;
			}
		}
    	System.out.println("Reservation number does not exist!");
    	return resv;
    }

    public void deleteReservation (int resvNo){
    	for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getResvNo() == resvNo) {
				reservationList.remove(i);
				numOfReservation--;
				System.out.println("Total number of reservations: " + numOfReservation);
				return;
			}
		}
    	System.out.println("Reservation number does not exist!");
    }

    public ArrayList<Reservation> saveToFile() {
    	return reservationList;
    }
    
    private boolean checkGap() { //Checks if any gap due to previously deleted resv
    	if (numOfReservation == 0)
    		return false;
    	else
    		return !(reservationList.get(reservationList.size()-1).getResvNo() == numOfReservation);
    }
}
