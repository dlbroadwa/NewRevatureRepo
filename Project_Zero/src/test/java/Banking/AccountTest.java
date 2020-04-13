package Banking;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    // I am considering the next two test testing whether the Account object/class handles positive numbers for the
    @Test
    public void accountIDConstructorAndGetterWorksWithZeroTest() {
        Account accountZero = new Account(0);
        Assert.assertEquals("The same accountID was not returned", 0, accountZero.getAccountID());
    }

    @Test
    public void accountIDConstructorAndGetterWorksWithOneTest() {
        Account accountZero = new Account(1);
        Assert.assertEquals("The same accountID was not returned", 1, accountZero.getAccountID());
    }

    @Test
    public void accountIDConstructorThrowsExceptionWhenANegagtiveAccountIDIsProvidedTest() {
        Assert.assertThrows("Account constructor for just accountID did not throw IllegalArgumentException when given a negative number for accountID", IllegalArgumentException.class, () -> new Account(-123));
    }

    @Test
    public void fullAccountConstructorTest() {

    }

}
