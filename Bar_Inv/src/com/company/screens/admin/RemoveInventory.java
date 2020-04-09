package com.company.screens.admin;

import com.company.app.Application;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

public class RemoveInventory implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //remove unwanted items and their associated values
        return new Menu();
    }
}
