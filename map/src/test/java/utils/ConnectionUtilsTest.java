package utils;

import org.junit.Assert;
import org.junit.Test;
import system.PostContextListener;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectionUtilsTest {


    @Test

    public void testClassConnection(){

        Comparable<ConnectionUtils> compare = mock(Comparable.class);

        when(compare.compareTo(isA(ConnectionUtils.class))).thenReturn(0);

        Assert.assertEquals(0,compare.compareTo(new ConnectionUtils() {
            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }
        }));

    }
}
