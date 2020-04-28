package com.ex.data;

import com.ex.models.Product;

public class ProductSQLDatabase implements GenericDAO<Product, Integer> {
    @Override
    public boolean add(Product newObj) {
        return false;
    }

    @Override
    public Product findByID(Integer integer) {
        return null;
    }

    @Override
    public boolean update(Integer integer, Product newObj) {
        return false;
    }

    @Override
    public boolean remove(Integer integer) {
        return false;
    }
}
