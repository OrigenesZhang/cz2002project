package com.ntu.scse;

//int roomType, double roomRate, int roomFloor, int roomNo, int bedType, int roomFacing,
//        int roomStatus, boolean enabledWifi, boolean isSmoking

import static com.ntu.scse.updateName.*;

class updateName {
    public static final int RMTYPE = 1, RMRATE = 2, BDTYPE = 3, RMFACING = 4,
            RMSTATUS = 5, RMWIFI = 6, RMSMOKING = 7;
}

public class RoomManager {

    private Room[][] roomList = new Room[6][8];

    public void createRoom() throws Main.InvalidInfoException {
        for (int floor = 0; floor < 6; floor++) {
            // Only private loop starts from zero,
            // any outer index/para remains the same as the real world info, floor-2, rm-1
            for (int rm = 0; rm < 8; rm++) {
//                try: read in one line from the file
//                except: don't have info; throw IO exception: !!!need define one
//                try: new room; Remember the output of the room info should be translated;
//                except: room return exception;
//                roomList[floor][rm] = theRoom;
            }
        }
    }

    public void updateRoom(int floor, int rm, int choice, double value) throws Main.InvalidInfoException {
        try {
            switch (choice) {
                case RMTYPE:
                    roomList[floor - 2][rm - 1].setType((int) value);

                case RMRATE:
                    roomList[floor - 2][rm - 1].setRate(value);

                case BDTYPE:
                    roomList[floor - 2][rm - 1].setBedtype((int) value);

                case RMFACING:
                    roomList[floor - 2][rm - 1].setFacing((int) value);

                case RMSTATUS:
                    roomList[floor - 2][rm - 1].setStatus((int) value);

                case RMWIFI:
                    roomList[floor - 2][rm - 1].setWifi(!((int) value == 0));

                case RMSMOKING:
                    roomList[floor - 2][rm - 1].setSmoking(!((int) value == 0));

                default:
                    throw new Main.InvalidInfoException("Update Room");
            }
        } catch (Exception NullPointerException) {

        }
    }

    public void print(){
//        dunno what happened
    }
}