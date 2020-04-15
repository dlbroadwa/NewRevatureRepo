package com.company;

import com.company.app.Application;
import com.company.app.OrderingApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Main {

    public static void main(String[] args) {
	Application app = new OrderingApplication();
    }
}
