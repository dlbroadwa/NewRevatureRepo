import com.game.data.AccountSQLRepo;
import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Account;
import com.game.models.Message;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class RepoTest {
    private final Logger logger = Logger.getLogger(RepoTest.class);
    Repository<Account,String> accountSQLRepo;
    MessageSQLRepo messageSQLRepo;

    @Before
    public void test_init(){
        BasicConfigurator.configure();
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.debug("db.properties not found", e);
        }
        ConnectionUtils connection = new PostgresConnectionUtil(prop.getProperty("url"),
                prop.getProperty("username"),prop.getProperty("password"));

        try {
            Assert.assertNotNull(connection.getConnection());
        } catch (SQLException e) {
            logger.debug("Connection not found", e);
        }

        accountSQLRepo = new AccountSQLRepo(connection);
        messageSQLRepo = new MessageSQLRepo(connection);
    }

    @Test
    public void accountRepoTest(){
        Account temp = new Account("test","password","dyltrashs@gmail,com");
        Account temp2;
        accountSQLRepo.save(temp);
        temp2 = accountSQLRepo.findById("test");
        Assert.assertNotNull("Account was not created", temp2);
        Assert.assertEquals("Account created is not the same", temp.getName(), temp2.getName());
        Assert.assertEquals("Account created is not the same", temp.getBalance(), temp2.getBalance());
        Assert.assertEquals("Account created is not the same", temp.getEmail(), temp2.getEmail());
        temp.addBalance(100);
        accountSQLRepo.update(temp,"test");
        temp2 = accountSQLRepo.findById("test");
        Assert.assertEquals("Account created is not the same", 100, temp2.getBalance());
        temp.subtractBalance(99);
        accountSQLRepo.update(temp,"test");
        temp2 = accountSQLRepo.findById("test");
        Assert.assertEquals("Account created is not the same", 1, temp2.getBalance());
        accountSQLRepo.delete("test");
    }

    @Test
    public void messageRepoTest(){
        accountSQLRepo.delete("test");
        Account temp = new Account("test","password","dyltrashs@gmail,com");
        accountSQLRepo.save(temp);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message m = new Message("Hello World", "test", timestamp,"test");
        messageSQLRepo.save(m);
        messageSQLRepo.clear(temp.getName());
        accountSQLRepo.delete("test");
    }
}
