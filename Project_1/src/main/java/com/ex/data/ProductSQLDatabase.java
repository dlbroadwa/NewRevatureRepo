package com.ex.data;

import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;

import java.util.List;

/**
 *   ProductSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *   Perry Lee: Added added the add method, findById method, update method, and remove method - April 28
 *   Paityn Maynard: Added findAll method, DatabaseConnection and Constructor - April 29
 */
public class ProductSQLDatabase implements GenericDAO<Product, Integer> {//Start of ProductsSQLDatabase
//Instant Variables
    private final DatabaseConnection dc;

//Constructors
    public ProductSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

//Methods
    public List<Product> findAll() {
        return null;
    }

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
