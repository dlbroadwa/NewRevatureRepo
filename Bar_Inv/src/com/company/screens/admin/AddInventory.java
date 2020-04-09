package com.company.screens.admin;

import com.company.app.Application;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

public class AddInventory implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //add new items and populate their initial values
        return new Menu();
    }
}
