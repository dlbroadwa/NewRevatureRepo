import com.game.data.AccountSQLRepo;
import com.game.data.ItemSQLRepo;
import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Account;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class RepoTest {
    private final Logger logger = Logger.getLogger(RepoTest.class);
    Repository<Account,String> accountSQLRepo;
    ItemSQLRepo itemSQLRepo;
    MessageSQLRepo messageSQLRepo

    @Before
    void test_init(){
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.debug("db.properties not found");
        }
        ConnectionUtils connection = new PostgresConnectionUtil(prop.getProperty("url"),
                prop.getProperty("username"),prop.getProperty("password"));

        try {
            Assert.assertNotNull(connection.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        accountSQLRepo = new AccountSQLRepo(connection);
        itemSQLRepo = new ItemSQLRepo(connection);
        messageSQLRepo = new MessageSQLRepo(connection);
    }

    @Test
    void accountRepoTest(){
        Account temp = new Account("Dylan","password","dyltrashs@gmail,com");
        Account temp2;
        accountSQLRepo.save(temp);
        temp2 = accountSQLRepo.findById("Dylan");
        Assert.assertNotNull("Account was not created", temp2);
        Assert.assertEquals("Account created is not the same", temp, temp2);
    }

    @Test
    void messageRepoTest(){

    }
}
