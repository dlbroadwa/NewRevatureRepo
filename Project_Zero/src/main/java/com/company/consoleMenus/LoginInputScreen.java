package com.company.consoleMenus;

import com.company.application.ATMApplication;
import com.company.loginAccounts.LoginAccount;

/***
 * This class prompts the user for login credentials and stores the credentials into a variable in the ATMApplication
 * to be processed by the LoginScreen.
 *
 * @author Shawyn Kane
 */
public class LoginInputScreen extends InputScreen {

    @Override
    public Screen run(ATMApplication app) {
        String[] responses = prompt(app.getScan(), "Username:", "PIN:", "Admin (Y/N):");
        boolean admin;
        switch (responses[2].toLowerCase()) {
            case "y":
            case"yes":
                app.setCredentialsEntered(new LoginAccount(responses[0].trim(), responses[1].trim(), true));
                break;
            case "n":
            case "no":
                app.setCredentialsEntered(new LoginAccount(responses[0].trim(), responses[1].trim(), false));
                break;
            default:
                app.setCredentialsEntered(null);
                break;
        }
        return new LoginScreen();

    }
}
