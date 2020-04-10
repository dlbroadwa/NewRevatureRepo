package com.company.screens.admin;

import com.company.app.Application;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

public class UpdateInventory implements Screen {

    @Override
    public Screen doScreen(Application app) {

        //update values for any item
        System.out.println("Update inventory here");

        return new Menu();
    }
}
