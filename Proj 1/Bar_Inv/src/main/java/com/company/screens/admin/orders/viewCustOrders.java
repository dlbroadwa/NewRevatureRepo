package com.company.screens.admin.orders;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.*;
import com.company.screens.customer.CustMenu;
import com.company.services.OrdersService;

import java.util.Scanner;

public class viewCustOrders implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        OrdersService ordersService = ((BarInventoryApplication) app).getOrdersService();
        System.out.println("Choose an option, then press Enter: \n" +
                "1. View all open orders \n" +
                "2. View all open orders for one user \n" +
                "3. View open and completed orders \n" +
                "4. Previous Menu \n"+
                "Press 0 to exit");

        // for input
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                //view open orders
                System.out.println("View all open orders");
                return new openOrders();
            case 2:
                //view orders for one user
                System.out.println("View all open orders for one user");
                return new oneUserOrders();
            case 3:
                //view open and closed orders
                System.out.println("View open and completed orders");
                return new allOrders();
            case 4:
                //go to previous menu
                return new Menu();
            case 0:
                //exit
                System.out.println("K, byeeee");
                break;
            default:
                System.out.println("Invalid entry, let's try that again \n");
                return new viewCustOrders();


        }
        return null;
    }
}
