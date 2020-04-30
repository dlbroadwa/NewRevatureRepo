package com.ex.orders_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.GenericDAO;
import com.ex.data.OrderSQLDatabase;
import com.ex.models.Order;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import java.util.List;

/**
 * OrderView Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added isManager, isEmployee, Constructors, doScreen Method -April 30
 */
public class OrderView implements Screen{

//Instant Variables
    Boolean isManager, isEmployee;

//Constructors
    public OrderView(){

    }

    public OrderView(Boolean isEmployee, Boolean isManager){
        this.isEmployee = isEmployee;
        this.isManager = isManager;
    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres","revature","project1");
        GenericDAO<Order,Integer> orders = new OrderSQLDatabase(connectionUtils);

        List<Order> allOrders = orders.findAll();
        for(Order o: allOrders){
            //PRINT OUT PRODUCTS SOMEHOW
        }
        return null;
    }
}
