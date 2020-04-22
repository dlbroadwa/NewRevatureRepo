package com.company.menus;
/**
 * Menu that contains the banking options
 */
import com.company.banking.BankCustomer;
import com.company.banking.BankService;
import com.company.DataAccess.DAOI;
import com.company.validation.Validate;
import com.company.app.Application;
import com.company.app.BankApplication;

public class BankMenu implements Menu {
    BankCustomer currentCustomer;

    public BankMenu(BankCustomer currentCustomer)
    {
        this.currentCustomer = currentCustomer;
    }
    @Override
    public Menu doMenu(Application app) {
        int accountType;
        DAOI<BankCustomer, Integer> dao = ((BankApplication)app).getDAO();
        BankService bankService = new BankService(dao, currentCustomer);
        Validate validation = ((BankApplication) app).getValidate();
        while(true) {
            int selection;
                bankService.checkBalance();
                System.out.println("Hello " + currentCustomer.getFirstName() + " Please enter a valid number.\n");
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
                        System.out.println("2. Savings\n");
                        accountType = validation.checkInt();
                        switch(accountType)
                        {
                            case 1:
                                System.out.println("How much would you like to deposit into checkings?");
                                bankService.deposit('c');
                                break;
                            case 2:
                                System.out.println("How much would you like to deposit into savings?");
                                bankService.deposit('s');
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Where would you like to withdraw at\n");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings\n");
                        accountType = validation.checkInt();
                        switch(accountType)
                        {
                            case 1:
                                System.out.println("How much would you like to withdraw from checkings?");
                                bankService.withdraw('c');
                                break;
                            case 2:
                                System.out.println("How much would you like to withdraw from savings?");
                                bankService.withdraw('s');
                                break;
                        }

                        break;
                    case 3:
                        System.out.println("How do you want to transfer?\n");
                        System.out.println("1. Checkings into Savings");
                        System.out.println("2. Savings into Checkings\n");
                        int transferLocation = validation.checkInt();
                        switch(transferLocation)
                        {
                            case(1):
                                System.out.println("How much do you want to transfer");
                                bankService.transfer('c', 's');
                                break;
                            case(2):
                                System.out.println("How much do you want to transfer");
                                bankService.transfer('s', 'c');
                                break;
                        }
                        break;
                    case 4:
                        bankService.viewTransactionHistory();
                        break;
                    case 5:
                        return new WelcomeMenu();
                    default:
                        System.out.println("That is not a valid option");
                }
        }
    }
}
