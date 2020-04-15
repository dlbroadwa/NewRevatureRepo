package MenuUtilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMScreen {
    final private int [] validOptions = {1,2,3,4};
    private int choice;
    private boolean validScannerEntry = false;

    public void printMenu()
    {
        System.out.print("Select your transactions:");
        System.out.println("\t\t{1} Withdraw");
        System.out.println("\t\t\t\t\t\t\t\t{2} Deposit");
        System.out.println("\t\t\t\t\t\t\t\t{3} Balance Inquiry");
        System.out.println("\t\t\t\t\t\t\t\t{4} Set User Preferences");
        System.out.println("\t\t\t\t\t\t\t\tEnter: ");
    }

    public void printMenuAfterInvalidEntry (){
        System.out.println("Please enter correct ATM entry!");
        System.out.print("Select your transactions:");
        System.out.println("\t\t{1} Withdraw");
        System.out.println("\t\t\t\t\t\t\t\t{2} Deposit");
        System.out.println("\t\t\t\t\t\t\t\t{3} Balance Inquiry");
        System.out.println("\t\t\t\t\t\t\t\t{4} Set User Preferences");
        System.out.println("\t\t\t\t\t\t\t\tEnter: ");
    }

    public boolean getUserInputValue() throws InputMismatchException {

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

    public int getChoice() {
        return choice;
    }

    public boolean isValidScannerEntry() {
        return validScannerEntry;
    }
}
