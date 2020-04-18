package com.company.screens.admin;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.customer.CustMenu;
import com.company.services.OrdersService;

public class viewCustOrders implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        OrdersService ordersService = ((BarInventoryApplication) app).getOrdersService();
        System.out.println("View all orders");
        ordersService.displayAllOrders();

        System.out.println("Press Enter to return to the previous menu");
        System.in.read();
        return new Menu();
    }
}
