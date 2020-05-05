import com.game.data.AccountSQLRepo;
import com.game.data.MessageSQLRepo;
import com.game.models.Account;
import com.game.service.accountservices.AccountDetailService;
import com.game.service.accountservices.AccountDetailServiceImp;
import com.game.service.messageservices.MessageService;
import com.game.service.messageservices.MessageServiceImp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MessageTest {

    @Mock MessageSQLRepo mockMessage;
    @Mock AccountSQLRepo mockAccount;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        AccountDetailService mockAService = new AccountDetailServiceImp(mockAccount);
        MessageService mockMService = new MessageServiceImp(mockMessage);
    }

    @Test
    public void sendTest(){

    }

    @Test
    public void receiveTest(){

    }

    @Test
    public void clearTest(){

    }

}
