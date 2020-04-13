package com.company.app;

import com.company.screens.Credentials;
import com.company.screens.Screen;
import com.company.screens.admin.AddInventory;
import com.company.screens.admin.Menu;
import com.company.screens.admin.UpdateInventory;
import com.company.screens.customer.ViewInventory;

import java.io.IOException;
import java.util.Scanner;

public class BarInventoryApplication extends Application{
    private Screen currentScreen = null;
    private Scanner scanner;



    public BarInventoryApplication(){
        this.scanner = new Scanner(System.in); // set our scanner to read input from the user
        currentScreen = new UpdateInventory();

    }

    public BarInventoryApplication(String title){
        this();
        this.title=title;
    }


    @Override
    public void run() throws Exception {
        while (currentScreen != null){
            currentScreen = currentScreen.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

}
