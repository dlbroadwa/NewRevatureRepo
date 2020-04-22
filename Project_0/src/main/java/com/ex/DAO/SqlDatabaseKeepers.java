package com.ex.DAO;

import com.ex.Objects.Keepers;
import com.ex.main.Runner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Class Description
    *The DAO methods are given procedures and connections to the database.
 */

public class SqlDatabaseKeepers implements DAO<Keepers> {//Start of SqlDatabaseKeepers Class

//Instant Variables
    private Runner connectionUtils;

//Constructor
    public SqlDatabaseKeepers(Runner connectionUtils) {
        if (connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

//Methods
    /*
    *findAll is used to return all keepers in the database. It is invoked in KeeperScreeningScreen to compare usernames and passwords
    */
    public List<Keepers> findAll(){//Start of findAll Method
        Connection connection = null;
        List<Keepers> keepers = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select * from " + schemaName + ".keepers";
                Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Keepers temp = new Keepers();
                    temp.setFirstname(firstname);
                    temp.setLastname(lastname);
                    temp.setUsernameKey(username);
                    temp.setPasswordKey(password);
                keepers.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return keepers;
    }//End of findAll Method
    /*
     *specificFind is used to select data from two tables in the database in order to return a list of the transactions on the inventory
     * such as Add Animal or Delete Animal along with the timestamp it occurred and which keeper was in charge of the action
     * It is invoked in TransactionScreen
     */
    public List<Keepers> specificFind() {//Start of specificFind Method
        Connection connection = null;
        List<Keepers> keepers = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select firstname, lastname,action, cur_timestamp from project_0.keepers, project_0.transactions where username = user_id";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String action = rs.getString("action");
                String time = rs.getString("cur_timestamp");


                Keepers temp = new Keepers();
                temp.setFirstname(firstname);
                temp.setLastname(lastname);
                temp.setAction(action);
                temp.setTime(time);

                keepers.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return keepers;
    }//End of specificFind Method
     /*
     *save is used to to add a transaction to the database. It is invoked in AnimalAdd and AnimalRemove
     */
    public void save(Keepers trans) {//Start of save Method
        Connection connection = null;
        PreparedStatement stmt = null;
        Boolean update;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "insert into project_0.transactions (user_id, action) values (?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,trans.getUsernameKey());
            stmt.setString(2,trans.getAction());

            update = stmt.executeUpdate() >0 ;
            if(update){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }//End of save Method

    public Integer numberOf() {//Start of numberOf Method
        Integer number = new Integer(0);
        Connection connection = null;
        try{
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select count(*) as total from " + schemaName + ".keepers";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            number = rs.getInt("total");
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(connection !=null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return number;
    }//End of numberOf Method

    //Unused methods implemented from DAO
    public void delete(Keepers keepers) {
    }

    public Integer specificNumberOf(){
        return null;
    }

}//End of SqlDatabaseKeepers Class
