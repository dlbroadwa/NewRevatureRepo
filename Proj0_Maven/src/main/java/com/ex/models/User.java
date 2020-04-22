package com.ex.models;

import java.util.Objects;

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

    public String getName() { return lastName + ", " + firstName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cardNumber == user.cardNumber &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, firstName, lastName);
    }
}
