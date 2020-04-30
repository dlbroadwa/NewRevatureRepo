package com.ex.orders_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.GenericDAO;
import com.ex.data.OrderSQLDatabase;
import com.ex.models.Account;
import com.ex.models.Order;
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import java.util.List;

/**
 * OrderAdd Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added isManager, isEmployee, order, customer,Constructors, doScreen Method -April 30
 */
public class OrderAdd implements Screen {

//Instant Variables
    private Boolean isManager, isEmployee;
    private Order order = new Order();
    private Account costomer = new Account();
    private List<Product> products;//HOW TO INITIALIZE??

//Constructors
    public OrderAdd(){

    }

    public OrderAdd(Boolean isEmployee, Boolean isManager){
        this.isEmployee = isEmployee;
        this.isManager = isManager;
    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres","revature","project1");
        GenericDAO<Order,Integer> orders = new OrderSQLDatabase(connectionUtils);

        //SET variables THROUGH HTML?
        order.setCustomer(costomer);
        order.setOrderProducts(products);

        orders.add(order);
        return null;
    }
}
