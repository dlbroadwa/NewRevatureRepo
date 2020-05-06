package Screens;

import java.util.Scanner;

/**
 * Class Description: The class will ask the user for the deposit amount.
 * The input will be of Float type.  Any other values will be rejected
 * If user enter incorrect values more than 3 times, the program will exit.
 */
public class DepositMenu {
    private float depositAmount;

    public float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(float depositAmount) {
        this.depositAmount = depositAmount;
    }


    public void depositMenu(){
        Scanner sc = new Scanner(System.in);
        float amount;
        System.out.println("Enter deposit amount: ");
        int count = 0;

        while (!sc.hasNextFloat()){
            System.out.println("That is not a proper number");
            count++;
            if (count > 4){
                System.out.println("Incorrect amount enter " + count + " times \nReturning to ATM Menu.");
               return;
            }
            sc.next();
            System.out.println("Enter deposit amount: ");
        }

        this.depositAmount = sc.nextFloat();
        /**
         * This block of code will force the amount to be a positive number.  The deposit amount will be calculated
         * properly by the transaction service
         */
        if (this.depositAmount < 0){
            this.depositAmount = this.depositAmount * -1 ;
        }

    }
}
