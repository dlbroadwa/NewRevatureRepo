package com.ex.services;

import com.ex.data.GenericDAO;
import com.ex.data.ProductList;
import com.ex.models.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService {
    private final GenericDAO<Product, Integer> prodDatabase;

    private boolean verifyProdInfo(Product prodInfo) {
        // Check String fields for null
        if (prodInfo.getName() == null || prodInfo.getProductType() == null)
            return false;

        // Remove whitespace
        prodInfo.setName(prodInfo.getName().trim());
        prodInfo.setProductType(prodInfo.getProductType().trim());

        if (prodInfo.getName().isEmpty() ||        // Name should not be empty
                prodInfo.getPrice() < 0 ||             // Price cannot be negative
                prodInfo.getQty() < 0 ||               // Can't have negative quanity
                prodInfo.getProductID() < 0 ||         // No negative product IDs
                prodDatabase.findByID(prodInfo.getProductID()) != null) { // Product ID must not already exist

            System.out.println("Invalid parameters for new product!");
            return false;
        }

        return true;
    }

    public ProductService(GenericDAO<Product, Integer> dao) {
        prodDatabase = dao;
    }

    public boolean addNewProduct(Product prodInfo) {
        if (verifyProdInfo(prodInfo))
            return prodDatabase.add(prodInfo);
        else
            return false;
    }

    public Product getProductByID(int prodID) {
        Product ret = prodDatabase.findByID(prodID);
        if (ret == null)
            System.out.println("Product " + prodID + " not found!");
        return ret;
    }

    public List<Product> getAllProducts() {
        return prodDatabase.findAll();
    }

    public List<Product> getProductsByType(String prodType) {
        // Trim whitespace
        prodType = prodType.trim();

        List<Product> allProds = prodDatabase.findAll();
        // Sort by name, I suppose
        allProds.sort(Comparator.comparing(Product::getName));

        List<Product> results = new ArrayList<>();
        for (Product p: allProds) {
            if (p.getProductType().equals(prodType)) {
                results.add(p);
            }
        }

        return results;
    }

    public boolean editProduct(int prodID, Product updatedProdInfo) {
        if (prodDatabase.findByID(prodID) == null) {
            System.out.println("Product " + prodID + " not found!");
            return false;
        }
        if (!verifyProdInfo(updatedProdInfo))
            return false;

        return prodDatabase.update(prodID, updatedProdInfo);
    }

    public boolean deleteProduct(int prodID) {
        return prodDatabase.remove(prodID);
    }
}
