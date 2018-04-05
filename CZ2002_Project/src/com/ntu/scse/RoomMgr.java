package com.ntu.scse;


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
    private Room[][] roomList = new Room[6][8];

    public RoomMgr() throws InvalidInfoException { //INITIALIZING
        this.roomList = readRoomList();
    }

    private Room[][] readRoomList() throws InvalidInfoException { //INITIALIZING THE ROOM FROM THE FILE
        Room[][] roomList = new Room[6][8];
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

        return roomList;
    }
//    OTHER WAYS OF INITIALIZATION ALSO CAN
//    OR JUST NO NEED INITIALIZATION BUT READ IN BINARY FILE FOR EVERY UPDATING OR SEARCHING

    private void writeRoomList(){} //DUNNO HOW

    public void updateRoom(int floor, int rm, int choice, double value) throws InvalidInfoException {
        roomList = readRoomList();
        try {
            switch (choice) {
                case ROOMTYPE:
                    roomList[floor - 2][rm - 1].setType((int) value);
                    break;

                case ROOMRATE:
                    roomList[floor - 2][rm - 1].setRate(value);
                    break;

                case BEDTYPE:
                    roomList[floor - 2][rm - 1].setBedtype((int) value);
                    break;

                case ROOMFACING:
                    roomList[floor - 2][rm - 1].setFacing((int) value);
                    break;

                case ROOMSTATUS:
                    roomList[floor - 2][rm - 1].setStatus((int) value);
                    break;

                case ROOMWIFI:
                    roomList[floor - 2][rm - 1].setWifi(!((int) value == 0));
                    break;

                case ROOMSMOKING:
                    roomList[floor - 2][rm - 1].setSmoking(!((int) value == 0));
                    break;

                default:
                    throw new InvalidInfoException("Update Room");
            }
            writeRoomList();

        } catch (Exception NullPointerException) {

        }
    }

    public roomStatusRecord checkStatus(int floor, int rm) throws InvalidInfoException { // RETURN the Status index
        roomList = readRoomList();
        roomStatusRecord rmRecord = new roomStatusRecord(floor, rm,
                roomList[floor-2][rm-1].getRoomType(), roomList[floor-2][rm-1].getRoomStatus());
        return rmRecord;
    }

    public roomStatusRecord[][] reportStatus() throws InvalidInfoException {
        roomList = readRoomList();
        roomStatusRecord[][] roomReport = new roomStatusRecord[6][8];
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 8; j++){
                checkStatus(i+2, j+1);
            }
        }
        return roomReport;
    }
}