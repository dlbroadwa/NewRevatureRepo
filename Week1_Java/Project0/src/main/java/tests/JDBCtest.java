package tests;

import com.Project0.utilities.ConnectionUtilities;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.Driver;

import java.sql.*;


public class JDBCtest {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Before
    public void startup() {

        }
            String url = System.getenv("location");
            String username = System.getenv("username");
            String pword = System.getenv("password");
            String schema = System.getenv("schema");
            Connection connection = null;
            {
                try {
                    connection = DriverManager.getConnection(url, username, pword);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (connection != null) {
                    System.out.println("Connection established");
                } else {
                    System.out.println("Connection Failed");
                }
            }


    @Test
    public void isNotNull()
    {
        //System.out.println("Dene there");
        try
        {
            //String sqlstr2 = "insert into flashcards.creator (creator_name) values ('august')";
            //Statement statement2 = connection.createStatement();
            //ResultSet rs2 = statement2.executeQuery(sqlstr2);
            String sqlStr = "select id, creator_name from flashcards.creator";
            Statement statement1 = connection.createStatement();
            ResultSet rs1 = statement1.executeQuery(sqlStr);
            while (rs1.next())
            {
                System.out.println(rs1.getString("creator_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void connectionIssuesDos()
    {
        Connection connection = null;
        try
        {
            connection = new PostgresConnectionUtilities().getConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void connectionIssuesResolved() {
        Connection connection = null;
        try {
            connection = new PostgresConnectionUtilities().getConnection();
            String schema = connection.getSchema();
            String preparation = "Select * from project0.trades";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            results.next();
            System.out.println(results.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException except) {
                    except.printStackTrace();
                }
            }

        }
    }
}

