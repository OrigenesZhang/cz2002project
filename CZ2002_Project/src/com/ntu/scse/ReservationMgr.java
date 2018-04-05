package com.ntu.scse;

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
}
