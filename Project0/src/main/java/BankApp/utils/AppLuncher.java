package BankApp.utils;

import BankApp.view.console.ConsoleLoginView;

public class AppLuncher {

    public static void lunch(String view){
        switch (view){
            case BankAppStrings.CONSOLE_VIEW :
               new ConsoleLoginView().lunch();
        }
    }
}
