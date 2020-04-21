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

import java.util.List;

public class GuestTests_Write {

    @Mock
    DAO<Keepers> keepersDAO;

    @Mock
    PostgresConnectionUtil postgresConnect;

    @Rule
    public MockitoRule mockitoRule_UDAO = MockitoJUnit.rule();

    @Before
    public void init(){
        keepersDAO = new SqlDatabaseKeepers();
    }
    @Test
    public void save(){
        Mockito.doNothing().when(keepersDAO).save();
    }

}
