package com.inventory.app;

import com.inventory.controller.Controller;
import com.inventory.model.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryAssistant extends Application{
    public InventoryAssistant() {
        super(TITLE);
    }

    private static final String TITLE = "Inventory Assistant";
    public final List<Item> items = new ArrayList<>();

    @Override
    public void run() {
        new Controller();
    }
}
