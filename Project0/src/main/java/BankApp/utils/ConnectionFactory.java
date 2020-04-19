package BankApp.utils;

import BankApp.jdbc.BankAppConnection;
import BankApp.jdbc.impl.PostgreSQLConnection;

import java.sql.Connection;

public class ConnectionFactory {

    public static BankAppConnection getConnection(String type){
        switch (type){
            case  BankAppStrings.POSTGREE_SQL:
               return new PostgreSQLConnection();
        }
        return null;
    }
}
