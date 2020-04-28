package com.ex.models;
/*
 * Account is used to set and get information of users logging in
 * Created by: Paityn Maynard on April 28 2020
 * Paityn Maynard: [Added orderConfirmation, customer (With Getters and Setters)
 *                  Created Orders Constructors]-April 25
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Orders {//Start of Orders Class
//Instant Variables
    int orderConfirmation, productId, qty;
    String customer, status;

//Constructors
    public Orders(){}

    public Orders(int orderConfirmation, String customer,int productId,int qty, String status){
        this.orderConfirmation = orderConfirmation;
        this.customer = customer;
        this.productId = productId;
        this.qty = qty;
        this.status = status;
    }

//Getters
    public int getOrderConfirmation() {
        return orderConfirmation;
    }

    public int getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public String getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

//Setters
    public void setOrderConfirmation(int orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}//End of Orders Class
