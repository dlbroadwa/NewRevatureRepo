import com.game.data.AccountSQLRepo;
import com.game.models.Account;
import com.game.service.accountservices.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class CreationTest {
    @Mock AccountSQLRepo mockAccount;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    AccountDetailService accountDetailService;
    CreationService creationService;

    @Before
    public void init(){
        List<String> accountList = new ArrayList<String>();
        accountList.add("admin");
        when(mockAccount.findAllID()).thenReturn(accountList);
        accountDetailService = new AccountDetailServiceImp(mockAccount);
        creationService = new CreationServiceImp(accountDetailService);
    }

    @Test
    public void createTest(){
        Assert.assertFalse("Cannot create existing account",creationService.signUp("admin","password", "email"));

        Assert.assertTrue("Did not create an account",creationService.signUp("test","password", "email"));

        Assert.assertEquals("Attribute name does not match","test",
                accountDetailService.getAccount("test").getName());
        Assert.assertEquals("Attribute name does not match","password",
                accountDetailService.getAccount("test").getPassword());
        Assert.assertEquals("Attribute name does not match","email",
                accountDetailService.getAccount("test").getEmail());
    }

    @Test
    public void closeTest(){
        Assert.assertFalse("Account that does not exist is deleted", creationService.delete("test1"));
        Assert.assertFalse("Admin account was deleted", creationService.delete("admin"));
        Assert.assertTrue("Account was not deleted",creationService.delete("test"));
    }


}
