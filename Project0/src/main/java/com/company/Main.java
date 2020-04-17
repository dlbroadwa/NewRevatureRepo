package com.company;

import com.company.Banking.Bank;
import com.company.Banking.BankCustomer;
import com.company.DataAccess.ConnectionUtils;
import com.company.DataAccess.CreatorSQLRepository;
import com.company.DataAccess.DAO;
import com.company.DataAccess.PostgresConnectionUtil;
import com.company.Menu.Menu;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Menu newMenu = new Menu();

        newMenu.runMenu();
    }
}
