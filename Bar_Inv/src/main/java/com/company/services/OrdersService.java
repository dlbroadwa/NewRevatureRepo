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
        Order newOrder = new Order();
        newOrder.setCustomerName(name);
        newOrder.setItemID(id);
        newOrder.setQuantity(quant);
        this.repo.save(newOrder); //save the new order in prevorders

    }

    public void displayAllOrders() throws SQLException {
        List<Order> tmp = this.repo.findAll();
        System.out.println("Order ID, Customer, Item ID, Quantity");
        for (Order o : tmp){
            System.out.println(o.getOrderID()+", "+o.getCustomerName()+", "+o.getItemID()+", "+o.getQuantity());
        }

    }

    public void displayOpenOrders() throws SQLException {
        List<Order> tmp = this.repo.findAll();
        System.out.println("Order ID, Customer, Item ID, Quantity");
        for (Order o : tmp){
            int complete = o.getMarked_complete();
            if(complete ==1) {
                //do nothing
            }else{
                System.out.println(o.getOrderID()+", "+o.getCustomerName()+", "+o.getItemID()+", "+o.getQuantity());
            }
        }
    }

    public void markOrderComplete(String id){
        Order completeOrder = this.repo.findByID(id);
        this.repo.updateByID(completeOrder);
    }

    public void displayUserOrders(String name) throws SQLException {
        List<Order> tmp = this.repo.findAllForName(name);
        System.out.println("OrderID, Customer, Item ID, Quantity");
        for (Order o : tmp){
            int complete = o.getMarked_complete();
            if (complete==1){
                break;
            }else  {
                System.out.println(o.getOrderID()+", "+ o.getCustomerName()+", "+o.getItemID()+", "+o.getQuantity());
            }
        }


    }

}
