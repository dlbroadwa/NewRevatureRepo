import com.game.data.AccountSQLRepo;
import com.game.service.accountservices.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class CreationTest {
    @Mock AccountSQLRepo mockAccount;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    AccountDetailService accountDetailService;
    CreationService creationService;

    @Before
    public void init(){
        accountDetailService = new AccountDetailServiceImp(mockAccount);
        creationService = new CreationServiceImp(accountDetailService);
    }

    @Test
    public void createTest(){

    }

    @Test
    public void closeTest(){

    }


}
