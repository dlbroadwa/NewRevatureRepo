package Com.Tests;

import clients.UsersService;
import data.BankUsersInSQLRepo;
import data.IBankUsers;
import model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

public class UserModelTest {



    public BankUsersInSQLRepo bankUser;
    ConnectionUtils connectionUtils;
    @Before
    public void testInit(){
        connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");

        bankUser = new BankUsersInSQLRepo(connectionUtils);
    }

    @Test
    public void TestUserModelSetUserNameMethod(){
        Users user = new Users();
        user.setName("Michael Jordan");

        String userName = user.getName();

        Assert.assertEquals("Didn't get correct user Name", "Michael Jordan", userName);
    }
    /**
     * adding this test for project 1
     */
    @Test
    public void IsAdminTest(){
        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);
        Users user = new Users();
        user.setEmail_address("user1@yahoo.com");
     //   user.setIs_admin(true);
        boolean test = usersService.isAdminUser(user);
        System.out.println("Is user admin?  " + test);

        Assert.assertTrue(test);
    }
}
