package com.ex.ers.DAO;

import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;

import java.util.List;

public class ReimbursementDAO implements DAOs<ReimbursementRequest> {
    private ConnectionUtils connectionUtils;

    public ReimbursementDAO() {
        connectionUtils = new PostgresqlConnectionUtil();
    }

    @Override
    public ReimbursementRequest findByName(String s) {
        return null;
    }

    @Override
    public ReimbursementRequest findByID(int id) {
        return null;
    }

    @Override
    public List<ReimbursementRequest> findAll() {
        return null;
    }

    @Override
    public List<ReimbursementRequest> findAllForName(String s) {
        return null;
    }

    @Override
    public int save(ReimbursementRequest obj) {
        return 0;
    }

    @Override
    public int update(ReimbursementRequest obj) {
        return 0;
    }
}
