package Application;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Account {
    private int accountID = -1;
    private BigDecimal currentBalance = new BigDecimal(0);
    private ArrayList<Transaction> balanceHistory = new ArrayList<Transaction>();

    public Account(int accountID) {
        if (accountID < 0) throw new IllegalArgumentException("AccountID has to be a positive number.");
    }

    public Account(int accountID, BigDecimal currentBalance, ArrayList<Transaction> balanceHistory) {
        this(accountID);
        this.currentBalance = currentBalance;
        this.balanceHistory = balanceHistory;
        // TODO create transaction
    }

    public void withdraw(BigDecimal amount) {
        currentBalance = currentBalance.subtract(amount);
        // TODO create transaction
    }

    public void deposit(BigDecimal amount) {
        currentBalance = currentBalance.add(amount);
        // TODO create transaction
    }

    public int getAccountID() {
        return accountID;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public ArrayList<Transaction> getBalanceHistory() {
        return balanceHistory;
    }
}
