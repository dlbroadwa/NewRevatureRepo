package com.company;

import com.company.app.Application;
import com.company.app.InventoryApp;

public class Main {
    public static void main(String[] args){

        Application app = new InventoryApp();

        app.run();
    }
}
