package com.ex.app;

import com.ex.controller.system.ConsoleOut;
import com.ex.model.Item;
import com.ex.view.NewItem;

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
        new NewItem(this);
        ConsoleOut.println(items.toString());
    }
}
