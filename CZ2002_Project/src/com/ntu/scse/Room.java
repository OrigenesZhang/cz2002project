package com.ntu.scse;

import static com.ntu.scse.Status.*;
import com.ntu.scse.InvalidInfoException.*;
import java.io.Serializable;

class BedType {
    public static final int SINGLE = 1, DOUBLE = 2, MASTER = 3;
}

class Facing {
    public static final int NORTH = 1, SOUTH = 2, EAST = 3, WEST = 4;
}

class Status {
    public static final int VACANT = 1;
    public static final int OCCUPIED = 2;
    public static final int RESERVED = 3;
    public static final int UNDER_MAINTENANCE = 4;
}

public class Room implements Serializable {
    private int roomType, roomFloor, roomNo, bedType, roomFacing, roomStatus;
    private double roomRate;
    private boolean enabledWifi, isSmoking;
    private final int roomTypeNum = 4; // Room Type: 1.Single, 2.Double, 3.Deluxe, 4.VIP Suite
    public final int maxFloor = 7, minFloor = 2; // Floor 2-7
    public final int maxNo = 8; // Room No 1-8
    public final int bedTypeNum = 3; // Bed Type: 1.single/ 2.double/ 3.master
    public final int facingNum = 4; // Facing: 1.North, 2.South, 3.East, 4.West
    public final int statusNum = 4; // Status: 1.Vacant, 2.Occupied, 3.Reserved, 4.Under Maintenance

    public Room(int roomFloor,
                int roomNo,
                int roomType,
                double roomRate,
                int bedType,
                int roomFacing,
                int roomStatus,
                boolean enabledWifi,
                boolean isSmoking)
            throws InvalidInfoException {

        this.setType(roomType);
        this.setRate(roomRate);
        this.setFloor(roomFloor);
        this.setNumber(roomNo);
        this.setBedtype(bedType);
        this.setFacing(roomFacing);
        this.setStatus(roomStatus);
        this.setWifi(enabledWifi);
        this.setSmoking(isSmoking);
    }
    
    public Room (int roomFloor, int roomNo) throws InvalidInfoException  { //TEMP CONSTRUCTOR TO TEST FILE IO
    	this.setFloor(roomFloor);
        this.setNumber(roomNo);
    }

//    public void avaiRoom() throws Main.InvalidInfoException {
//        this.setStatus(VACANT);
//    }
//
//    public void maintainRoom() throws Main.InvalidInfoException {
//        this.setStatus(UNDER_MAINTENANCE);
//    }

    //************SETTER*************
    public void setType(int type) throws InvalidInfoException {
        if (type < 1 || type > roomTypeNum) {
            throw new InvalidInfoException("Room Type");
        } else {
            this.roomType = type;
        }
    }

    public void setRate(double rate) {
        this.roomRate = rate;
    }

    public void setFloor(int floor) throws InvalidInfoException {
        if (floor < minFloor || floor > maxFloor) {
            throw new InvalidInfoException("Floor");
        } else {
            this.roomFloor = floor;
        }
    }

    public void setNumber(int number) throws InvalidInfoException {
        if (number < 1 || number > maxNo) {
            throw new InvalidInfoException("Room No");
        } else {
            this.roomNo = number;
        }
    }

    public void setBedtype(int bedtype) throws InvalidInfoException {
        if (bedtype < 1 || bedtype > bedTypeNum) {
            throw new InvalidInfoException("Bed Type");
        } else {
            this.bedType = bedtype;
        }
    }

    public void setFacing(int facing) throws InvalidInfoException {
        if (facing < 1 || facing > facingNum) {
            throw new InvalidInfoException("Facing");
        } else {
            this.roomFacing = facing;
        }
    }

    public void setStatus(int status) throws InvalidInfoException {
        if (status < 1 || status > statusNum) {
            throw new InvalidInfoException("Facing");
        } else {
            this.roomStatus = status;
        }
    }


    public void setWifi(boolean wifi) {
        this.enabledWifi = wifi;
    }

    public void setSmoking(boolean smoking) {
        this.isSmoking = smoking;
    }

    public int getRoomType() {
        return roomType;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getBedType() {
        return bedType;
    }

    public int getRoomFacing() {
        return roomFacing;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public double getRoomRate() {
        return roomRate;
    }

    public boolean isEnabledWifi() {
        return enabledWifi;
    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public int getRoomTypeNum() {
        return roomTypeNum;
    }
}
