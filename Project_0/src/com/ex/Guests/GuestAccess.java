package com.ex.Guests;

import com.ex.MainAndMenu.Runner;
import com.ex.Resources.InventoryScreen;

public class GuestAccess extends Runner {

    InventoryScreen inventoryScreen = null;

    @Override
    public void run() {
        System.out.println("Guest");
    }


    public InventoryScreen getInventoryScreen() {
        return inventoryScreen;
    }
}
