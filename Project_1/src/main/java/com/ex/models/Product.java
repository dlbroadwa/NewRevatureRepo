package com.ex.models;

import java.util.Objects;

/**
 * Products is used to set and get information about products in the "store"
 * Created by: Perry Lee on April 24 2020
 *      Perry Lee: [Added productId, description,price, and qty (With Getters and Setters)
 *                  Create Product Constructors]-April 24
 *      Paityn Maynard: Added productTypeId (With Getters and Setters) and Renamed price to priceInCents -April 28
 */

public class Product {//Start of Product Class

//Instance Variables
    private int productID;
    private String name;
    private int priceInCents;
    private int qty;
    private String productType;

//Constructors
    public Product() {}

    public Product(int id, String name, String prdType, int priceInCents, int qty) {
        this.productID = id;
        this.name = name;
        this.productType = prdType;
        this.priceInCents = priceInCents;
        this.qty = qty;
    }
//Getters
    public int getProductID() {
        return productID;
    }

    public String getProductType() {
        return productType;
    }

    public String getName() {
        return name;
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

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.priceInCents = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productID == product.productID &&
                priceInCents == product.priceInCents &&
                qty == product.qty &&
                Objects.equals(name, product.name) &&
                Objects.equals(productType, product.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, priceInCents, qty, productType);
    }
}//End of Product Class
