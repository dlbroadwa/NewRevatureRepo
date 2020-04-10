package com.company.screens.customer;

import com.company.app.Application;
import com.company.screens.Screen;

public class PrevOrders implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //display order history for user
        System.out.println("See your old orders");
        return new CustMenu();

    }
}
