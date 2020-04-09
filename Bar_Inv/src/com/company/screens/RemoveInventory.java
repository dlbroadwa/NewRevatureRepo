package com.company.screens;

import com.company.app.Application;

public class RemoveInventory implements Screen{
    @Override
    public Screen doScreen(Application app) {
        //remove unwanted items and their associated values
        return new Menu();
    }
}
