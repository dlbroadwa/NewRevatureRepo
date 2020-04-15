package Banking;

import org.junit.Assert;
import org.junit.Test;

public class TransactionTest {
    double previousBalance = 2;
    double transactionAmount = -1;
    String description = "withdrawal";

    @Test
    public void transactionIDEqualsZeroTest() {
        final int transactionID = 0;
        Transaction transaction = new Transaction(transactionID, previousBalance,transactionAmount, description);
        Assert.assertEquals(transactionID, transaction.getTransactionID());
    }

    @Test
    public void transactionIDEqualsOneTest() {
        final int transactionID = 1;
        Transaction transaction = new Transaction(transactionID, previousBalance,transactionAmount, description);
        Assert.assertEquals(transactionID, transaction.getTransactionID());
    }

    @Test
    public void transactionIDEqualsNegativeOneTest() {
        final int transactionID = -1;
        Assert.assertThrows("IllegalArgumentException was not thrown when transactionID was less than zero!", IllegalArgumentException.class, () -> new Transaction(transactionID, previousBalance, transactionAmount, description));

    }

    @Test
    public void transactionToStringTest() {
        Transaction transaction = new Transaction(0, previousBalance, transactionAmount, description);
        //Assert.assertEquals("");
    }
}
