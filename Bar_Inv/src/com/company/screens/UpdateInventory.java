package com.company.screens;

import com.company.app.Application;

public class UpdateInventory implements Screen {

    @Override
    public Screen doScreen(Application app) {

        //update values for any item
        System.out.println("Update inventory here");

        return new Menu();
    }
}
