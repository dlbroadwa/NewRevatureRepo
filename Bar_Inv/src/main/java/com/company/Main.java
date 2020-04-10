package com.company;


import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
//            CSVReader reader;
//            reader = new CSVReader(new FileReader("resources/inventory.csv"));
//            List<String[]> allRows = reader.readAll();
//            for (String[] row : allRows) {
//                System.out.println(Arrays.toString(row));
//            }
//

        Application app;
        app = new BarInventoryApplication();
        app.run();


    }
}