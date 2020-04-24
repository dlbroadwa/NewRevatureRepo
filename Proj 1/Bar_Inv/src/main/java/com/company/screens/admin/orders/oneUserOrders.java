package com.company.screens.admin.orders;

import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.AddInventory;
import com.company.screens.admin.Menu;
import com.company.services.OrdersService;
import com.company.services.UserService;

import java.util.List;
import java.util.Scanner;

public class oneUserOrders implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        OrdersService ordersService = ((BarInventoryApplication)app).getOrdersService();
        UserService userService = ((BarInventoryApplication)app).getUserService();

        //get a list of all users
        System.out.println("Here are all of our customers:");
        List<User> users = userService.getAllUsers();
        for (User o : users){
            System.out.println(o.getUserName());
        }

        //choose a customer to view their open orders
        System.out.println("Enter the name of the customer whose orders you wish to view");
        String name = scanner.nextLine();
        //view all the open orders for that customer
        ordersService.displayUserOrders(name);

        //option to mark orders as complete
        System.out.println("Would you like to mark an order as complete? [y/n]");
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            System.out.println("Enter the order ID of the order to mark complete");
            String orderID = scanner.nextLine();
            ordersService.markOrderComplete(orderID);

            System.out.println("Order is complete. Would you like to do another [y/n]");
            String cont1 = scanner.nextLine();
            if (cont1.equals("y")) {
                return new oneUserOrders();
            } else {
                return new viewCustOrders();
            }

        } else {
            System.out.println("Alright, would you like to view another customer's orders? [y/n]");
            String contAgain = scanner.nextLine();
            if (contAgain.equals("y")) {
                return new oneUserOrders();
            } else {
                return new viewCustOrders();
            }

        }
    }
}
