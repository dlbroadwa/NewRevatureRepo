package com.ex.guests;

import com.ex.main.KeeperGuestSorter;
import com.ex.main.Runner;
import com.ex.main.InventoryScreen;
import com.ex.main.Screen;

/*GuestAccess is intended to be the guest only menu to view animal info and get a friendly message */

public class GuestAccess implements Screen {

//Methods
    public Screen doScreen(Runner anInterface) {
        System.out.println("We hope you are planning on visiting and see all of amazing animals listed below:");
        return new InventoryScreen();
    }
}
