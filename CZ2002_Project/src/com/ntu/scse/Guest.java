package com.ntu.scse;

import com.ntu.scse.InvalidInfoException.*;

public class Guest {
    private int guestID, idType;
    private char gender;
    private String[] firstName, lastName, creditCardNo, address, country, id;

    public Guest(int guestID,
                 int idType,
                 char gender,
                 String[] firstName,
                 String[] lastName,
                 String[] creditCardNo,
                 String[] address,
                 String[] id) throws InvalidInfoException {
        this.guestID = guestID;
        this.gender = gender;
        this.idType = idType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCardNo = creditCardNo;
        this.address = address;
        this.id = id;
    }

    //GETTER
    public int getGuestID() {
        return guestID;
    }

    public int getIdType() {
        return idType;
    }

    public char getGender() {
        return gender;
    }

    public String[] getFirstName() {
        return firstName;
    }

    public String[] getLastName() {
        return lastName;
    }

    public String[] getCreditCardNo() {
        return creditCardNo;
    }

    public String[] getAddress() {
        return address;
    }

    public String[] getCountry() {
        return country;
    }

    public String[] getId() {
        return id;
    }

    //SETTER

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setFirstName(String[] firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String[] lastName) {
        this.lastName = lastName;
    }

    public void setCreditCardNo(String[] creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public void setId(String[] id) {
        this.id = id;
    }
}
