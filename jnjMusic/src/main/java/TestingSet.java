import data.InstrumentSQLRepository;
import data.UserRepo;
import models.InstrumentModel;
import models.Users;
import org.junit.Test;
import utils.PostgresConnectionUtil;

import java.sql.*;
import java.util.List;

public class TestingSet
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
    public void userGetEmail()
    {
        Users stuff = new UserRepo(new PostgresConnectionUtil()).findById("daniel@yahoo.com");
        System.out.println(stuff.getEmail());
    }
    @Test
    public void userGetPass()
    {
        Users stuff = new UserRepo(new PostgresConnectionUtil()).findById("daniel@yahoo.com");
        System.out.println(stuff.getPassword());
    }
    @Test
    public void userGetPhones()
    {
        Users stuff = new UserRepo(new PostgresConnectionUtil()).findById("daniel@yahoo.com");
        System.out.println(stuff.getPhone());
    }
    @Test
    public void instrumentsGetBy()
    {
        InstrumentModel inst = new InstrumentSQLRepository(new PostgresConnectionUtil()).findById(0);
        System.out.println(inst.getPrice());
    }
    @Test
    public void instrumentsAll()
    {
        List<InstrumentModel> inst = new InstrumentSQLRepository(new PostgresConnectionUtil()).findAll();
        System.out.println(inst.size());
    }

    @Test
    public void newInstrument()
    {

    }
    @Test
    public void saveInstrument()
    {

    }
    @Test
    public void getInstrumentPrice()
    {

    }
    @Test
    public void getInstrumentDescription()
    {

    }
    @Test
    public void getSaleTitle()
    {

    }
}