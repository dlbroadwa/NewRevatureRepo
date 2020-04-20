package com.company.screens.admin.orders;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.OrdersService;

import java.util.Scanner;

public class allOrders implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        OrdersService ordersService = ((BarInventoryApplication) app).getOrdersService();
        //this option lets the admin view orders that are open and ones that have already been completed
        System.out.println("Here are all open and completed orders");
        ordersService.displayAllOrders();

        System.out.println("Press Enter to return to the previous menu");
        System.in.read();
        return new viewCustOrders();
        }
    }

