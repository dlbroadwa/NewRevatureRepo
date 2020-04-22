import Screens.ATMLogInScreen;
import Screens.ATMScreenMenu;
import Screens.DepositMenu;
import Screens.WithdrawMenu;
import clients.AccountsService;
import clients.TransactionService;
import clients.UsersService;
import data.*;
import model.Accounts;
import model.Transactions;
import model.Users;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

import java.util.List;

/**
 * main class where the app will be launched from
 */
public class BankATM {
    public static void main (String args[]) {
        //database connection details
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");


        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);

        IAccounts<Accounts, Integer> IAccountHolders = new AccountsInSQLRepo(connectionUtils);
        AccountsService accountsService = new AccountsService(IAccountHolders);

        ITransaction<Transactions, Integer> ITransactions = new TransactionsSQLRepo(connectionUtils);
        TransactionService transactionService = new TransactionService(ITransactions);

        /**
         * The next three lines of code will ask the user for login details and authenticate against the database.
         * if user entered correct email & password, authentication is true and false if entered incorrect
         */
        ATMLogInScreen atmLogInScreen = new ATMLogInScreen();
        atmLogInScreen.printFirstScreen();
        boolean authentication = usersService.authenticate(atmLogInScreen.getEmail(), atmLogInScreen.getPin());

        if (authentication){

            /**
             * Once user are authenticated, user will be offered the account details and the ATM operation choices.
             * User interaction will be launched via ATMScreenMenu class where user choice will be asked.
             */
            System.out.println("User logged in");
            Users user = usersService.findUserByEmail(atmLogInScreen.getEmail());
            ATMScreenMenu atmScreenMenu= new ATMScreenMenu();
            atmScreenMenu.printATMMenuScreen(user);
            Accounts userAccount = accountsService.findByAccount(user.getEmail_address());
            atmScreenMenu.printUserAccount(userAccount);
            atmScreenMenu.printUserOptions();


            System.out.println("User menu option is " + atmScreenMenu.getUserMenuOption());

            do {
                //Do this if user select choice 1 -- withdraw
                if (atmScreenMenu.getUserMenuOption() == 1){
                    WithdrawMenu withdrawMenu = new WithdrawMenu();
                    withdrawMenu.withdrawMenu();
                    System.out.println("User is withdrawing $" + withdrawMenu.getWithdrawAmount());
                    float withdrawAmount = withdrawMenu.getWithdrawAmount() * (-1);
                    accountsService.updateBalance(userAccount, withdrawAmount);
                    transactionService.insertTransaction(userAccount, withdrawMenu.getWithdrawAmount(), "withdraw");
                }
                //Do this if user select choice 2 -- deposit
                else if (atmScreenMenu.getUserMenuOption() == 2){
                    DepositMenu depositMenu = new DepositMenu();
                    depositMenu.depositMenu();
                    System.out.println("User is depositing $" + depositMenu.getDepositAmount());
                    accountsService.updateBalance(userAccount, depositMenu.getDepositAmount());
                    transactionService.insertTransaction(userAccount, depositMenu.getDepositAmount(), "deposit");
                }
                //Do this if user select choice 1 -- review past transactions
                else if (atmScreenMenu.getUserMenuOption() == 3){
                    List<Transactions> allTransactionByUser = transactionService.allTransactionByAccount(userAccount);
                    System.out.println("All transaction by user: ");
                    for (Transactions t : allTransactionByUser){
                        System.out.println(t.getTimestamp() + ":::" + t.getTransaction_type() + ":::Amount: $" +
                                t.getTrans_amount() + ":::Account Holder: " + t.getAccount().getHolder().getEmail_address() + ":::Transaction ID: " + t.getId()) ;
                    }
                }
                //Looping back after provided the user choice with the service.
                atmScreenMenu= new ATMScreenMenu();
                atmScreenMenu.printATMMenuScreen(user);
                userAccount = accountsService.findByAccount(user.getEmail_address());
                atmScreenMenu.printUserAccount(userAccount);
                atmScreenMenu.printUserOptions();


            }while (atmScreenMenu.getUserMenuOption() != 4);

        }
        else{
            System.out.println("User is not found or pin is not matched");
        }

    }
}
