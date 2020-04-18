package BankApp.app;

import java.text.DecimalFormat;
import java.util.Scanner;
import BankApp.DAO.UserDAO;


/***********************************************
 *
 * This class will setup all the functionality
 * of the deposit, withdraw, and balance options
 * given in the menu
 *
 * methods:
 * deposit - deposit money into the account
 * withdraw - withdraw money from the account
 * checkBalance - print balance of the account
 *
 ***********************************************/

public class Functions {

    //variable declarations
    Scanner in = new Scanner(System.in);
    private final UserDAO userDao;
    int userChoice;
    boolean quit = false;
    DecimalFormat df = new DecimalFormat("###.##");
    float balance = 0f;
    float amount = 0f;


    public Functions() {
        userDao = new UserDAO();
    }

    //method for depositing money
    public void deposit() {

        System.out.print("\n" +"Amount to deposit: ");
        amount = in.nextFloat();

        if (amount <= 0)
            System.out.println("\n" +"Can't deposit nonpositive amount.");
        else {
            userDao.updateAmount(UserAuthentication.getUserId(), amount, true);
            balance += amount;
            System.out.println("\n" +"$" + amount + " has been deposited.");
        }

    }

    //method for withdrawing the money
    public void withdraw() {
        System.out.print("\n" +"Amount to withdraw: ");
        amount = in.nextFloat();

        if (amount <= 0 || amount > balance)
            System.out.println("\n" +"Withdrawal can't be completed.");
        else {
            userDao.updateAmount(UserAuthentication.getUserId(), amount, false);
            balance -= amount;
            System.out.println("\n" +"$" + amount + " has been withdrawn.");
        }
    }


    //method to check the balance of user
    public void checkBalance() {
        balance = userDao.getUser(UserAuthentication.getUserId()).getBalance();
        System.out.println("\n" +"Your balance: $" + df.format(balance));


    }

}