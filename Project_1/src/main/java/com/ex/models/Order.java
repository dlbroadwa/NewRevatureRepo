package com.ex.models;
/**
 * Account is used to set and get information of users logging in
 * Created by: Paityn Maynard on April 28 2020
 * Paityn Maynard: [Added orderConfirmation, customer, status and, productId (With Getters and Setters)
 *                  Created Orders Constructors]-April 28
 * Perry Lee: [Added orderProducts, change customer (String to Account), Updated corresponding Getters and Setters] April 28
 */

import java.util.List;

public class Order {//Start of Order Class
    // I could use an enum here instead, but making these be ints is more database-friendly
    public static class Status {
        private Status() {}
        public static final int PROCESSING = 0;
        public static final int SHIPPED = 1;
        public static final int DELIVERED = 2;
    }

//Instance Variables
    private int orderConfirmation;
    private Account customer;
    // List of products (with sale price and quantity)
    private List<Product> orderProducts;
    private int status;

//Constructors
    public Order(){}

    public Order(int orderConfirmation, Account customer, List<Product> products, int status){
        this.orderConfirmation = orderConfirmation;
        this.customer = customer;
        this.orderProducts = products;
        this.status = status;
    }

//Getters
    public int getOrderConfirmation() {
        return orderConfirmation;
    }

    public List<Product> getOrderProducts() { return orderProducts; }

    public Account getCustomer() {
        return customer;
    }

    public int getStatusCode() {
        return status;
    }
    public String getStatus() {
        switch (status) {
            case Status.PROCESSING:
                return "Processing";
            case Status.SHIPPED:
                return "Shipped";
            case Status.DELIVERED:
                return "Delivered";
            default:
                return "Unknown";
        }
    }

//Setters
    public void setOrderConfirmation(int orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public void setOrderProducts(List<Product> products) { this.orderProducts = products; }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public void setStatusCode(int status) {
        this.status = status;
    }
}//End of Order Class
