package Com.Tests;

import model.Users;
import org.junit.Assert;
import org.junit.Test;

public class UserModelTest {

    @Test
    public void TestUserModelSetUserNameMethod(){
        Users user = new Users();
        user.setName("Michael Jordan");

        String userName = user.getName();

        Assert.assertEquals("Didn't get correct user Name", "Michael Jordan", userName);
    }
}
