package com.ex.data;

/**
 * Wrapper class that exists solely to provide information to add_to_cart.html about the item that was just added
 */
public class AddedCartInfo {
    public String name;
    public int subtotal;

    public AddedCartInfo() {
        name = "";
        subtotal = 0;
    }
    public AddedCartInfo(String name, int subtotal) {
        this.name = name;
        this.subtotal = subtotal;
    }
}
