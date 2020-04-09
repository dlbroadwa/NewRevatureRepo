package com.company.screens;

import com.company.app.Application;

public class AddInventory implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //add new items and populate their initial values
        return new Menu();
    }
}
