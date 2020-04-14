package Banking;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransactionTest {
    BigDecimal previousBalance = new BigDecimal(2);
    BigDecimal updatedBalance = new BigDecimal(1);
    BigDecimal transactionAmount = new BigDecimal(-1);
    String description = "withdrawal";

    @Test
    public void transactionIDEqualsZeroTest() {
        final int transactionID = 0;
        Transaction transaction = new Transaction(transactionID, previousBalance, updatedBalance,transactionAmount, description);
        Assert.assertEquals(transactionID, transaction.getTransactionID());
    }

    @Test
    public void transactionIDEqualsOneTest() {
        final int transactionID = 1;
        Transaction transaction = new Transaction(transactionID, previousBalance, updatedBalance,transactionAmount, description);
        Assert.assertEquals(transactionID, transaction.getTransactionID());
    }

    @Test
    public void transactionIDEqualsNegativeOneTest() {
        final int transactionID = -1;
        Assert.assertThrows("IllegalArgumentException was not thrown when transactionID was less than zero!", IllegalArgumentException.class, () -> new Transaction(transactionID, previousBalance, updatedBalance,transactionAmount, description));

    }
}
