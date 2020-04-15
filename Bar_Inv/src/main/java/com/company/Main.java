package com.company;


import com.company.DAO.DatabaseConnection;
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
    public static void main(String[] args) throws Exception {
        Application app;
        DatabaseConnection connection = new DatabaseConnection();
        app = new BarInventoryApplication();
        connection.connect();
        app.run();


    }
}