package test.java;

import com.ex.DAO.DAO;
import com.ex.DAO.Keepers;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.DAO.SqlDatabaseKeepers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class GuestTests_Write {
    String url,username,password,schema;
    @Mock
    DAO<Keepers> keepersDAO;

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
        postgresConnect = new PostgresConnectionUtil(url,username,password,schema);
        keepersDAO = new SqlDatabaseKeepers(postgresConnect);
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

        keepersDAO.save(temp);
    }

}
