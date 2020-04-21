package com.Project0.users;
import com.Project0.Repository;
import com.Project0.brokers.StockBrokers;
import com.Project0.utilities.PostgresConnectionUtilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<Users, String>
{
    private PostgresConnectionUtilities connectionUtilities;

    public UserRepository(PostgresConnectionUtilities connection)
    {
        if (connection != null) this.connectionUtilities = connection;
    }
    @Override
    public Users findbyID(String name)
    {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select username, passkey,privledges from " + schema + ".users" +
                    " where (username = '" + name + "')";
            System.out.println(preparation);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            if (results.next()) {
                String title = results.getString("username");
                String privy = results.getString("privledges");
                String passw = results.getString("passkey");
                return (new Users(title, passw, privy));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                try
                {
                    connection.close();
                }
                catch (SQLException except)
                {
                    except.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<Users> findAll()
        {
            Connection connection = null;
            ArrayList users = new ArrayList(0);
            try {
                connection = this.connectionUtilities.getConnection();
                String schema = this.connectionUtilities.getSchema();
                String preparation = "Select * from " + schema + ".users";
                Statement statement = connection.createStatement();
                System.out.println(preparation);
                ResultSet results = statement.executeQuery(preparation);
                while (results.next())
                {
                    String title = results.getString("username");
                    String privy = results.getString("privledges");
                    String passkey = results.getString("passkey");
                    Users shortterm = new Users(title,passkey,privy);
                    users.add(shortterm);
                }
                return users;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (connection != null) {
                    try
                    {
                        connection.close();
                    }
                    catch (SQLException except)
                    {
                        except.printStackTrace();
                    }
                }
            }
            return null;
        }

    @Override
    public String save(Users var1)
    {
        update(var1, var1.getUsername());
        return var1.getUsername();
    }

    @Override
    public void update(Users us, String name)
    {
        Connection connection = null;
        try
        {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "insert into "+schema+ ".users" +" (username,privledges,passkey) values ('"
            + name + "','"+us.getPrivy()+"','"+us.getPass() +"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
//            System.out.println("User not added, check stacktrace on UserRepository at line 124");
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException except)
                {
                    except.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String name)
    {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "delete from "+ schema + ".users where username = '" + name+"'";
            //System.out.println(preparation);
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException except)
                {
                    except.printStackTrace();
                }
            }
        }
    }
    public Users login(String name, String pass)
    {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".users" +
                    " where (username = '" + name + "')";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            if (results.next()) {
                String title = results.getString("username");
                String privy = results.getString("privledges");
                String passw = results.getString("passkey");
                System.out.println(title +"   " +passw);
                if((title.equals(name)) && (pass.equals(passw))) return new Users(title, passw,privy);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                try
                {
                    connection.close();
                }
                catch (SQLException except)
                {
                    except.printStackTrace();
                }
            }
        }
        return null;
    }
}
