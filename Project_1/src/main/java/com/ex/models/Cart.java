package com.ex.models;

import com.ex.services.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a shopping cart
 */
public class Cart {
    private List<Product> cart;
    private int subtotal;

    /**
     * Constructs an empty shopping cart.
     */
    public Cart() {
        cart = new ArrayList<>();
        subtotal = 0;
    }

    /**
     * Retrieves the contents of the shopping cart.
     * @return A list of products in the cart
     */
    public List<Product> getContents() {
        return cart;
    }

    /**
     * Adds an item to the shopping cart.
     * If the item already exists in the shopping cart, the requested quantity is added to that item's quantity.
     * @param prod the product to add to the cart
     * @param qty the quantity of the product to add. If a nonpositive value is specified, the method has no effect.
     */
    public void addToCart(Product prod, int qty) {
        if (prod == null || qty <= 0)
            return;

        for (Product p: cart) {
            if (p.getProductID() == prod.getProductID()) {
                // Update the price just in case
                p.setPrice(prod.getPrice());
                p.setQty(p.getQty() + qty);
                return;
            }
        }

        prod.setQty(qty);
        cart.add(prod);
    }

    /**
     * Changes the quantity of an item in the shopping cart.
     * Setting the quantity to 0 removes the item from the cart.
     * This method has no effect if the item is not currently in the cart.
     * @param prod the product whose quantity should be updated
     * @param newQty the new quantity of the item. If a negative value is specified, the method has no effect.
     */
    public void editQuantity(Product prod, int newQty) {
        if (prod == null || newQty < 0)
            return;
        else if (newQty == 0) {
            remove(prod);
            return;
        }

        for (Product p: cart) {
            if (p.getProductID() == prod.getProductID()) {
                p.setQty(newQty);
                return;
            }
        }
    }

    /**
     * Removes all quantities of an item from the shopping cart.
     * This method has no effect if the item is not currently in the cart.
     * @param prod the product to remove
     */
    public void remove(Product prod) {
        if (prod == null)
            return;
        cart.removeIf(p -> p.getProductID() == prod.getProductID());
    }

    /**
     * Clears the shopping cart
     */
    public void clear() {
        cart.clear();
        subtotal = 0;
    }

    /**
     * Gets the total price of all items in the cart.
     * @return The subtotal
     */
    public int getSubtotal() {
        subtotal = 0;
        for (Product prod: cart) {
            subtotal += (prod.getPrice() * prod.getQty());
        }

        return subtotal;
    }

    /**
     * Performs validation on the shopping cart, ensuring that the price and quantity of each item is
     * consistent with the in-stock price and availability of that item.
     * @param ps the ProductService providing access to the products database
     */
    public void validate(ProductService ps) {
        if (ps == null)
            return;

        // Remove all nonexistent or out-of-stock items
        cart.removeIf(p -> {
            Product prod = ps.getProductByID(p.getProductID());
            return prod == null || prod.getQty() <= 0;
        });

        // Update prices, quantities, and subtotal
        subtotal = 0;
        for (Product prod: cart) {
            Product stockInfo = ps.getProductByID(prod.getProductID());
            prod.setPrice(stockInfo.getPrice());
            if (prod.getQty() > stockInfo.getQty())
                prod.setQty(stockInfo.getQty());
            subtotal += (prod.getPrice() * prod.getQty());
        }
    }
}
