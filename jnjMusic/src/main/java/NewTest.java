import data.InstrumentSQLRepository;
import data.UserRepo;
import models.InstrumentModel;
import models.Users;
import org.junit.Before;
import utils.PostgresConnectionUtil;
import org.junit.Test;

import java.sql.*;
import java.util.List;


public class NewTest
{
    @Test
    public  void testMakeUSer()
    {
       UserRepo repo = new UserRepo(new PostgresConnectionUtil());
       Users user = new Users("daniel@yahoo.com","password","4836","Bassoon",true);
       repo.save(user);
       System.out.println(user.getEmail());
    }
    @Test
    public void startup() {

    }
    String url = System.getenv("url");
    String username = System.getenv("name");
    String pword = System.getenv("password");
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
            String sqlStr = "select * from users";
            Statement statement1 = connection.createStatement();
            ResultSet rs1 = statement1.executeQuery(sqlStr);
            while (rs1.next())
            {
                System.out.println(rs1.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void userGetEmail()
    {
        Users stuff = new UserRepo(new PostgresConnectionUtil()).findById("daniel@yahoo.com");
        System.out.println(stuff.getEmail());
    }
    @Test
    void userGetPass()
    {
        Users stuff = new UserRepo(new PostgresConnectionUtil()).findById("daniel@yahoo.com");
        System.out.println(stuff.getPassword());
    }
    @Test
    void userGetPhones()
    {
        Users stuff = new UserRepo(new PostgresConnectionUtil()).findById("daniel@yahoo.com");
        System.out.println(stuff.getPhone());
    }
    @Test
    void instrumentsGetAll()
    {
        InstrumentModel inst = new InstrumentSQLRepository(new PostgresConnectionUtil()).findById(0);
        System.out.println(inst.getPrice());
    }

}
