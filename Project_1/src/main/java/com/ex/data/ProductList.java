package com.ex.data;

import com.ex.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    public List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }
    public ProductList(List<Product> prods) {
        products = prods;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                '}';
    }
}
