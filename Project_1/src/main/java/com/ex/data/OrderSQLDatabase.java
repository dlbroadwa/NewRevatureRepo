package com.ex.data;

import com.ex.models.Order;

public class OrderSQLDatabase implements GenericDAO<Order, Integer> {
    @Override
    public boolean add(Order order) {
        return false;
    }

    @Override
    public Order findByID(Integer orderNumber) {
        return null;
    }

    @Override
    public boolean update(Integer orderNumber, Order newOrderInfo) {
        return false;
    }

    @Override
    public boolean remove(Integer orderNumber) {
        return false;
    }
}
