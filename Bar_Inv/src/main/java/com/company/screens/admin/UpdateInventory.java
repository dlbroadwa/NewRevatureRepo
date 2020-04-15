package com.company.screens.admin;

import com.company.DAO.fileIO.ReadWholeInv;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;

import java.util.Arrays;
import java.util.Scanner;

public class UpdateInventory implements Screen {

    @Override
    public Screen doScreen(Application app) throws Exception {
        //update values for any item
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        //view everything
        ReadWholeInv.printAll();
        System.out.println("Enter the ID number of the item you want to update");
        String id = scanner.nextLine();
        String[] oneItem = ReadWholeInv.pullRow(id); //pull one item based on ID number
        System.out.println("You picked this: \n" + Arrays.toString(oneItem));


        //update another or go back to menu
        System.out.println("Awesome, got it. Want to update another? [y/n]");
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new UpdateInventory();
        } else {
            return new Menu();
        }
    }
}
