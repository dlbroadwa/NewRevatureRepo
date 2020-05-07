package com.ex.ers.newServletsAndData;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    String SCHEMA_TABLE = "public.reimreqs";
    private ConnectionUtils connectionUtils = new PostgresqlConnectionUtil();

    public List<ReimbursementRequest> getReimbursementsByPersonID(int requestorID) {
        String sql = "select * from " + SCHEMA_TABLE + " where requestorid = " + requestorID;
        Connection con;
        Statement statement;
        ResultSet rs = null;
        try {
            con = connectionUtils.getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
        try {
            while (rs.next()) {
                ReimbursementRequest reimbursementRequest = new ReimbursementRequest();
                reimbursementRequest.setPending(rs.getBoolean("pending"));
                reimbursementRequest.setApproved(rs.getBoolean("approved"));
                reimbursementRequest.setApprover(rs.getString("approver"));
                reimbursementRequest.setAmount(rs.getFloat("amount"));
                reimbursementRequest.setComment(rs.getString("scomment"));
                reimbursementRequest.setId(rs.getInt("id"));
                reimbursementRequest.setRequestorid(rs.getInt("requestorid"));
                reimbursementRequests.add(reimbursementRequest);

            }
        } catch (Exception e){
            System.out.println(e);
        }
        return reimbursementRequests;
    }
}