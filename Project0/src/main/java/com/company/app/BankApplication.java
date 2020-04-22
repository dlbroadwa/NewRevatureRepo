package com.company.app;

import com.company.Banking.BankCustomer;
import com.company.DataAccess.ConnectionUtils;
import com.company.DataAccess.DAO;
import com.company.DataAccess.DAOI;
import com.company.DataAccess.PostgresConnectionUtil;
import com.company.Validation.Validate;
import com.company.menus.Menu;
import com.company.menus.WelcomeMenu;

import java.util.Scanner;

public class BankApplication extends Application {
    Scanner scan;
    Menu menu;
    ConnectionUtils connectionUtils;
    DAOI<BankCustomer, Integer> dao;
    Validate validate;
    public BankApplication()
    {
       scan = new Scanner(System.in);
       connectionUtils = new PostgresConnectionUtil(
                System.getenv("URL"),System.getenv("DBUSERNAME"), System.getenv("DBPASSWORD"), System.getenv("SCHEMA"));
       dao = new DAO(connectionUtils);
       menu = new WelcomeMenu();
       validate = new Validate();
    }
    @Override
    public void run() {
        while (menu != null) {
            menu = menu.doMenu(this);
        }
        scan.close();
    }

    public Scanner getScanner() {
        return scan;
    }

    public Menu getMenu() {
        return menu;
    }

    public ConnectionUtils getConnectionUtils() {
        return connectionUtils;
    }

    public DAOI getDAO(){
        return dao;
    }

    public Validate getValidate() {
        return validate;
    }
}
