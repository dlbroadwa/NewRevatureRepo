package com.company.screens.admin;

import com.company.DAO.ReadWholeInv;
import com.company.DAO.RemoveItem;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

import java.util.Scanner;

public class RemoveInventory implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        System.out.println("Remove inventory here:");
        ReadWholeInv.printAll();
        //remove unwanted items and their associated values
        System.out.println("Enter ID number");
        String id = scanner.nextLine();

//        remove any row containing the specified id number
//        RemoveItem.remover(id);

        System.out.println("Awesome, got it. Want to remove another? [y/n]"); //choose to remove another or return to menu
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new AddInventory();
        } else {
            return new Menu();
        }
    }
}
