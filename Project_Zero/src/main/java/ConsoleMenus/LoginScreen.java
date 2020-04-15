package ConsoleMenus;

import Application.ATMApplication;

/**
 * Author: Shawyn Kane
 * This class is to handle the checking of the login credentials.
 * If the credentials are wrong or not provided then it will re-prompt the user for credentials through the LoginInputScreen.
 */
public class LoginScreen implements Screen {
    @Override
    public Screen run(ATMApplication app) {
        while ((app.getCredentialsEntered() == null) || (!app.getCredentialsEntered().equals(app.getLoginAccount()))) {
            return new LoginInputScreen();
        }
        return new TransactionInputScreen();
    }
}
