package com.company;


import com.company.DAO.DatabaseConnection;
import com.company.DAO.data.ItemRepository;
import com.company.DAO.data.Repository;
import com.company.DAO.data.UserRepository;
import com.company.DAO.models.Item;
import com.company.DAO.models.User;
import com.company.DAO.utils.ConnectionUtils;
import com.company.DAO.utils.PostgresqlConnectionUtils;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.services.ItemService;
import com.company.services.UserService;
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
        app = new BarInventoryApplication();
        app.run();


    }

}