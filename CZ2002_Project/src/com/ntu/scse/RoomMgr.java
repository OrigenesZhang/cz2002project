package com.ntu.scse;

//int roomType, double roomRate, int roomFloor, int roomNo, int bedType, int roomFacing,
//        int roomStatus, boolean enabledWifi, boolean isSmoking

import static com.ntu.scse.roomUpdateChoice.*;

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
}