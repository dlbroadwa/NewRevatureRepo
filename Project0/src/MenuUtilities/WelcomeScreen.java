package MenuUtilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeScreen {
    final private int [] validOptions = {1,2};
    private int userInput;
    public void printMenu (){
        System.out.println("Welcome to Bank of America:");
        System.out.println("{1} Enter your account ID: ");
        System.out.println("{2} Exit");
        System.out.print("Enter: ");
    }
    public boolean getUserInput() throws InputMismatchException{
        Scanner myObj = new Scanner(System.in);
        int input;
        boolean validScreenInput = false;


        input = myObj.nextInt();


        for (int i = 0; i < validOptions.length; i++) {
            if (validOptions[i] == input) {
                userInput = input;
                validScreenInput = true;
                break;
            } else {
                validScreenInput = false;
            }
        }
        return validScreenInput;

    }

    public WelcomeScreen() {

    }
}
