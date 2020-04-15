package com.company.DAO.fileIO;

import com.company.app.Application;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddItems {
    //add an item by entering a string array with the new item's info
    //write into a specified file
    public static void adder(String[] newThing, File file){
        File inventory = file;
        if(!inventory.exists()){
            //if the specified file is not there, make a new one
            try{
                System.out.println("Can't find that file, I'll make a new one");
                FileWriter writer = new FileWriter(inventory, true);
                String[] header = new String[] {"Item Name","ID","onHand","lowLevel","optLevel"};
                writer.append(header[0]);
                writer.append(",");
                writer.append(header[1]);
                writer.append(",");
                writer.append(header[2]);
                writer.append(",");
                writer.append(header[3]);
                writer.append(",");
                writer.append(header[4]);
                writer.append("\n");
                writer.flush();
                writer.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                //add the new item
                FileWriter writer = new FileWriter(inventory, true);
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
