package com.ex.ers.DAO;

import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        int status =0;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into public.re";



        return status;
    }

    @Override
    public int update(ReimbursementRequest obj) {
        return 0;
    }
}
