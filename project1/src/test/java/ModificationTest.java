import com.game.data.AccountSQLRepo;
import com.game.service.accountservices.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ModificationTest {
    @Mock AccountSQLRepo mockAccount;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    AccountDetailService accountDetailService;
    ModificationService modificationService;

    @Before
    public void init(){
        accountDetailService = new AccountDetailServiceImp(mockAccount);
        modificationService = new ModificationServiceImp(accountDetailService);
    }

    @Test
    public void depositTest(){

    }

    @Test
    public void withdrawtest(){

    }

    @Test
    public void changePasswordTest(){

    }

    @Test
    public void changeBankAccountTest(){

    }

}
