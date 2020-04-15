package MenuUtilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeScreen {
    final private int [] validOptions = {1,2};
    private int choice;
    private String customerUserIdInput;
    private boolean validScannerEntry = false;


    public void printInitialMenu (){
        System.out.println("Welcome to Bank of America:");
        System.out.println("{1} Enter your account ID: ");
        System.out.println("{2} Exit");
        System.out.print("Enter: ");
    }

    public void printMenuAfterInvalidEntry (){
        System.out.println("Please enter correct entry!");
        System.out.println("{1} Enter your account ID: ");
        System.out.println("{2} Exit");
        System.out.print("Enter: ");
    }
    public void getUserIdScreen(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your bank ID: ");
        customerUserIdInput = myObj.next();

    }
    public boolean getUserInputValue() throws InputMismatchException{

        boolean validScreenInput = false;
        Scanner myObj = new Scanner(System.in);
        int input = myObj.nextInt();



        for (int i = 0; i < validOptions.length; i++) {
            if (validOptions[i] == input) {
                choice = input;
                validScreenInput = true;

                break;
            }
        }
        return validScreenInput;

    }

    public boolean isValidScannerEntry() {
        return validScannerEntry;
    }
    public String getCustomerUserIdInput() {
        return customerUserIdInput;
    }
    public int getChoice(){
        return this.choice;
    }
}
