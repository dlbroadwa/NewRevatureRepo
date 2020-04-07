package Validation;

import java.util.Scanner;

public class Validate {

    public int checkInt(){

        Scanner sc = new Scanner(System.in);
        int number;

        do {

            while (!sc.hasNextInt()) {
                System.out.println("That's not a valid number!");
                sc.next(); // this is important!
            }

            number = sc.nextInt();

        } while (number <= 0);
        return number;

    }

    public boolean checkUserExist(String userName, String Password)
    {
        boolean userExists = true;


        return userExists;
    }
}
