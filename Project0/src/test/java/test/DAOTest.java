package test;

import com.company.banking.BankCustomer;
import com.company.DataAccess.ConnectionUtils;
import com.company.DataAccess.DAO;
import com.company.DataAccess.DAOI;
import com.company.DataAccess.PostgresConnectionUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;


public class DAOTest {

    ConnectionUtils connectionUtils = new PostgresConnectionUtil(
            System.getenv("URL"),System.getenv("DBUSERNAME"), System.getenv("DBPASSWORD"), System.getenv("SCHEMA"));
    DAOI<BankCustomer, Integer> dao = new DAO(connectionUtils);

    @Test
    public void findAccountByIdValidID()
    {
        BankCustomer tempCustomer = new BankCustomer();
        tempCustomer = dao.findAccountById(1000);
        Assert.assertEquals("ID not the same", true, (tempCustomer.getId() == 1000)
                && (tempCustomer.getCheckings() == 10000)
                && (tempCustomer.getSavings() == 10000));
    }

    @Test
    public void findAccountByIdInvalidID()
    {
        BankCustomer tempCustomer = new BankCustomer();
        tempCustomer = dao.findAccountById(2);
        Assert.assertNotEquals("ID not he same", 2, tempCustomer.getId());
    }

    @Test
    public void findByUserNamePasswordValidUsernamePassword()
    {
        BankCustomer tempCustomer = new BankCustomer();
        tempCustomer = dao.findByUserNamePassword("test1", "123");
        Assert.assertEquals("ID not he same", 1000, tempCustomer.getId());
    }

    @Test
    public void findByUserNamePasswordValidUsernameInvalidPassword()
    {
        BankCustomer tempCustomer = dao.findByUserNamePassword("test1", "1234");
        Assert.assertNotEquals("ID not he same", 1000, tempCustomer.getId());
    }

    @Test
    public void updateOneAccountCheckingsValidInformation()
    {
        BankCustomer tempCustomer = dao.findAccountById(1001);
        double oldAmount = tempCustomer.getCheckings();
        dao.updateOneAccount(1001, 200, "checkings");
        tempCustomer = dao.findAccountById(1001);
        double newAmount = tempCustomer.getCheckings();
        Assert.assertTrue("Did not deposit", (newAmount != oldAmount) && (newAmount == 200));
    }

    @Test
    public void updateOneAccountSavingsValidInformation()
    {
        BankCustomer tempCustomer = dao.findAccountById(1001);
        double oldAmount = tempCustomer.getSavings();
        dao.updateOneAccount(1001, 200, "savings");
        tempCustomer = dao.findAccountById(1001);
        double newAmount = tempCustomer.getSavings();
        Assert.assertTrue("Did not deposit", (newAmount != oldAmount) && (newAmount == 200));
    }

    @Test
    public void updateOneAccountInvalidID()
    {

        boolean actual = dao.updateOneAccount(-1, 100, "checkings");
        Assert.assertFalse("Did deposit", actual);
    }

    @Test
    public void updateAccountsValidInformation()
    {
        BankCustomer tempCustomer = dao.findAccountById(1002);
        double oldChecking = tempCustomer.getCheckings();
        double oldSaving = tempCustomer.getSavings();
        dao.updateAccounts(1001, 200, 200);
        tempCustomer = dao.findAccountById(1001);
        double newChecking = tempCustomer.getCheckings();
        double newSaving = tempCustomer.getSavings();
        Assert.assertTrue("Did update", (oldChecking != newChecking)
                && (oldSaving != newSaving)
                && (newChecking == 200)
                && (newSaving == 200));
    }

    @Test
    public void updateAccountsInValidID()
    {
        BankCustomer tempCustomer = dao.findAccountById(-1);
        double oldChecking = tempCustomer.getCheckings();
        double oldSaving = tempCustomer.getSavings();
        dao.updateAccounts(-1, 200, 200);
        tempCustomer = dao.findAccountById(-1);
        double newChecking = tempCustomer.getCheckings();
        double newSaving = tempCustomer.getSavings();
        Assert.assertFalse("Did update", (oldChecking != newChecking)
                && (oldSaving != newSaving)
                && (newChecking == 200)
                && (newSaving == 200));
    }

    @Test
    public void addTransactionValid()
    {
        String testDate = "2016-08-16";
        LocalDate testLocal = LocalDate.parse(testDate);
        dao.addTransaction(1001 , testLocal, "The transaction worked");
        List<String> transactions = dao.findAllTransactionsById(1001);
        Assert.assertEquals("Transaction Failed", 1, transactions.size());
    }

    @Test
    public void addTransactionInValidId()
    {
        String testDate = "2016-08-16";
        LocalDate testLocal = LocalDate.parse(testDate);
        dao.addTransaction(-1 , testLocal, "The transaction worked");
        List<String> transactions = dao.findAllTransactionsById(1001);
        Assert.assertNotEquals("Transaction Failed", 1, transactions.size());
    }

    @Test
    public void findAllTransactionByIDValid()
    {
        String testDate = "2016-08-16";
        LocalDate testLocal = LocalDate.parse(testDate);
        dao.addTransaction(1003 , testLocal, "The transaction worked1");
        dao.addTransaction(1003 , testLocal, "The transaction worked2");
        dao.addTransaction(1003 , testLocal, "The transaction worked3");
        List<String> transactions = dao.findAllTransactionsById(1003);
        Assert.assertNotEquals("Transaction Failed", 3, transactions.size());
    }

    @Test
    public void findAllTransactionByIDInvalid()
    {
        String testDate = "2016-08-16";
        LocalDate testLocal = LocalDate.parse(testDate);
        List<String> transactions = dao.findAllTransactionsById(-1);
        Assert.assertTrue("Transaction Failed", transactions.size() == 0);
    }

    @After
    public void reset()
    {
        dao.updateAccounts(1001, 100, 100);
        dao.updateAccounts(1002, 100, 100);
        dao.deleteAllTransactionsById(1001);
        dao.deleteAllTransactionsById(1003);
    }
}
