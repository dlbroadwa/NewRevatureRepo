package Com.Tests;

import clients.AccountsService;
import data.AccountsInSQLRepo;
import data.IAccounts;
import data.TransactionsSQLRepo;
import model.Accounts;
import model.Transactions;
import model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

import java.util.List;

public class TransactionTests {
    public TransactionsSQLRepo transaction;
    ConnectionUtils connectionUtils;
    @Before
    public void testInit(){
        connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");

        transaction = new TransactionsSQLRepo(connectionUtils);
    }

    @Test
    public void testFindTransaction(){
        IAccounts<Accounts, Integer> IAccountHolders = new AccountsInSQLRepo(connectionUtils);
        AccountsService accountsService = new AccountsService(IAccountHolders);
        Accounts testAccount = accountsService.findByAccount("user@yahoo.com");

        Transactions test = transaction.findTransactionbyId(testAccount, 20);

        Assert.assertNotNull(test);

    }

    @Test
    public void testFindAllTransaction(){
        IAccounts<Accounts, Integer> IAccountHolders = new AccountsInSQLRepo(connectionUtils);
        AccountsService accountsService = new AccountsService(IAccountHolders);
        Accounts testAccount = accountsService.findByAccount("user@yahoo.com");

        List<Transactions> testAllTrans = transaction.allTransactionByAccount(testAccount);

        Assert.assertNotNull(testAllTrans);

    }

    @Test
    public void testInsertTransaction(){
        IAccounts<Accounts, Integer> IAccountHolders = new AccountsInSQLRepo(connectionUtils);
        AccountsService accountsService = new AccountsService(IAccountHolders);
        Accounts testAccount = accountsService.findByAccount("user@yahoo.com");
        float amount = 10.02f;
        String type = "deposit";
        transaction.insertTransaction(testAccount, amount, type);

    }

    @Test
    public void testTransactionModelClass(){
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

        Transactions trans = new Transactions();
        trans.setTransaction_type("withdraw");
        trans.setTrans_amount(100f);
        trans.setId(100);;
        trans.setAccount(testAccount);
        trans.setTimestamp("2020-04-19 09:20:19");

        Assert.assertNotNull(trans);

        Assert.assertEquals("Incorrect email", "hello@yahoo.com", trans.getAccount().getHolder().getEmail_address());
    }

}

