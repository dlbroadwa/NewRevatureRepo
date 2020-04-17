package com.ex.models;

public class User {
    int cardNumber;
    String firstName;
    String lastName;

    public User() {}
    public User(int cardNum, String fName, String lName) {
        cardNumber = cardNum;
        firstName = fName;
        lastName = lName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
