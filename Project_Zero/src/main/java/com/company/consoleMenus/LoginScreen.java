package com.company.consoleMenus;

import com.company.application.ATMApplication;
import com.company.services.LoginServices;

/**
 * Author: Shawyn Kane
 * This class is to handle the checking of the login credentials.
 * If the credentials are wrong or not provided then it will re-prompt the user for credentials through the LoginInputScreen.
 */
public class LoginScreen implements Screen {
    @Override
    public Screen run(ATMApplication app) {
        while (!LoginServices.verifyLoginAccount(app.getCredentialsEntered(), app.getLoginAccountDAO())) {
            return new LoginInputScreen();
        }
        return new TransactionInputScreen();
    }
}
