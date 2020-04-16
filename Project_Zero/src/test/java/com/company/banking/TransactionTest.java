package com.company.banking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class TransactionTest {
    double previousBalance = 2;
    double transactionAmount = -1;
    String description = "withdrawal";
    Timestamp timeOfTransaction = new Timestamp(System.currentTimeMillis());

    @Test
    public void transactionIDEqualsZeroThrowsExceptionTest() {
        final int transactionID = 0;
        Transaction transaction = new Transaction(transactionID, previousBalance,transactionAmount, description, timeOfTransaction);
        Assert.assertEquals(transactionID, transaction.getTransactionID());
    }

    @Test
    public void transactionIDEqualsOneTest() {
        final int transactionID = 1;
        Transaction transaction = new Transaction(transactionID, previousBalance,transactionAmount, description, timeOfTransaction);
        Assert.assertEquals(transactionID, transaction.getTransactionID());
    }

    @Test
    public void transactionIDEqualsNegativeOneTest() {
        final int transactionID = -1;
        Assert.assertThrows("IllegalArgumentException was not thrown when transactionID was less than zero!", IllegalArgumentException.class, () -> new Transaction(transactionID, previousBalance, transactionAmount, description, timeOfTransaction));

    }

    @Test
    public void transactionToStringTest() {
        Transaction transaction = new Transaction(0, previousBalance, transactionAmount, description, timeOfTransaction);
        //Assert.assertEquals("");
    }
}
