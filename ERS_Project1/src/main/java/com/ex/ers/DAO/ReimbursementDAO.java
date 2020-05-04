package com.ex.ers.DAO;

import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.ReimbursementService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements DAOs<ReimbursementRequest> {
    private ConnectionUtils connectionUtils;

    public ReimbursementDAO() {
        connectionUtils = new PostgresqlConnectionUtil();
    }

    @Override
    public List<ReimbursementRequest> findAllByID(int id) {
        Connection conn = null;
        List<ReimbursementRequest> all = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String sql = "Select * from public.reimreqs where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ReimbursementRequest tmp = new ReimbursementRequest();
                tmp.setRequester(rs.getObject("requester", JsonObject.class));
                tmp.setComment(rs.getString("scomment"));
                tmp.setAmount(rs.getFloat("amount"));
                tmp.setApproved(rs.getBoolean("approved"));
                tmp.setPending(rs.getBoolean("pending"));
                tmp.setApprover(rs.getString("approver"));
                tmp.setId(rs.getInt("id"));
                all.add(tmp);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return all;
        }

    }

    @Override
    public List<ReimbursementRequest> findAll() {
        Connection conn = null;
        List<ReimbursementRequest> all = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String sql = "Select * from public.reimreqs";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ReimbursementRequest tmp = new ReimbursementRequest();
                tmp.setRequester(rs.getObject("requester", JsonObject.class));
                tmp.setComment(rs.getString("scomment"));
                tmp.setAmount(rs.getFloat("amount"));
                tmp.setApproved(rs.getBoolean("approved"));
                tmp.setPending(rs.getBoolean("pending"));
                tmp.setApprover(rs.getString("approver"));
                tmp.setId(rs.getInt("id"));
                all.add(tmp);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return all;
        }
    }

    @Override
    public int save(ReimbursementRequest obj) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into public.reimreqs (requester, amount, scomment) values (?,?,?)";
        try {
            conn = connectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,obj.getRequester());
            ps.setFloat(2, obj.getAmount());
            ps.setString(3,obj.getComment());
            ps.executeUpdate();
            status = 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return status;
        }
    }

    @Override
    public int update(ReimbursementRequest obj) {
        return 0;
    }

    @Override
    public ReimbursementRequest findByName(String s) { //won't use this
        return null;
    }
    @Override
    public List<ReimbursementRequest> findAllForName(String s) { //use all for id
        return null;
    }



}
