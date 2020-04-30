package bank.services;

import bank.dataaccess.AccountDataAccessObject;
import bank.dataaccess.PostGresConnectionUtil;
import bank.model.BankAccount;

public class AccountService {
    private AccountDataAccessObject accountDAO;

    public AccountService(){
        this.accountDAO = new AccountDataAccessObject(new PostGresConnectionUtil());
    };

    public AccountService(AccountDataAccessObject accountRepo) {
        this.accountDAO = accountRepo;
    }

    public void deposit(String userName, int accountID, double amount) {
        if(amount < 0)
        {
            throw new IllegalArgumentException("Invalid Amount");
        }
        //Check if username matches accountID
        if(false)
        {
            throw new IllegalArgumentException("User did not have access or account did not exist");
        }
        BankAccount currentAccount = new BankAccount(accountID, amount);
        boolean wasUpdated = true;
        currentAccount.setCurrentBalance(currentAccount.getCurrentBalance() + amount);
        wasUpdated = accountDAO.update(currentAccount);
        if(wasUpdated)
        {
            //Success
        }
        else
        {
            //Fail
        }
    }
    public void withdraw(String userName, int accountID, double amount) {
        double currentAmount = 0 - amount;
    }
    public void transfer(String userName, int userAccountID, double amount, int transferredAccountID) {
    }
}
