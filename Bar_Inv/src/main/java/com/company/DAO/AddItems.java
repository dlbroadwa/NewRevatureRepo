package com.company.DAO;

import com.company.app.Application;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddItems {
    public static void adder(String[] newThing){
            File inventory = new File("resources/inventory.csv");
        if(!inventory.exists()){
            try{
                FileWriter writer = new FileWriter("resources/inventory.csv");
                writer.append("Item Name");
                writer.append(",");
                writer.append("ID");
                writer.append(",");
                writer.append("onHand");
                writer.append(",");
                writer.append("lowLevel");
                writer.append(",");
                writer.append("optLevel");
                writer.append("\n");
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter writer = new FileWriter("resources/inventory.csv", true);
                writer.append(newThing[0]);
                writer.append(",");
                writer.append(newThing[1]);
                writer.append(",");
                writer.append(newThing[2]);
                writer.append(",");
                writer.append(newThing[3]);
                writer.append(",");
                writer.append(newThing[4]);
                writer.append("\n");

                writer.flush();
                writer.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
