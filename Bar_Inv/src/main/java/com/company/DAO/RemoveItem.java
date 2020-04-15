package com.company.DAO;

import com.company.app.BarInventoryApplication;
import com.company.app.Application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveItem {
    public static void remover(String id) throws Exception {
        File inventory = new File("resources/inventory.csv");
        File tempInv = new File("resources/tempInv.csv");
        //read the whole inventory.csv
        ArrayList wholeThing = ReadWholeInv.readAll(inventory);
        //get the number of rows
        int size = wholeThing.size();
        int i;
        //find the row that contains a certain string
        //will be ID number, so second column
         String[] removeThis = ReadWholeInv.pullRow(id);

        //remove that row
         if(!inventory.exists()){       //check that the file exists
             System.out.println("Well, everything is gone anyways");
         } else{
             //go through each row, write the things to keep to tempInv.csv
             for (i=0;i<size;i++){
             }
         }
    }
}
