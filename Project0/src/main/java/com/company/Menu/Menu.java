package com.company.Menu;

import com.company.Banking.Bank;
import com.company.Banking.BankActions;
import com.company.Banking.BankCustomer;
import com.company.DataAccess.ConnectionUtils;
import com.company.DataAccess.CreatorSQLRepository;
import com.company.DataAccess.DAO;
import com.company.DataAccess.PostgresConnectionUtil;
import com.company.Validation.Validate;
import login.LoginService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Validate validation = new Validate();
    BankCustomer customer = new BankCustomer();
    BankActions bank = new Bank();

    public void runMenu(){

        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0db.ccqumcqa2asp.us-west-1.rds.amazonaws.com:5432/postgres",
                "project0_user", "123", "project0");
        DAO<BankCustomer, Integer> creatorRepo = new CreatorSQLRepository(connectionUtils);
        Bank service = new Bank(creatorRepo);
        List<BankCustomer> allCreators = service.getAllCreators();
        LoginService loginService = new LoginService(creatorRepo);
        for(BankCustomer c : allCreators) {
            System.out.println("Creator " + c.getUserName());
        }
        Scanner sc = new Scanner(System.in);

        int number;
        double amount;
        int accountType;
        boolean canTransfer;
        do {
            System.out.println("Hello Customer! Please enter a valid number.\n");
            System.out.println("1. Sign In");
            System.out.println("2. Exit");
            number = validation.checkInt();

            switch(number)
            {
                case 1:
                    boolean doesUserExist = true;

                    System.out.println("Please Enter Username: ");
                    String userName = sc.nextLine();
                    System.out.println("Please Enter Password: ");
                    String passWord = sc.nextLine();

                    customer = loginService.login(userName, passWord);

                    if(customer.getId() == 0)
                    {
                        System.out.println("That is not a valid username or password");
                        break;
                    }
                    else {
                        System.out.println("User Successfully Logged in!");

                        int selection;
                        do {

                            System.out.println("Hello Customer! Please enter a valid number.\n");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdrawal");
                            System.out.println("3. Transfer");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Exit");

                            selection = validation.checkInt();

                            switch(selection)
                            {
                                case 1:
                                    System.out.println("Where would you like to deposit in\n");
                                    System.out.println("1. Checking\n");
                                    System.out.println("2. Savings\n");
                                    accountType = validation.checkInt();
                                    switch(accountType)
                                    {
                                        case 1:
                                            System.out.println("How much would you like to deposit into checkings?");
                                            bank.deposit(customer.getId(), 'c');
                                            break;
                                        case 2:
                                            System.out.println("How much would you like to deposit into savings?");
                                            amount = validation.checkDouble();
                                            bank.deposit(customer.getId(), 's');
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("Where would you like to withdraw at\n");
                                    System.out.println("1. Checking\n");
                                    System.out.println("2. Savings\n");
                                    accountType = validation.checkInt();
                                    switch(accountType)
                                    {
                                        case 1:
                                            System.out.println("How much would you like to deposit into checkings?");
                                            amount = validation.checkDouble();
                                            canTransfer = validation.verifyAmount(customer.getCheckings(), amount);
                                            bank.withdraw(customer.getId(), 'c');
                                            break;
                                        case 2:
                                            System.out.println("How much would you like to deposit into savings?");
                                            amount = validation.checkDouble();
                                            canTransfer = validation.verifyAmount(customer.getCheckings(), amount);
                                            bank.withdraw(customer.getId(), 's');
                                            break;
                                    }

                                    break;
                                case 3:
                                    System.out.print("How do you want to transfer?");
                                    break;
                                case 4:
                                    System.out.println("Checkings: " + customer.getCheckings());
                                    System.out.println("Savings: " + customer.getSavings());
                                    break;
                                case 5:
                                    break;
                            }
                        } while (selection != 5);
                    }

                case 2:
                    break;
            }
        } while (number != 2);

        System.out.println("Exited Successfully");




    }
}
