package Com.Tests;

import data.BankUsersInSQLRepo;
import model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

public class FindUserDetailByEmail {
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
    public void shouldReturnNonDefaultUserObj(){
        Users user = bankUser.findByEmail("user@yahoo.com");
        boolean test = false;
        if (!user.getEmail_address().equals("default")){
            if (!user.getPhone_number().equals("default"))
                if (!user.getName().equals("default"))
                    if (!user.getUser_pin().equals("default")){
                        test = true;
                    }

        }

        Assert.assertTrue(test);

    }
}
