package com.company.DAO.models;

public class Item {
    private String itemName;
    private int id;
    private int onHand;
    private int lowLevel;
    private int optLevel;

    public Item(){}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOnHand() {
        return onHand;
    }

    public void setOnHand(int onHand) {
        this.onHand = onHand;
    }

    public int getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(int lowLevel) {
        this.lowLevel = lowLevel;
    }

    public int getOptLevel() {
        return optLevel;
    }

    public void setOptLevel(int optLevel) {
        this.optLevel = optLevel;
    }
}
