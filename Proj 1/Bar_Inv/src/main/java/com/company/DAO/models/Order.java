package com.company.DAO.models;

public class Order {
    //each order represents one customer buying one type of alcohol
    private int orderID;
    private String customerName;
    private int itemID;
    private int quantity;
    private int marked_complete;

    public Order(){}

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getMarked_complete() {
        return marked_complete;
    }

    public void setMarked_complete(int marked_complete) {
        this.marked_complete = marked_complete;
    }

}
