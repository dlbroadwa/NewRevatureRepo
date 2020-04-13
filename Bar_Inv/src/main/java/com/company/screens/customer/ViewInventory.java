package com.company.screens.customer;

import com.company.DAO.ReadWholeInv;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewInventory implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        //view all inventory
        System.out.println("View the inventory list");
        ReadWholeInv.printAll();


        //place orders... I'll get there will use updateInventory or whatever I name it
        System.out.println("Enter the ID number of the booze you want");
        int id = scanner.nextInt();
        System.out.println("How many do you want?");
        int quant = scanner.nextInt();

        return new CustMenu();
    }
}
