package com.company.DAO;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class fileIoDAO {

        ArrayList<String[]> inventory = new ArrayList<String[]>();
//        String[] item1 = new String[]{"vodka", "101", "3", "1", "5"};
//        String[] item2 = new String[]{"gin", "102", "4", "1", "6"};

//        inventory.add(item1);
//        inventory.add(item2);
        public fileIoDAO(String invFilePath) {
            FileReader reader = null;
            BufferedReader bReader = null;
            inventory = new ArrayList<String[]>();

            try{
                reader = new FileReader(invFilePath);
                bReader = new BufferedReader(reader);

            } catch (FileNotFoundException e) {
                System.err.println("Error loading answer file");
            }

        }


    }
