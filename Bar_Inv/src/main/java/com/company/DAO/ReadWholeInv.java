package com.company.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadWholeInv {
    public static ArrayList readAll() throws IOException {
        //take inventory.csv and make it an ArrayList
        File inventory = new File("resources/inventory.csv");
        String delimiter = ",";
        BufferedReader br = new BufferedReader(new FileReader(inventory));
        String line;
        ArrayList rows = new ArrayList();
        while ((line = br.readLine()) != null) {
            List<String> values = Arrays.asList(line.split(delimiter));
            rows.add(values);
        }
        return rows;
    }

    public static void printAll() throws Exception {
        ArrayList wholeThing = ReadWholeInv.readAll();
        int size = wholeThing.size();
        int i;
        for (i = 0; i < size; i++) {
            System.out.println(wholeThing.get(i));
        }
    }

    public static String[] pullRow(String id) throws Exception {
        //find the row that contains the specified id number (column 2)
        ArrayList wholeThing = ReadWholeInv.readAll();
        int size = wholeThing.size(); //size is number of rows
        int i = 0;
        int index =0;
        String[] aRow = new String[size];
        String[] myRow;
        if (!id.equals(aRow[1]) && i<size) {
            aRow = ((wholeThing.get(i)).toString()).split(",");
            i++;
        } else
            myRow = aRow;

        return ((wholeThing.get(index)).toString()).split(",");


    }
}
