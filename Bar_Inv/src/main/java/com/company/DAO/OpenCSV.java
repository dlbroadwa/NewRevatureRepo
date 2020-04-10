package com.company.DAO;

import com.company.app.Application;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OpenCSV implements DAO{
    public void ReadInv() throws IOException, CsvException {
        CSVReader reader;
        reader = new CSVReader(new FileReader("resources/inventory.csv"));
        List<String[]> allRows = reader.readAll();
        for (String[] row : allRows) {
            System.out.println(Arrays.toString(row));
        }

    }

    public DAO doDAO(Application app) throws Exception{
        //look at inventory.csv
        //get full table
        //add to table
        //remove from table
        //update any values on table
        return null;
    }

}

