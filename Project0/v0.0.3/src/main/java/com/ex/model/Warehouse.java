package com.ex.model;

public class Warehouse {
    public Warehouse(int id, String state, String city, String address, int zipCode) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    private final int id;
    private final String state;
    private final String city;
    private final String address;
    private final int zipCode;

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
