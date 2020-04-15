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
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0db.ccqumcqa2asp.us-west-1.rds.amazonaws.com:5432/postgres",
                "dylanchhin", "Personals12", "project0");
        DAO<BankCustomer, Integer> creatorRepo = new CreatorSQLRepository(connectionUtils);
       Bank service = new Bank(creatorRepo);

        List<BankCustomer> allCreators = service.getAllCreators();

        for(BankCustomer c : allCreators) {
            System.out.println("Creator " + c.getUserName());
        }
        Menu newMenu = new Menu();

        newMenu.runMenu();
    }
}
