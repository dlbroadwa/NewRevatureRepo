/**
 * The PostgresConnectionUtilTest tests if the properties for the PostgresConnectionUtil
 * is properly set up.
 */
package utils;

import org.junit.Test;
import org.mockito.Mock;
import static org.junit.Assert.*;

public class PostgresConnectionUtilTest
{
    private String username;
    private String password;
    private String url;
    @Mock
    PostgresConnectionUtil testConnection;


    // Tests to see if the connection is not null when the user credentials are passed through.
    @Test
    public void shouldSetTestingVariables()
    {
        this.username = System.getenv("user_creds");
        this.password = System.getenv("user_password");
        this.url = System.getenv("url");
        testConnection = new PostgresConnectionUtil(url, username, password, "public", "woodwinds");
        assertNotNull(testConnection);
    }

}