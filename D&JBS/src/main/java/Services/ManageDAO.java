package Services;

import Controler.AccountHolderPerSonalInfo;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * this Manager class basically control the entire application
 * it helps us have all the methods from the DAO abstract class
 *
 * it implements all of them and also have some extra method
 *
 * this class control all the CRUD operation system , it is simple need to call the method
 *
 * all the metods are self describe with their names
 */

public class ManageDAO extends DAO {

Connection connection;
PreparedStatement pst;
Statement statement;
ResultSet rs;

List<AccountHolderPerSonalInfo> list = new ArrayList<AccountHolderPerSonalInfo>();

//    public Connection getConnection() {
//        return connection;
//    }

    public  void databaseConnection() throws ClassNotFoundException {
//

//        }
        // Driver d = new org.postgrest.jdbc.Driver;

        String driver = "org.postgresql.Driver";
        String user ="damier";
        String password = "password";
        String url = "jdbc:postgresql://projects.ccdplq7q6hxq.us-east-2.rds.amazonaws.com:5432/postgres";




        Class.forName(driver); // loading the driver


        try{
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("One moment..");

        }
        catch (SQLException ex){
            System.out.println("There is no CONNECTION");
            System.out.println(ex.getMessage());
        }

    }
    public void InsertMethod(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {

        databaseConnection();
    }

    public void UpdateMethod(String tableName ,String something, String somethingElse, int id ) throws ClassNotFoundException {

        databaseConnection();

        String sql = "UPDATE banking."+tableName + "SET " + something + " = ' " + somethingElse + " ' WHERE register_id " +"="+ id;


        try{

            statement =connection.createStatement();
            statement.executeQuery(sql);
            System.out.println("data is deleted");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void DeleteMethod(String tableName ,String something, int id )
            throws ClassNotFoundException {

        databaseConnection();

        String sql = "DELETE FROM banking."+tableName +"WHERE " + something +"="+ id;


        try{

            statement =connection.createStatement();
            statement.executeQuery(sql);
            System.out.println("data is deleted");


    } catch (SQLException throwables) {
        throwables.printStackTrace();
        throwables.getLocalizedMessage();
    } finally {
        try {
            connection.close();
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        }

    }

    }

    public void SelectedAll(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {
        databaseConnection();

        String sql = "SELECT * FROM banking.register";

        try{
            pst = connection.prepareStatement(sql);
            rs =pst.executeQuery();

            while (rs.next()){
//create the getters and setters with the data from database

                employee.setFirstName(rs.getString("firstname"));
                employee.setLastname(rs.getString("lastname"));
                employee.setStreetAddress(rs.getString("streetaddress"));
                employee.setCity(rs.getString("city"));
                employee.setState(rs.getString("state"));
                employee.setZipCode(rs.getString("zipCode"));
                employee.setPhoneNumber( rs.getString("phone"));
                employee.setSSN(rs.getString("social_security"));
                employee.setAccountNumber(rs.getString("accountnumber"));
                employee.setBalance(rs.getDouble("balance"));
                employee.setGender(rs.getString("sex"));
                employee.setDateOpenningAccount(rs.getTimestamp("dateOpenningAccount"));
                employee.setRegisterID(rs.getInt("register_id"));
                employee.setLoginID(rs.getInt("login_id"));
                employee.setAccountID(rs.getInt("accounNumber_id"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("user_password"));


                // Add object Employee to the Arraylist

                list.add(employee);



            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        }
        finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void InsertToRegisterTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {
        databaseConnection();

        String sql = "INSERT INTO banking.register (firstname,lastname,streetaddress,city,state," +
                "zipCode,phone,social_security,sex,accountnumber,dateOpenningAccount)" +
                " values(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,employee.getFirstName());
            pst.setString(2,employee.getLastname());
            pst.setString(3,employee.getStreetAddress());
            pst.setString(4,employee.getCity());
            pst.setString(5,employee.getState());
            pst.setString(6,employee.getZipCode());
            pst.setString(7,employee.getPhoneNumber());
            pst.setString(8,employee.getSSN());
            pst.setString(9,employee.getGender());
            pst.setString(10,employee.getAccountNumber());
            pst.setTimestamp(11, employee.getDateOpenningAccount());


            int row = pst.executeUpdate();

            if(row == 0){
                System.out.println("Data Not Inserted");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void InsertToLoginTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {

        databaseConnection();

        String sql = "INSERT INTO banking.Login(email,user_password)" +
                " values(?,?)";


        try {
            pst = connection.prepareStatement(sql);
            pst.setString(2,employee.getEmail());
            pst.setString(3, employee.getPassword());
//            pst.setInt(3,employee.getRegisterID());

            int row = pst.executeUpdate();

            if(row == 0){
                System.out.println("Data Not Inserted");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void InsertToAccountNumberTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {

        databaseConnection();

        String sql = "INSERT INTO banking.AccountNumber (accountNumber,register_id,balance) values(?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
           pst.setString(1,employee.getAccountNumber());
           pst.setInt(2,employee.getRegisterID());
           pst.setDouble(3,employee.getBalance());

            int row = pst.executeUpdate();

            if(row == 0){
                System.out.println("Data Not Inserted");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void UpdateToRegisterTable(AccountHolderPerSonalInfo employee, AccountHolderPerSonalInfo employee2) {

    }

    public void UpdateToLoginTable(AccountHolderPerSonalInfo employee, AccountHolderPerSonalInfo employee2) {

    }

    public void UpdateToAccounNumberTable(AccountHolderPerSonalInfo employee, AccountHolderPerSonalInfo employee2) {

    }

    public void SelectFromRegisterTable() throws ClassNotFoundException {


        databaseConnection();

//        String sql = "SELECT * FROM banking.Register WHERE social_security =" + ssn;
        String sql = "SELECT * FROM banking.Register " ;

        try {

            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            AccountHolderPerSonalInfo employee = new AccountHolderPerSonalInfo();

            while ((rs.next())){



                employee.setFirstName(rs.getString("firstname"));
                employee.setLastname(rs.getString("lastname"));
                employee.setStreetAddress(rs.getString("streetaddress"));
                employee.setCity(rs.getString("city"));
                employee.setState(rs.getString("state"));
                employee.setZipCode(rs.getString("zipCode"));
                employee.setPhoneNumber( rs.getString("phone"));
                employee.setSSN(rs.getString("social_security"));
                employee.setAccountNumber(rs.getString("accountnumber"));
                employee.setGender(rs.getString("sex"));
                employee.setDateOpenningAccount(rs.getTimestamp("dateOpenningAccount"));


            list.add(employee); // add object AccountHolderPerson to a list


            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void SelectFromLoginTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {

        databaseConnection();

        String sql = "SELECT * FROM banking.Login WHERE user_password = " + employee.getPassword();

        try {

            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while ((rs.next())){



                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("user_password"));
                employee.setLoginID(rs.getInt("login_id"));

                list.add(employee); // add object AccountHolderPerson to a list


            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public void SelectFromAccountNumberTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException {

        databaseConnection();

        String sql = "SELECT * FROM banking.AccountNumber WHERE accounNumber_id = "+ employee.getAccountNumber();

        try {

    statement = connection.createStatement();
    rs = statement.executeQuery(sql);

    while ((rs.next())){


    employee.setAccountID(rs.getInt("accounNumber_id"));
    employee.setAccountNumber(rs.getString("accountNumber"));
    employee.setRegisterID( rs.getInt("register_id"));
    employee.setBalance(rs.getDouble("balance"));

        list.add(employee); // add object AccountHolderPerson to a list

    }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getLocalizedMessage();
            }

        }

    }

    public int getRegisterId() throws ClassNotFoundException {

        databaseConnection();
        int the_id = 0;

        String sql = "SELECT register_id FROM banking.Register ";

        try {

            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while ((rs.next())){

                the_id = rs.getInt("register_id");


            }



        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throwable.getLocalizedMessage();
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                throwable.getLocalizedMessage();
            }

        }
        return the_id;
    }


    public Connection getTheConnection() {
        return connection;
    }

    public List<AccountHolderPerSonalInfo> getAllTablesForAccountHolder(){

        return list;
    }

//    public  Timestamp getDate(java.util.Date) {
//        String date = new java.sql.Timestamp(date.getTime());
//
//
//    }

    public Timestamp getTimestamp(java.util.Date date)
    { return date == null ? null : new java.sql.Timestamp(date.getTime()); }



    /** we use this class to get the current time for any time that a new user will be created **/

    public  Timestamp getTimer(){
        java.util.Date date = new java.util.Date();

       return  (date == null) ? null : new java.sql.Timestamp(date.getTime());

//        if(date == null) {
//            return null;
//        }
//
//        return new java.sql.Timestamp(date.getTime());


        
    }






}
