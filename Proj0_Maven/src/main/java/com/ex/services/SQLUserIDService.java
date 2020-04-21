package com.ex.services;

import com.ex.utils.DatabaseConnection;

public class SQLUserIDService extends DatabaseIDService {
    public SQLUserIDService(DatabaseConnection dc) {
        super(dc);
    }

    @Override
    public int toID(int cardNumber) {
        if (cardNumber == 0) return 0;
        String sql = "SELECT id from " + getConnection().getSchema() + ".users WHERE card_number = ?";
        return runQuery(sql, cardNumber);
    }

    @Override
    public int fromID(int uid) {
        if (uid == 0) return 0;
        String sql = "SELECT card_number from " + getConnection().getSchema() + ".users where id = ?";
        return runQuery(sql, uid);
    }
}
