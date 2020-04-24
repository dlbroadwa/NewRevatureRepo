package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;
import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import com.company.app.BarInventoryApplication;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;

public class OrdersService {
    private Repository<Order, String, String> repo;

    public OrdersService(Repository<Order,String, String> repo) {this.repo=repo;}

    public void addOrder(String name, int id, int quant){
        //take a username, and Item ID, and a quantity to make a new order
        Order newOrder = new Order();
        newOrder.setCustomerName(name);
        newOrder.setItemID(id);
        newOrder.setQuantity(quant);
        this.repo.save(newOrder); //save the new order in prevorders

    }

    public void displayAllOrders() throws SQLException {
        //display all open and closed orders
        List<Order> tmp = this.repo.findAll();
        //loop through the result set
        System.out.println("Order ID, Customer, Item ID, Quantity");
        for (Order o : tmp){
            System.out.println(o.getOrderID()+", "+o.getCustomerName()+", "+o.getItemID()+", "+o.getQuantity());
        }

    }

    public void displayOpenOrders() throws SQLException {
        //display orders that are still open
        List<Order> tmp = this.repo.findAll();
        System.out.println("Order ID, Customer, Item ID, Quantity");
        //loop through the result set
        for (Order o : tmp){
            int complete = o.getMarked_complete();
            if(complete ==1) {
                //if the order is marked complete, do nothing
            }else{
                System.out.println(o.getOrderID()+", "+o.getCustomerName()+", "+o.getItemID()+", "+o.getQuantity());
            }
        }
    }

    public void markOrderComplete(String id){
        //take an open order and mark it complete
        //take an input to define the order ID
        Order completeOrder = this.repo.findByID(id);
        this.repo.updateByID(completeOrder);
    }

    public void displayUserOrders(String name) throws SQLException {
        //display all the open users for one user
        //takes an input to define the username you want to look for
        List<Order> tmp = this.repo.findAllForName(name);
        System.out.println("OrderID, Customer, Item ID, Quantity");
        for (Order o : tmp){
            int complete = o.getMarked_complete();
            if (complete==1){
                //do nothing
            }else  {
                System.out.println(o.getOrderID()+", "+ o.getCustomerName()+", "+o.getItemID()+", "+o.getQuantity());
            }
        }


    }

}
