package Screens;

import java.util.Scanner;

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

        if (this.withdrawAmount < 0){
            this.withdrawAmount = this.withdrawAmount * -1 ;
        }

    }

}
