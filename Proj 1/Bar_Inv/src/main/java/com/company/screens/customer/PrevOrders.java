package com.company.screens.customer;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.ItemService;
import com.company.services.OrdersService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class PrevOrders implements Screen {
    @Override
    public Screen doScreen(Application app) throws IOException, SQLException {
        OrdersService ordersService = ((BarInventoryApplication) app).getOrdersService();

        //display open orders for user
        System.out.println("See your open orders");
        String user = ((BarInventoryApplication)app).getCurrentUser();
        ordersService.displayUserOrders(user);

        System.out.println("Press Enter to return to the previous menu");
        System.in.read();
        return new CustMenu();

    }
}
