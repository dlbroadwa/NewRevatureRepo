


/**
 * this  class only implement the interface class that will provide
 * or generate Account number for each new client
 *
 * this will be a brand new 16 digit number
 *
 *
 */







package Controler;

import java.util.Random;

public class AccountNumberGenerator implements RandomNumber {

    /**
     * this  is were we start the method that wil gett a number from val then will be saved in a character
     * after we will iterate to each of them then return the final 16 digit
     *
     */

    public String accountGenerator() {
        String val ="12345678910259801"; // number to random to


        char []ch = new  char[16]; // get a character array to set a limit of 16 number
        Random random = new  Random(); // Random number

        // Loop to  the character to get the length
        for (int i=0 ; i< ch.length ; i++){

            ch [i]=val.charAt(random.nextInt(val.length())); // store each character

        }

        String string = String.valueOf(ch); // change the format to a String then Return it
        // System.out.println("String : "+ string);

        return string;

    }
}
