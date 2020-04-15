package Banking;


import java.util.ArrayList;

public class Account {
    private int accountID = -1;
    private double currentBalance;
    private ArrayList<Transaction> balanceHistory = null;

    public Account(int accountID) throws IllegalArgumentException {
        if (accountID < 0) throw new IllegalArgumentException("AccountID has to be a positive number.");
        this.accountID = accountID;
    }

    public Account(int accountID, double currentBalance, ArrayList<Transaction> balanceHistory) {
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

    public double getCurrentBalance() {
        return currentBalance;
    }

    public ArrayList<Transaction> getBalanceHistory() {
        return balanceHistory;
    }

    @Override
    public String toString() {

        return accountID + "," + currentBalance + "," + balanceHistory.toString();
    }
}
