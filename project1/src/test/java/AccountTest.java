import com.game.data.AccountSQLRepo;
import com.game.service.accountservices.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AccountTest {
    @Mock AccountSQLRepo mockAccount;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    AccountDetailService accountDetailService;
    ModificationService modificationService;
    FriendService friendService;
    CreationService creationService;

    @Before
    public void init(){
        accountDetailService = new AccountDetailServiceImp(mockAccount);
        modificationService = new ModificationServiceImp(accountDetailService);
        friendService = new FriendServiceImp(accountDetailService);
        creationService = new CreationServiceImp(accountDetailService);
    }

    @Test
    public void checkExistTest(){

    }

    @Test
    public void credentialsTest(){

    }

    @Test
    public void getFindByID(){

    }

    @Test
    public void getAccountTest(){

    }

    @Test
    public void getAccountListTest(){

    }
}
