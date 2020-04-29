package com.ex.data;

import com.ex.models.Order;
import com.ex.utils.DatabaseConnection;

import java.util.List;

/**
 *   OrderSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *   Perry Lee: Added the add method, findById method, update method, and remove method - April 28
 *   Paityn Maynard: Added findAll method, DatabaseConnection and Constructor - April 29
 */
public class OrderSQLDatabase implements GenericDAO<Order, Integer> {//Start OrderSQLDatabase Class
//Instant Variables
    private final DatabaseConnection dc;

//Constructors
    public OrderSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

//Methods
    public List<Order> findAll() {
        return null;
    }

    public boolean add(Order order) {
        return false;
    }

    public Order findByID(Integer orderNumber) {
        return null;
    }

    public boolean update(Integer orderNumber, Order newOrderInfo) {
        return false;
    }

    public boolean remove(Integer orderNumber) {
        return false;
    }

}//End OrderSQLDatabase Class
