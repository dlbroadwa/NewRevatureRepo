package Screens;

import java.util.Scanner;

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

        if (this.depositAmount < 0){
            this.depositAmount = this.depositAmount * -1 ;
        }

    }
}
