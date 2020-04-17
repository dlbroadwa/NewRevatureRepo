package com.company.app;

import com.company.DAO.data.ItemRepository;
import com.company.DAO.data.Repository;
import com.company.DAO.data.UserRepository;
import com.company.DAO.models.Item;
import com.company.DAO.models.User;
import com.company.DAO.utils.ConnectionUtils;
import com.company.DAO.utils.PostgresqlConnectionUtils;
import com.company.screens.Credentials;
import com.company.screens.Screen;
import com.company.screens.admin.AddInventory;
import com.company.screens.admin.Menu;
import com.company.screens.admin.UpdateInventory;
import com.company.screens.customer.ViewInventory;
import com.company.services.ItemService;
import com.company.services.UserService;

import java.io.IOException;
import java.util.Scanner;

public class BarInventoryApplication extends Application{
    private Screen currentScreen = null;
    private Scanner scanner;
    ConnectionUtils connectionUtils = new PostgresqlConnectionUtils(
            "jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres",
            "bar_guy", "bigpass","public");
    Repository<User, String> userRepo = new UserRepository(connectionUtils);
    Repository<Item, Integer> itemRepo = new ItemRepository(connectionUtils);
    UserService userService = new UserService(userRepo);
    ItemService itemService = new ItemService(itemRepo);

    public BarInventoryApplication(){
        this.scanner = new Scanner(System.in); // set our scanner to read input from the user
        currentScreen = new Credentials();
    }

    public BarInventoryApplication(String title){
        this();
        this.title=title;
    }

 @Override
    public void run() throws Exception {
        while (currentScreen != null){
            currentScreen = currentScreen.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public UserService getUserService() {
        return userService;
    }

    public ItemService getItemService() {
        return itemService;
    }
}
