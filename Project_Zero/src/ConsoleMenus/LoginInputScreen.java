package ConsoleMenus;

import Application.ATMApplication;
import LoginAccounts.LoginAccount;

public class LoginInputScreen extends InputScreen {
    LoginInputScreen() {
        super("Username:", "PIN:");
    }

    @Override
    public Screen run(ATMApplication app) {
        String[] responses = prompt(app.getScan());
        app.setCredentialsEntered(new LoginAccount(responses[0].trim(), responses[1].trim()));
        return new LoginScreen();
    }
}
