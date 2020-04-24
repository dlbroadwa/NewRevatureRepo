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

/***
 * This is an inventory management system, currently directed towards bars and liquor distributors.
 * It would be extremely easy, and way less fun, to apply this inventory management system to other types of inventories.
 * We would just have to change a few messages.
 *
 * The admin is intended to be a liquor distributor. The admin has access to the inventory list, and can update that as they need to.
 * The admin also has access to the orders placed by the users.
 * The users are bar owners/managers who are placing orders to be delivered to their bars.
 *
 *
 * To Do:
 * add in more input validation
 *
 * change to prevent SQL injection
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Application app;
        app = new BarInventoryApplication();
        app.run();


    }

}