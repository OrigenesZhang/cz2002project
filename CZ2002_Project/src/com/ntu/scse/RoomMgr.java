package com.ntu.scse;

//int roomType, double roomRate, int roomFloor, int roomNo, int bedType, int roomFacing,
//        int roomStatus, boolean enabledWifi, boolean isSmoking

import static com.ntu.scse.roomUpdateChoice.*;
import java.io.*;

class roomUpdateChoice {
    public static final int
            ROOMTYPE = 1,
            ROOMRATE = 2,
            BEDTYPE = 3,
            ROOMFACING = 4,
            ROOMSTATUS = 5,
            ROOMWIFI = 6,
            ROOMSMOKING = 7;
}

class roomStatusRecord {
    private int roomFloor;
    private int roomNo;
    private int roomType;
    private int roomStatus;

    public roomStatusRecord(int roomFloor, int roomNo, int roomType, int roomStatus) {
        this.roomFloor = roomFloor;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
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

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }
}

public class RoomMgr {

    private static Room[][] roomList = new Room[6][8];    
    
    public RoomMgr (Room[][] roomList) { //Initialize with room array from main
    	for (int i=0 ; i<6 ; i++) {
    		for (int j=0 ; j<8 ; j++) {
    			this.roomList[i][j] = roomList[i][j];
    			System.out.print(this.roomList[i][j].getRoomFloor() + "-" + this.roomList[i][j].getRoomNo() + " "); //FOR IO TESTING
    		}
    		System.out.println(""); //FOR IO TESTING
    	}
    }
    

    public void updateRoom(int floor, int rm, int choice, double value) throws InvalidInfoException {
    	//roomList = readRoomList();
        try {
            switch (choice) {
                case ROOMTYPE:
                    roomList[floor - 2][rm - 1].setType((int) value);

                case ROOMRATE:
                    roomList[floor - 2][rm - 1].setRate(value);

                case BEDTYPE:
                    roomList[floor - 2][rm - 1].setBedtype((int) value);

                case ROOMFACING:
                    roomList[floor - 2][rm - 1].setFacing((int) value);

                case ROOMSTATUS:
                    roomList[floor - 2][rm - 1].setStatus((int) value);

                case ROOMWIFI:
                    roomList[floor - 2][rm - 1].setWifi(!((int) value == 0));

                case ROOMSMOKING:
                    roomList[floor - 2][rm - 1].setSmoking(!((int) value == 0));

                default:
                    throw new InvalidInfoException("Update Room");
            }
            //writeRoomList();
        } catch (Exception NullPointerException) {

        }
    }

<<<<<<< HEAD
    public roomStatusRecord checkStatus(int floor, int rm) throws InvalidInfoException { // RETURN the RoomStatus index
        roomList = readRoomList();
=======
    public roomStatusRecord checkStatus(int floor, int rm) throws InvalidInfoException { // RETURN the Status index
        //roomList = readRoomList();
>>>>>>> 79c26be47a76d2106884d5922c80c49939392d83
        roomStatusRecord rmRecord = new roomStatusRecord(floor, rm,
                roomList[floor-2][rm-1].getRoomType(), roomList[floor-2][rm-1].getRoomStatus());
        return rmRecord;
    }

    public roomStatusRecord[][] reportStatus() throws InvalidInfoException {
        //roomList = readRoomList();
        roomStatusRecord[][] roomReport = new roomStatusRecord[6][8];
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 8; j++){
                checkStatus(i+2, j+1);
            }
        }
        return roomReport;
    }
    
    
    public void saveToFile(String roomFileName) {
    	
    	try { //SIMULATE A CHANGE IN ROOM ATTRIBUTE BEFORE SAVING TO FILE, TEMPORARY ONLY
			roomList[0][0] = new Room(3,3);
		} catch (InvalidInfoException e) {
			e.printStackTrace();
		}
    	
    	try {
			FileOutputStream foStream = new FileOutputStream(roomFileName);
			BufferedOutputStream boStream = new BufferedOutputStream(foStream);
			ObjectOutputStream doStream = new ObjectOutputStream(boStream);
			
			for (int i=0 ; i<6 ; i++) { //Writes out 48 Room Objects by level: 02-01, 02-02, 02-03, ... , 07-07, 07-08
    			for (int j=0 ; j<8 ; j++) { //Writes out 48 Room Objects by level: 02-01, 02-02, 02-03, ... , 07-07, 07-08
        			doStream.writeObject(roomList[i][j]);
        		}
    		}
			doStream.close();
		}
		catch (IOException e){
			System.out.println("[Room] File IO Error!" + e.getMessage());
		}
    }
}