/**
 * Menu Class of the Whole Banking application
 * Displays options for banking application and takes in user input to navigate
 */
package com.company.Menu;
import com.company.Banking.BankService;
import com.company.Banking.BankCustomer;
import com.company.DataAccess.ConnectionUtils;
import com.company.DataAccess.CreatorSQLRepository;
import com.company.DataAccess.DAO;
import com.company.DataAccess.PostgresConnectionUtil;
import com.company.Validation.Validate;
import com.company.login.LoginService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Scanner;

public class Menu {
    Validate validation = new Validate();
    BankCustomer customer = new BankCustomer();

    /**
     * Main menu function that user interacts with
     */
    public void runMenu(){
        //Database Connection - will use enviromental variables in other projects
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                System.getenv("URL"),System.getenv("DBUSERNAME"), System.getenv("DBPASSWORD"), System.getenv("SCHEMA"));
        DAO<BankCustomer, Integer> creatorRepo = new CreatorSQLRepository(connectionUtils);
        BankService bankService = new BankService(creatorRepo);
        LoginService loginService = new LoginService(creatorRepo);

        Scanner sc = new Scanner(System.in);
        int number;
        int accountType;
        // Start of Menu when application starts
        do {
            System.out.println("Hello Customer! Please enter a valid number.\n");
            System.out.println("1. Sign In");
            System.out.println("2. Exit");

            number = validation.checkInt();

            switch(number)
            {
                case 1:
                    System.out.println("Please Enter Username: ");
                    String userName = sc.nextLine();
                    System.out.println("Please Enter Password: ");
                    String passWord = sc.nextLine();

                    //Go to login service to verify user login
                    customer = loginService.login(userName, passWord);

                    if(customer.getId() == 0)
                    {
                        System.out.println("That is not a valid username or password\n");
                        break;
                    }
                    else {
                        System.out.println(customer.getFirstName() + " Successfully Logged in!\n");

                        int selection;
                        do {
                            bankService.checkBalance((customer.getId()));

                            System.out.println("Hello " + customer.getFirstName() + " Please enter a valid number.\n");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdrawal");
                            System.out.println("3. Transfer");
                            System.out.println("4. Check Transaction History");
                            System.out.println("5. Log Out");

                            selection = validation.checkInt();

                            switch(selection)
                            {
                                case 1:
                                    System.out.println("Where would you like to deposit in\n");
                                    System.out.println("1. Checking");
                                    System.out.println("2. Savings");
                                    accountType = validation.checkInt();
                                    switch(accountType)
                                    {
                                        case 1:
                                            System.out.println("How much would you like to deposit into checkings?");
                                            bankService.deposit(customer.getId(), 'c');
                                            break;
                                        case 2:
                                            System.out.println("How much would you like to deposit into savings?");
                                            bankService.deposit(customer.getId(), 's');
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
                                            System.out.println("How much would you like to withdraw from checkings?");
                                            bankService.withdraw(customer.getId(), 'c');
                                            break;
                                        case 2:
                                            System.out.println("How much would you like to withdraw from savings?");
                                            bankService.withdraw(customer.getId(), 's');
                                            break;
                                    }

                                    break;
                                case 3:
                                    System.out.println("How do you want to transfer?");
                                    System.out.println("1.Checkings into Savings");
                                    System.out.println("2.Savings into Checkings");
                                    int transferLocation = validation.checkInt();
                                    switch(transferLocation)
                                    {
                                        case(1):
                                            System.out.println("How much do you want to transfer");
                                            bankService.transfer(customer.getId(), 'c', 's');
                                            break;
                                        case(2):
                                            System.out.println("How much do you want to transfer");
                                            bankService.transfer(customer.getId(), 's', 'c');
                                            break;
                                    }
                                    break;
                                case 4:
                                    bankService.viewTransactionHistory(customer.getId());
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
