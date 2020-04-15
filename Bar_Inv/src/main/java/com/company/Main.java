package com.company;


import com.company.DAO.DatabaseConnection;
import com.company.DAO.utils.ConnectionUtils;
import com.company.DAO.utils.PostgresqlConnectionUtils;
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
        ConnectionUtils connectionUtils = new PostgresqlConnectionUtils(
                "jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres",
                "bar_guy", "bigpass","public");

        Application app;
        app = new BarInventoryApplication();
        app.run();


    }
}