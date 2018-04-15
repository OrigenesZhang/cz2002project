package com.ntu.scse;

import java.io.Serializable;


public class Guest implements Serializable{
    private int guestID;
    private char gender;
    private String firstName, lastName, creditCardNo, address, country, idNumber, idType;

    public Guest(int guestID,
                 String firstName,
                 String lastName,
                 char gender,
                 String creditCardNo,
                 String address,
                 String country,
                 String idType,
                 String idNumber)  {
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

    //GETTER
    public int getGuestID() {
        return guestID;
    }

    public String getIdType() {
        return idType;
    }

    public char getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public String getAddress() {
        return address;
    }
    
    public String getCountry() {
		return country;
	}

	public String getIdNumber() {
        return idNumber;
    }

    //SETTER

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setGender(char gender)  {
       this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setCountry(String country) {
		this.country = country;
	}

	public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
