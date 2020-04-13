package Banking;

import java.math.BigDecimal;

public abstract class Transaction {
    protected BigDecimal transactionAmount;
    protected boolean newTransaction = false;

    public abstract void print();

    public void setNewTransactionTrue() {
        newTransaction = true;
    }

    public void setNewTransactionFalse() {
        newTransaction = false;
    }

    public boolean getNewTransaction() {
        return newTransaction;
    }
}
