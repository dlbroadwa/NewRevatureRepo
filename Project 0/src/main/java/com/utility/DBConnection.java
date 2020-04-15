package com.utility;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnection {
    protected String url;
    protected String schema;

    public abstract Connection getConnection() throws SQLException;

    public String getSchema() {
        return this.schema;
    }
}
