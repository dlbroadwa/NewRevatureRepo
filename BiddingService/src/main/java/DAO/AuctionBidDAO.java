package DAO;

import dataaccess.ConnectionUtils;
import models.AuctionBid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuctionBidDAO implements DAO<AuctionBid, Integer> {
    private ConnectionUtils connectionUtils;
    private Connection connection = null;
    public AuctionBidDAO(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean save(AuctionBid obj) {
        Connection connection = null;
        String tableName = "auctionbids";
        try {
            connection = connectionUtils.getConnection();
            String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + tableName + " (auctionid, bidderid, sellerid, bidamount, timestamp) VALUES (?,?,?,?,?)";
            PreparedStatement auctionBidStatement = connection.prepareStatement(saveStatement);
            auctionBidStatement.setInt(1, obj.getAuctionID());
            auctionBidStatement.setInt(3, obj.getBidderID());
            auctionBidStatement.setInt(3, obj.getSellerID());
            auctionBidStatement.setDouble(4, obj.getBidAmount());
            auctionBidStatement.setTimestamp(5, obj.getTimestamp());
            auctionBidStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }

    /**
     *
     * @return
     */
    @Override
    public List<AuctionBid> retrieveAll() {
        Connection connection = null;
        String tableName = "auctionbids";
        ArrayList<AuctionBid> auctionbids = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String sql = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + tableName;
            PreparedStatement auctionBidStatement = connection.prepareStatement(sql);
            ResultSet resultSet = auctionBidStatement.executeQuery();

            while (resultSet.next()) {
                auctionbids.add(new AuctionBid(resultSet.getInt("auctionid"), resultSet.getInt("bidderid"),
                resultSet.getInt("sellerid"), resultSet.getDouble("bidamount"), resultSet.getTimestamp("timestamp")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return auctionbids;
    }

    /**
     *
     * @param integer
     * @return
     */
    @Override
    public AuctionBid retrieveByID(Integer integer) {

        return null;
    }

    /**
     *
     * @param integer
     * @return
     */
    public List<AuctionBid> retrieveAllByAucyionID(Integer integer) {

        Connection connection = null;
        String tableName = "auctionbids";
        ArrayList<AuctionBid> auctionbids = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String sql = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + tableName + " WHERE auctionid = ?";
            PreparedStatement auctionBidStatement = connection.prepareStatement(sql);
            auctionBidStatement.setInt(1, integer);
            ResultSet resultSet = auctionBidStatement.executeQuery();

            while (resultSet.next()) {
                auctionbids.add(new AuctionBid(resultSet.getInt("auctionid"), resultSet.getInt("bidderid"),
                resultSet.getInt("sellerid"), resultSet.getDouble("bidamount"), resultSet.getTimestamp("timestamp")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return auctionbids;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(AuctionBid obj) {
        Connection connection = null;
        String tableName = "auctionbids";

        try {
            connection = connectionUtils.getConnection();
            String sql = "DELETE FROM " + connectionUtils.getDefaultSchema() + " WHERE auctionid = ? AND bidderid = ?";
            PreparedStatement auctionBidStatement = connection.prepareStatement(sql);
            auctionBidStatement.setInt(1, obj.getAuctionID());
            auctionBidStatement.setInt(2, obj.getBidderID());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param newObj
     * @return
     */
    @Override
    public boolean update(AuctionBid newObj) {
        Connection connection = null;
        String sql = "UPDATE " + connectionUtils.getDefaultSchema() + " SET bidmount = ? AND timestamp = ? WHERE auctionid = ? AND bidderid = ?";
        try
        {
            connection = connectionUtils.getConnection();
            PreparedStatement auctionBidStatement = connection.prepareStatement(sql);

            auctionBidStatement.setDouble(1, newObj.getBidAmount());
            auctionBidStatement.setTimestamp(3, newObj.getTimestamp());
            auctionBidStatement.setInt(3, newObj.getAuctionID());
            auctionBidStatement.setDouble(4, newObj.getBidderID());

            auctionBidStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
