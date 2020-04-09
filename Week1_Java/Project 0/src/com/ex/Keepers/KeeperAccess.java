package com.ex.Keepers;
import com.ex.MainAndMenu.Runner;
import com.ex.Resources.InventoryScreen;

public class KeeperAccess extends Runner {
    InventoryScreen inventoryScreen = null;

    public KeeperAccess(){
    inventoryScreen = new InventoryScreen();
    }

    @Override
    public void run () {
        System.out.println("Keeper");
    }

    public InventoryScreen getInventoryScreen() {
        return inventoryScreen;
    }
}
