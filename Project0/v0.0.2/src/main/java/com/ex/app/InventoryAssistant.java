package com.ex.app;

import com.ex.screens.NewItemScreen;
import com.ex.services.io.*;
import com.ex.types.*;
import com.ex.screens.*;

import java.util.ArrayList;
import java.util.List;

public class InventoryAssistant extends Application{
    public InventoryAssistant() {
        super(TITLE);
    }

    private static final String TITLE = "Inventory Assistant";
    public final List<Item> items = new ArrayList<>();

    @Override
    public void run(){
        ConsoleOut.println("Running " + TITLE);
        new NewItemScreen(this);
        ConsoleOut.println(items.toString());
    }
}
