package ApplicationViews;

import Application.AccountHolderInfo;
import Application.DispatchingTask;

import java.io.IOException;
import java.util.Scanner;

/**
 this class is for Customers
 after verifying if credential is matched Database then customers will be prompted to select some options
 such as withdraw money , deposit, checking balance acct status
 */
public class Transaction {

    Scanner scanner;
    String name =null;
    DispatchingTask task;

DispatchingTask dispatchingTask;
    AccountHolderInfo accountHolderInfo;


    public  Transaction(DispatchingTask task){
  //    name = dispatchingTask.getBankName();
   //    scanner = dispatchingTask.getScanner();
        this.task =task;

    }


    public void Withdraw (){
        //get the current balance from the file
        System.out.println("How much money that you want to Withdraw Today ? ");

        //substrate enter amount to the previous balance


        // write the Remaining Amount to the file/database

    }

    public  void deposit(){
        //get the current balance from the file
        System.out.println("How much money that you want to Deposit Today ? ");

        //add enter amount to the previous balance


        // write the somme Amount to the file/database
    }
//checking balance
    public  void checkingBalance(){

        //get the current balance from the file
        System.out.println("here is your current balance: ");


    }

    // menu to display welcome and selection choice
    public void menu() throws IOException, ClassNotFoundException {
        name = task.getBankName();

        System.out.println("----------------------------------------------------");
       System.out.println(" WELCOME TO " + name);





        while(true){

            System.out.println("----------------------------------------------------");
            System.out.println("\t Please Let me Identify You  \n" +
                    "\t\t 1- Account Holder \n" +
                    "\t\t 2- Employee (Manager/Supervisor) \n" +
                    "\t\t 3- Want to Open Account");
            System.out.println("What is your Selection? :");
            scanner= task.getScanner();

            while(scanner.hasNextInt()){




                int number =scanner.nextInt();

                if(number>3 || number == 0){ // check if user enter other number than the selection required
                    System.out.println("choose number between 1 and 3");
                }
                else{
                    task.dispatch(number); //Passing the User 's Selection to the DispatchingTask
                }

              //  System.out.println("your selection :" + number);
                // String Input;
            }
            System.out.println("Please Enter Number of your Selection");
            scanner.nextLine();
        }

    }

    public void thankYouNote(AccountHolderInfo obj) throws IOException {

        System.out.println("THANK YOU "
                +obj.getName()
                +" For being a member of " + task.getBankName());
        System.out.println("Here is your information ");
        System.out.println(obj.toString());

        //Writing to the file
//        String str =String.valueOf(obj.getSocialSecurityNumber());
//        String format = str.substring(0,3)+"-"+str.substring(3,5)+"-"+str.substring(5,9);
//        int ssn =Integer.parseInt(format);
//
//        String str2 =String.valueOf(obj.getAccountNumber());
//        String format2 =str2.substring(0,4)+"-"+str2.substring(4,8)+"-"+str2.substring(8,12)+"-"+str2.substring(12,16);
//        //int account =Integer.parseInt(format2);


    task.getDaOaccountHolder().writingFile(obj.getName(),obj.getAddress(),obj.getAccountNumber(),obj.getSocialSecurityNumber());
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
