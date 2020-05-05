import com.game.data.AccountSQLRepo;
import com.game.service.accountservices.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class FriendTest {
    @Mock AccountSQLRepo mockAccount;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    AccountDetailService accountDetailService;
    FriendService friendService;

    @Before
    public void init(){
        accountDetailService = new AccountDetailServiceImp(mockAccount);
        friendService = new FriendServiceImp(accountDetailService);
    }

    @Test
    public void addFriendTest(){

    }

    @Test
    public void getFriendsTest(){

    }

    @Test
    public void removeFriendTest(){

    }

}
