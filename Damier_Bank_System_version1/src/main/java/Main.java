import Application.App;
import Application.DispatchingTask;


/**
 * This Application is  a bank System the will interact
 * with 3 main users
 *
 * 1- person who already has an account with the Bank
 * 2- person do not have yet an Account
 * 3- person who register as Employee
 *
 * This is the main class that control the whole Application
 *
 *  Create a variable that hole the bank name that will be use for the Entire Application
 */

public class Main {
    public static void main(String[] args) {

       String  bankName = "DAMIER'S BANK SYSTEM CORPORATION";

        App myApp = new DispatchingTask(bankName); //calling Constructor of this class Dispatching

        myApp.run();
    }
}
