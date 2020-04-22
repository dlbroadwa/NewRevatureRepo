package ApplicationViews;

import Application.AccountHolderInfo;
import Application.DispatchingTask;
import Info.DAODdatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * This class  concern  clients for verification purposes
 * will be asked  for customer credential
 * then will be verify credential if
 * it is exist in our database system
 *if everything positive customer will be prompted a welcome back message
 *
 * also if credential match data will be sent to other classes that will need and keep this information
 * for the entire time customer will stay or log to our system
 */


public class AccountHolder  implements Credential {

    DispatchingTask task;
    Scanner scanner;
    DAODdatabase daoDdatabase;
    AccountHolder accountHolder  ;

    String name,address,account,ssnFormat,acctFormat;
    int social;
    double balance;


    List<AccountHolderInfo> list = new ArrayList<>();

   // public AccountHolder(){}


    public AccountHolder(DispatchingTask task){
        this.scanner = task.getScanner(); // using same scanner at the Dispatching class
        this.task = task;
        daoDdatabase = new DAODdatabase();
      //  accountHolder = new AccountHolder(task);

    }




    @Override
    public void AskForCredential() throws ClassNotFoundException {
        System.out.println("What is your Social Security number ?");

        while(true){


            while(scanner.hasNextInt()){


                int number = scanner.nextInt();
                String converter = String.valueOf(number);
                System.out.println(converter.length() +" Digit");

                if (converter.length() == 9){
                    System.out.println("social");

                    daoDdatabase.flterCustomerAccount(number, task);
                    sendingDataToTheHoldClass();

                    greentingAccountHolder();
                }

                else if(converter.length() > 9){
                    System.out.println("Can not be greater than 9 digit");
                }
                else{
                    System.out.println("SSN must have 9  ");
                }


            }

            System.out.println("Please, Only number");
            scanner.nextLine();
        }

    }


    /**
     * Receiving data from the database to create the greeting method with accurate information
     */
    public void sendingDataToTheHoldClass(){

 // Loop through the Array List and assign each of them to variables
        for(AccountHolderInfo info : daoDdatabase.getHolderAccountInfo()){

            name =info.getName();
            address = info.getAddress();
            account = info.getAccountNumber();
            social =info.getSocialSecurityNumber();
            balance =info.getBalance();
        }
    }

    /**
     * Greeting the customer and also format the data receiving
     * like the ssn receiving it like this 777777777 format it to this 777-777-777
     * it just an example
     */

    public void greentingAccountHolder() throws ClassNotFoundException {



        String str =String.valueOf(social);
        String str2 =String.valueOf(account);

        if(str.length() == 9 ){
ssnFormat =str.substring(0,3) +"-" +str.substring(3,5)+"-" +str.substring(5,9);
acctFormat =str2.substring(0,4) +"-" +str2.substring(4,8)+"-" +str2.substring(8,12)+"-" +str2.substring(12,16);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t "+ task.getBankName());
        System.out.println("******* WELCOME BACK "+ name + " ******* \n " +
                "Address : "+ address +"\n Account Number : "+acctFormat +"\n Social Security : "+ ssnFormat);
        System.out.println("******************************************* ");

        System.out.println("How Can I help you today ? \n" +
                " 1- Withdraw \n 2- Deposit \n 3- Checking Balance \n 4- Go Back / approve Today Transaction \n");

        while (scanner.hasNextInt()){

            int number = scanner.nextInt();
            operationControl(number); // find out what is entered
        }
        }
        else{
            System.out.println("The Social Security Is not match our Record");
        }

    }

    /**
     * control the transaction type
     * either withdraw , deposit, or just checking balance
     */
    public void operationControl(int input) throws ClassNotFoundException {

        if(input == 1){

            System.out.println("How much money that you want to withdraw Today?");
            while (scanner.hasNextDouble()){

                withdraw(scanner.nextDouble());
                System.out.println("new balance : USD "+ balance + " $");

                greentingAccountHolder();
            }
        }
        else if (input == 2){

            System.out.println("How much that you want to depose Today?");
            while (scanner.hasNextDouble()){

                    deposit(scanner.nextDouble());
                System.out.println("new balance : USD "+ balance + " $");
                greentingAccountHolder();
            }

        }
        else if (input == 3){

            System.out.println("current balance USD "+ balance + " $");
           // greentingAccountHolder();
        }
        else if (input == 4){
            daoDdatabase.UpdateCustomer("balance", String.valueOf(balance), social);
            try {
                task.getTransaction().menu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("#1 Withdraw #2 Deposit #3 Checking Balance #4 Go Back");
        }

    }

    public double withdraw(double amount){

        if(amount >balance){ // condition to avoid negative number
            balance =  amount - balance;
        }
        balance =  balance - amount;
        return balance;
    }

    public double deposit(double amount){

        balance = balance + amount;
        return balance;
    }

    public double balance(){

        return balance;
    }
}
