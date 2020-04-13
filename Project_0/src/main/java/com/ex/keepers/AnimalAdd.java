package com.ex.keepers;

import com.ex.main.InventoryScreen;
import com.ex.main.Runner;
import com.ex.main.Screen;

public class AnimalAdd extends InventoryScreen implements Screen {

    @Override
    public Screen doScreen(Runner anInterface) {
        System.out.println("In the add screen");
        return null;
    }
}
