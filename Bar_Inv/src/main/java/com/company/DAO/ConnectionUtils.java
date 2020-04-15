package com.company.DAO;

import java.sql.Connection;

public abstract class ConnectionUtils {
    protected String url;
    protected String username;
    protected String password;
    protected String schema;

    public abstract Connection getConnection();
}
