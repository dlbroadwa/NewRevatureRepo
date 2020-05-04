package Com.Tests;

import model.Accounts;
import model.Users;
import org.junit.Assert;
import org.junit.Test;

public class AccountsModelTest {

    @Test
    public void TestAccountsModelSetBalanceMethod() {
        Users user = new Users();
        user.setName("Kobe Bryant");
        user.setPhone_number("6197776868");
        user.setUser_pin("3692581");
        user.setEmail_address("hello@yahoo.com");
        Accounts testAccount = new Accounts();
        testAccount.setHolder(user);
        testAccount.setAccount_id(1000);
        testAccount.setBalance(10000.05f);
        testAccount.setAccountType("checking");

        Assert.assertEquals(100,10000.05f,testAccount.getBalance());

    }
}