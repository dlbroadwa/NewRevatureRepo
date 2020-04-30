package com.ex.orders_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.GenericDAO;
import com.ex.data.OrderSQLDatabase;
import com.ex.models.Order;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

/**
 * OrderDelete Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added order, id, isManager, isEmployee, Constructors, doScreen Method -April 30
 */
public class OrderDelete {
//Instant Variables
    private Boolean isManager, isEmployee;
    private Order order= new Order();
    private int id;

//Constructors
    public OrderDelete(){

    }

    public OrderDelete(Boolean isEmployee, Boolean isManager){
        this.isEmployee = isEmployee;
        this.isManager = isManager;
    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres","revature","project1");
        GenericDAO<Order,Integer> products = new OrderSQLDatabase(connectionUtils);

        //SET id THROUGH HTML?

        order.setOrderConfirmation(id);

        products.remove(order.getOrderConfirmation());
        return null;
    }
}
