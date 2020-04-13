package com.company.screens.admin;

import com.company.DAO.ReadWholeInv;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UpdateInventory implements Screen {

    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();

        //update values for any item
        //view everything
        ReadWholeInv.printAll();
        System.out.println("Enter the ID number of the item you want to update");
        String id = scanner.nextLine();
        String[] oneItem = ReadWholeInv.pullRow(id);
        System.out.println("You picked this: \n" + Arrays.toString(oneItem));


        System.out.println("Awesome, got it. Want to update another? [y/n]");
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new UpdateInventory();
        } else {
            return new Menu();
        }
    }
}
