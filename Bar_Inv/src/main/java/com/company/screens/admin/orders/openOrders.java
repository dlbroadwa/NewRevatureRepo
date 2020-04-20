package com.company.screens.admin.orders;

import com.company.DAO.models.User;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.OrdersService;
import com.company.services.UserService;

import java.util.List;
import java.util.Scanner;

public class openOrders implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        OrdersService ordersService = ((BarInventoryApplication)app).getOrdersService();
        //display all open orders
        ordersService.displayOpenOrders();

        System.out.println("Would you like to mark an order as complete? [y/n]"); //option to mark an order as completed, which will remove it from any open order lists
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            System.out.println("Enter the order ID of the order to mark complete");  //use order id to mark orders as completed
            String orderID = scanner.nextLine();
            ordersService.markOrderComplete(orderID);

            System.out.println("Order is marked complete. Would you like to do another [y/n]");
            String cont1 = scanner.nextLine();
            if (cont1.equals("y")) {
                return new openOrders();
            } else {
                return new viewCustOrders();
            }

        } else {
            return new viewCustOrders();
            }

        }
    }
