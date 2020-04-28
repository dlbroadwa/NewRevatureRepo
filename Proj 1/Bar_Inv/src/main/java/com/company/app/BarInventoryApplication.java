package com.company.app;

import com.company.DAO.data.ItemRepository;
import com.company.DAO.data.OrdersRepository;
import com.company.DAO.data.Repository;
import com.company.DAO.data.UserRepository;
import com.company.DAO.models.Item;
import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import com.company.DAO.utils.ConnectionUtils;
import com.company.DAO.utils.PostgresqlConnectionUtils;
import com.company.screens.Credentials;
import com.company.screens.Screen;
import com.company.screens.admin.AddInventory;
import com.company.screens.admin.Menu;
import com.company.screens.admin.UpdateInventory;
import com.company.screens.admin.orders.viewCustOrders;
import com.company.screens.customer.PrevOrders;
import com.company.screens.customer.ViewInventory;
import com.company.services.ItemService;
import com.company.services.OrdersService;
import com.company.services.UserService;

import java.io.IOException;
import java.util.Scanner;

public class BarInventoryApplication extends Application{
    private Screen currentScreen = null;
    private Scanner scanner;
    ConnectionUtils connectionUtils = new PostgresqlConnectionUtils(
            "jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres",
            "bar_guy", "bigpass","public");
    Repository<User, String, String> userRepo = new UserRepository(connectionUtils);
    Repository<Item, Integer, String> itemRepo = new ItemRepository(connectionUtils);
    Repository<Order, String, String> orderRepo = new OrdersRepository(connectionUtils);
    UserService userService = new UserService(userRepo);
    ItemService itemService = new ItemService(itemRepo);
    OrdersService ordersService = new OrdersService(orderRepo);
    String currentUser = new String();

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


    //getters and setters for some important variables and functions
    public Scanner getScanner() {
        return scanner;
    }

    public UserService getUserService() {
        return userService;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public OrdersService getOrdersService() {
        return ordersService;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
