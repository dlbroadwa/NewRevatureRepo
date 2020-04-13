package Banking;

import java.math.BigDecimal;

public class Transaction {
    private int transactionID;
    private BigDecimal previousBalance;
    private BigDecimal updatedBalance;
    private BigDecimal transactionAmount;
    private String description;

    public Transaction(int transactionID, BigDecimal previousBalance, BigDecimal updatedBalance, BigDecimal transactionAmount, String description) {
        this.transactionID = transactionID;
        this.previousBalance = previousBalance;
        this.updatedBalance = updatedBalance;
        this.transactionAmount = transactionAmount;
        this.description = description;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public BigDecimal getUpdatedBalance() {
        return updatedBalance;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public void printToScreen() {
        System.out.println("Transaction Amount:\t" + transactionAmount + "\nPrevious Balance:\t" + previousBalance + "\nUpdated Balance:\t" + updatedBalance + "\nDescription:\t" + description);
    }
}
