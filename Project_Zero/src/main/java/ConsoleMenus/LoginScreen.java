package ConsoleMenus;

import Application.ATMApplication;


public class LoginScreen implements Screen {


    @Override
    public Screen run(ATMApplication app) {
        // TODO
        // while invalid credentials
            // return input screen
        while ((app.getCredentialsEntered() == null) || (!app.getCredentialsEntered().equals(app.getLoginAccount()))) {
            return new LoginInputScreen();
        }
        return new TransactionInputScreen();
    }
}
