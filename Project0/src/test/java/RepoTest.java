import com.game.app.GameAccountApplication;
import com.game.data.AccountSQLRepo;
import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Account;
import com.game.models.Message;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class RepoTest {
    private Repository<Account, String> accounts;
    private Repository<Message, String> messages;

    @Before
    public void testInit() {
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://dyltrashs.crxekrgyc1qs.us-east-2.rds.amazonaws.com:5432/dyltrashs",
                "dyltra", "password");
        accounts = new AccountSQLRepo(connectionUtils);
        messages = new MessageSQLRepo(connectionUtils);
    }

    @Test
    public void testCreateDelete(){
        Message temp2 = new Message("Help me","dylan","dylan");
        Account temp = new Account("bob","dylan");
        accounts.save(temp);
        accounts.delete(temp.getName());
        messages.save(temp2);
        messages.delete(temp2.getTo());
    }

}
