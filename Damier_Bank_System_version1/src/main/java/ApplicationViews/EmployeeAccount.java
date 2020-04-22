package ApplicationViews;

import Application.DispatchingTask;
import Info.DAODdatabase;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * This class is built for Employees
 * who has some type of privileges to Customers account
 *
 * first employees will be screen with credential
 * must have an employee number which provide them at the first day of hiring
 *
 * employees must have customers social security to verify
 * if it is exalt what the employees entente to do then proceed
 *
 * We will greet also the employees personal information in case needed
 * also CRUD operations will be available for this employee once credential is clear
 *
 */

public class EmployeeAccount implements Credential {



    DispatchingTask task  ;
    PreparedStatement pstm;
    ResultSet rc;

    Scanner scanner;

DAODdatabase daoDdatabase;
    Connection connection ;

    public EmployeeAccount(DispatchingTask task){ // getting access to Dispatching class Object


        this.task =task;
        this.scanner = task.getScanner(); // get the scanner from the Distribution task class
        daoDdatabase = new DAODdatabase();

    }

    @Override
    public void AskForCredential() throws ClassNotFoundException {
        System.out.println("What is your Employee Id ? :");

int newScanner;

    while (scanner.hasNextInt()){
        newScanner = scanner.nextInt();
        queryDatabase(newScanner);
    }



    }


    public void queryDatabase(int id) throws ClassNotFoundException {

       // task.getDaoDdatabase().databaseConnection();

        daoDdatabase.databaseConnection();
   connection =daoDdatabase.getConnection();
    String sql = "SELECT employeeid," +
            "firstname, lastname, hours FROM public.employee WHERE employeeid = " +id;
        System.out.println("One moment please..");

    //creating variables to store data from database
        String fN, lN;
        int eID, hrs;

        try {
            pstm =connection.prepareStatement(sql);
           // Statement tm = connection.createStatement();
            //rc = tm.executeQuery(sql);

           rc =pstm.executeQuery();

    if(rc != null){


            while(rc.next()){

                eID = rc.getInt(1);
                fN = rc.getString("firstname");
                lN =  rc.getString(3);
                hrs = rc.getInt(4);

                greetingEmpoyee(eID,fN,lN,hrs);
            }
           // System.out.println("one moment please..");
    }
    else{
        System.out.println("No such Id in our Database system");
    }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("There is an Issue with our Database System");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    };



    public  void greetingEmpoyee(int eID , String fN, String lN, int hrs) throws IOException, ClassNotFoundException {

        System.out.println("\n\t************" +task.getBankName()+ "************");
        System.out.println("\t\t\t\t WELCOME BACK "+ lN +" "+fN +
                "\n Your time schedule to work for this Week : " +hrs +" hours "+
                " Your Employee Id #: "+ eID);
        System.out.println("____________________________________________________________________________________________");
       greetingEmpoyeeMenu(lN);
    }

    public void greetingEmpoyeeMenu(String name) throws IOException, ClassNotFoundException {
        System.out.println("\n  Hello "+ name +" You can choose an option of the following:");
        System.out.println("\t or Just slide mouse up to see your Information \n ");

        System.out.println("" +
                "\t\t 1-See all Customers'Account \n" +
                "\t\t 2-Delete a specific customer Account \n" +
                "\t\t 3-Update a specific customer Account \n" +
                "\t\t 4-Register a new Customer \n" +
                "\t\t 5-It was only to see my hours for this Week \n");

        while (scanner.hasNextInt()){
            int option = scanner.nextInt();
            selectionOption(option,name); // parse this argument to analyse it at the selectionOption function
        }

    }

    public void selectionOption(int option, String name) throws IOException, ClassNotFoundException {
        if(option == 1){
            System.out.println("All Acct");
            daoDdatabase.SelectEverything();
            greetingEmpoyeeMenu(name);
            System.out.println("type anything to go back or make a selection if you want to");
        }
        else if(option == 2){

            System.out.println("You must have Customer id or Social Security to DELETE it");

            System.out.println("please enter either one (SSN/ID)");

            while (scanner.hasNextInt()){
                int number = scanner.nextInt();
                String str = String.valueOf(number);
                if(str.length() == 9){
                    String format = str.substring(0,3)+"-"+str.substring(3,5)+"-"+str.substring(5,9);
                    System.out.println("Customer is deleted with this Social # " +format);
                    daoDdatabase.deleteAccount("socialsecurity", number);
                    greetingEmpoyeeMenu(name);
                }
                else if(str.length() <= 3){
                    System.out.println("You enter Customer ID");
                    daoDdatabase.deleteAccount("id",number);
                    greetingEmpoyeeMenu(name);
                }
                else{
                    System.out.println("Make sure that you get a correct ID number\n");
                }
            }


        }

        else if(option == 3){
            System.out.println( name +" you must have Social Security to UPDATE either Customer  Address or name");

        filterTheInputForTheUpdate(name);
          //  greetingEmpoyeeMenu(name);

        }
        else if(option == 4){
            System.out.println("Register");
            task.getDoNotHaveAcount().createAccount(task); //Re-use the previous code from the Dispatching class
            greetingEmpoyeeMenu(name);
        }
        else if(option == 5){
            System.out.println("Ok !" + name  +" you choose to go back then, THANK YOU !");
            try {
                task.getTransaction().menu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("NOT a valid Option");
        }
    }


    public void filterTheInputForTheUpdate(String name) throws IOException, ClassNotFoundException {

        System.out.println("what is the customer Social Security Number ?");

        while(scanner.hasNextInt()){
            int number = scanner.nextInt();
            String str = String.valueOf(number);

            if(str.length() == 9){

                System.out.println("OK , now what to you want to update customer name or address");

                System.out.println(" 1- Name \n 2- Address");
                while (scanner.hasNextInt()){
                    int select = scanner.nextInt();

                    if(select == 1){

                        System.out.println("what is the customer new name");
                        while (!scanner.hasNextInt())
                        {
                            daoDdatabase.UpdateCustomer("fullname",scanner.nextLine(),number);
                            System.out.println(" Thank you "+ name + " For this UPDATE \n");

                           greetingEmpoyeeMenu(name);
                        }
                       // scanner.nextLine();
                    }
                    else if (select ==2){

                        System.out.println("what is the customer new Address ?");
                        while (scanner.nextLine() != null){
                            String address = scanner.nextLine();
                            System.out.println(address);
                            daoDdatabase.UpdateCustomer("address",address,number);
                            greetingEmpoyeeMenu(name);
                        }

                    }
                    else{
                        System.out.println("must be either number 1 for he name or #2 for Address");
                    }

                }
            }
            else {
                System.out.println("SSN must have 9 digit, "+ name);
                break;
            }
        }
    }
};



