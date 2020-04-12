package com.ex;

import com.ex.console.ConsoleInput;
import com.ex.storage.JavaFileIO;
import com.ex.app.*;

public class Main {

    public static void main(String[] args) {
        Application application = new Test("TestApplication");
        application.run();
//        Application application = new InventoryAssistant();
//        application.run();
    }
}
