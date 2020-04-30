package com.ex.products_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.GenericDAO;
import com.ex.data.ProductSQLDatabase;
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

/**
 * ProductAdd Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added isManager, isEmployee, Constructors, doScreen Method -April 30
 */
public class ProductAdd {
//Instant Variables
    private Boolean isManager, isEmployee;
    private Product product= new Product();
    private String name, type;
    private int id,price,qty;

//Constructors
    public ProductAdd(){

    }

    public ProductAdd(Boolean isEmployee, Boolean isManager){
        this.isEmployee = isEmployee;
        this.isManager = isManager;
    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection();//ADD DATABASE INFORMATION
        GenericDAO<Product,Integer> products = new ProductSQLDatabase(connectionUtils);

        //SET variables THROUGH HTML?
        product.setProductID(id);
        product.setName(name);
        product.setProductType(type);
        product.setPrice(price);
        product.setQty(qty);

        products.add(product);
        return null;
    }
}
