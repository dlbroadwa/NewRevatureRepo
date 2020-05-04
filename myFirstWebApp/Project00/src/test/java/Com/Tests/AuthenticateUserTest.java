package Com.Tests;

import data.BankUsersInSQLRepo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AuthenticateUserTest {
    public BankUsersInSQLRepo bankUser;
    ConnectionUtils connectionUtils;
    @Before
    public void testInit(){
        connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");

        bankUser = new BankUsersInSQLRepo(connectionUtils);
    }

    /**
     * Testing the user authentication.  The test will query the database for the user (Username is the
     * email address and his pin number if his username is found and pin number is matched with the provided.
     * The test would be considered successful.
     */
    @Test
    public void TestUserAuthentication(){
        boolean userFound ;
        userFound = bankUser.authenticate("user1@yahoo.com", "123456");
        Assert.assertTrue(userFound);
    }

    /**
     * Testing that the submitted query is  valid  and therefore, it means that the user table in Database is persistent,
     * and the result set returning from database is always valid.
     */
    @Test
    public void ResultSetNotNull(){
        Connection conn2 = null;
        String email = "user2@yahoo.com";

        try {
            conn2 = connectionUtils.getConnection();

            String sql =  "select * from myschema.users3 where email_address = '" + email + "';" ;
            Statement statement = conn2.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            Assert.assertNotNull(rs);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        finally {
            if (conn2 != null){
                try {
                    conn2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
