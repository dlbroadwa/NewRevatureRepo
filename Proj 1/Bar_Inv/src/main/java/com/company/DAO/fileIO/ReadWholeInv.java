package com.company.DAO.fileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadWholeInv {
    //methods for reading the whole inventory or a specific row
    static File inventory = new File("resources/inventory.csv"); //the file we are looking at
    public static ArrayList readAll(File file) throws IOException {
        //take inventory.csv and make it an ArrayList
        String delimiter = ",";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ArrayList rows = new ArrayList();  //read the whole csv file and write it to an ArrayList
        while ((line = br.readLine()) != null) {
            List<String> values = Arrays.asList(line.split(delimiter));
            rows.add(values);
        }
        return rows;
    }

    public static void printAll() throws Exception {  //print the whole inventory list
        ArrayList wholeThing = ReadWholeInv.readAll(inventory);
        int size = wholeThing.size();
        int i;
        for (i = 0; i < size; i++) {
            System.out.println(wholeThing.get(i));
        }
    }

    public static String[] pullRow(String id) throws Exception {
        //find the row that contains the specified id number (column 2)
        ArrayList wholeThing = ReadWholeInv.readAll(inventory);
        int size = wholeThing.size(); //size is number of rows
        int i = 0;
        String[] aRow = ((wholeThing.get(0)).toString()).split(",");
        for (i = 0; i < size; i++) {
           if (aRow[1].equals(" " + id)) {                  //if it's the one we want, return it
                return aRow;
            } else if (!aRow[1].equals(" " + id)) {         //if it is not the one we want, look at the next row and loop back
                aRow = ((wholeThing.get(i)).toString()).split(",");
            } else if (i==size-1){                           // if we don't find anything, say so
                String[] negRow = {"I can't find that one"};
                return negRow;
           }
        }
        return aRow;
    }
}