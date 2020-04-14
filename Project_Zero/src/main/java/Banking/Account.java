package Banking;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {
    private int accountID = -1;
    private BigDecimal currentBalance = null;
    private ArrayList<Transaction> balanceHistory = null;

    public Account(int accountID) {
        if (accountID < 0) throw new IllegalArgumentException("AccountID has to be a positive number.");
        this.accountID = accountID;
    }

    public Account(int accountID, BigDecimal currentBalance, ArrayList<Transaction> balanceHistory) {
        this(accountID);
        this.currentBalance = currentBalance;
        this.balanceHistory = balanceHistory;
    }

    public void processTransaction(Transaction transaction) {
        currentBalance = transaction.getUpdatedBalance();
        balanceHistory.add(transaction);
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

    @Override
    public String toString() {
        return accountID + "," + currentBalance.toString() + "," + balanceHistory.toString();
    }
}
