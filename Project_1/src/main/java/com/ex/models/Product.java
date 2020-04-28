package com.ex.models;
/*
 * Products is used to set and get information about products in the "store"
 * Created by: Perry Lee on April 24 2020
 *      Perry Lee: [Added productId, description,price, and qty (With Getters and Setters)
 *                  Create Product Constructors]-April 24
 *      Paityn Maynard: Added productTypeId (With Getters and Setters) and Renamed price->priceInCents -April 28
 */

public class Product {//Start of Product Class

//Instant Variables
    private int productID,priceInCents,qty,productTypeID;
    private String description;

//Constructors
    public Product() {}

    public Product(int id, int prdType, String desc, int priceInCents, int qty) {
        this.productID = id;
        this.productTypeID = prdType;
        this.description = desc;
        this.priceInCents = priceInCents;
        this.qty = qty;
    }
//Getters
    public int getProductID() {
        return productID;
    }

    public int getProductTypeID() {
        return productTypeID;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return priceInCents;
    }

    public int getQty() {
        return qty;
    }

//Setters
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductTypeID(int productTypeID) {
        this.productTypeID = productTypeID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.priceInCents = priceInCents;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}//End of Product Class
