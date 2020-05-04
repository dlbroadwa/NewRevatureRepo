package bank.services;
import bank.dataaccess.AccountDataAccessObject;
import bank.dataaccess.PostGresConnectionUtil;
import bank.dataaccess.TransactionDAO;
import bank.dataaccess.UserNameBankAccountIDPairDAO;
import bank.model.BankAccount;
import bank.model.Transaction;
import bank.model.UserNameBankAccountIDPair;

import java.sql.Timestamp;

public class AccountService {
    private AccountDataAccessObject accountDAO;

    private UserNameBankAccountIDPairDAO userNameAccountDao;

    private TransactionDAO transactionDAO;

    public AccountService(){
        this.accountDAO = new AccountDataAccessObject(new PostGresConnectionUtil());
        this.userNameAccountDao = new UserNameBankAccountIDPairDAO(new PostGresConnectionUtil());
        this.transactionDAO = new TransactionDAO(new PostGresConnectionUtil());
    };

    public AccountService(AccountDataAccessObject accountRepo, UserNameBankAccountIDPairDAO userNameAccountDao, TransactionDAO transactionDAO) {

        this.accountDAO = accountRepo;
        this.userNameAccountDao = userNameAccountDao;
        this.transactionDAO = transactionDAO;
    }

    public void deposit(String userName, Integer accountID, double amount) {
        if(amount < 0)
        {
            throw new IllegalArgumentException("Invalid Amount");
        }
        //Check if username matches accountID
        UserNameBankAccountIDPair pair = new UserNameBankAccountIDPair(accountID, userName);
        if(!(userNameAccountDao.relationshipBetweenUserAndAccountExists(pair)))
        {
            throw new IllegalArgumentException("User did not have access or account did not exist");
        }
        BankAccount[] currentAccount = accountDAO.retrieveByID(accountID);
        double oldBalance = currentAccount[0].getCurrentBalance();
        currentAccount[0].setCurrentBalance(currentAccount[0].getCurrentBalance() + amount);
        boolean wasUpdated = accountDAO.update(currentAccount[0]);
        if(wasUpdated)
        {
            //Success
            Timestamp timeOfTransaction = new Timestamp(System.currentTimeMillis());
            Transaction transaction = new Transaction(0, accountID, oldBalance, amount, "deposit", timeOfTransaction );
            transactionDAO.save(transaction);
        }
        else
        {
            //Fail
        }
    }
    public void withdraw(String userName, Integer accountID, double amount) {
        if(amount < 0)
        {
            throw new IllegalArgumentException("Invalid Amount");
        }
        //Check if username matches accountID
        UserNameBankAccountIDPair pair = new UserNameBankAccountIDPair(accountID, userName);
        if(!(userNameAccountDao.relationshipBetweenUserAndAccountExists(pair)))
        {
            throw new IllegalArgumentException("User did not have access or account did not exist");
        }
        BankAccount[] currentAccount = accountDAO.retrieveByID(accountID);
        double oldBalance = currentAccount[0].getCurrentBalance();
        currentAccount[0].setCurrentBalance(currentAccount[0].getCurrentBalance() - amount);
        boolean wasUpdated = accountDAO.update(currentAccount[0]);
        if(wasUpdated)
        {
            //Success
            Timestamp timeOfTransaction = new Timestamp(System.currentTimeMillis());
            Transaction transaction = new Transaction(0, accountID, oldBalance, amount, "withdrawal", timeOfTransaction );
            transactionDAO.save(transaction);
        }
        else
        {
            //Fail
        }
    }
    public void transfer(String userName, int userAccountID, double amount, int transferredAccountID) {
        //Check if username matches accountID
        UserNameBankAccountIDPair pair = new UserNameBankAccountIDPair(userAccountID, userName);
        if (!(userNameAccountDao.relationshipBetweenUserAndAccountExists(pair))) {
            throw new IllegalArgumentException("User did not have access or account did not exist");
        }
        BankAccount[] accounts = accountDAO.retrieveByID(userAccountID);
        if (accounts.length == 0) {
            //Throw some error saying account DNE
            return;
        }
        BankAccount currentAccount = accounts[0];
        if (currentAccount.getCurrentBalance() - amount < 0) {
            throw new IllegalArgumentException("Invalid Amount");
        }
        double oldBalance = accounts[0].getCurrentBalance();
        accounts = accountDAO.retrieveByID(transferredAccountID);
        if (accounts.length == 0) {
            //Throw some DNE error
            return;
        }
        BankAccount transferAccount = accounts[0];
        currentAccount.setCurrentBalance(currentAccount.getCurrentBalance() - amount);
        transferAccount.setCurrentBalance((transferAccount.getCurrentBalance() + amount));
        Boolean wasTransferred = accountDAO.transfer(currentAccount, transferAccount);
        if (wasTransferred) {
            Timestamp timeOfTransaction = new Timestamp(System.currentTimeMillis());
            Transaction transaction = new Transaction(0, userAccountID, oldBalance, amount, "transfer", timeOfTransaction );
            transactionDAO.save(transaction);
        } else
        {
            //Throw some error
            return;
        }
    }
}
