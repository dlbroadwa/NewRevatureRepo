package com.ex.model;

/**
 * @author that-team
 *This class defines an address.
 * @param addressID - identifying number for each address, used for identifying a user's address
 * @param number - numerical part of a street address, e.g. the 123 in 123 Fake St
 * @param street - street name, e.g. the Fake in 123 Fake St
 * @param address2 - second line of address, e.g. apartment number. It can be left blank
 * @param city - city
 * @param state - state, currently limited to 2 characters, may need to make larger for worldwide deployment
 * @param country - country
 * @param zipCode - zip code, may need to change for worldwide deployment
 *
 */

public class Address {
    private int addressID;
    private int number;
    private String street;
    private String address2;
    private String city;
    private String state;
    private String country;
    private int zipCode;

    public Address(){}

    public Address(int number, String street, String address2, String city, String state, String country, int zipCode) {
        this.number = number;
        this.street = street;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getAddressID() {
        return addressID;
    }
}
