package data;

import model.Users;
import utilities.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankUsersInSQLRepo implements IBankUsers<Users, String> {

    private ConnectionUtils connectionUtils;
    public BankUsersInSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

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



    public List<Users> findAll() {
        Connection conn = null;
        List <Users> users = new ArrayList<Users>();
        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.users3";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pin = rs.getString("pin");
                String email_address = rs.getString("email_address");
                String phone_number = rs.getString("phone_number");

                Users tmpUser = new Users ();
                tmpUser.setName(name);
                tmpUser.setUser_pin(pin);
                tmpUser.setEmail_address(email_address);
                tmpUser.setPhone_number(phone_number);

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

    public boolean insert(Users newObj)  {
        Users tmpUser = this.findByEmail(newObj.getEmail_address());
        boolean insertSuccess = true;
        if (tmpUser.getEmail_address().equals(newObj.getEmail_address())){
            insertSuccess = false;
            System.out.println("User exists in database, DO NOT INSERT: " + tmpUser.getEmail_address());
        }
        else{
            Connection conn = null;

            try {
                conn = connectionUtils.getConnection();
                String sql = "insert into myschema.users3 "
                        + "(name, pin, phone_number, email_address)"
                        + " values "
                        + "('" + newObj.getName() + "', '" + newObj.getUser_pin() +  "', '" + newObj.getPhone_number() + "', '" + newObj.getEmail_address() + "');";

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




}
