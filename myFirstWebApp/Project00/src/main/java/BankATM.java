import Screens.*;
import clients.AccountsService;
import clients.TransactionService;
import clients.UsersService;
import com.sun.deploy.cache.CacheEntry;
import data.*;
import model.Accounts;
import model.Transactions;
import model.Users;
import org.postgresql.util.PSQLException;
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
//user1@yahoo.com - 123456789
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
            boolean isManager = usersService.isAdminUser(user);
                if (isManager == false) {
                    ATMScreenMenu atmScreenMenu = new ATMScreenMenu();
                    atmScreenMenu.printATMMenuScreen(user);
                    Accounts userAccount = accountsService.findByAccount(user.getEmail_address());
                    atmScreenMenu.printUserAccount(userAccount);
                    atmScreenMenu.printUserOptions();


                    System.out.println("User menu option is " + atmScreenMenu.getUserMenuOption());

                    do {
                        //Do this if user select choice 1 -- withdraw
                        if (atmScreenMenu.getUserMenuOption() == 1) {
                            WithdrawMenu withdrawMenu = new WithdrawMenu();
                            withdrawMenu.withdrawMenu();
                            System.out.println("User is withdrawing $" + withdrawMenu.getWithdrawAmount());
                            float withdrawAmount = withdrawMenu.getWithdrawAmount() * (-1);
                            accountsService.updateBalance(userAccount, withdrawAmount);
                            transactionService.insertTransaction(userAccount, withdrawMenu.getWithdrawAmount(), "withdraw");
                        }
                        //Do this if user select choice 2 -- deposit
                        else if (atmScreenMenu.getUserMenuOption() == 2) {
                            DepositMenu depositMenu = new DepositMenu();
                            depositMenu.depositMenu();
                            System.out.println("User is depositing $" + depositMenu.getDepositAmount());
                            accountsService.updateBalance(userAccount, depositMenu.getDepositAmount());
                            transactionService.insertTransaction(userAccount, depositMenu.getDepositAmount(), "deposit");
                        }
                        //Do this if user select choice 1 -- review past transactions
                        else if (atmScreenMenu.getUserMenuOption() == 3) {
                            List<Transactions> allTransactionByUser = transactionService.allTransactionByAccount(userAccount);
                            System.out.println("All transaction by user: ");
                            for (Transactions t : allTransactionByUser) {
                                System.out.println(t.getTimestamp() + ":::" + t.getTransaction_type() + ":::Amount: $" +
                                        t.getTrans_amount() + ":::Account Holder: " + t.getAccount().getHolder().getEmail_address() + ":::Transaction ID: " + t.getId());
                            }
                        }
                        //Looping back after provided the user choice with the service.
                        atmScreenMenu = new ATMScreenMenu();
                        atmScreenMenu.printATMMenuScreen(user);
                        userAccount = accountsService.findByAccount(user.getEmail_address());
                        atmScreenMenu.printUserAccount(userAccount);
                        atmScreenMenu.printUserOptions();


                    } while (atmScreenMenu.getUserMenuOption() != 4);
                }
                else{
                    System.out.println(user.getEmail_address() + " is a bank manager");
                    ManagerMenu managerMenu = new ManagerMenu();
                    managerMenu.printManagerMenu(user);
                    do{
                        if (managerMenu.getManagerMenuOption() == 1){
                            List <Users> allBankUsers = usersService.findAll();
                            for (Users tmpUser : allBankUsers){
                                System.out.println("User ID: " + tmpUser.getUserID() + ":::User Name: " + tmpUser.getName() +
                                        ":::User Email: " + tmpUser.getEmail_address() + ":::is a Manager: " +
                                        tmpUser.isIs_admin() + ":::User Phone number: " +
                                        tmpUser.getPhone_number() );
                            }
                        }
                        else if (managerMenu.getManagerMenuOption() == 2){
                            ManagerViewSingleAccountMenu viewAccountByIDScreen = new ManagerViewSingleAccountMenu();
                            viewAccountByIDScreen.printRequestAccountId();
                            Accounts tmpAccount = accountsService.findByAccount(viewAccountByIDScreen.getUerAccountEmail());

                            System.out.println("Account holder: " + tmpAccount.getHolder().getEmail_address() + ".------Balance: $" +
                                    tmpAccount.getBalance() + ".------Account Type: " + tmpAccount.getAccountType() + ".------Account ID: " + tmpAccount.getAccount_id());

                        }
                        else if (managerMenu.getManagerMenuOption() == 3){
                            CreateUserMenu createUserMenu = new CreateUserMenu();
                            Users tmpUser = createUserMenu.requestNewUserDetails();
                            if ( tmpUser != null){
                                try {
                                    usersService.insert(tmpUser);
                                } catch (PSQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                System.out.println("User was not inserted due to problems");
                            }

                        }
                        else if (managerMenu.getManagerMenuOption() == 4){
                            CreateAccountMenu createAccountMenu = new CreateAccountMenu();
                            createAccountMenu.askAccountDetails();
                            Accounts newAccount = null;
                            String email = createAccountMenu.getCustomer().getEmail_address();

                            Users tmpUser = usersService.findUserByEmail(email) ;
                            if (tmpUser.getEmail_address().equals(email)){
                                Accounts tmpAccount = accountsService.findByAccount(email);
                                if (tmpAccount.getHolder().getEmail_address().equals(email)){
                                    System.out.println("User already has an account.");
                                }
                                else{
                                    newAccount = accountsService.createNewAccount(tmpUser, createAccountMenu.getType(), createAccountMenu.getInitalBalance());
                                    System.out.println("Account created successfully");
                                }
                            }
                            else{
                                System.out.println("User does not exist.  Create user first before creating account");
                            }

                        }
                        else if (managerMenu.getManagerMenuOption() == 5){
                            RetsetPinPasswordMenu retsetPinPasswordMenu = new RetsetPinPasswordMenu();
                            Users tmpUser= retsetPinPasswordMenu.askNewPassword();

                            if (tmpUser.getUser_pin().equals("default")){
                                System.out.println("User pin was not changed");
                            }
                            else{
                                boolean savedSuccess = usersService.save(tmpUser, "pin");
                                if (savedSuccess){
                                    System.out.println("successfully saved new pin");
                                }
                            }
                        }
                        else if (managerMenu.getManagerMenuOption() == 6){
                            List<Accounts> allAccounts = accountsService.findAll();
                            for (Accounts a : allAccounts){
                                System.out.println("Account ID: " + a.getAccount_id() + ":::Name: " + a.getHolder().getName() +
                                        ":::Email: " + a.getHolder().getEmail_address() + ":::" + a.getAccountType() +
                                        ":::Balance: $" + a.getBalance());
                            }
                        }
                        managerMenu = new ManagerMenu();
                        System.out.println("\n" + user.getEmail_address() + " is a manager.");
                        managerMenu.printManagerMenu(user);
                    }while (managerMenu.getManagerMenuOption() != 7);

                }
        }
        else{
            System.out.println("User is not found or pin is not matched");
        }

    }
}
