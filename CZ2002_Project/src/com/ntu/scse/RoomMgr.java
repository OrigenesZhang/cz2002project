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

public class RoomMgr {

    private Room[][] roomList = new Room[6][8];    
    
    public RoomMgr (Room[][] roomList) { //Initialize with room array from main
    	for (int i=0 ; i<6 ; i++) {
    		for (int j=0 ; j<8 ; j++) {
    			this.roomList[i][j] = roomList[i][j];
    			//System.out.print(roomList[i][j].getRoomFloor() + "-" + roomList[i][j].getRoomNo() + " ");
    		}
    		//System.out.println("");
    	}
    	
    }
    
    public void createRoom() throws InvalidInfoException { //INITIALIZING THE ROOM FROM THE FILE
    	
    	for (int floor = 0; floor < 6; floor++) {
            // Only private loop starts from zero,
            // Any outer index/para remains the same as the real world info, floor-2, rm-1
            for (int rm = 0; rm < 8; rm++) {
//                try: read in one line from the file
//                except: don't have info; throw IO exception: !!!need define one
//                try: new room; Remember the output of the room info should be translated;
//                except: room return exception;
//                roomList[floor][rm] = theRoom;
            }
        }
    	
        
    }

    public void updateRoom(int floor, int rm, int choice, double value) throws InvalidInfoException {
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
        } catch (Exception NullPointerException) {

        }
    }

    public void print(){
//        dunno what happened
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