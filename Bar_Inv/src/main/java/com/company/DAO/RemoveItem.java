package com.company.DAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveItem {
    public static void remover(String id) throws IOException {
        File inventory = new File("resources/inventory.csv");
        File tempInv = new File("resources/tempInv.csv");
        //read the whole inventory.csv
        ArrayList wholeThing = ReadWholeInv.readAll();
        //find the row that contains a certain string
            //will be ID number, so second column
        //remove that row
         if(!inventory.exists()){
                    System.out.println("Well, everything is gone anyways");
                } else{

                }
    }
}
