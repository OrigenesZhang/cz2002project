package com.ntu.scse;

import static com.ntu.scse.guestUpdateChoice.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

	static ArrayList<Guest> guestList;
	int numOfGuest =0;
		
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
    
    public GuestMgr(ArrayList<Guest> guestList) { //Initialize
        this.guestList = new ArrayList<>(guestList);
        numOfGuest = this.guestList.size();
        System.out.println(numOfGuest + " Guests loaded!");
    }

    public Guest readGuestInfo(int guestID) {
        Guest theGuest = null;
        for (int i = 0; i < guestList.size(); i++) {
			if (guestList.get(i).getGuestID() == guestID) {
				theGuest = guestList.get(i);
				return theGuest;
			}
		}
    	System.out.println("Guest ID does not exist!");
        return theGuest;
    }

    public Guest readGuestInfo(String lastName, String firstName) {
        GuestBrief guestBrief = searchGuest(lastName, firstName);
        if (guestBrief == null) {
        	System.out.println("Guest does not exist!");
        	return null;
        }
        else {
        	int guestID = guestBrief.guestID;

            Guest theGuest = readGuestInfo(guestID);
            return theGuest;
        }
    }

    public void writeGuestInfo(int guestID, String value, int choice)
            throws InvalidInfoException {
        Guest theGuest = readGuestInfo(guestID);
        if (theGuest != null)
        {
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
        }

    } // firstName, lastName, creditCardNo, Addr, idNo;

    public void writeGuestInfo(int guestID, char value, int choice)
            throws InvalidInfoException {
        if (choice != GENDER) {
            throw new InvalidInfoException("Updating Gender");
        } else {
            Guest theGuest = readGuestInfo(guestID);
            if (theGuest != null)
            	theGuest.setGender(value);
        }
    } // gender;

    public void writeGuestInfo(int guestID, int value, int choice)
            throws InvalidInfoException {
        if (choice != IDTYPE) {
            throw new InvalidInfoException("Updating Gender");
        } else {
            Guest theGuest = readGuestInfo(guestID);
            if (theGuest != null)
            	theGuest.setIdType(value);
        }
    } // idType;

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

    private GuestBrief[] readGuestList() {
        int n = this.guestList.size();

        GuestBrief[] GuestList = new GuestBrief[n];
        for (int i = 0; i < n; i++) {
            int guestID;
            String lastName, firstName;
            guestID = guestList.get(i).getGuestID();
            lastName = guestList.get(i).getLastName();
            firstName = guestList.get(i).getFirstName();
            GuestList[i] = new GuestBrief(guestID, lastName, firstName);
        }
        return GuestList;
    }
    
    private boolean checkGap() { //Checks if any gap due to previously deleted guest
    	return !(guestList.get(guestList.size()-1).getGuestID() == numOfGuest);
    }

    public Guest addNewGuest(int guestID,
                            String firstName,
                            String lastName,
                            char gender,
                            String creditCardNo,
                            String address,
                            int idType,
                            String idNumber) {
    	int newGuestID =0;
        
    	if (checkGap() == false) { //no gap, add guest at back
    		newGuestID = numOfGuest + 1;
    	}
    	else { //add guest in between
    		for (int i = 0; i < guestList.size(); i++) {
    			if (guestList.get(i).getGuestID() != (i+1)) {
    				newGuestID = i+1;
    				break;
    			}
    		}
    	}
    	
    	try {

            Guest newGuest = new Guest(newGuestID, firstName, lastName, gender,
                    creditCardNo, address, idType, idNumber);
            guestList.add(newGuest);
            numOfGuest++;
            System.out.println("Total number of guests: " + numOfGuest);
            return newGuest;
        } catch (InvalidInfoException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    
    public void deleteGuest (int guestID){
    	for (int i = 0; i < guestList.size(); i++) {
			if (guestList.get(i).getGuestID() == guestID) {
				guestList.remove(i);
				numOfGuest--;
				System.out.println("Total number of guests: " + numOfGuest);
				return;
			}
		}
    	System.out.println("Guest ID does not exist!");
    }

    
    public void saveToFile(String guestFileName) { 
    	//numOfGuest = guestList.size();
    	if (numOfGuest == 0) { //Nothing to save
    		System.out.println("No guests to save to file!");

    	}
    	else {
	    	try {
				FileOutputStream foStream = new FileOutputStream(guestFileName);
				BufferedOutputStream boStream = new BufferedOutputStream(foStream);
				ObjectOutputStream doStream = new ObjectOutputStream(boStream);
				
				for (int i = 0 ; i < numOfGuest ; i++) {
					doStream.writeObject(guestList.get(i)); //Write guest list into file
				}
				System.out.println(numOfGuest + " Guests saved to " + guestFileName);
				doStream.close();
			}
			catch (IOException e){
				System.out.println("[Guest] File IO Error!" + e.getMessage());
			}
    	}
    }
}//Need to WRITE TO A NEW FILE!!!