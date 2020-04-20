package com.inventory.app;

import com.inventory.controller.Controller;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Item;
import com.inventory.view.NewItem;

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
        new Controller();
    }
}
