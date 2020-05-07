import bank.dataaccess.*;
import bank.model.BankAccount;
import bank.model.UserNameBankAccountIDPair;
import bank.services.AccountService;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.ArgumentMatchers.any;

public class AccountServiceTest {

    private AccountService accountService;
    private String userName;
    private int accountID;
    private double amount;
    private BankAccount account;
    private UserNameBankAccountIDPair pair;
    BankAccount[] accounts;
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
        userName = "jack@gmail.com";
        accountID = 6;
        amount = 1100;
        account = new BankAccount(accountID, amount);
        accounts = new BankAccount[]{account};
        pair = new UserNameBankAccountIDPair(accountID, userName);

    }
    @Test
    public void depositSuccess()
    {
        Mockito.when(userPairDAO.relationshipBetweenUserAndAccountExists(any(UserNameBankAccountIDPair.class))).thenReturn(true);
        Mockito.when(bankAccountDAO.retrieveByID(accountID)).thenReturn(accounts);
        Mockito.when(bankAccountDAO.update(account)).thenReturn(true);
        Assert.assertEquals("Not Passed", true, accountService.deposit(userName, accountID, amount));
    }

    @Test
    public void depositFailInsufficientFunds()
    {
        boolean passed = false;
        try
        {
            accountService.deposit(userName, accountID, 10000);
            passed = true;
        }
        catch(IllegalArgumentException e)
        {
        }
        Assert.assertFalse("Passed", passed);
    }

    @Test
    public void depositFailEmailAccountNotMatch()
    {
        boolean passed = false;
        try
        {
            Mockito.when(userPairDAO.relationshipBetweenUserAndAccountExists(any(UserNameBankAccountIDPair.class))).thenThrow(IllegalArgumentException.class);
            accountService.deposit(userName, accountID, 1000);
            passed = true;
        }
        catch(IllegalArgumentException e)
        {
        }
        Assert.assertFalse("Passed", passed);
    }

    @Test
    public void accountFailedDeposit()
    {
        Mockito.when(userPairDAO.relationshipBetweenUserAndAccountExists(any(UserNameBankAccountIDPair.class))).thenReturn(true);
        Mockito.when(bankAccountDAO.retrieveByID(accountID)).thenReturn(accounts);
        Mockito.when(bankAccountDAO.update(account)).thenReturn(false);
        Assert.assertEquals("Passed", false, accountService.deposit(userName, accountID, amount));
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
