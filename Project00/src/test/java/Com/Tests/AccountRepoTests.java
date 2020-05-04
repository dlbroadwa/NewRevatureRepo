package Com.Tests;

import data.AccountsInSQLRepo;
import model.Accounts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

public class AccountRepoTests {
    public AccountsInSQLRepo account;
    ConnectionUtils connectionUtils;
    @Before
    public void testInit(){
        connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");

        account = new AccountsInSQLRepo(connectionUtils);
    }

    @Test
    public void findAccountTest(){
        String email = "user@yahoo.com";
        Accounts testAccount = account.findByAccount(email);

        Assert.assertTrue(testAccount.getHolder().getEmail_address().equals(email));

    }

    @Test
    public void updateBalanceTest(){
        float amount = 1.31f;
        String email = "user@yahoo.com";
        Accounts testAccount = account.findByAccount(email);
        float initialBalance = testAccount.getBalance();
        float tmpNewAmount = amount + initialBalance;
        account.updateBalance(testAccount, amount);

        testAccount = account.findByAccount(email);
        float afterAmount = testAccount.getBalance();

        Assert.assertTrue(tmpNewAmount == afterAmount);

    }



}
