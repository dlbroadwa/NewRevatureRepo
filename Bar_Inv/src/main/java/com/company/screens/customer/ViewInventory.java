package com.company.screens.customer;

import com.company.app.Application;
import com.company.screens.Screen;

public class ViewInventory implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //view all inventory and place orders
        System.out.println("View the inventory list and place your orders!");
        return new CustMenu();
    }
}
