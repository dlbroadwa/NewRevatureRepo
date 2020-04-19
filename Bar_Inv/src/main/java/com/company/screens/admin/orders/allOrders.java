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
        System.out.println("Here are all open and completed orders");
        ordersService.displayAllOrders();

        System.out.println("Press Enter to return to the previous menu");
        System.in.read();
        return new viewCustOrders();
        }
    }

