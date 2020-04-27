package com.ex.models;

public class Product {
    private int productID;
    private String description;
    private int price;
    private int qty;

    public Product() {}
    public Product(int id, String desc, int price, int qty) {
        this.productID = id;
        this.description = desc;
        this.price = price;
        this.qty = qty;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
