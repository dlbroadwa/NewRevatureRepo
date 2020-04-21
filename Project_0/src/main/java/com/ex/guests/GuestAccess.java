package com.ex.guests;

import com.ex.main.Runner;
import com.ex.AnimalActions.InventoryScreen;
import com.ex.main.Screen;
/*
 * This is where non-Keepers AKA Guests get sent from the KeeperGuestSorter. This Screen prints a greeting and returns InventoryScreen
 */
public class GuestAccess implements Screen {

//Methods

    public Screen doScreen(Runner anInterface) {
        System.out.println("We hope you are planning on visiting and see all of amazing animals listed below:");
        return new InventoryScreen(false);//Allows the Guest to enter the InventoryScreen and not return to the KeeperAccess Screen
    }
}
