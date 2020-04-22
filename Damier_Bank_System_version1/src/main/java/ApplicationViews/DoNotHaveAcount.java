package ApplicationViews;

import Application.AccountHolderInfo;
import Application.DispatchingTask;
import Info.DAODdatabase;
import Info.DAOaccountHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**this is the class that implements the RandomNumber interface
 * When customers choose  DonotHaveAccount at the menu option
 * we will prompt them to this class for new registration
 *
 * also this class wil be used as well for Employees who will want to register new customer
 */

public class DoNotHaveAcount implements RandomNumber {

    AccountHolderInfo accountHolderInfo ;
    DAOaccountHolder daOaccountHolder;


    List list = new ArrayList();
   public   int accountNumber;

    DoNotHaveAcount doNotHave;
    DAODdatabase daoDdatabase;
    DispatchingTask task;

    Scanner scan = new Scanner(System.in);

    public DoNotHaveAcount(DispatchingTask task){
        daoDdatabase = new DAODdatabase();
        accountHolderInfo = new AccountHolderInfo();
       // daOaccountHolder = new DAOaccountHolder();
        this.task =task;
    }

    public void createAccount(DispatchingTask task) throws IOException, ClassNotFoundException {
        //Scanner scan =task.getScanner();


        System.out.println("What is your (customer) name ? :");

        String name = scan.nextLine();
        while (!scan.hasNextInt()) {


            if (name.length() == 0 || name.equals(null)) {
                System.out.println("Can not be Empty");
                continue;
            } else if (name.matches("[A-Z-a-z_0-9]")) {

                System.out.println("Number Or Character is not allowed");
                continue;
            } else {

                list.add(name);
                System.out.println("Name Registered: " + name);
                scan.nextLine();

                System.out.println("What is your (Customer) Social Security Number? ");
                while (scan.hasNextInt()) {
                    int ssn = scan.nextInt();
                    String convert =Integer.toString(ssn);

                    if(convert.length()>=9){
                      //  System.out.println("Social Security :" + convert.substring(0,3) +"-" + convert.substring(3,5) +"-"+ convert.substring(5,9));

                        list.add(ssn);
                        scan.nextLine();

                        System.out.println("What is your Physical address ? ");


                                            String Address = scan.nextLine();
                                            list.add(Address);


                    //    accountHolderInfo = new AccountHolderInfo(list.get(0).toString(),list.get(2).toString(),accountGenerator(),ssn);

           /** Creating object of the class Account Holder Info **/

                        AccountHolderInfo accountHolderInfo =new AccountHolderInfo();
                        accountHolderInfo.setName(name);
                        accountHolderInfo.setAddress(Address);
                        accountHolderInfo.setAccountNumber(String.valueOf(task.getDoNotHaveAcount().accountGenerator()));
                        accountHolderInfo.setSocialSecurityNumber(ssn);
                        accountHolderInfo.setBalance(0.00);

                        task.getTransaction().thankYouNote(accountHolderInfo);

    daoDdatabase.Inserting(name , Address ,ssn , accountGenerator()); // insert Data to database

//task.getDaOaccountHolder().writingFile(name,Address,accountGenerator(),ssn); //writing to the file System
   // task.getDaOaccountHolder().writingFile("name","Address",accountGenerator(),999);
                        task.getTransaction().menu();
                  /**
Passing object of AccountHolderInfo to The Tankyou method at the TRANSACTION Class


                   **/
//
                    }

                    else {
                        System.out.println("Social Security must be 9 digit");
                    }


                }


            }
        }


    }

    public  Object registerInput(){


//        System.out.println("list :"+ list.get(0));
//        System.out.println("list :"+ list.get(1));
//        System.out.println("list :"+ list.get(2));


        return "";
    }

    /**
     * this is the implementation method from the RandomNumber Interface
     * like we know will generate a brand new 16 digit number for every new customers
     */
    @Override
    public String accountGenerator() {

    String val ="12345678910259801"; // number to random to


        char []ch = new  char[16]; // get a character array to set a limit of 16 number
        Random random = new  Random(); // Random number

        // Loop to  the character to get the length
        for (int i=0 ; i< ch.length ; i++){

    ch [i]=val.charAt(random.nextInt(val.length())); // store each character

        }

        String string = String.valueOf(ch); // change the format to a String then Return it
       // System.out.println("String : "+ string);

        return string;
    }
}
