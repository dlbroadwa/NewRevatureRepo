package com.company.DAO.fileIO;

import java.io.File;
import java.util.ArrayList;

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
