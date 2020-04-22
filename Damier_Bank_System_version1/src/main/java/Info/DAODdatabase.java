package Info;

import Application.AccountHolderInfo;
import Application.DispatchingTask;
import ApplicationViews.AccountHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * this is the database file class
 * it control all of the data that coming in and out
 * it is called anywhere for the entire application when needs it
 */

public class DAODdatabase {

    Connection connection =null;
    Statement  statement =null;
    ResultSet src =null;
    DispatchingTask task ;
    AccountHolder accountHolder ;

//    static {
//        try {
//            DriverManager.registerDriver(new org.postgresql.Driver());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


    /**
     * this getConnection method will hold the connection to other classes
     * whenever it is being called anywhere to the application
     * @return
     */
    public Connection getConnection() {
        return connection;
    }


List<AccountHolderInfo> listOf = new ArrayList<>() ;

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

    public void SelectEverything(){
        try {
            databaseConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM accountholder";
        try {
           statement = connection.createStatement();

         src = statement.executeQuery(sql);

            while ((src.next())){

                System.out.printf(" %s %s %s %s %s %s ",

                       "ID:"+ src.getInt("id") +"\n",
                        "Full Name :" +src.getString("fullname")+"\n",
                "Address: " +src.getString("address")+"\n",
               "SSN : " +src.getInt("socialsecurity")+"\n",
                       "AccountNUmber :"+ src.getString("accountnumber")+"\n",
                       "Balance :"+ " USD " + src.getInt("balance")+" $ \n\n"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * this method will deleteAccount with whatever input
     * input was screening
     * At the method it was called
     * the sql is delete base on the user input
     */

    public void deleteAccount(String selection ,int socialnumber_or_idnumber) throws ClassNotFoundException {

        databaseConnection();
        String sql = "DELETE FROM accountholder WHERE " + selection + " = "+ socialnumber_or_idnumber;
        try {
            statement =connection.createStatement();
            statement.execute(sql);
            System.out.println("Data is SUCCESSFULLY DELETED");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data is NOT DELETED");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * this method will UpdateAccount  with whatever input
     * input was screening
     * At the method it was called
     * the sql is update base on the user input
     */

    // Update Account base on what the Employee have access to Change
    public void UpdateCustomer(String nameOrAddress, String  anything, int ssn) throws ClassNotFoundException {

        databaseConnection();
        String sql = "UPDATE public.accountholder SET "+ nameOrAddress +"  =  ' " +anything   +"' WHERE socialsecurity ="+ ssn;
        try {
            statement =connection.createStatement();
          if(statement != null){
              statement.executeUpdate(sql);
              System.out.println("Data is SUCCESSFULLY UPDATED");
          }
          else{
              System.out.println("there is NO search of Record or Check your SSN ");
          }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data is not UPDATED");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Insert Information to the Database

    /**
  this method will use for inserting data to the database
     */

    public void Inserting(String fullname ,String address ,int SSN ,String accountnumber) throws ClassNotFoundException {

        databaseConnection();

        try {
            String sql = "INSERT INTO accountholder (fullname, address, socialsecurity,accountnumber,balance)" +
                    "values ('"+fullname+"','"+address+"', '"+SSN+"','"+accountnumber+"' ,'"+0.00+"')";

          PreparedStatement  stm =connection.prepareStatement(sql);
             stm.executeUpdate();

            System.out.println("Data SUCCESSFULLY Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data is not Inserted");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void flterCustomerAccount(int ssn, DispatchingTask Dis) throws ClassNotFoundException {

        accountHolder =new AccountHolder( Dis);
        databaseConnection();


//            PreparedStatement  stm =connection.prepareStatement(sql);
//            stm.executeUpdate();

        try {
            String sql = "SELECT id, fullname, address, socialsecurity ,accountnumber,balance" +
                    " FROM public.accountholder where socialsecurity = " + ssn;

            statement = connection.createStatement();
            if(statement != null){
            src = statement.executeQuery(sql);




                            while (src.next()) {
                                // System.out.println(src.getString("fullname"));

                                String name= src.getString("fullname");
                                String address = src.getString(3);
                                int social =  src.getInt("socialsecurity");
                                String acct = src.getString("accountnumber");
                                Double balance =src.getDouble("balance");


                                // Passing Result Set to this method and give AccountHolder access to them

            // creating object of Account Holder Info to set the setters
                                AccountHolderInfo accountHolderInfo = new AccountHolderInfo();

                                accountHolderInfo.setName(name);
                                accountHolderInfo.setAddress(address);
                                accountHolderInfo.setAccountNumber(acct);
                                accountHolderInfo.setSocialSecurityNumber(social);
                                accountHolderInfo.setBalance(balance);

                                listOf.add(accountHolderInfo); // Save the setters to the Array of type AccountHolderInfo

                                System.out.println("\nData SUCCESSFULLY loaded\n");

                            }
            }
            else {
                System.out.println("Information is not CORRECT");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nData is not loaded\n");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




    }

    /**
     * After adding to the list all the data that is also send to the AccountHolderInfo
     * this method will aly return the arraylist<>() object
     * It helps other classes have access to it
     * @return
     */
    public List<AccountHolderInfo> getHolderAccountInfo(){

        return listOf;
    }

}
