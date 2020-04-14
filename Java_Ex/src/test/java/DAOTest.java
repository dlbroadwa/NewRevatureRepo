import gameaccounts.AccountSQLDAO;
import org.junit.Before;
import org.junit.Test;

public class DAOTest {
    AccountSQLDAO demo;

    @Before
    public void testInit() {
        demo = new AccountSQLDAO();
    }

    @Test
    public void listTest() {
        demo.list();
    }

    @Test
    public void userMessageTest() {
    }

}
