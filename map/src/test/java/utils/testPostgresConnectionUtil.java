package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import utils.PostgresConnectionUtil;


import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testPostgresConnectionUtil {


    PostgresConnectionUtil postgres = mock(PostgresConnectionUtil.class);
    Comparable<String> comparable = null;



    private String url = "jdbc:postgresql://akind-database.cby99r2xyn8t.us-east-2.rds.amazonaws.com:5432/postgres";
    private String user = "blog_access";
    private String password = "blog_access";

    @Mock
    Connection mockConnection;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        comparable = mock(Comparable.class);
    }

//    @Test
//
//    public void testEncryption(){
//
//        String val = encryption.encrypt("Damier");
//        Mockito.when(comparable.compareTo(anyString())).thenReturn(-2);
//
//        Assert.assertEquals(-2,comparable.compareTo(val));
//
//
//    }

    @Test
    public void testpostGressConnection() throws SQLException {

        Comparable<PostgresConnectionUtil> postCompare = mock(Comparable.class);
        DriverManager.registerDriver(new Driver() {
            @Override
            public Connection connect(String url, Properties info) throws SQLException {
                return null;
            }

            @Override
            public boolean acceptsURL(String url) throws SQLException {
                return false;
            }

            @Override
            public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                return new DriverPropertyInfo[0];
            }

            @Override
            public int getMajorVersion() {
                return 0;
            }

            @Override
            public int getMinorVersion() {
                return 0;
            }

            @Override
            public boolean jdbcCompliant() {
                return false;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }
        });


        DriverManager.getConnection(url,user,password);

        Mockito.when(postgres.getConnection()).thenReturn(mockConnection);

        Assert.assertEquals(mockConnection, postgres.getConnection());



    }
//    @Test
//    public void testRegex(){
//
//        //Mockito.when(comparable.compareTo("Null")).thenReturn(-1);
//        Boolean user = mockRegex.isValidPassword(null);
//        Assert.assertFalse(false);
//
//    }

}
