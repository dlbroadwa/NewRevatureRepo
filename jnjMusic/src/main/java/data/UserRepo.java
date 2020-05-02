package data;


import models.Users;
import utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements Repository<Users, String> {
    private ConnectionUtils connectionUtils;

    public UserRepo(ConnectionUtils connectionUtils)
    {
        if(connectionUtils != null)
        {
            this.connectionUtils = connectionUtils;
        }
    }
    @Override
    // Attempts to query the database to find the instrument with the specified identification number.
    public Users findById(String i)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("select * from  Users where email = '%s'", i);
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
            String l_name = rs.getString("l_name");
            String passkey = rs.getString("passphrase");
            String f_name = rs.getString("f_name");
            String email = rs.getString("email");
            Integer phone = rs.getInt("phone");
            Boolean admin = rs.getBoolean("admin_rights");
            return new Users(l_name,f_name, passkey,email,phone, admin);

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    // Queries the database, gets all the instruments, and stores it within the List Object.
    @Override
    public List<Users> findAll()
    {
        Connection connection = null;
        List<Users> users = new ArrayList<Users>();
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = String.format("select * from users");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                String f_name = rs.getString("f_name");
                String l_name = rs.getString("l_name");
                String passphrase = rs.getString("passphrase");
                String email = rs.getString("email");
                Integer phone = rs.getInt("phone");
                Boolean admin_rights = rs.getBoolean("admin_rights");
                users.add(new Users(f_name,l_name,email,passphrase, phone, admin_rights));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }


    // Queries the database to allow a new instrument to be added.
    @Override
    public void update(String p) {
        return;
//        Connection connection = null;
//        try
//        {
//            connection = connectionUtils.getConnection();
//            String sql = String.format("update set available  false where upc = %i"+p);
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(sql);
//        }
//        catch(SQLException e)
//        {
//            //e.printStackTrace();
//            // Will throw an exception anyway due to being a void method.
//        }
//        finally
//        {
//            if(connection != null)
//            {
//                try
//                {
//                    connection.close();
//                } catch (SQLException e)
//                {
//                    //e.printStackTrace();
//                    // Will throw an exception anyway due to being a void method.
//                }
//            }
//        }
//    }
    }


    @Override
    public void save(Users obj)
    {

        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("insert into Userss (f_name,l_name,email, " +
                            "passphrase,email,admin_rights)" +
                            "values (%s, '%s', %s, '%s', false)",
                    obj.getF_name(),obj.getL_Name(),obj.getPassword(),obj.getEmail());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
            // Will throw an exception anyway due to being a void method.
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }

    // Queries the database to allow the removal of an instrument.
    @Override
    public void delete(String email)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("delete from users where email = '%s'",email);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
            // Will throw an exception anyway due to being a void method.
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }
}

