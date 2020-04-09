package com.company.screens.customer;

import com.company.app.Application;
import com.company.screens.Screen;

public class PrevOrders implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //display order history for user
        return new CustMenu();

    }
}
