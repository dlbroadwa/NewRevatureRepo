package com.company.DAO.utils.temporaryConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresSQLService {
    private PostgresSQLService(){
    }

    private static final List<PostgresSQLConnection> activeConnections = new ArrayList<>();

    public static final void addDBConnection(String url, String username, String password) throws SQLException{
        PostgresSQLConnection postgresSQLConnection = new PostgresSQLConnection(url, username, password);
        postgresSQLConnection.connect();
        activeConnections.add(postgresSQLConnection);
    }

    public static final Connection getConnection(int index) {
        if(activeConnections.isEmpty() || index > activeConnections.size()){
           throw new IndexOutOfBoundsException();
        }
        return activeConnections.get(index).getConnection();
    }
}
