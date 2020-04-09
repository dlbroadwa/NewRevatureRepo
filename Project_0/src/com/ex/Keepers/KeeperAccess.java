package com.ex.Keepers;
import com.ex.MainAndMenu.Runner;
import com.ex.Resources.InventoryScreen;

/*KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals*/

public class KeeperAccess extends Runner/*Change This?? */ {

    InventoryScreen inventoryScreen = null;

    public KeeperAccess(){

        inventoryScreen = new InventoryScreen();

    }


    public void run () {

        /*Test Print out to ensure sorting is working. DELETE/COMMENT OUT LATER*/
        System.out.println("Keeper");

    }

    public InventoryScreen getInventoryScreen() {

        return inventoryScreen;

    }
}
