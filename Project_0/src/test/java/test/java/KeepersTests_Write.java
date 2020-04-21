package test.java;

import com.ex.DAO.DAO;
import com.ex.Objects.Keepers;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.services.KeepersService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class KeepersTests_Write {
    String url,username,password,schema;

    @InjectMocks
    KeepersService keepersService;

    @Mock
    DAO keepersDAO;

    @Mock
    PostgresConnectionUtil postgresConnect;

    @Rule
    public MockitoRule mockitoRule_UDAO = MockitoJUnit.rule();

    @Before
    public void init(){
        url = System.getenv("PROJECT_0_URL");
        username= System.getenv("PROJECT_0_USERNAME");
        password= System.getenv("PROJECT_0_PASSWORD");
        schema= System.getenv("PROJECT_0_SCHEMA");
        keepersService = new KeepersService(keepersDAO);
    }

    @Test
    public void save(){
        Keepers temp = new Keepers();
        temp.setFirstname("Frank");
        temp.setLastname("Fort");
        temp.setUsernameKey("frankf");
        temp.setPasswordKey("Revature");
        temp.setAction("Added Boo");
        Mockito.doNothing().when(keepersDAO).save(temp);

        boolean successMock = keepersService.save(temp);
        Assert.assertTrue("NO SAVE HAPPENED", successMock);
    }

}
