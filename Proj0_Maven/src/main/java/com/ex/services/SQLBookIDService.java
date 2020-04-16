package com.ex.services;

import com.ex.utils.DatabaseConnection;

public class SQLBookIDService extends DatabaseIDService {
    public SQLBookIDService(DatabaseConnection dc) {
        super(dc);
    }

    @Override
    public int toID(int barcode) {
        String sql = "SELECT id from " + getConnection().getSchema() + ".books WHERE barcode = ?";
        return runQuery(sql, barcode);
    }

    @Override
    public int fromID(int id) {
        String sql = "SELECT barcode from " + getConnection().getSchema() + ".books WHERE id = ?";
        return runQuery(sql, id);
    }
}
