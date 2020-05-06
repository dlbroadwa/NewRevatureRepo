import com.game.data.AccountSQLRepo;
import com.game.service.accountservices.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

public class FriendTest {
    @Mock AccountSQLRepo mockAccount;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    AccountDetailService accountDetailService;
    FriendService friendService;

    @Before
    public void init(){
        List<String> accountList = new ArrayList<String>();
        accountList.add("admin");
        accountList.add("king");
        accountList.add("queen");
        accountList.add("jack");
        when(mockAccount.findAllID()).thenReturn(accountList);
        accountDetailService = new AccountDetailServiceImp(mockAccount);
        friendService = new FriendServiceImp(accountDetailService);
        accountDetailService.addAccount("test","password","email");
    }


    @Test
    public void addFriendTest(){
        //Add an account that doesn't exist
        Assert.assertFalse("Non existent friend was added",
                friendService.addFriend("test", "ten"));
        //Add an account that exists
        Assert.assertTrue("Friend was not added",friendService.addFriend("test", "jack"));
    }

    @Test
    public void removeFriendTest(){
        friendService.addFriend("test", "jack");
        friendService.addFriend("test", "queen");
        friendService.addFriend("test", "king");

        //remove non-existing friend
        Assert.assertFalse("Friend was not added",friendService.removeFriend("test", "ten"));
        //remove friend on friend list
        Assert.assertTrue("Friend was not added",friendService.removeFriend("test", "jack"));
    }

    @Test
    public void getFriendsTest(){
        //friend list should be empty at the beginning
        Assert.assertTrue("FriendList os not empty somehow",friendService.getFriends("test").isEmpty());
        friendService.addFriend("test", "jack");
        //Friend list should contain 1 friend
        Assert.assertFalse("FriendList is not empty somehow",friendService.getFriends("test").isEmpty());
        Assert.assertEquals("FriendList is not empty somehow",1,friendService.getFriends("test").size());
        friendService.addFriend("test", "queen");
        friendService.addFriend("test", "king");
        Assert.assertEquals("FriendList is not empty somehow",3,friendService.getFriends("test").size());
    }
}
