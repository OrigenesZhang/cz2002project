package com.ntu.scse;

import static com.ntu.scse.guestUpdateChoice.*;

class guestUpdateChoice {
    public static final int
            FIRSTNAME = 1,
            LASTNAME = 2,
            GENDER = 3,
            CREDITCARDNO = 4,
            ADDRESS = 5,
            IDTYPE = 6,
            IDNUMBER = 7;
}


public class GuestMgr {
    //    Functional Requirement:
//    1. Generate a Lookup table for guestID and first name, last name; (INITIALIZE)
//    2. Given a guestID, read/write his/her information from file;
//    3. ASK to create a new guest if not found or required

    class GuestBrief {
        private int guestID;
        private String lastName, firstName;

        public GuestBrief() {
            this.guestID = -1;
            this.lastName = null;
            this.firstName = null;
        }

        public GuestBrief(int guestID, String lastName, String firstName) {
            this.guestID = guestID;
            this.lastName = lastName;
            this.firstName = firstName;
        }
    }

    public GuestMgr() {//Initialize
        int n;
//        **Get the Total File Number
        n = 20;//TEST

        GuestBrief[] GuestList = new GuestBrief[n];

        for (int i = 0; i < n; i++) {
//            **Scan all files (Loop)
//            **Get guestID, last name and first name
            int guestID;
            String lastName, firstName;
            guestID = 1001;//TEST
            lastName = "John";//TEST
            firstName = "Doe";//TEST

            GuestList[i] = new GuestBrief(guestID, lastName, firstName);

//            **Write the list back to file

        }
    }

    public Guest readGuestInfo(int guestID) {
        Guest theGuest = null;
//        Open the file with the name guestID;
//        If UNSUCCESSFUL return exception.
        return theGuest;
    }

    public Guest readGuestInfo(String lastName, String firstName) {
        GuestBrief guestBrief = searchGuest(lastName, firstName);
        int guestID = guestBrief.guestID;

        Guest theGuest = readGuestInfo(guestID);
        return theGuest;
    }

    public void writeGuestInfo(int guestID, String value, int choice)
            throws InvalidInfoException {
        Guest theGuest = readGuestInfo(guestID);
        switch (choice) {
            case FIRSTNAME:
                theGuest.setFirstName(value);
                break;
            case LASTNAME:
                theGuest.setLastName(value);
                break;
            case CREDITCARDNO:
                theGuest.setCreditCardNo(value);
                break;
            case ADDRESS:
                theGuest.setAddress(value);
                break;
            case IDNUMBER:
                theGuest.setIdNumber(value);
                break;
            default:
                throw new InvalidInfoException("Write Guest Info of String");
        }

//        Write Back to the file!!

    } // firstName, lastName, creditCardNo, Addr, idNo;
//    NEED WRITE BACK TO THE FILE!

    public void writeGuestInfo(int guestID, char value, int choice)
            throws InvalidInfoException{
        if (choice!=GENDER){
            throw new InvalidInfoException("Updating Gender");
        } else {
            Guest theGuest = readGuestInfo(guestID);
            theGuest.setGender(value);
//            NEED WRITE BACK TO THE FILE!
        }
    } // gender;
//        NEED WRITE BACK TO THE FILE!

    public GuestBrief searchGuest(String lastName, String firstName) {
        GuestBrief[] guestList = readGuestList();
        for (GuestBrief guestBrief : guestList) {
            if (lastName.toUpperCase().equals(guestBrief.lastName.toUpperCase()) &&
                    firstName.toUpperCase().equals(guestBrief.lastName.toUpperCase())) {
                return guestBrief;
            }
        }
        return null;
    }

//    public GuestBrief searchGuest(int guestID) {
//        GuestBrief[] guestList = readGuestList();
//        for (GuestBrief guestBrief : guestList) {
//            if (guestBrief.guestID == guestID) {
//                return guestBrief;
//            }
//        }
//        return null;
//    }

    private GuestBrief[] readGuestList() {
        int n;
        n = 20; //TEST

        GuestBrief[] GuestList = new GuestBrief[n];
        for (int i = 0; i < n; i++) {
//            **Scan all files (Loop)
//            **Get guestID, last name and first name
            int guestID;
            String lastName, firstName;
            guestID = 1001;//TEST
            lastName = "John";//TEST
            firstName = "Doe";//TEST

            GuestList[i] = new GuestBrief(guestID, lastName, firstName);
        }
        return GuestList;
    }
}
