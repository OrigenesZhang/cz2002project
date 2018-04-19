package com.ntu.scse;

import java.io.Serializable;

/**
 Represents a hotel Guest, with details of the guest being stored.
 One GuestManager can contain many Guests.
 @author Cai LingZhi, Liu Fangbing, Christopher Lim, Eliza Wong
 @version 1.0
 @since 19/04/2018
 */
public class Guest implements Serializable, Comparable<Guest>{
    /**
     * The Guest's unique identifier
     */
    private int guestID;
    /**
     * The Guest's gender
     */
    private char gender;
    /**
     * The Guest's first name
     */
    private String firstName;
    /**
     * The Guest's last name
     */
    private String lastName;
    /**
     * The Guest's credit card number
     */
    private String creditCardNo;
    /**
     * The Guest's address
     */
    private String address;
    /**
     * The Guest's country
     */
    private String country;
    /**
     * The Guest's national identification number
     */
    private String idNumber;
    /**
     * The Guest's identification document type
     */
    private String idType;
    /**
     * Creates a new Guest.
     * @param firstName The Guest's first name
     * @param lastName The Guest's last name
     * @param gender The Guest's gender
     * @param creditCardNo The Guest's credit card number
     * @param address The Guest's address
     * @param country The Guest's country
     * @param idType The Guest's identification document type
     * @param idNumber The Guest's national identification number
     */
    public Guest(int guestID,
                 String firstName,
                 String lastName,
                 char gender,
                 String creditCardNo,
                 String address,
                 String country,
                 String idType,
                 String idNumber){
        this.guestID = guestID;
        this.gender = gender;
        this.idType = idType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCardNo = creditCardNo;
        this.address = address;
        this.country = country;
        this.idNumber = idNumber;
    }
    /**
     * Gets the unique Guest identifier.
     * @return The Guest's identification number.
     */
    public int getGuestID() {
        return guestID;
    }
    /**
     * Gets the guest's identification document type
     * @return the guest's identification document type
     */
    public String getIdType() {
        return idType;
    }
    /**
     * Gets the gender of the guest
     * @return the gender of the guest
     */
    public char getGender() {
        return gender;
    }
    /**
     * Gets the first name of the guest
     * @return the first name of the guest
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Gets the last name of the guest
     * @return the last name of the guest
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Gets the credit card number of the guest
     * @return the credit card number of the guest
     */
    public String getCreditCardNo() {
        return creditCardNo;
    }
    /**
     * Gets the address of the guest
     * @return the address of the guest
     */
    public String getAddress() {
        return address;
    }
    /**
     * Gets the country of the guest
     * @return the country of the guest
     */
    public String getCountry() {
		return country;
	}
    /**
     * Gets the national identification number of the guest
     * @return the national identification number of the guest
     */
	public String getIdNumber() {
        return idNumber;
    }


    /**
     * Sets the identification document type for the guest
     * @param idType new ID document type to set
     *               1 = "DRIVING LICENSE", 2 = "PASSPORT"
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }
    /**
     * Sets the gender of the guest
     * @param gender new gender to set
     *               M = "MALE", F = "FEMALE"
     */
    public void setGender(char gender)  {
       this.gender = gender;
    }
    /**
     * Sets the first name of the guest
     * @param firstName new first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Sets the last name of the guest
     * @param lastName new last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Sets the credit card number of the guest
     * @param creditCardNo new credit card number to set
     */
    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }
    /**
     * Sets the address of the guest
     * @param address new address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Sets the country of the guest
     * @param country new country to set
     */
    public void setCountry(String country) {
		this.country = country;
	}
    /**
     * Sets the national identification number of the guest
     * @param idNumber new identification number to set
     */
	public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    /**
     * Overwritten method for sorting Guests according to their ID
     */
    @Override
    public int compareTo(Guest comparesTo) {
        int compareID=(comparesTo).getGuestID();
        return this.guestID-compareID;
    }
}
