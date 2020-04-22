package com.williamchung.project0;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;

public class Main {
    public static void main(String[] args) {
        Application app = new BankAccountApplication();
        app.run();
    }
}