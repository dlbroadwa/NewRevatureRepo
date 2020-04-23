//Note: this class has a natural ordering that is inconsistent with equals.

package com.inventory.model;

import org.jetbrains.annotations.NotNull;

public class Warehouse implements SQL, Comparable<Warehouse> {
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

    @Override
    public String getSQLColumnFormat() {
        return "(\"id\", \"state\" , \"city\", \"address\", \"zipCode\") ";
    }

    @Override
    public String toSQLString() {
        return "(" + id + ", '" + state + "', '" + city + "', '" + address + "', " + zipCode + ") ";
    }

    @Override
    public int compareTo(@NotNull Warehouse warehouse) {
        int first = Integer.compare(id, warehouse.id);
        if(first != 0)
            return first;

        int second = state.compareTo(warehouse.state);
        if(second != 0)
            return second;

        int third = city.compareTo(warehouse.city);
        if(third != 0)
            return third;

        int fourth = address.compareTo(warehouse.address);
        if(fourth != 0)
            return fourth;

        return Integer.compare(zipCode, warehouse.zipCode);
    }
}