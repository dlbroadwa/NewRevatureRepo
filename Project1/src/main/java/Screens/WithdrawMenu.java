package Screens;

import java.util.Scanner;
/**
 * Class Description: The class will ask the user for the withdraw amount.
 * The input will be of Float type.  Any other values will be rejected
 * If user enter incorrect values more than 3 times, the program will exit.
 */
public class WithdrawMenu {

    private float withdrawAmount;

    public float getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(float withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public void withdrawMenu(){
        Scanner sc = new Scanner(System.in);
        float amount;
        System.out.println("Enter withdrawal amount: ");
        int count = 0;
        while (!sc.hasNextFloat()){
            System.out.println("That is not a proper number");
            count++;
            if (count > 4){
                System.out.println("Incorrect amount enter " + count + " times \nReturning to ATM Menu.");
                return;
            }
            sc.next();
            System.out.println("Enter withdrawal amount: ");
        }

        withdrawAmount = sc.nextFloat();
        /**
         * This block of code will force the amount to be a positive number.  The withdraw amount will be cacuclated
         * properly by the transaction service
         */
        if (this.withdrawAmount < 0){
            this.withdrawAmount = this.withdrawAmount * -1 ;
        }

    }

}
