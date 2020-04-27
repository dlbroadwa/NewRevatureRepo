package com.ex.models;

public class Product {//Start of Product Class

//Instant Variables
    private int productID,productTypeID;
    private String description;
    private int price;
    private int qty;

//Constructors
    public Product() {}

    public Product(int id, int prdType, String desc, int price, int qty) {
        this.productID = id;
        this.productTypeID = prdType;
        this.description = desc;
        this.price = price;
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
        return price;
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
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}//End of Product Class
