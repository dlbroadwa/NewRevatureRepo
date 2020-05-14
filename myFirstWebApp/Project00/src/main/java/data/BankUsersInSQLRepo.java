package data;

import model.Users;
import utilities.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is the controller class for user model.  It will authenticate user and retrieve user data from Database
 */
public class BankUsersInSQLRepo implements IBankUsers<Users, String> {

    private ConnectionUtils connectionUtils;
    public BankUsersInSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     * Authenticate user method.
     * @param email User login
     * @param pinNumber pin/password
     * @return true if user is found or password is matched.  Otherwise return false
     */
    public boolean authenticate (String email, String pinNumber){
        Connection conn = null;
        boolean foundUser = false;
        try {
            conn = connectionUtils.getConnection();
            String sql =  "select * from myschema.users3 where email_address = '" + email + "';" ;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String pin = rs.getString("pin");
                if (pin.equals(pinNumber)){
                    return true;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn!= null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        return foundUser;
    }

    /**
     * This method will query database for user name based on email and created Users object to the calling method.
     * @param email
     * @return User object based on user email (email is used as user name in this bank app)
     */
    public Users findByEmail(String email) {
        Connection conn = null;
        Users tmp  = new Users ();
        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.users3 where email_address = '" + email + "';" ;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);


            int count = 0;
            while (rs.next()){
                tmp.setName(rs.getString("name"));
                tmp.setPhone_number(rs.getString("phone_number"));
                tmp.setUser_pin(rs.getString("pin"));
                tmp.setEmail_address(rs.getString("email_address"));
                count++;
            }

            if (count > 1){
                System.out.println("Email address is not unique!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return tmp;
    }


    /**
     * This method will query whole database (table users3) for all the user objects in the db.
     * @return List of all users
     */
    public List<Users> findAll() {
        Connection conn = null;
        List <Users> users = new ArrayList<Users>();
        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.users3 order by id desc";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pin = rs.getString("pin");
                String email_address = rs.getString("email_address");
                String phone_number = rs.getString("phone_number");
                boolean isAdminUser = rs.getBoolean("is_admin");
                Users tmpUser = new Users ();
                tmpUser.setName(name);
                tmpUser.setUser_pin(pin);
                tmpUser.setEmail_address(email_address);
                tmpUser.setPhone_number(phone_number);
                tmpUser.setUserID(id);
                tmpUser.setIs_admin(isAdminUser);
                users.add(tmpUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    /**
     * Unused method at the moment, however, it maybe used at future point for later project if needed.
     */
    public boolean  save(Users obj, String field) {

        boolean savedSuccess = false;

        if (!field.equals("email_address")){
            Connection conn = null;

            try {
                conn = connectionUtils.getConnection();
                String sql = "select * from myschema.users3 where email_address = '" + obj.getEmail_address() + "';" ;
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()){
                    if (rs.getString("email_address").equals(obj.getEmail_address())){
                        String sql2;
                        String newValue;
                        if (field.equals("phone_number")) {
                            newValue = obj.getPhone_number();
                        }
                        else if (field.equals("pin")){
                            newValue = obj.getUser_pin();
                        }
                        else  {
                            newValue = obj.getName();
                        }

                        sql2 = "update  myschema.users3 set " + field + " = '" + newValue + "' where email_address = '" + obj.getEmail_address() + "';";
                        System.out.println("SQL query is " + sql2);
                        Statement statement2 = conn.createStatement();
                        statement2.executeUpdate(sql2);

                        savedSuccess = true;
                        System.out.println("Updated user successfully");
                    }
                    else{
                        System.out.println("User with email address: '" + obj.getEmail_address() + "' was not found.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("Email is persistent field and will not be updated!");
        }

        return savedSuccess;
    }

    /**
     * Unused method at the moment, however, it maybe used at future point for later project if needed.
     * @param newObj
     */
    public boolean insert(Users newObj)  {
        Users tmpUser = this.findByEmail(newObj.getEmail_address());
        boolean insertSuccess = true;
        if (tmpUser.getEmail_address().equals(newObj.getEmail_address())){
            insertSuccess = false;
            System.out.println("User exists in database, can not insert: " + tmpUser.getEmail_address());
        }
        else{
            Connection conn = null;

            try {
                conn = connectionUtils.getConnection();
                String sql = "insert into myschema.users3 "
                        + "(name, pin, phone_number, email_address, is_admin)"
                        + " values "
                        + "('" + newObj.getName() + "', '" + newObj.getUser_pin() +  "', '" + newObj.getPhone_number() +
                        "', '" + newObj.getEmail_address() + "', " + newObj.isIs_admin() + ");";
                System.out.println(sql);
                Statement statement = conn.createStatement();

                statement.executeUpdate(sql);

          

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if (conn != null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return insertSuccess;
    }

    public boolean isAdminUser(Users newObj) {
        Connection conn = null;
        boolean is_Admin_User = false;
        String email_address = newObj.getEmail_address();
        try {
            conn = connectionUtils.getConnection();
            String sql =  "select * from myschema.users3 where email_address = '" + email_address + "';" ;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                if (email_address.equals(rs.getString("email_address"))){
                    is_Admin_User = rs.getBoolean("is_admin");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return is_Admin_User;
    }

    public Users createUser() {
        Connection conn = null;
        boolean is_Admin_User = false;
        return null;
    }


}