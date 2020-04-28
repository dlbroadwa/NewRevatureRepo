package com.company.consoleMenus;

import java.util.Scanner;

/***
 * This is an abstract class to handle getting input from the user.
 *
 * @author Shawyn Kane
 */
public abstract class InputScreen implements Screen {

    /**
     * Makes multiple prompts (that are provided) and returns the user's responses.
     * @param scan
     * @return responses
     */
    public String[] prompt(Scanner scan, String... prompts) {
        String[] responses = new String[prompts.length];
        for (int i = 0; i < prompts.length; i++) {
            System.out.print(prompts[i] + "\n\t>>\t");
            responses[i] = scan.nextLine();
        }
        return responses;
    }
}