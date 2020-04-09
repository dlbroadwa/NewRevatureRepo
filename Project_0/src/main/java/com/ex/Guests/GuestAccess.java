package com.ex.Guests;

import com.ex.MainAndMenu.Runner;
import com.ex.Resources.InventoryScreen;

/*GuestAccess is intended to be the guest only menu to view animal info */

public class GuestAccess extends Runner /*Change This??*/{

    InventoryScreen inventoryScreen = null;


    public void run() {

        /*Test Print out to ensure sorting is working. DELETE/COMMENT OUT LATER*/
        System.out.println("Guest");

    }


    public InventoryScreen getInventoryScreen() {

        return inventoryScreen;

    }
}
