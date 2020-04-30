package com.ex.products_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.GenericDAO;
import com.ex.data.ProductSQLDatabase;
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

import java.util.List;

/**
 * ProductView Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added isManager, isEmployee, Constructors, doScreen Method -April 30
 */
public class ProductView implements Screen {
//Instant Variables
    Boolean isManager, isEmployee;

//Constructors
    public ProductView(){

    }

    public ProductView(Boolean isEmployee, Boolean isManager){
        this.isEmployee = isEmployee;
        this.isManager = isManager;
    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection();//ADD DATABASE INFORMATION
        GenericDAO<Product,Integer> products = new ProductSQLDatabase(connectionUtils);

        List<Product> allProducts = products.findAll();
        for(Product p: allProducts){
            //PRINT OUT PRODUCTS SOMEHOW
        }
        return null;
    }
}
