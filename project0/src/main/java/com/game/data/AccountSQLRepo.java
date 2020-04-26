package com.game.data;

import com.game.models.Account;
import com.game.utils.ConnectionUtils;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountSQLRepo implements Repository<Account,String> {
    private ConnectionUtils connectionUtils;
    static final Logger logger = Logger.getLogger(AccountSQLRepo.class);
    static final String PEL = "prepared statement not closed";
    static final String REL = "prepared statement not closed";
    static final String CEL = "Connection did not close";

    public AccountSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     * Finds a account by the username
     * No longer used in the main application code
     * Now using an account list for quicker access
     * @param s username
     * @return account
     */
    @Override
    public Account findById(String s) {
        Account temp = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "select password, isadmin, credits from " + schemaName + ".accountlist where username = ?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1,s);
            rs = ps.executeQuery(sql);
            while(rs.next()) {
                temp = new Account(s, rs.getString("password"), rs.getBoolean("isadmin"), rs.getInt("credits"));
            }
        } catch (SQLException e) {
            logger.info("SQL find by id failed", e);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { logger.info(REL, e); }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
        }
        return temp;
    }

    @Override
    public List<Account> findAll() {
        //reuse code from flashcard project
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> accountList = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select username, password, credits, isadmin from " + schemaName + ".accountlist ";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            Account temp;
            while(rs.next()) {
                temp = new Account(rs.getString("username"),rs.getString("password"),
                        rs.getBoolean("isadmin"),rs.getInt("credits"));

                accountList.add(temp);
            }
        } catch (SQLException e) {
            logger.info("SQL find all failed", e);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { logger.info(REL, e); }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
        }
        return accountList;
    }

    /**
     * Insert a new record with corresponding username, password, and credits
     * as the account object that is passed in
     * @param obj account object
     */
    @Override
    public void save(Account obj) {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "insert into " + schemaName +
                    ".accountlist (username,password,isadmin,credits) values (?,?,?,?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1,obj.getName());
            ps.setString(2,obj.getPassword());
            ps.setBoolean(3,obj.getIsAdmin());
            ps.setInt(4,obj.getBalance());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("SQL save failed", e);
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
        }
    }

    /**
     * Finds the record with the same username as the account object.
     * Updates the record with the changed field(s) in the account object.
     * @param obj Account object
     */
    @Override
    public void update(Account obj) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "update " + schemaName + ".accountlist " +
                    "set password = ?,"+
                    "credits = ? where " +
                    "username = ?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1,obj.getPassword());
            ps.setInt(2,obj.getBalance());
            ps.setString(3,obj.getName());
            ps.executeUpdate(sql);
        } catch (SQLException e) {
            logger.info("SQL update failed", e);
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
        }
    }

    /**
     * Deletes record that has the inputted username
     * @param s the username
     */
    @Override
    public void delete(String s) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "delete from " + schemaName + ".accountlist " +
                    "where username = ?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1,s);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("SQL delete failed", e);
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
        }
    }
}
