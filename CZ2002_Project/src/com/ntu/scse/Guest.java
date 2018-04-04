package com.ntu.scse;

class IDType{
    public static final int
            DRIVINGLICENSE = 1,
            PASSPORT = 2;
}
public class Guest {
    private int guestID, idType;
    private char gender;
    private String firstName, lastName, creditCardNo, address, country, idNumber;

    public Guest(int guestID,
                 String firstName,
                 String lastName,
                 char gender,
                 String creditCardNo,
                 String address,
                 int idType,
                 String idNumber) throws InvalidInfoException {
        this.guestID = guestID;
        this.gender = gender;
        this.idType = idType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCardNo = creditCardNo;
        this.address = address;
        this.idNumber = idNumber;
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

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setGender(char gender) throws InvalidInfoException {
        if (Character.toUpperCase(gender) == 'M' ||
                Character.toUpperCase(gender) == 'F') {
            this.gender = gender;
        } else {
            throw new InvalidInfoException("Setting Gender");
        }
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
