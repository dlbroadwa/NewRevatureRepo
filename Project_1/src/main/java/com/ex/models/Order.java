package com.ex.models;
/*
 * Account is used to set and get information of users logging in
 * Created by: Paityn Maynard on April 28 2020
 * Paityn Maynard: [Added orderConfirmation, customer (With Getters and Setters)
 *                  Created Orders Constructors]-April 25
 */

import java.util.List;

public class Order {//Start of Order Class
//Instant Variables
    private int orderConfirmation;
    private Account customer;
    // List of products (with sale price and quantity)
    private List<Product> orderProducts;
    private String status;

//Constructors
    public Order(){}

    public Order(int orderConfirmation, Account customer, List<Product> products, String status){
        this.orderConfirmation = orderConfirmation;
        this.customer = customer;
        this.orderProducts = products;
        this.status = status;
    }

//Getters
    public int getOrderConfirmation() {
        return orderConfirmation;
    }

    List<Product> getOrderProducts() { return orderProducts; }

    public Account getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

//Setters
    public void setOrderConfirmation(int orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public void setOrderProducts(List<Product> products) { this.orderProducts = products; }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}//End of Order Class
