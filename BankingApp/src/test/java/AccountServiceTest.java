import bank.dataaccess.*;
import bank.model.BankAccount;
import bank.model.Transaction;
import bank.model.User;
import bank.model.UserNameBankAccountIDPair;
import bank.services.AccountService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class AccountServiceTest {

    private AccountService accountService;
    @Mock
    private AccountDataAccessObject bankAccountDAO = null;
    @Mock
    private UserNameBankAccountIDPairDAO userPairDAO = null;
    @Mock
    private TransactionDAO transactionDAO = null;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        accountService = new AccountService(bankAccountDAO, userPairDAO, transactionDAO);
    }
    @Test
    public void depositSuccess()
    {
        BankAccount account = new BankAccount(6, 1000);
        BankAccount[] accounts = {account};
        String userName = "jack@gmail.com";
        int accountID = 6;
        double amount = 1100;
        UserNameBankAccountIDPair pair = new UserNameBankAccountIDPair(accountID, userName);
        //Mockito.when(userPairDAO.relationshipBetweenUserAndAccountExists(pair)).thenReturn(true);
        Mockito.when(bankAccountDAO.retrieveByID(accountID)).thenReturn(accounts);
        Mockito.when(bankAccountDAO.update(account)).thenReturn(true);
        //Mockito.when(transactionDAO.save(new Transaction())).thenReturn(6);
        Assert.assertEquals("Not Passed", true, accountService.deposit(userName, accountID, amount));
    }

    @Test
    public void depositFail()
    {

    }

    @Test
    public void accountTransfer()
    {

    }

    @Test
    public void accountSave()
    {

    }

    @Test
    public void accountDelete()
    {

    }

    @After
    public void reset()
    {

    }
}
