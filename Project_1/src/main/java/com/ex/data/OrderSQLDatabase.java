package com.ex.data;

import com.ex.models.Order;
/**
 *   OrderSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *
 */
public class OrderSQLDatabase implements GenericDAO<Order, Integer> {//Start OrderSQLDatabase Class

//Methods
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
