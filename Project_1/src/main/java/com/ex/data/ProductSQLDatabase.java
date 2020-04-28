package com.ex.data;

import com.ex.models.Product;
/**
 *   ProductSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 */
public class ProductSQLDatabase implements GenericDAO<Product, Integer> {//Start of ProductsSQLDatabase

//Methods
    public boolean add(Product newObj) {
        return false;
    }

    public Product findByID(Integer integer) {
        return null;
    }

    public boolean update(Integer integer, Product newObj) {
        return false;
    }

    public boolean remove(Integer integer) {
        return false;
    }

}//End of ProductSQLDatabase
