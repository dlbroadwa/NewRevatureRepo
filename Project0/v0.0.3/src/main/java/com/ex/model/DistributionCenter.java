package com.ex.model;

public class DistributionCenter {
    public DistributionCenter(int id, String state, String address, int zipCode) {
        this.id = id;
        this.state = state;
        this.address = address;
        this.zipCode = zipCode;
    }

    private final int id;
    private final String state;
    private final String address;
    private final int zipCode;

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "DistributionCenter{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
